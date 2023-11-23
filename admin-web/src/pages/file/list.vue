<template>
  <div>
    <div style="height: 100px;margin-bottom: 20px;">
      搜索栏
    </div>
    <div style="height: 100%">
      <el-scrollbar>
        <el-table :data="tableData">
          <el-table-column prop="date" label="Date" width="140"/>
          <el-table-column prop="name" label="Name" width="120"/>
          <el-table-column prop="address" label="Address"/>
        </el-table>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
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
}
</script>

<style scoped>
</style>