<template>
  <div class="container">
    <div class="login">
      <div style="font-size: 20px;margin-bottom: 30px;">用户登录</div>
      <el-form :model="userInfo" style="width: 300px">
        <el-form-item>
          <el-input v-model="userInfo.userName" :clearable="true" maxlength="20" placeholder="请输入登录账号"
                    size="large">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="userInfo.password" :show-password="true" maxlength="20" placeholder="请输入登录密码"
                    size="large" @keyup.enter.native="login">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
      </el-form>
      <el-button size="large" style="width: 100%;margin-top: 20px;" type="primary" @click="login">登录</el-button>
    </div>
  </div>
</template>

<script>
import {login} from "@/api/sys-user";
import {setToken} from "@/util/auth";

export default {
  name: "login",
  data() {
    return {
      userInfo: {
        // userName: 'admin',
        // password: '123456'
      }
    }
  },
  methods: {
    login() {
      login(this.userInfo).then(
          (res) => {
            this.$router.push('/home/resource/list')
            setToken(res.data)
          }
      );
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