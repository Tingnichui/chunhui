<template>
  <div>
    <div style="height: 100px;margin-bottom: 20px;">
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
    </div>
    <!--    <div style="height: 100%">-->
    <!--      <el-scrollbar>-->
    <!--        <el-table :data="tableData">-->
    <!--          <el-table-column prop="date" label="Date" width="140"/>-->
    <!--          <el-table-column prop="name" label="Name" width="120"/>-->
    <!--          <el-table-column prop="address" label="Address"/>-->
    <!--        </el-table>-->
    <!--      </el-scrollbar>-->
    <!--    </div>-->
  </div>
</template>

<script>

import {getUploadAccess, uploadOss} from "@/api/sys-user";

export default {
  name: "index",
  data() {
    return {
      menuList: [],
      fileList: [],
      tableData: Array.from({length: 20}, () => Object.assign({}, {
        date: '2016-05-02',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
      })),
    }
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
    }
  }
}
</script>

<style scoped>
</style>