<template>
  <div class="booking">
    <div class="bookingContainer mt-4 elevation-6">
      <v-img
        class="smallScreen rounded img-fluid"
        style="width: 100%"
        :aspect-ratio="1.5"
        src="../assets/booking-small.jpg"
        alt="naptár fotó"
      />
      <v-img
        class="bigScreen rounded img-fluid"
        style="width: 100%"
        :aspect-ratio="3.06"
        src="../assets/booking-big.jpg"
        alt="naptár fotó"
      />
      <div class="content">
        <h4 class="font-bold">Foglaljon időpontot most.</h4>
        <h2>A tanácsadói díjról a válasz e-mailben tájékoztatom.</h2>
      </div>
    </div>
    <h1 v-if="freeTimesLoaded && freeTimes.length == 0">
      Sajnos jelenleg nincs szabad időpont, kérem látogasson vissza késöbb.
    </h1>
    <v-card
      v-else
      elevation="2"
      outlined
      class="mt-5"
      v-for="ft in freeTimes"
      :key="ft.date"
    >
      <v-card-text class="pa-3 card-header text-center"
        >{{ ft.dateShown }} Péntek</v-card-text
      >
      <v-card
        elevation="2"
        outlined
        class="ma-2"
        v-for="time in ft.times"
        :key="time.time"
      >
        <v-row class="mt-3">
          <v-col cols="6">
            <v-card-text class="pa-3">{{ time.timeShown }}-tól</v-card-text>
          </v-col>
          <v-col cols="5 " class="pb-0">
            <v-select
              dense
              solo
              class="pa-0"
              label="30 perc"
              :items="['30 perc', '60 perc']"
              v-model="time.interval"
            >
            </v-select>
          </v-col>
        </v-row>
        <v-col class="pt-0"
          ><v-btn
            elevation="2"
            block
            dark
            color="green "
            class=""
            @click="openDialog(ft, time)"
            >Lefoglalom
          </v-btn>
        </v-col>
      </v-card>
    </v-card>
    <v-dialog v-model="dialog.show" persistent max-width="600px" eager>
      <v-card>
        <div class="text-right" style="width: 100%">
          <v-btn icon @click="dialog.show = false">
            <v-icon>close</v-icon>
          </v-btn>
        </div>
        <v-card-title>
          <span class="headline"
            >Kérem töltse ki a foglaláshoz az adatait.<br />{{
              dialog.title + dialog.time
            }}</span
          >
        </v-card-title>

        <v-card-text>
          <v-form ref="form" v-model="dialog.form">
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    label="Teljes név*"
                    required
                    v-model="dialog.data.name"
                    :rules="[
                      (v) =>
                        (!!v && v.length > 3 && v.length < 30) ||
                        'Kötelező kitölteni.',
                    ]"
                    :counter="30"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="Email cím*"
                    required
                    v-model="dialog.data.email"
                    :rules="[
                      (v) =>
                        (!!v && /.+@.+\..+/.test(v)) ||
                        'Az e-mail címet a valaki@pelda.com formátumban írja be.',
                    ]"
                    :counter="50"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-select
                    :items="['Skype', 'Személyes']"
                    label="Találkozó módja*"
                    required
                    v-model="dialog.data.meetingType"
                    :rules="[(v) => !!v || 'Kötelező kitölteni.']"
                  ></v-select>
                </v-col>
                <v-col cols="12">
                  <v-textarea
                    outlined
                    autocomplete="email"
                    label="Rövid leírás*"
                    placeholder="Kérem röviden írja le milyen ügyben keresett fel."
                    v-model="dialog.data.description"
                    :rules="[
                      (v) =>
                        (!!v && v.length > 3 && v.length < 60) ||
                        'Kötelező kitölteni.',
                    ]"
                    :counter="60"
                  ></v-textarea>
                  <v-checkbox
                    label="Hozzájárulok, hogy a megadott adataimat megőrizzék és felvegyék velem a kapcsolatot.*"
                    color="info"
                    value="dataManagement"
                    class="mt-0"
                    hide-details
                    :rules="[(v) => !!v || 'Kérem fogadja el a hozzájárulást.']"
                  ></v-checkbox>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" dark @click="book"> Lefoglalom </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-overlay :value="loading">
      <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
    <v-dialog v-model="response.show" persistent max-width="600px">
      <v-card :color="response.color" dark class="text-center">
        <div class="text-right" style="width: 100%">
          <v-btn icon @click="response.show = false">
            <v-icon>close</v-icon>
          </v-btn>
        </div>
        <v-card-text class="font">
          <h1 class="mb-2">{{ response.title }}</h1>
          <h3>{{ response.description }}</h3>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import $ from "jquery";
export default {
  data: () => ({
    response: {
      show: false,
      title: "",
      color: "",
      description: "",
    },
    freeTimesLoaded: false,
    freeTimes: [],
    loading: false,
    availableTimesShown: ["14:30", "16:00", "17:30"],
    availableTimes: ["PM2_30", "PM4_00", "PM5_30"],
    selectedDate: {},
    selectedTime: {},

    dialog: {
      form: true,
      show: false,
      data: {
        name: "",
        email: "",
        description: "",
        meetingTime: "",
        meetingLenght: "",
        meetingType: "",
        meetingDate: "",
      },
      title: "",
      date: "",
      time: "",
    },
  }),
  created() {
    $.ajax({
      url: "/api/free-times",
      success: (data) => {
        Object.keys(data).forEach((key) => {
          let value = data[key];
          let times = [];
          for (let i = 0; i < value.length; i++) {
            let timeShown = this.availableTimesShown[
              this.availableTimes.indexOf(value[i])
            ];
            times.push({ timeShown, enumTime: value[i], interval: "30 perc" });
          }
          let dateShown = key.replaceAll("-", ".") + ".";
          this.freeTimes.push({ dateShown, date: key, times });
        });
        this.freeTimesLoaded = true;
      },
    });
  },

  methods: {
    openDialog(date, time) {
      this.dialog.show = true;
      this.dialog.title = date.dateShown + time.timeShown + " " + time.interval;
      this.$refs.form.reset();
      this.selectedDate = date;
      this.selectedTime = time;
      this.dialog.data.meetingDate = date.date;
      this.dialog.data.meetingTime = time.enumTime;
      if (time.interval == "30 perc") {
        this.dialog.data.meetingLenght = "HALF_HOUR";
      } else if (time.interval == "60 perc") {
        this.dialog.data.meetingLenght = "ONE_HOUR";
      }
    },
    book() {
      if (this.$refs.form.validate()) {
        if (this.dialog.data.meetingType == "Skype") {
          this.dialog.data.meetingType = "SKYPE";
        } else {
          this.dialog.data.meetingType = "PERSONAL";
        }
        this.dialog.show = false;
        this.loading = true;
        $.ajax({
          url: "/api/book",
          method: "POST",
          contentType: "application/json",
          data: JSON.stringify(this.dialog.data),
          success: () => {
            this.response.color = "green";
            this.response.title = "Sikeres foglalás.";
            this.response.description =
              "Amenyiben nem kap emailt az idöpont megerösitéséröl a találkozó elött, kérem keressen telefonon.";

            this.loading = false;
            this.response.show = true;

            if (this.selectedDate.times.length == 1) {
              this.freeTimes.splice(
                this.freeTimes.indexOf(this.selectedDate),
                1
              );
            } else {
              this.selectedDate.times.splice(
                this.selectedDate.times.indexOf(this.selectedTime),
                1
              );
            }
          },
          error: () => {
            this.response.color = "red";
            this.response.title = "Sikertelen foglalás.";
            this.response.description =
              "Sajnáljuk váratlan hiba miatt meghiúsult a foglalás.";
            this.loading = false;
            this.response.show = true;
          },
        });
      }
    },
  },
};
</script>
<style>
.card-header {
  padding: 0.75rem 1.25rem;
  margin-bottom: 0;
  background-color: rgba(0, 0, 0, 0.03);
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
}
.dot {
  height: 25px;
  width: 25px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}
</style>
