<template>
  <div>
    <el-container style="height: 100vh">
      <!-- 侧边栏 -->
      <el-aside width="200px">
        <el-menu default-active="2">
          <div v-for="(item,index) in menuList" :key="index">
            <template v-if="!item.url">
              <!-- 根菜单 -->
              <el-sub-menu :index="item.id">
                <template #title>
                  <component :is="item.icon" style="width: 24px;height: 18px;margin-right: 5px;"></component>
                  <span>{{ item.name }}</span>
                </template>
                <el-menu-item v-for="(subMenu,index2) in item.subMune" :key="index2" :index="subMenu.id"
                              @click="to(subMenu.url)">
                  {{ subMenu.name }}
                </el-menu-item>
              </el-sub-menu>
            </template>
            <template v-else>
              <!-- 子菜单 -->
              <el-menu-item :index="item.id" @click="to(item.url)">
                <component :is="item.icon" style="width: 24px;height: 18px;margin-right: 5px;"></component>
                <template #title>{{ item.name }}</template>
              </el-menu-item>
            </template>
          </div>
        </el-menu>
      </el-aside>

      <el-container>
        <!-- 顶部 -->
        <el-header style="text-align: right; font-size: 12px;">
          <div class="toolbar">
            <el-dropdown>
              <el-icon style="margin-right: 8px; margin-top: 1px"
              >
                <setting
                />
              </el-icon>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="logoutNow">登出</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            <span>你好</span>
          </div>
        </el-header>
        <!-- 主体 -->
        <el-main style="background: #E3F2FD">
          <router-view style="background: #E3F2FD;height: 100%"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import {getHomeMenu} from "@/api/sys-menu";
import {logout} from "@/api/sys-user";

export default {
  data() {
    return {
      menuList: []
    }
  },
  mounted() {
    getHomeMenu().then(res => {
      this.menuList = res.data
    });
  },
  methods: {
    logoutNow() {
      logout().then(() => {
        this.$router.push('/login')
      })
    },
    to(url) {
      console.log(url)
      this.$router.push(url)
    }
  }
}

</script>

<style scoped>
</style>