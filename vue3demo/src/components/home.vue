<template>
  <div class="hello">
    <h1>home3333</h1>
    <input type="text"
           v-model="value"><br /><br />
    <button @click="setAlias">设置别名</button><br /><br />
    <button @click="unSetAlias">取消别名</button><br /><br />
    <router-link :to="'/test1'">测试1</router-link><br /><br /><br /><br />
    <router-link :to="'/test2'">测试2</router-link>
  </div>
</template>

<script>
import pushServer from "@/common/js/pushServer.js";
export default {
  name: "home",
  data () {
    return {
      pushServer: pushServer,
      value: ''
    }
  },
  props: {
    msg: String,
  },
  created () {
    this.pushServer.initialize();
    this.pushServer.that = this;
    this.value = this.pushServer.regId
  },
  methods: {
    setAlias () {
      this.pushServer.setAlias(this.value);
    },
    unSetAlias () {
      this.pushServer.unSetAlias(this.value);
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
