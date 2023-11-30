<template>
  <div>
    <div style="display: flex;height: 100vh;flex-flow: wrap">
      <div v-for="item in list" style="width: 400px;padding: 5px;max-height: 150px">
        <el-card shadow="hover">
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
    <div>
      <el-dialog v-model="showDetailFlag" center width="40%">
        <el-form :model="detailInfo" label-position="right" label-width="80px">
          <el-form-item label="标题">
            <el-input v-model="detailInfo.title" disabled/>
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="detailInfo.content" disabled type="textarea"/>
          </el-form-item>
          <el-form-item label="文件">
            <el-upload
                v-model:file-list="fileList"
                :auto-upload="false"
                :on-preview="downLoad"
            >
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="this.showDetailFlag = false">
              取消
            </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {getDownLoadAccess, getResInfoById, resPageList} from "@/api/sys-user";
import {ElMessageBox} from "element-plus";

export default {
  name: "index",
  data() {
    return {
      list: [],
      showDetailFlag: false,
      detailInfo: {},
      fileList: [],
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
    detail(id) {
      this.showDetailFlag = true
      getResInfoById(id).then(
          res => {
            this.detailInfo = res.data
            const fileList = res.data.fileList;
            this.fileList = fileList.map(v => ({
              name: v.fileName,
              id: v.id,
              url: v.url
            }))
          }
      )
    },
    downLoad(file) {
      this.$confirm(`确认下载${file.name}`, '提示',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'info',
          }
      )
          .then(() => {
            getDownLoadAccess(file.id).then(
                res => {
                  window.open(res.data)
                }
            )
          })

    }
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