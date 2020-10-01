<template>
  <div class="main">
    <div class="bookingContainer elevation-6 pt-2" >
      <v-img
        class="bigScreen  rounded"
        :aspect-ratio="2.857"
        src="../assets/accountant-big.jpg"
        alt="Könyvelö fotó."
      />
      <v-img
        class="smallScreen  rounded"
        :aspect-ratio="1.333"
        src="../assets/accountant-small.jpg"
        alt="Könyvelö fotó."
      />
      <div class="content" style="padding: 0px">
        <h1 class="font-bold ml-3" style="line-height: 1.3;">
          Vezetői számvitel, adótanácsadás, átalakulás, végelszámolás könyvelési
          teendő.
        </h1>
      </div>
    </div>
    <p class="font text-justify mt-4">
      Többéves adószakértői, adótanácsadói, mérlegképes könyvelői tapasztalattal
      segítem leendő és jelenlegi ügyfeleimet. Megbízhatóság, szerződött
      garancia titoktartással vállalkozása biztonsága érdekében.
    </p>
    <p class="font text-justify">
      Amennyiben vélemények birtokában szeretné meghozni vállalkozása fontos
      döntéseit, keressen bizalommal. Segítek megoldást találni az adózás
      útvesztőjében.
    </p>
    <v-col cols="8" offset="2" md="2"  offset-md="5" >
     <v-img class="rounded-circle"
        :aspect-ratio="1"
        src="../assets/studies.jpg"
        alt="Tanulmányok könyv fotó"
      />
    </v-col>
    <v-row v-for="content in contents" :key="content.title">
      <v-col cols="12">
        <h1 class="font-bold text-center">{{content.title}}</h1>
      </v-col>
      <v-col v-for="place in content.places" :key="place.name"
      cols="12" md="6">
      <v-card shaped dark class="mx-auto" max-width="600" height="100%" color="light-blue darken-3 "  elevation="6">
        <v-card-title>{{place.name}}</v-card-title>
        <v-card-subtitle class="pb-0">{{place.date}}</v-card-subtitle>
        <v-card-text  class="white--text">
          {{place.description}}
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
            name: "Adótanácsadók Egyesület",
            date: "2009.01.01",
            description:
              "PM.regisztrált adószakértő, adótanácsadó Adótanácsadók Egyesületének tagsága mellett kiváló minősítésű védjegyhasználattal.",
          },
          {
            name: "PM.regisztrált mérlegképes könyvelő",
            date: "2003.01.01-",
            description: "",
          },
          {
            name: "GKM-Fejlesztési projektek,kapcsolódó pályázatok PL-0603",
            date: "2004.09.13-10.15.",
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
            date: "2011-2015.12.31.",
            description: "Gazdasági vezető",
          },
          { name: "2005-", date: "2005-", description: "Ügyvezető igazgató" },
        ],
      },
    ],
  }),
};
</script>
<style>
.font-bold {
  font-family: "Times New Roman", Times, serif;
  font-weight: bold;
  width: auto;
  font-size: 23px;
}
.font {
  font-family: "Times New Roman", Times, serif;
  width: auto;
  font-size: 18px;
}
.bookingContainer {
  position: relative;

  margin: 0 auto;
  font-size: 10px;
}
.bookingContainer img {
  vertical-align: middle;
}

.bookingContainer .content {
  position: absolute;
  bottom: 0;
  background: rgb(0, 0, 0); /* Fallback color */
  background: rgba(0, 0, 0, 0.5); /* Black background with 0.5 opacity */
  color: #f1f1f1;
  width: 100%;
  padding: 20px;
}
@media screen and (min-width: 401px) {
  .font-bold {
    font-size: 30px;
  }
}
@media screen and (max-width: 368px) {
  .font-bold {
    font-size: 25px;
  }
}

@media (max-width: 1000px) {
  .bigScreen {
    display: none;
  }
  .smallScreen {
    display: block;
  }
}

@media (min-width: 1001px) {
  .bigScreen {
    display: block;
  }
  .smallScreen {
    display: none;
  }
}
</style>
