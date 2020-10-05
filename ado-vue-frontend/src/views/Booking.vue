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

    <v-card
      elevation="2"
      outlined
      class="mt-5"
      v-for="ft in freeTimesShown"
      :key="ft.date"
    >
      <v-card-text class="pa-3 card-header text-center"
        >{{ ft.date }} Péntek</v-card-text
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
            <v-card-text class="pa-3">{{ time.time }}-tól</v-card-text>
          </v-col>
          <v-col cols="5 " class="pb-0">
            <v-select
              dense
              solo
              class="pa-0"
              label="30 perc"
              :items="interval"
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
            @click="openDialog(ft.date, time)"
            >Lefoglalom
          </v-btn>
        </v-col>
      </v-card>
    </v-card>
    <v-dialog v-model="dialog.show" persistent max-width="600px">
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
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Teljes név*"
                  required
                  v-model="dialog.data.name"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Email cím*"
                  required
                  v-model="dialog.data.email"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-select
                  :items="['Skype', 'Személyes']"
                  label="Találkozó módja*"
                  required
                  v-model="dialog.data.meetingType"
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-textarea
                  outlined
                  autocomplete="email"
                  label="Rövid leírás*"
                  placeholder="Kérem röviden írja le milyen ügyben keresett fel."
                  v-model="dialog.data.description"
                ></v-textarea>
                <v-checkbox
                  v-model="ex4"
                  label="Hozzájárulok, hogy a megadott adataimat megőrizzék és felvegyék velem a kapcsolatot.*"
                  color="info"
                  value="dataManagement"
                  class="mt-0"
                  hide-details
                ></v-checkbox>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" dark @click="dialog.show = false">
            Lefoglalom
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import $ from "jquery";

export default {
  props: ["booking"],

  data: () => ({
    freeTimes: {},
    availableTimesShown: ["14:30", "16:00", "17:30"],
    availableTimes: ["PM2_30", "PM4_00", "PM5_30"],
    interval: ["30 perc", "60 perc"],

    freeTimesShown: [],
    dialog: {
      show: false,
      data: {
        ///mezonevek amik az entitáson vannak a:
        name: "",
        email: "",
        description: "",
        meetingTime: "",
        meetingLenght: "",
        meetingType: "",
      },
      title: "",
      date: "",
      time: "",
    },
  }),
  methods: {
    openDialog(date, time) {
      this.dialog.show = true;
      this.dialog.title = date + time.time + " " + time.interval;
    },
  },

  created() {
    $.ajax({
      url: "/api/free-times",
      success: (data) => {
        this.freeTimes = data;

        $.each(this.freeTimes, (key, value) => {
          var av = [];
          for (let i = 0; i < value.length; i++) {
            av.push({
              time: this.availableTimesShown[
                this.availableTimes.indexOf(value[i])
              ],
              interval: "30 perc",
            });
          }
          this.freeTimesShown.push({
            date: key.replaceAll("-", ".") + ".",
            times: av,
          });
        });
      },
    });
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
</style>
