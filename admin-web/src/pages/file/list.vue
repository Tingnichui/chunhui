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
  </div>
</template>

<script>

import {getUploadAccess, resPageList, saveResInfo, uploadOss} from "@/api/sys-user";

export default {
  name: "index",
  data() {
    return {
      menuList: [],
      fileList: [],
      tableData: [],
      uploadFlag: false,
      saveForm: {},
      searchForm: {}
    }
  },
  mounted() {
    this.reset();
  },
  methods: {
    doUpload(option) {
      getUploadAccess({
        fileName: option.file.name,
        fileType: option.file.type,
        fileSize: option.file.size
      }).then((res) => {
        const oss = {
          key: res.data.filePath,
          success_action_status: '200',
          OSSAccessKeyId: res.data.accessid,
          policy: res.data.policy,
          signature: res.data.signature
        }
        uploadOss(res.data.host, oss, option)
      });
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
    showSaveForm() {
      this.saveForm = {}
      this.uploadFlag = true
    }
  }
}
</script>

<style scoped>
</style>