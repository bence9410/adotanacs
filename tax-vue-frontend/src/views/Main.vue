<template>
  <div class="main">
    <div class="mainContainer elevation-6">
      <v-img
        class="bigScreen rounded"
        :aspect-ratio="4.02"
        src="../assets/accountant-big.png"
        alt="Könyvelö fotó."
      />
      <v-img
        class="smallScreen rounded"
        :aspect-ratio="1.333"
        src="../assets/accountant-small.jpg"
        alt="Könyvelö fotó."
      />
      <div class="content pt-md-3 pb-md-2" style="padding: 0px">
        <h1 class="font-bold ml-3" style="line-height: 1.3">
          Vezetői számvitel, adótanácsadás, átalakulás, végelszámolás könyvelési
          teendő.
        </h1>
      </div>
    </div>

    <p class="font mt-4">
      Többéves adószakértői, adótanácsadói, mérlegképes könyvelői tapasztalattal
      segítem leendő és jelenlegi ügyfeleimet. Megbízhatóság, szerződött
      garancia titoktartással vállalkozása biztonsága érdekében.
    </p>
    <p class="font">
      Amennyiben vélemények birtokában szeretné meghozni vállalkozása fontos
      döntéseit, keressen bizalommal. Segítek megoldást találni az adózás
      útvesztőjében.
    </p>
    <sequential-entrance fromRight>
      <div class="text-center">
        <v-btn
          elevation="5"
          x-large
          outlined
          color="red"
          class="mt-2 px-2"
          to="/idopontfoglalas"
        >
          <h4 class="text-uppercase">Foglaljon időpontot most!</h4>
          <v-icon style="font-size: 30px" class="ml-2"
            >mdi-calendar-clock</v-icon
          >
        </v-btn>
      </div>
    </sequential-entrance>
  </div>
</template>
<script>
export default {
  metaInfo: () => ({
    meta: [
      {
        name: "title",
        content:
          "Vezetői számvitel, adótanácsadás, átalakulás, végelszámolás könyvelési teendő",
      },
      {
        name: "description",
        content:
          "Többéves adószakértői, adótanácsadói, mérlegképes könyvelői tapasztalattal segítem leendő és jelenlegi ügyfeleimet. Megbízhatóság, szerződött garancia titoktartással vállalkozása biztonsága érdekében.",
      },
    ],
  }),
};
</script>

<style>
.font-bold {
  font-weight: bold;
  width: auto;
  font-size: 23px;
}
.font {
  width: auto;
  font-size: 18px;
}
.mainContainer {
  position: relative;
  margin: 0 auto;
  font-size: 10px;
}
.mainContainer img {
  vertical-align: middle;
}

.mainContainer .content {
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
