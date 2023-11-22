<template>
  <div>
    <el-container style="height: 100vh">
      <!-- 侧边栏 -->
      <el-aside width="200px">
<!--        <div style="display: flex; justify-content: center; align-items: center; height: 80px;position: sticky;top: 0;">-->
<!--          淳辉-->
<!--        </div>-->
        <el-scrollbar>
          <el-menu v-for="(item,index) in menuList" :key="index">
            <!-- 根菜单 -->
            <el-sub-menu v-if="!item.url" :index="item.id">
              <template #title>
                <component :is="item.icon" style="width: 24px;height: 18px;margin-right: 5px;"></component>
                <span>{{ item.title }}</span>
              </template>
              <el-menu-item v-for="(leaf,index2) in item.subMune" :key="index2" :index="leaf.id">{{ leaf.title }}
              </el-menu-item>
            </el-sub-menu>
            <!-- 子菜单 -->
            <el-menu-item v-else :index="item.id">
              <component :is="item.icon" style="width: 24px;height: 18px;margin-right: 5px;"></component>
              <template #title>{{ item.title }}</template>
            </el-menu-item>
          </el-menu>
        </el-scrollbar>
      </el-aside>

      <el-container>
        <!-- 顶部 -->
        <el-header style="text-align: right; font-size: 12px">
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
        <el-main>
          <el-scrollbar>
            <el-table :data="tableData">
              <el-table-column prop="date" label="Date" width="140"/>
              <el-table-column prop="name" label="Name" width="120"/>
              <el-table-column prop="address" label="Address"/>
            </el-table>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuList: [],
      tableData: Array.from({length: 20}, () => Object.assign({}, {
        date: '2016-05-02',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
      })),
    }
  },
  mounted() {
    this.getMenu();
  },
  methods: {
    getMenu() {
      this.menuList = [
        {
          "subMune": [],
          "icon": "HomeFilled",
          "title": "首页",
          "url": "/home/home",
        },
        {
          "subMune": [
            {
              "title": "机具管理",
              "url": "/machine/machine",
            }
          ],
          "icon": "list",
          "title": "机具",
          "url": "",
        },
        {
          "subMune": [
            {
              "title": "交易记录",
              "url": "/data/trade",
            }
          ],
          "icon": "Histogram",
          "title": "数据",
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
    }
  }
}

</script>

<style scoped>
</style>