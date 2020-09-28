<template>
  <div class="main">
    <div class="bookingContainer">
      <v-img
        class="bigScreen"
        :aspect-ratio="2.857"
        src="../assets/accountant-big.jpg"
        alt="Könyvelö fotó."
      />
      <v-img
        class="smallScreen"
        :aspect-ratio="1.333"
        src="../assets/accountant-small.jpg"
        alt="Könyvelö fotó."
      />
      <div class="content" style="padding: 0px">
        <h1 class="font-bold ml-3">
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
    <div class="text-center col-lg-6 offset-sm-3 offset-lg-3 mt-3 mb-3">
      <img
        class="col-7 rounded-circle"
        src="img/studies.jpg"
        alt="Tanulmányok könyv fotó"
      />
    </div>
    <div class="row">
      <div class="col-12">
        <h1 class="font-bold text-center">Tanulmányok</h1>
      </div>
      <v-card shaped class="mx-auto" max-width="400">
        <v-card-title>Top 10 Australian beaches</v-card-title>
        <v-card-subtitle class="pb-0">2009.01.01</v-card-subtitle>

        <v-card-text class="text--primary">
          <div>Whitehaven Beach</div>

          <div>
            PM.regisztrált adószakértő, adótanácsadó Adótanácsadók Egyesületének
            tagsága mellett kiváló minősítésű védjegyhasználattal.
          </div>
        </v-card-text>
      </v-card>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">2009.01.01</h5>
            <p class="card-text"></p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">2003.01.01-</h5>
            <p class="card-text">PM.regisztrált mérlegképes könyvelő.</p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">2004.09.13-10.15.</h5>
            <p class="card-text">
              GKM-Fejlesztési projektek,kapcsolódó pályázatok PL-0603
            </p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">2001-2003</h5>
            <p class="card-text">Perfekt Zrt.-adótanácsadó</p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">1999-2001.</h5>
            <p class="card-text">Perfekt Zrt.-mérlegképes könyvelő</p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">1983-1988.</h5>
            <p class="card-text">
              Szlovák Műszaki Egyetem Vegyészmérnöki Kara-MSC vegyészmérnök
            </p>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <h1 class="font-bold text-center">Munkahelyek, Munkakörök</h1>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">2017-</h5>
            <p class="card-text">
              Saldo Zrt. – gazdasági tanácsadó, számvitel, társasági adó,
              kisadók területén.
            </p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card bg-secondary text-white shadow" style="height: 100%">
          <div class="card-body">
            <h5 class="card-title">2016-2017</h5>
            <p class="card-text">Bírósági Végrehajtói Kar - Főkönyvelő</p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card text-white bg-info shadow">
          <div class="card-body">
            <h5 class="card-title">2011-2015.12.31.</h5>
            <p class="card-text">VEGYTERV Zrt.-gazdasági vezető</p>
          </div>
        </div>
      </div>
      <div class="col-12 offet-1 col-md-6 mb-3 mb-md-4">
        <div class="card bg-secondary text-white shadow">
          <div class="card-body">
            <h5 class="card-title">2005-</h5>
            <p class="card-text">PROMARK-VF Kft.-Ügyvezető igazgató</p>
          </div>
        </div>
      </div>
    </div>
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
