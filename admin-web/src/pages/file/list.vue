<template>
  <div>
    <div style="padding: 20px">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="资源标题">
          <el-input v-model="searchForm.title" clearable placeholder="请输入文件名称"/>
        </el-form-item>
        <!--        <el-form-item label="上传时间">-->
        <!--          <el-date-picker-->
        <!--              v-model="searchForm.createDate"-->
        <!--              end-placeholder="结束时间"-->
        <!--              range-separator="至"-->
        <!--              start-placeholder="开始时间"-->
        <!--              type="daterange"-->
        <!--          />-->
        <!--        </el-form-item>-->
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
          <el-button type="primary" @click="reset">重置</el-button>
          <el-button type="primary" @click="showSaveForm">添加</el-button>
        </el-form-item>
      </el-form>
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
    <div style="height: 100%;padding: 20px">
      <el-table :data="tableData.records" height="500px">
        <el-table-column label="标题" prop="title" width="180"/>
        <el-table-column label="描述" prop="content" width="180"/>
        <el-table-column label="创建时间" prop="createTime"/>
      </el-table>
      <el-pagination
          v-model:current-page="tableData.current"
          v-model:page-size="tableData.size"
          :background="background"
          :disabled="disabled"
          :page-sizes="[15, 30, 60, 80]"
          :small="small"
          :total="tableData.total"
          layout="sizes, prev, pager, next"
          @size-change="changePageSize"
          @current-change="changeCurrentPage"
      />
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