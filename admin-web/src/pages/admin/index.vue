<template>
  <div>
    <el-container style="height: 100vh">
      <!-- 侧边栏 -->
      <el-aside style="background: #81D4FA" width="200px">
        <el-menu default-active="2">
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
      </el-aside>

      <el-container>
        <!-- 顶部 -->
        <el-header style="text-align: right; font-size: 12px;background: #FFF9C4;">
          <div class="toolbar">
            <el-dropdown>
              <el-icon style="margin-right: 8px; margin-top: 1px"
              >
                <setting
                />
              </el-icon>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>View</el-dropdown-item>
                  <el-dropdown-item>Add</el-dropdown-item>
                  <el-dropdown-item>Delete</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            <span>Tom</span>
          </div>
        </el-header>
        <!-- 主体 -->
        <el-main style="background: #E3F2FD">
          <router-view style="background: #FFFFFF;height: 100%"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuList: []
    }
  },
  mounted() {
    this.getMenu();
    this.$router.push('/admin/resource/list')
  },
  methods: {
    getMenu() {
      this.menuList = [
        {
          "subMune": [],
          "icon": "HomeFilled",
          "title": "回到首页",
          "url": "/home",
        },
        {
          "subMune": [],
          "icon": "list",
          "title": "资源管理",
          "url": "/admin/resource/list",
        },
        {
          "subMune": [],
          "icon": "list",
          "title": "菜单管理",
          "url": "/admin/menu/list",
        },
        {
          "subMune": [],
          "icon": "list",
          "title": "角色管理",
          "url": "/admin/sys-role/list",
        },
        {
          "subMune": [],
          "icon": "list",
          "title": "权限管理",
          "url": "/admin/sys-permission/list",
        },
        {
          "subMune": [],
          "icon": "list",
          "title": "用户管理",
          "url": "/admin/sys-user/list",
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
    to(url) {
      console.log(url)
      this.$router.push(url)
    }
  }
}

</script>

<style scoped>
</style>