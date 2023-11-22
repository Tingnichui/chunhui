<template>
  <div class="main">
    <router-view></router-view>
  </div>
</template>

<script>
import {getToken} from "@/util/auth";

export default {
  mounted() {
    if (!getToken()) {
      this.$router.push('/index')
    }
  }
}

</script>


<style scoped>
/* 设置占满页面 */
.main {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 100%;
}
</style>
