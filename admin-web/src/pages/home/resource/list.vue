<template>
  <div>
    <div style="display: flex;height: 100vh;">
      <div v-for="item in list" style="width: 400px;height: 200px;padding: 10px">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>{{ item.title }}</span>
              <el-button type="primary" @click="detail(item.id)">详情</el-button>
            </div>
          </template>
          <div class="text">{{ item.content }}</div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import {resPageList} from "@/api/sys-user";

export default {
  name: "index",
  data() {
    return {
      list: []
    }
  },
  mounted() {
    this.research()
  },
  methods: {
    research() {
      this.searchForm = {
        current: 1,
        size: 15
      }
      this.search()
    },
    search() {
      resPageList(this.searchForm).then(res => {
        this.list = res.data.records
      })
    },
    detail() {

    },
  }
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

</style>