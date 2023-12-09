<template>
  <div class="page">
    <el-card>
      <el-form
          class="query-form"
          inline
          :label-width="80"
          :model="searchForm"
      >
        <el-form-item
            label="标题 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.title"
          />
        </el-form-item>
        <el-form-item
            label="内容 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.content"
          />
        </el-form-item>
        <div class="action-groups">
          <el-button type="primary" plain @click="search">查询</el-button>
          <el-button type="primary" plain @click="research">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card>
      <template #header>
        <div class="card-header">
          <el-space><span>菜单管理</span></el-space>
          <el-space>
            <el-button type="primary" plain @click="showSaveForm">新增</el-button>
          </el-space>
        </div>
      </template>
      <el-table
          :data="tableData.records"
          style="width: 100%"
          size="default"
          height="500"
          :highlight-current-row="true"
          row-key="id"
          empty-text="No Data"
          :stripe="true"
      >
        <el-table-column
            prop="title"
            label="标题"
        />
        <el-table-column
            prop="content"
            label="内容"
        />
        <el-table-column
            prop="act"
            label="操作"
            :width="160"
            align="center"
            fixed="right"
        >
          <template #default="scope">
            <el-space>
              <el-button link type="success" @click="showUpdate(scope.row.id)">编辑</el-button>
              <el-button link type="danger" @click="deleteInfo(scope.row.id)">删除</el-button>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-card>
      <el-pagination
          layout="total,prev,pager,next,sizes"
          :background="true"
          :small="true"
          :total="tableData.total"
          :page-sizes="[15,30,50,80,100]"
          v-model:page-size="tableData.pageSize"
          v-model:current-page="tableData.current"
          @current-change="changeCurrentPage"
          @size-change="changePageSize"
      />
    </el-card>
    <el-dialog v-model="saveDialogFlag" center :title="updateFlag ? '修改' : '新增'" width="40%">
      <el-form :model="saveForm" label-position="right" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="saveForm.title"/>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="saveForm.content"/>
        </el-form-item>
        <el-form-item label="文件">
          <el-upload
              v-model:file-list="fileList"
              :auto-upload="true"
              :before-remove="beforeRemove"
              :http-request="doUpload"
              :on-change="changeFile"
              :on-preview="previewFile"
              :on-remove="removeFile"
          >
            <el-button type="primary">上传文件</el-button>
            <template #tip>
              <div class="el-upload__tip">
                限制上传大小为1G
              </div>
            </template>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="saveDialogFlag = false">
              取消
            </el-button>
            <el-button type="primary" @click="saveData">
              保存
            </el-button>
          </span>
      </template>
    </el-dialog>
    <!-- 上传文件进度条 -->
    <div v-if="uploadLoading" class="loading-overlay">
      <el-progress
          :percentage="progressPercent" :stroke-width="18" color="#ff0000" striped-flow type="circle">
      </el-progress>
    </div>
  </div>
</template>

<script>
import {deleteById, detail, pageList, save, update} from "@/api/res-info.js";
import {createBaseAxios} from "@/util/http";
import {ElMessageBox} from "element-plus";
import {getDownLoadAccess, getUploadAccess} from "@/api/sys-file";

export default {
  name: "index",
  data() {
    return {
      fileList: [],
      tableData: {total: 0},
      saveDialogFlag: false,
      saveForm: {},
      searchForm: {},
      uploadLoading: false,
      progressPercent: 0,
      updateFlag: false
    }
  },
  mounted() {
    this.research();
  },
  methods: {
    showUpdate(id) {
      this.updateFlag = true
      this.resetSaveForm()
      detail(id).then((res) => {
        this.saveDialogFlag = true
        this.saveForm = res.data
        const fileList = res.data.fileList;
        this.saveForm.fileList = fileList.map(v => v.id)
        this.fileList = fileList.map(v => ({
          name: v.fileName,
          id: v.id,
          url: v.url
        }))
      })
    },
    deleteInfo(id) {
      ElMessageBox.confirm('确定删除该资源？')
          .then(() => {
            deleteById(id).then(res => {
              this.research()
              this.$message({
                message: "删除成功",
                type: 'success'
              })
            })
          })
    },
    changeFile(file, fileList) {
      // 数据小于0.1M的时候按KB显示
      const size = file.size / 1024 / 1024 > 0.1 ? `(${(file.size / 1024 / 1024).toFixed(1)}M)` : `(${(file.size / 1024).toFixed(1)}KB)`
      file.name.indexOf('M') > -1 || file.name.indexOf('KB') > -1 ? file.name : file.name += size
    },
    doUpload(option) {
      this.progressPercent = 0
      const file = option.file;
      getUploadAccess({
        fileName: file.name,
        fileType: file.type,
        fileSize: file.size
      }).then((res) => {
        const oss = {
          key: res.data.filePath,
          success_action_status: '200',
          OSSAccessKeyId: res.data.accessid,
          policy: res.data.policy,
          signature: res.data.signature
        }
        const config = {
          onUploadProgress: progressEvent => {
            this.progressPercent = Number((progressEvent.loaded / progressEvent.total * 100).toFixed(2))
          },
          headers: {'Content-Type': 'multipart/form-data'}
        }
        const formData = new FormData()
        if (oss) {
          for (const [key, value] of Object.entries(oss)) {
            formData.append(key, value)
          }
        }
        formData.append('file', file, file.name)
        this.uploadLoading = true
        createBaseAxios(60 * 60 * 1000).post(res.data.host, formData, config).then(() => {
              this.saveForm.fileList.push(res.data.id)
              this.uploadLoading = false
              this.$message({
                message: "上传成功",
                type: 'success'
              })
            }
        );
      });
    },
    removeFile(file, fileList) {
      console.log(file);
      console.log(fileList);
      // const
    },
    previewFile(file) {
      this.$confirm(`确认下载${file.name}`, '提示',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'info',
          }
      )
          .then(() => {
            const index = this.fileList.findIndex(item => item.uid === file.uid)
            const fileId = this.saveForm.fileList[index];
            getDownLoadAccess(fileId).then(
                res => {
                  window.open(res.data)
                }
            )
          })

    },
    beforeRemove(file, fileList) {
      return ElMessageBox.confirm(
          `确定移除文件 ${file.name} ?`
      ).then(
          () => {
            const index = fileList.findIndex(item => item.uid === file.uid)
            this.saveForm.fileList.splice(index, 1)
            return true
          },
          () => false
      )
    },
    saveData() {
      const promiseFn = this.updateFlag ? update : save;
      promiseFn(this.saveForm).then(res => {
        this.$message({
          message: res.message,
          type: 'success',
        })
        this.research();
        this.saveDialogFlag = false
        this.updateFlag = false
      })
    },
    search() {
      pageList(this.searchForm).then(res => {
        this.tableData = res.data
      })
    },
    research() {
      this.searchForm = {
        current: 1,
        size: 15
      }
      this.search()
    },
    changePageSize(size) {
      this.searchForm.size = size
      this.search()
    },
    changeCurrentPage(current) {
      this.searchForm.current = current
      this.search()
    },
    resetSaveForm() {
      this.fileList = []
      this.saveForm = {
        fileList: []
      }
    },
    showSaveForm() {
      this.resetSaveForm()
      this.saveDialogFlag = true
      this.updateFlag = false
    }
  }
}
</script>


<style>
.loading-overlay {
  flex-direction: column;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.page {
  height: 100%;
  padding: 10px;
  background-color: rgba(0, 0, 0, 0.1);
}

.page .query-form {
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
}

.page .query-form .action-groups {
  margin-left: auto;
}

.page .el-card {
  margin-bottom: 4px;
}

.page .el-form--inline .el-form-item {
  margin: 0;
  margin-bottom: 10px;
}

.page .el-card__body, .page .el-card__header {
  padding: 8px;
}

.page .card-header {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: center;
}
</style>