<template>
  <div class="main">
    <router-view style="height: 100%"></router-view>
  </div>
</template>

<script>
import {getToken} from "@/util/auth";

export default {
  mounted() {
    console.log(import.meta.env.VITE_BASE_URL)
    if (getToken()) {
      this.$router.push('/admin')
    } else {
      this.$router.push('/login')
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
}
</style>
