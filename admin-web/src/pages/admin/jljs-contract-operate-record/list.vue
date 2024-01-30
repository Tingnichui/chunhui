<template>
  <div style="height: 50%;">
    <el-card>
      <template #header>
        <div class="card-header">
          <el-space><span>{{ contractId }}</span></el-space>
          <el-space>
            <el-button plain type="primary" @click="search">查询</el-button>
            <!--            <el-button type="primary" plain @click="showSaveForm">新增</el-button>-->
          </el-space>
        </div>
      </template>
      <el-table
          :data="tableData.records"
          :highlight-current-row="true"
          :stripe="true"
          empty-text="No Data"
          height="300"
          row-key="id"
          size="default"
      >
        <el-table-column
            label="合同操作类型"
            prop="contractOperateType"
        >
          <template #default="scope">
            <div v-for="item in contractOperateTypeList">
              <span v-if="item.value === scope.row.contractOperateType"> {{ item.label }} </span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            label="开始时间"
            prop="operateBeginDate"
        />
        <el-table-column
            label="结束时间"
            prop="operateEndDate"
        />
        <el-table-column
            label="间隔天数"
            prop="intervalDays"
        />
        <el-table-column
            label="操作金额"
            prop="operateAmount"
        />
        <el-table-column
            label="操作原因"
            prop="operateReason"
        />
        <el-table-column
            label="操作状态"
            prop="operateReason"
        >
          <template #default="scope">
            <div v-for="item in operateStatusList">
              <span v-if="item.value === scope.row.operateStatus"> {{ item.label }} </span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            :width="160"
            align="center"
            fixed="right"
            label="操作"
            prop="act"
        >
          <template #default="scope">
            <el-space>
              <el-button v-if="scope.row.operateStatus !== '2'" link type="danger" @click="revoke(scope.row.id)">撤销
              </el-button>
              <!--              <el-button link type="success" @click="showUpdate(scope.row.id)">编辑</el-button>-->
              <!--              <el-button link type="danger" @click="deleteInfo(scope.row.id)">删除</el-button>-->
            </el-space>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-card>
      <el-pagination
          v-model:current-page="tableData.current"
          v-model:page-size="tableData.pageSize"
          :background="true"
          :default-page-size="15"
          :page-sizes="[15,30,50,80,100]"
          :small="true"
          :total="tableData.total"
          layout="total,prev,pager,next,sizes"
          @current-change="changeCurrentPage"
          @size-change="changePageSize"
      />
    </el-card>
  </div>
</template>

<script>
import {
  deleteJljsContractOperateRecordById,
  getJljsContractOperateRecordDetail,
  pageJljsContractOperateRecordList,
  revokeJljsContractOperateRecordById,
  saveJljsContractOperateRecord,
  updateJljsContractOperateRecord
} from "@/api/jljs-contract-operate-record.js";
import {ElMessageBox} from "element-plus";

export default {
  name: 'contractOperateRecord',
  props: {
    contractId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tableData: {total: 0},
      saveForm: {},
      updateFlag: false,
      saveDialogFlag: false,
      searchForm: {
        current: 1,
        size: 15
      },
      contractInfoId: this.contractId,
      contractOperateTypeList: [
        {label: '开卡', value: '1'},
        {label: '暂停', value: '2'},
        {label: '退课', value: '3'},
        {label: '补缴', value: '4'},
      ],
      operateStatusList: [
        {label: '操作成功', value: '1'},
        {label: '已撤销', value: '2'},
      ]
    }
  },
  mounted() {
    this.research()
  },
  methods: {
    search() {
      pageJljsContractOperateRecordList(this.searchForm).then(res => {
            this.tableData = res.data
          }
      )
    },
    research() {
      this.searchForm = {
        current: 1,
        size: 15,
        contractInfoId: this.contractInfoId
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
      this.saveForm = {
        contractInfoId: this.contractInfoId
      }
    },
    showSaveForm() {
      this.resetSaveForm()
      this.saveDialogFlag = true
      this.updateFlag = false
    },
    showUpdate(id) {
      this.resetSaveForm()
      this.updateFlag = true
      getJljsContractOperateRecordDetail(id).then((res) => {
        this.saveDialogFlag = true
        this.saveForm = res.data
      })
    },
    saveData() {
      const promiseFn = this.updateFlag ? updateJljsContractOperateRecord : saveJljsContractOperateRecord;
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
    deleteInfo(id) {
      ElMessageBox.confirm('确定删除？').then(() => {
        deleteJljsContractOperateRecordById(id).then(res => {
          this.research()
          this.$message({
            message: "删除成功",
            type: 'success'
          })
        })
      })
    },
    revoke(id) {
      ElMessageBox.confirm('确定撤销该操作？').then(() => {
        revokeJljsContractOperateRecordById(id).then(res => {
          this.research()
          // this.$message({
          //   message: res.message,
          //   type: 'success'
          // })
        })
      })
    }
  }
}
</script>


<style></style>