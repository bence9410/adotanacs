<template>
  <v-app>
    <Navbar />

    <v-main>
      <v-container mt-2> <Main /> </v-container>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Navbar from "./components/Navbar";
import Main from "./components/Main";
import Footer from "./components/Footer";

export default {
  name: "App",

  components: {
    Navbar,
    Main,
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