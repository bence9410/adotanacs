<template>
  <div class="articles">
    <div class="text-center">
      <h1>Cikkek</h1>
    </div>
    <v-col cols="12" md="4" offset-md="8">
      <v-autocomplete
        auto-select-first
        clearable
        outlined
        dense
        label="Keresés"
        no-data-text="Nincs elérhető adat"
        v-model="selected"
        @change="search"
        :items="autocompleteItems"
      >
      </v-autocomplete>
    </v-col>
    <v-col>
      <v-card
        shaped
        dark
        class="mx-auto prevent-copy mb-5"
        max-width="600"
        height="100%"
        color="light-blue darken-3 "
        elevation="6"
        v-for="article in articleShown"
        :key="article.id"
      >
        <v-card-title>{{ article.title }}</v-card-title>
        <v-card-subtitle class="pb-0">{{ article.date }}</v-card-subtitle>
        <v-card-text class="white--text" v-html="article.content">
        </v-card-text>
      </v-card>
    </v-col>
  </div>
</template>
<script>
export default {
  props: ["articles"],
  data: () => ({
    selected: "",
    searchKey: "",
  }),
  computed: {
    autocompleteItems() {
      var items = [];
      for (var i = 0; i < this.articles.length; i++) {
        items.push({
          text: this.articles[i].title,
          value: this.articles[i].searchKey,
        });
      }
      return items;
    },
    articleShown() {
      let result = [];
      if (this.searchKey == "") {
        result = this.articles;
      } else {
        for (let i = 0; i < this.articles.length; i++) {
          if (this.articles[i].searchKey == this.searchKey) {
            result.push(this.articles[i]);
          }
        }
      }
      return result;
    },
  },
  created() {
    this.setSearchKeyAndSelected(this.$route.path);
  },
  watch: {
    $route(to) {
      this.setSearchKeyAndSelected(to.path);
    },
  },

  methods: {
    search() {
      if (this.selected != null) {
        this.$router.push("/cikkek/" + this.selected);
      } else {
        this.$router.push("/cikkek");
      }
    },
    setSearchKeyAndSelected(path) {
      this.searchKey = path.substring(8, path.length);
      if (this.searchKey != "") {
        for (let i = 0; i < this.autocompleteItems.length; i++) {
          if (this.autocompleteItems[i].value == this.searchKey) {
            this.selected = this.autocompleteItems[i].value;
          }
        }
      }
    },
  },
};
</script>
<style>
.prevent-copy {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
</style>
