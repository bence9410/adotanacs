<template>
  <div>
    <v-app-bar fixed dark app color="blue-grey darken-4" style="height: 70px">
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
      temporary
      color="blue-grey darken-4 "
      dark
      app
      style="width:50%;"
    >
      <v-btn icon @click.stop="drawer = !drawer">
        <v-icon>close</v-icon>
      </v-btn>
      <v-list dense rounded>
        <v-list-item-group v-model="group" active-class="blue-grey darken-4">
          <v-list-item
            v-for="link in links"
            :key="link.searchKey"
            router
            :to="link.to"
          >
            <v-list-item-icon>
              <v-icon>{{ link.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal;">
              {{ link.name }}
            </v-list-item-title>
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
  props: ["links"],
  data: () => ({
    drawer: false,
    group: 1,
  }),
};
</script>
