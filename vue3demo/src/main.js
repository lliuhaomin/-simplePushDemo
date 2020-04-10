import Vue from "vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import routers from "./routers";
Vue.config.productionTip = false;

Vue.use(VueRouter);

const router = new VueRouter({
// cordova 打包需要注释
//   mode: "history",
  routes: routers,
});

// new Vue({
//   el: "#app",
//   router,
//   render: (h) => h(App),
// });
// 开发时使用, cordova打包时注释掉
// window.Vue = new Vue({
//   router,
//   render: h => h(App)
// }).$mount("#app");
// cordova打包时放开注释
document.addEventListener(
  "deviceready",
  function() {
    window.Vue = new Vue({
      router,
      render: h => h(App)
    }).$mount("#app");
  },
  false
);
