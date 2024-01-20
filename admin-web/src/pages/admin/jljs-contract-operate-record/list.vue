<template>
  <div class="page">
<!--    <el-card>-->
<!--      <el-form-->
<!--          class="query-form"-->
<!--          inline-->
<!--          :label-width="80"-->
<!--          :model="searchForm"-->
<!--      >-->
<!--        <div class="action-groups">-->
<!--        </div>-->
<!--      </el-form>-->
<!--    </el-card>-->
    <el-card>
      <template #header>
        <div class="card-header">
          <el-space><span>{{ contractId }}</span></el-space>
          <el-space>
            <el-button type="primary" plain @click="search">查询</el-button>
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
            prop="contractOperateType"
            label="合同操作类型"
        />
        <el-table-column
            prop="intervalDays"
            label="间隔天数"
        />
        <el-table-column
            prop="operateBeginDate"
            label="开始时间"
        />
        <el-table-column
            prop="operateEndDate"
            label="结束时间"
        />
        <el-table-column
            prop="operateReason"
            label="操作原因"
        />
        <el-table-column
            prop="operateAmount"
            label="操作金额"
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
          :default-page-size="15"
          v-model:page-size="tableData.pageSize"
          v-model:current-page="tableData.current"
          @current-change="changeCurrentPage"
          @size-change="changePageSize"
      />
    </el-card>
    <el-dialog v-model="saveDialogFlag" center :title="updateFlag ? '修改' : '新增'" width="40%">
      <el-form :model="saveForm" label-position="right" label-width="80px">
        <el-form-item label="合同操作类型">
          <el-select v-model="saveForm.contractOperateType" filterable placeholder="请选择合同操作">
            <el-option
                v-for="item in contractOperateTypeList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <div>
          <!-- 开卡 -->
          <div v-if="saveForm.contractOperateType === '1'">
            <el-form-item label="开卡时间">
              <el-date-picker
                  v-model="saveForm.operateBeginDate"
                  type="date"
                  value-format="YYYY-MM-DD"
                  placeholder="请选择开卡时间"
              />
            </el-form-item>
          </div>
          <!-- 停课 -->
          <div v-if="saveForm.contractOperateType === '2'">
            <el-form-item label="开始时间">
              <el-date-picker
                  v-model="saveForm.operateBeginDate"
                  type="date"
                  value-format="YYYY-MM-DD"
                  placeholder="请选择开始时间"
              />
            </el-form-item>
            <el-form-item label="结束时间">
              <el-date-picker
                  v-model="saveForm.operateEndDate"
                  type="date"
                  value-format="YYYY-MM-DD"
                  placeholder="请选择结束时间"
              />
            </el-form-item>
          </div>
          <!-- 退课 -->
          <div v-if="saveForm.contractOperateType === '3'">
            <el-form-item label="操作金额">
              <el-input-number v-model="saveForm.operateAmount"/>
            </el-form-item>
          </div>
          <!-- 补缴 -->
          <div v-if="saveForm.contractOperateType === '4'">
            <el-form-item label="操作金额">
              <el-input-number v-model="saveForm.operateAmount"/>
            </el-form-item>
          </div>
        </div>
        <el-form-item label="操作原因">
          <el-input v-model="saveForm.operateReason"/>
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
  </div>
</template>

<script>
import {
  deleteJljsContractOperateRecordById,
  getJljsContractOperateRecordDetail,
  pageJljsContractOperateRecordList,
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
    }
  }
}
</script>


<style>
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