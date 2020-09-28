<template>
  <v-app>
    <Navbar />

    <v-main>
      <v-container> <Main /> </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Navbar from "./components/Navbar";
import Main from "./components/Main";

export default {
  name: "App",

  components: {
    Navbar,
    Main,
  },

  data: () => ({
    //
  }),
};
</script>
