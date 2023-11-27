<template>
  <div>
    <div style="padding: 20px">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="资源标题">
          <el-input v-model="searchForm.title" clearable placeholder="请输入文件名称"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
          <el-button type="primary" @click="reset">重置</el-button>
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
      <el-dialog v-model="uploadFlag" center title="上传文件" width="40%">
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
                :http-request="doUpload"
                :on-change="changeFile"
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
            <el-button type="primary" @click="save">
              保存
            </el-button>
            <el-button type="primary" @click="uploadFlag = false">
              取消
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

import {getUploadAccess, resPageList, saveResInfo, uploadOss} from "@/api/sys-user";
import {createBaseAxios} from "@/util/http";

export default {
  name: "index",
  data() {
    return {
      menuList: [],
      fileList: [],
      tableData: [],
      uploadFlag: false,
      saveForm: {},
      searchForm: {},
      uploadLoading: false,
      progressPercent: 0
    }
  },
  mounted() {
    this.reset();
  },
  methods: {
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
        debugger
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
    removeFile(file) {
      console.log(file);
    },
    save() {
      saveResInfo(this.saveForm).then(res => {
        this.$message({
          message: res.message,
          type: 'success',
        })
        this.reset();
        this.uploadFlag = false
      })
    },
    search() {
      resPageList(this.searchForm).then(res => {
        this.tableData = res.data
      })
    },
    reset() {
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
      this.uploadFlag = true
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