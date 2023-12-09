<template>
  <div>
    <el-row gutter="20" style="width: 100%;padding: 0 20px;">
      <el-col v-for="item in list" :span="6">
        <el-card shadow="hover" style="padding: 5px;min-height: 100px;margin-bottom: 20px">
          <template #header>
            <div class="card-header">
              <span>{{ item.title }}</span>
              <el-button type="primary" @click="detail(item.id)">详情</el-button>
            </div>
          </template>
          <div class="text">{{ item.content }}</div>
        </el-card>
      </el-col>
    </el-row>
    <div>
      <el-dialog v-model="showDetailFlag" center width="40%" @close="close">
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
            <el-button type="primary" @click="close">
              取消
            </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {detail, pageList} from "@/api/res-info";
import {getDownLoadAccess} from "@/api/sys-file";

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
    close() {
      this.showDetailFlag = false
      this.detailInfo = {}
      this.fileList = []
    },
    research() {
      this.searchForm = {
        current: 1,
        size: 15
      }
      this.search()
    },
    search() {
      pageList(this.searchForm).then(res => {
        this.list = res.data.records
      })
    },
    detail(id) {
      this.showDetailFlag = true
      detail(id).then(
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