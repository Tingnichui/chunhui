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
            label="heto :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.contractInfoId"
          />
        </el-form-item>
        <el-form-item
            label="操作类型;1暂停；2补缴；3退课 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.operateType"
          />
        </el-form-item>
        <el-form-item
            label="间隔天数 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.intervalDays"
          />
        </el-form-item>
        <el-form-item
            label="开始时间 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.operateBeginDate"
          />
        </el-form-item>
        <el-form-item
            label="结束时间 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.operateEndDate"
          />
        </el-form-item>
        <el-form-item
            label="操作原因 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.operateReason"
          />
        </el-form-item>
        <el-form-item
            label="操作金额 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.operateAmount"
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
            prop="contractInfoId"
            label="heto"
        />
        <el-table-column
            prop="operateType"
            label="操作类型;1暂停；2补缴；3退课"
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
          v-model:page-size="tableData.pageSize"
          v-model:current-page="tableData.current"
          @current-change="changeCurrentPage"
          @size-change="changePageSize"
      />
    </el-card>
    <el-dialog v-model="saveDialogFlag" center :title="updateFlag ? '修改' : '新增'" width="40%">
      <el-form :model="saveForm" label-position="right" label-width="80px">
        <el-form-item label="heto">
          <el-input v-model="saveForm.contractInfoId"/>
        </el-form-item>
        <el-form-item label="操作类型;1暂停；2补缴；3退课">
          <el-input v-model="saveForm.operateType"/>
        </el-form-item>
        <el-form-item label="间隔天数">
          <el-input v-model="saveForm.intervalDays"/>
        </el-form-item>
        <el-form-item label="开始时间">
          <el-input v-model="saveForm.operateBeginDate"/>
        </el-form-item>
        <el-form-item label="结束时间">
          <el-input v-model="saveForm.operateEndDate"/>
        </el-form-item>
        <el-form-item label="操作原因">
          <el-input v-model="saveForm.operateReason"/>
        </el-form-item>
        <el-form-item label="操作金额">
          <el-input v-model="saveForm.operateAmount"/>
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
import {pageJljsMemberInfoList} from "@/api/jljs-member-info";
import {pageJljsCoachInfoList} from "@/api/jljs-coach-info";

export default {
  data() {
    return {
      tableData: {total: 0},
      saveForm: {},
      updateFlag: false,
      saveDialogFlag: false,
      memberList: [],
      coachList: [],
      searchForm: {
        current: 1,
        size: 15
      }
    }
  },
  mounted() {
    this.research()
    pageJljsMemberInfoList({size: -1}).then(res => {
      this.memberList = res.data.records
    })
    pageJljsCoachInfoList({size: -1}).then(res => {
      this.coachList = res.data.records
    })
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
      this.saveForm = {}
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