<template>
  <div>
    <el-menu mode="horizontal">
      <div v-for="(item,index) in menuList" :key="index">
        <template v-if="!item.url">
          <!-- 根菜单 -->
          <el-sub-menu :index="item.id">
            <template #title>
              <component :is="item.icon" style="width: 24px;height: 18px;margin-right: 5px;"></component>
              <span>{{ item.title }}</span>
            </template>
            <el-menu-item v-for="(subMenu,index2) in item.subMune" :key="index2" :index="subMenu.id"
                          @click="to(subMenu.url)">
              {{ subMenu.title }}
            </el-menu-item>
          </el-sub-menu>
        </template>
        <template v-else>
          <!-- 子菜单 -->
          <el-menu-item :index="item.id" @click="to(item.url)">
            <component :is="item.icon" style="width: 24px;height: 18px;margin-right: 5px;"></component>
            <template #title>{{ item.title }}</template>
          </el-menu-item>
        </template>
      </div>
    </el-menu>
  </div>

  <div style="height: 100%">
    <router-view></router-view>
  </div>

</template>

<script>

export default {
  name: "index",
  data() {
    return {
      menuList: []
    }
  },
  mounted() {
    this.getMenu();
  },
  methods: {
    to(url) {
      console.log(url)
      this.$router.push(url)
    },
    getMenu() {
      this.menuList = [
        {
          "subMune": [],
          "icon": "HomeFilled",
          "title": "后台管理",
          "url": "/admin",
        },
        {
          "subMune": [],
          "icon": "list",
          "title": "资源",
          "url": "/home/resource/list",
        }
      ]
      // 在这里处理一下菜单id
      let menuId = 0;
      let subMenuId = 100;
      this.menuList.forEach(m => {
        m.id = ++menuId + "";
        if (m.subMune.length !== 0) {
          m.subMune.forEach(s => {
            s.id = ++subMenuId + "";
            s.s_menu_id = menuId + "";
          })
        }
      })
    },
    handleSelect() {

    }
  }
}


</script>

<style scoped>

</style>