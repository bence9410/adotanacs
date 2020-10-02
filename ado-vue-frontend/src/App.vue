<template>
  <v-app>
    <Navbar />

    <v-main>
      <v-container mt-2>
        <router-link to="/">Föoldal</router-link>
        <router-link to="/cikkek">Cikkek</router-link>
        <router-link to="/idopontfoglalas">Időpontfoglalás</router-link>
        <router-view />
      </v-container>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

export default {
  name: "App",

  components: {
    Navbar,
    Footer,
  },

  data: () => ({
    //
  }),
};
</script>
<style>
.line-height-lower {
  line-height: 1.1;
}
</style>