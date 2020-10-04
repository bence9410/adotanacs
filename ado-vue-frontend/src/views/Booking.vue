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
        :key="time"
      >
        <v-row class="mt-3">
          <v-col cols="6">
            <v-card-text class="pa-3">{{ time }}-tól</v-card-text>
          </v-col>
          <v-col cols="5 " class="pb-0">
            <v-select dense solo class="pa-0" label="30 perc"> </v-select>
          </v-col>
        </v-row>
        <v-col class="pt-0">
          <v-btn elevation="2" block dark color="green " class="mx-auto"
            >Lefoglalom
          </v-btn>
        </v-col>
      </v-card>
    </v-card>

    <div id="avaiableTimes"></div>
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
  }),
  computed: {
    freeTimesShown() {
      var ft = [];
      $.each(this.freeTimes, (key, value) => {
        var av = [];
        for (let i = 0; i < value.length; i++) {
          av.push(
            this.availableTimesShown[this.availableTimes.indexOf(value[i])]
          );
        }
        ft.push({ date: key.replaceAll("-", ".") + ".", times: av });
      });
      return ft;
    },
  },

  created() {
    $.ajax({
      url: "/api/free-times",
      success: (data) => (this.freeTimes = data),
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
