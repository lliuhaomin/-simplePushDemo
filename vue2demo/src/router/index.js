import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/components/home.vue";
import Test1 from "@/components/test1.vue";
import Test2 from "@/components/test2.vue";
Vue.use(Router)

export default new Router({
  routes: [
    {
        path: "/home",
        name: "home",
        component: Home,
      },
      {
        path: "/",
        component: Home,
      },
      {
        path: "/test1",
        component: Test1,
      },
      {
        path: "/test2",
        component: Test2,
      },
  ]
})
