<template>
  <div>
    <el-form
        label-position="right"
        label-width="100px"
        :model="userInfo"
        style="max-width: 460px"
    >
      <el-form-item label="Name">
        <el-input v-model="userInfo.phone" type="tel"/>
      </el-form-item>
      <el-form-item label="Activity zone">
        <el-input v-model="userInfo.password" type="password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">Create</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
import {login} from "@/api/sys-user";

export default {
  name: "login",
  data() {
    return {
      userInfo: {}
    }
  },
  methods: {
    login() {
      console.log(JSON.stringify(this.userInfo))
      login(this.userInfo);
    }
  }
}
</script>

<style scoped>

</style>