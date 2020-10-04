<template>
  <div class="articles">
    <v-col cols="12" md="4" offset-md="8">
      <v-autocomplete
        auto-select-first
        clearable
        outlined
        dense
        label="Keresés"
        no-data-text="Nincs elérhető adat"
        v-model="value"
        :items="arts"
      >
      </v-autocomplete>
    </v-col>
    <v-col>
      <v-card
        shaped
        dark
        class="mx-auto"
        max-width="600"
        height="100%"
        color="light-blue darken-3 "
        elevation="6"
        v-for="article in articles"
        :key="article.id"
      >
        <v-card-title>{{ article.title }}</v-card-title>
        <v-card-subtitle class="pb-0">{{ article.date }}</v-card-subtitle>
        <v-card-text class="white--text">{{ article.content }} </v-card-text>
      </v-card>
    </v-col>
  </div>
</template>
<script>
export default {
  props: ["articles"],
  data: () => ({
    value: [],
  }),
  computed: {
    arts: function () {
      var art = [];
      for (var i = 0; i < this.articles.length; i++) {
        art.push(this.articles[i].title);
      }
      return art;
    },
  },
};
</script>
<style></style>
