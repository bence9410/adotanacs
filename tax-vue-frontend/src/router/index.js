import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/Main.vue";


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
    component: () => import(/* webpackChunkName: "me" */ '@/views/Me.vue')
  },
  {
    path: "/idopontfoglalas",
    name: "Booking",
    component: () => import(/* webpackChunkName: "booking" */ '@/views/Booking.vue')
  },
  {
    path: "/cikkek",
    name: "Articles",
    component: () => import(/* webpackChunkName: "articles" */ '@/views/Articles.vue'),
    alias: "/cikkek/:searchKey",
  },
  {
    path: "*",
    name: "NotFound",
    component: () => import(/* webpackChunkName: "notFound" */ '@/views/NotFound.vue')
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
