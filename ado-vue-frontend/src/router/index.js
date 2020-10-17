import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/Main.vue";
import Me from "@/views/Me.vue";
import Booking from "@/views/Booking.vue";
import Articles from "@/views/Articles.vue";
import NotFound from "@/views/NotFound.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/nemeth-erzsebet-adoszakerto",
    name: "Me",
    component: Me,
  },
  {
    path: "/idopontfoglalas",
    name: "Booking",
    component: Booking,
  },
  {
    path: "/cikkek",
    name: "Articles",
    component: Articles,
    alias: "/cikkek/:searchKey",
  },
  {
    path: "*",
    name: "NotFound",
    component: NotFound,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
