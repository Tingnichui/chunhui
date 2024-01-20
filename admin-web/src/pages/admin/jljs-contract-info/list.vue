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
            label="会员 :"
            prop="name"
            style="width:25%"
        >
          <el-select v-model="searchForm.memberId" filterable placeholder="请选择会员">
            <el-option
                v-for="item in memberList"
                :key="item.id"
                :label="item.memberName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
            label="课程 :"
            prop="name"
            style="width:25%"
        >
          <el-select v-model="searchForm.courseInfoId" placeholder="请选择课程" filterable @change="changeCourse">
            <el-option
                v-for="item in courseList"
                :key="item.id"
                :label="item.courseName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
            label="开单教练 :"
            prop="name"
            style="width:25%"
        >
          <el-select v-model="searchForm.belongCoachId" filterable placeholder="请选择教练">
            <el-option
                v-for="item in coachList"
                :key="item.id"
                :label="item.coachName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
            label="合同状态 :"
            prop="name"
            style="width:25%"
        >
          <el-select v-model="searchForm.contractStatus" filterable placeholder="请选择教练">
            <el-option
                v-for="item in contractStatusList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <!--        <el-form-item-->
        <!--            label="使用开始日期 :"-->
        <!--            prop="name"-->
        <!--            style="width:25%"-->
        <!--        >-->
        <!--          <el-input-->
        <!--              v-model="searchForm.useBeginDate"-->
        <!--          />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item-->
        <!--            label="使用结束日期 :"-->
        <!--            prop="name"-->
        <!--            style="width:25%"-->
        <!--        >-->
        <!--          <el-input-->
        <!--              v-model="searchForm.useEndDate"-->
        <!--          />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item-->
        <!--            label="购买日期 :"-->
        <!--            prop="name"-->
        <!--            style="width:25%"-->
        <!--        >-->
        <!--          <el-input-->
        <!--              v-model="searchForm.buyTime"-->
        <!--          />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item-->
        <!--            label="合同备注 :"-->
        <!--            prop="name"-->
        <!--            style="width:25%"-->
        <!--        >-->
        <!--          <el-input-->
        <!--              v-model="searchForm.contractRemark"-->
        <!--          />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item-->
        <!--            label="实际收取金额 :"-->
        <!--            prop="name"-->
        <!--            style="width:25%"-->
        <!--        >-->
        <!--          <el-input-->
        <!--              v-model="searchForm.actualChargeAmount"-->
        <!--          />-->
        <!--        </el-form-item>-->
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
            prop="memberId"
            label="会员"
        >
          <template #default="scope">
            <div v-for="item in memberList">
              <span v-if="item.id === scope.row.memberId">{{ item.memberName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            prop="courseInfoId"
            label="课程"
        >
          <template #default="scope">
            <div v-for="item in courseList">
              <span v-if="item.id === scope.row.courseInfoId"> {{ item.courseName }} </span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            prop="belongCoachId"
            label="开单教练"
        >
          <template #default="scope">
            <div v-for="item in coachList">
              <span v-if="item.id === scope.row.belongCoachId"> {{ item.coachName }} </span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            prop="contractAmount"
            label="合同金额"
        />
        <el-table-column
            prop="useBeginDate"
            label="使用开始日期"
        />
        <el-table-column
            prop="useEndDate"
            label="使用结束日期"
        />
        <el-table-column
            prop="buyTime"
            label="购买日期"
        />
        <el-table-column
            prop="contractRemark"
            label="合同备注"
        />
        <el-table-column
            prop="actualChargeAmount"
            label="实际收取金额"
        />
        <el-table-column
            prop="actualChargeAmount"
            label="合同状态"
        >
          <template #default="scope">
            <div v-for="item in contractStatusList">
              <span v-if="item.value === scope.row.contractStatus"> {{ item.label }} </span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            prop="act"
            label="操作"
            :width="160"
            align="center"
            fixed="right"
        >
          <template #default="scope">
            <el-space>
              <div>
                <el-button v-if="scope.row.contractStatus === '1'" link type="primary" @click="showkaika(scope.row.id)">开卡</el-button>
                <el-button v-else link type="primary" @click="operateDialogFlag = true;contractId = scope.row.id">操作</el-button>
              </div>
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
    <el-dialog v-model="operateDialogFlag">
      <contractOperateRecord :contract-id="contractId"></contractOperateRecord>
    </el-dialog>
    <el-dialog v-model="kaikaDialogFlag" center title="开卡" width="40%">
      <el-form :model="kaikaForm" label-position="right" label-width="80px">
        <el-form-item label="开卡时间">
          <el-date-picker
              v-model="kaikaForm.operateBeginDate"
              type="date"
              placeholder="请选择开卡时间"
              value-format="YYYY-MM-DD"
          />
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="doKaika">
              确定
            </el-button>
          </span>
      </template>
    </el-dialog>
    <el-dialog v-model="saveDialogFlag" center :title="updateFlag ? '修改' : '新增'" width="40%">
      <el-form :model="saveForm" label-position="right" label-width="80px">
        <el-form-item label="会员">
          <el-select v-model="saveForm.memberId" filterable placeholder="请选择会员">
            <el-option
                v-for="item in memberList"
                :key="item.id"
                :label="item.memberName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="课程">
          <el-select v-model="saveForm.courseInfoId" filterable placeholder="请选择课程" @change="changeCourse">
            <el-option
                v-for="item in courseList"
                :key="item.id"
                :label="item.courseName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="开单教练">
          <el-select v-model="saveForm.belongCoachId" filterable placeholder="请选择教练">
            <el-option
                v-for="item in coachList"
                :key="item.id"
                :label="item.coachName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="合同金额">
          <el-input-number v-model="saveForm.contractAmount"/>
        </el-form-item>
        <el-form-item label="实际收取">
          <el-input-number v-model="saveForm.actualChargeAmount" type="number"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="saveForm.contractRemark"/>
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
  deleteJljsContractInfoById,
  getJljsContractInfoDetail,
  pageJljsContractInfoList,
  saveJljsContractInfo,
  updateJljsContractInfo
} from "@/api/jljs-contract-info.js";
import {ElMessageBox} from "element-plus";
import {pageJljsCoachInfoList} from "@/api/jljs-coach-info";
import {pageJljsCourseInfoList} from "@/api/jljs-course-info";
import {pageJljsMemberInfoList} from "@/api/jljs-member-info";
import contractOperateRecord from '@/pages/admin/jljs-contract-operate-record/list.vue'
import {saveJljsContractOperateRecord, updateJljsContractOperateRecord} from "@/api/jljs-contract-operate-record";

export default {
  components: {
    contractOperateRecord
  },
  data() {
    return {
      tableData: {total: 0},
      saveForm: {},
      updateFlag: false,
      saveDialogFlag: false,
      operateDialogFlag: false,
      kaikaDialogFlag: false,
      kaikaForm: {},
      memberList: [],
      courseList: [],
      coachList: [],
      contractId: '',
      contractStatusList: [
        {label: '未生效', value: '1'},
        {label: '使用中', value: '2'},
        {label: '已完成', value: '3'},
        {label: '暂停', value: '4'},
        {label: '终止', value: '5'},
      ],
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
    pageJljsCourseInfoList({size: -1}).then(res => {
      this.courseList = res.data.records
    })
    pageJljsCoachInfoList({size: -1}).then(res => {
      this.coachList = res.data.records
    })
  },
  methods: {
    search() {
      pageJljsContractInfoList(this.searchForm).then(res => {
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
      getJljsContractInfoDetail(id).then((res) => {
        this.saveDialogFlag = true
        this.saveForm = res.data
      })
    },
    saveData() {
      const promiseFn = this.updateFlag ? updateJljsContractInfo : saveJljsContractInfo;
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
        deleteJljsContractInfoById(id).then(res => {
          this.research()
          this.$message({
            message: "删除成功",
            type: 'success'
          })
        })
      })
    },
    changeCourse(value) {
      this.saveForm.contractAmount = this.courseList.find(v => v.id === value).coursePrice
    },
    showkaika(id) {
      this.kaikaForm = {
        contractInfoId: id,
        contractOperateType: '1'
      }
      this.kaikaDialogFlag = true
    },
    doKaika() {
      saveJljsContractOperateRecord(this.kaikaForm).then(res => {
        this.$message({
          message: res.message,
          type: 'success',
        })
        this.kaikaDialogFlag = false
        this.research()
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