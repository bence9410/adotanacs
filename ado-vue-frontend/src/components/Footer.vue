<template>
  <v-footer dark color="blue-grey darken-4" style="padding: 0px">
    <v-col cols="12" class="text-center">
      <h5 class="font-footer line-height-lower">
        Sok esetben az együttgondolkodás is hasznos tud lenni a döntés
        megszületéséhez.
      </h5>
    </v-col>
    <v-col color="white" class="pt-3 pb-4" style="background-color: white">
      <v-col cols="6" offset="3" md="2" offset-md="5" class="mt-2">
        <v-img
          src="../assets/perfectMemberLogo.png"
          style="width: 100%"
          alt="Kiváló adószakértői tag logo."
        />
      </v-col>
    </v-col>
    <v-col cols="12" class="container col-12 text-center text-md-left">
      <v-col cols="6" offset="3" md="2" offset-md="5" class="mb-4">
        <v-img
          class="rounded-circle elevation-3"
          src="../assets/nemethErzsebet.jpg"
          alt="Németh Erzsébet fotó"
        />
      </v-col>
      <h5 class="font-footer text-center line-height-lower mb-5">
        Minden megkeresésére válaszolok, érdeklődjön bizalommal.
      </h5>
      <v-row>
        <v-col md="4" class="text-center">
          <h4 class="text-uppercase">Elérhetőségek</h4>
          <hr class="dark mb-4 d-inline-block mx-auto" style="width: 128px" />
          <br />
          <p v-for="footerData in footerDatas" :key="footerData.title">
            <v-icon>{{ footerData.icon }} </v-icon>
            <span class="force-select">{{ footerData.title }}</span>
          </p>
        </v-col>

        <v-col cols="12" md="3">
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2694.9856539600414!2d19.125926414935144!3d47.50967060266319!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4741db5911839703%3A0x531b5f6ae779ace1!2sBudapest%2C%20Nagy%20Lajos%20kir%C3%A1ly%20%C3%BAtja%2056b%2C%201148!5e0!3m2!1shu!2shu!4v1599340674161!5m2!1shu!2shu"
            width="300"
            height="200"
            style="border: 0"
            aria-hidden="false"
            tabindex="0"
          ></iframe>
        </v-col>
        <v-col md="3" offset-md="1" order-md="first">
          <a
            v-for="link in links"
            :key="link.searchKey"
            :href="link.to"
            style="text-decoration: none"
          >
            <div class="ma-2">{{ link.name }}</div>
          </a>
        </v-col>
      </v-row>
    </v-col>
  </v-footer>
</template>
<script>
export default {
  props: ["links"],
  data: () => ({
    footerDatas: [
      { icon: "mdi-skype", title: "erzsike196469" },
      { icon: "call", title: "+36 30 276 2608" },
      { icon: "local_post_office", title: "ennerzsebet@gmail.com" },
      {
        icon: "place",
        title: "1148 Budapest, Nagy Lajos király útja 56. B",
      },
    ],
  }),
};
</script>
<style>
.font-footer {
  font-weight: bold;
  width: auto;
  font-size: 19px;
}
.force-select {
  -webkit-user-select: all;
  -moz-user-select: all;
  -ms-user-select: all;
  user-select: all;
}
</style>
