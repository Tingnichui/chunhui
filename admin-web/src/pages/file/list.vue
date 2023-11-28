<template>
  <div>
    <div style="padding: 20px">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="资源标题">
          <el-input v-model="searchForm.title" clearable placeholder="请输入文件名称"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
          <el-button type="primary" @click="research">重置</el-button>
          <el-button type="primary" @click="showSaveForm">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div style="height: calc(80%);">
      <el-table :data="tableData.records" border stripe style="height: 100%">
        <el-table-column label="序号" type="index" width="100"/>
        <el-table-column label="标题" prop="title" width="180"/>
        <el-table-column label="描述" prop="content" width="180"/>
        <el-table-column label="创建时间" prop="createTime"/>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" @click="showUpdate(scope.row.id)">编辑</el-button>
            <el-button size="small" type="danger" @click="deleteInfo(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 10px;display: flex;justify-content: right">
        <el-pagination
            :current-page="tableData.current"
            :page-size="tableData.size"
            :page-sizes="[15, 30, 60, 80]"
            :total="tableData.total"
            background
            layout="total, prev, pager, next, sizes"
            small
            @size-change="changePageSize"
            @current-change="changeCurrentPage"
        />
      </div>
    </div>
    <div>
      <el-dialog v-model="saveDialogFlag" center title="上传文件" width="40%">
        <el-form :model="saveForm" label-position="right" label-width="80px">
          <el-form-item label="标题">
            <el-input v-model="saveForm.title"/>
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="saveForm.content" type="textarea"/>
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
            <el-button type="primary" @click="save">
              保存
            </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
    <!-- 上传文件进度条 -->
    <div v-if="uploadLoading" class="loading-overlay">
      <el-progress
          :percentage="progressPercent" :stroke-width="18" color="#ff0000" striped-flow type="circle">
      </el-progress>
    </div>
  </div>
</template>

<script>

import {
  deleteById, getDownLoadAccess,
  getResInfoById,
  getUploadAccess,
  resPageList,
  saveResInfo,
  updateResInfo,
  uploadOss
} from "@/api/sys-user";
import {createBaseAxios} from "@/util/http";
import {ElMessageBox} from "element-plus";

export default {
  name: "index",
  data() {
    return {
      fileList: [],
      tableData: [],
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
      getResInfoById(id).then((res) => {
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
        createBaseAxios().post(res.data.host, formData, config).then(() => {
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
      const index = this.fileList.findIndex(item => item.uid === file.uid)
      const fileId = this.saveForm.fileList[index];
      getDownLoadAccess(fileId).then(
          res => {
            window.open(res.data)
          }
      )
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
    save() {
      const promiseFn = this.updateFlag ? updateResInfo : saveResInfo;
      promiseFn(this.saveForm).then(res => {
        this.$message({
          message: res.message,
          type: 'success',
        })
        this.research();
        this.saveDialogFlag = false
        this.updateFlag = false
      })
      // if (this.updateFlag) {
      //   updateResInfo(this.saveForm).then(res => {
      //     this.$message({
      //       message: res.message,
      //       type: 'success',
      //     })
      //     this.research();
      //     this.saveDialogFlag = false
      //     this.updateFlag = false
      //   })
      // } else {
      //   saveResInfo(this.saveForm).then(res => {
      //     this.$message({
      //       message: res.message,
      //       type: 'success',
      //     })
      //     this.research();
      //     this.saveDialogFlag = false
      //   })
      // }
    },
    search() {
      resPageList(this.searchForm).then(res => {
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
    }
  }
}
</script>

<style scoped>
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
</style>