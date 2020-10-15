<template>
  <v-app>
    <Navbar :links="links" />
    <v-main>
      <v-container mt-2>
        <v-col col="12" md="10" offset-md="1">
          <router-view :articles="articles"></router-view>
        </v-col>
      </v-container>
    </v-main>
    <Footer :links="links" />
  </v-app>
</template>

<script>
import Navbar from "@/components/Navbar";
import Footer from "@/components/Footer";
import $ from "jquery";

export default {
  name: "App",

  components: {
    Navbar,
    Footer,
  },

  data: () => ({
    links: [
      { to: "/", name: "Főoldal", icon: "home" },
      {
        to: "/nemeth-erzsebet-adoszakerto",
        name: "Magamról",
        icon: "account_box",
      },
      {
        to: "/idopontfoglalas",
        name: "Időpontfoglalás",
        icon: "mdi-calendar-clock",
      },
      { to: "/cikkek", name: "Cikkek", icon: "article" },
    ],
    articles: [],
  }),
  created() {
    $.ajax({
      url: "/api/articles",
      success: (data) => {
        for (let i = 0; i < data.length; i++) {
          this.articles.push(data[i]);
          this.links.push({
            to: "/cikkek/" + data[i].searchKey,
            name: data[i].title,
            icon: "mdi-record",
          });
        }
      },
    });
  },
};
</script>
<style>
.line-height-lower {
  line-height: 1.1;
}
</style>
