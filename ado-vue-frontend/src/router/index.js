import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import Articles from "../views/Articles.vue";
import Article from "../views/Article.vue";
import Booking from "../views/Booking.vue";
import "material-design-icons-iconfont/dist/material-design-icons.css";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/cikkek",
    name: "Articles",
    component: Articles,
  },
  {
    path: "/cikkek/:searchKey",
    name: "Article",
    component: Article,
  },

  {
    path: "/idopontfoglalas",
    name: "Booking",
    component: Booking,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;