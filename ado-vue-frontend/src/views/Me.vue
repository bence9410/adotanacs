<template>
  <div>
    <v-col cols="8" offset="2" md="2" offset-md="5">
      <v-img
        class="rounded-circle"
        :aspect-ratio="1"
        src="../assets/studies.jpg"
        alt="Tanulmányok könyv fotó"
      />
    </v-col>
    <v-row v-for="content in contents" :key="content.title">
      <v-col cols="12">
        <h1 class="font-bold text-center mt-5">{{ content.title }}</h1>
      </v-col>
      <v-col v-for="place in content.places" :key="place.name" cols="12" md="6">
        <v-card
          shaped
          dark
          class="mx-auto"
          max-width="600"
          height="100%"
          color="light-blue darken-3 "
          elevation="6"
        >
          <v-card-title>{{ place.name }}</v-card-title>
          <v-card-subtitle class="pb-0">{{ place.date }}</v-card-subtitle>
          <v-card-text class="white--text">
            {{ place.description }}
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>
<script>
export default {
  data: () => ({
    contents: [
      {
        title: "Tanulmányok",
        places: [
          {
            name: "Adótanácsadók Egyesülete",
            date: "2009",
            description:
              "PM.regisztrált adószakértő, adótanácsadó Adótanácsadók Egyesületének tagsága mellett kiváló minősítésű védjegyhasználattal.",
          },
          {
            name: "PM.regisztrált mérlegképes könyvelő",
            date: "2003-",
            description: "",
          },
          {
            name: "GKM-Fejlesztési projektek,kapcsolódó pályázatok PL-0603",
            date: "2004",
            description: "",
          },
          {
            name: "Perfekt Zrt.-adótanácsadó",
            date: "2001-2003",
            description: "",
          },
          {
            name: "Perfekt Zrt.-mérlegképes könyvelő",
            date: "1999-2001",
            description: "",
          },
          {
            name:
              " 	Szlovák Műszaki Egyetem Vegyészmérnöki Kara-MSC vegyészmérnök",
            date: "1983-1988",
            description: "",
          },
        ],
      },
      {
        title: "Munkahelyek, Munkakörök",
        places: [
          {
            name: "Saldo Zrt. ",
            date: "2017-",
            description:
              "Gazdasági tanácsadó, számvitel, társasági adó, kisadók területén",
          },
          {
            name: "Bírósági Végrehajtói Kar",
            date: "2016-2017",
            description: "Főkönyvelő",
          },
          {
            name: "VEGYTERV Zrt.",
            date: "2011-2016",
            description: "Gazdasági vezető",
          },
          { name: "2005-", date: "2005-", description: "Ügyvezető igazgató" },
        ],
      },
    ],
  }),
};
</script>