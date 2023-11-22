<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import {getToken, removeToken, setToken} from "@/util/auth";

export default {
  mounted() {
    if (!getToken()) {
      this.$router.push('/login')
    }
  }
}

</script>


<style>
/*header {*/
/*  line-height: 1.5;*/
/*}*/

/*@media (min-width: 1024px) {*/
/*  header {*/
/*    display: flex;*/
/*    place-items: center;*/
/*    padding-right: calc(var(--section-gap) / 2);*/
/*  }*/

/*  .logo {*/
/*    margin: 0 2rem 0 0;*/
/*  }*/

/*  header .wrapper {*/
/*    display: flex;*/
/*    place-items: flex-start;*/
/*    flex-wrap: wrap;*/
/*  }*/
/*}*/
</style>
