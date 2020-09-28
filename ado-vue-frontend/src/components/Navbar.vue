<template>
  <div>
    <v-app-bar color="blue-grey darken-4" dark style="height: 70px">
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-toolbar-title
        style="width: 100%; padding-left: 0px"
        class="text-center mt-auto font-navbar"
      >
        Németh Erzsébet
        <div>Adószakértő</div></v-toolbar-title
      >
      <img
        class="mt-auto"
        style="height: 60px"
        alt="Kiváló tag logó."
        src="../assets/perfectMemberLogo.png"
      />
    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
      color="blue-grey darken-4 "
      dark
    >
      <v-list dense>
        <v-list-item-group v-model="group" active-class="blue-grey darken-4">
          <v-list-item
            v-for="navbarItem in navbarItems"
            :key="navbarItem.title"
          >
            <v-list-item-icon>
              <v-icon>{{ navbarItem.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-title>{{ navbarItem.title }}</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>
<style>
.font-navbar {
  font-family: "Times New Roman", Times, serif;
  font-weight: bold;
  width: auto;
  font-size: 22px;
}
</style>
<script>
export default {
  data: () => ({
    drawer: false,
    group: 1,
    navbarItems: [
      { icon: "home", title: "Föoldal" },
      { icon: "event_note", title: "Időpontfoglalás" },
      { icon: "article", title: "Cikkek" },
    ],
  }),
};
</script>
