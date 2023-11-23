<template>
  <div class="container">
    <div class="login">
      <div style="font-size: 20px;margin-bottom: 30px;">用户登录</div>
      <el-form style="width: 300px" :model="userInfo">
        <el-form-item>
          <el-input v-model="userInfo.phone" size="large" :clearable="true" maxlength="20" placeholder="请输入登录账号">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input @keyup.enter.native="login" v-model="userInfo.password" :show-password="true" size="large"
                    maxlength="20" placeholder="请输入登录密码">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
      </el-form>
      <el-button @click="login" size="large" type="primary" style="width: 100%;margin-top: 20px;">登录</el-button>
    </div>
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
      // login(this.userInfo);
      this.$router.push('/home/home')
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-image: url("@/assets/img/login.png");
}

.login {
  background-color: rgba(255, 255, 255, 1);
  padding: 40px;
  border-radius: 5px;
  box-sizing: border-box;
}
</style>