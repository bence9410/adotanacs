<template>
  <v-app>
    <Navbar :links="links" />
    <v-main>
      <v-container mt-2>
        <router-view :articles="articles"></router-view>
      </v-container>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import $ from "jquery";

export default {
  name: "App",

  components: {
    Navbar,
    Footer,
  },

  data: () => ({
    links: [
      { to: "/", name: "Föoldal", icon: "home" },
      {
        to: "/idopontfoglalas",
        name: "Idöpontfoglalás",
        icon: "mdi-calendar-clock",
      },
      { to: "/cikkek", name: "Cikkek", icon: "article" },
    ],
    articles: [],
  }),
  created() {
    $.ajax({
      url: "/api/articles",
      success: function (data) {
        for (let i = 0; i < data.length; i++) {
          this.articles.push(data[i]);
        }

        this.links.push({
          to: "/cikkek/" + data[0].searchKey,
          name: data[0].title,
          icon: "mdi-record",
        });
      }.bind(this),
    });
  },
};
</script>
<style>
.line-height-lower {
  line-height: 1.1;
}
</style>
