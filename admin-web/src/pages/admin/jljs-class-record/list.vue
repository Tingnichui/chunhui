<template>
  <div class="page">
    <el-card>
      <el-form
          :label-width="80"
          :model="searchForm"
          class="query-form"
          inline
      >
        <el-form-item
            label="教练 :"
            prop="name"
            style="width:25%"
        >
          <el-select v-model="searchForm.coachId" clearable filterable placeholder="请选择教练">
            <el-option
                v-for="item in coachList"
                :key="item.id"
                :label="item.coachName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
            label="会员 :"
            prop="name"
            style="width:25%"
        >
          <el-select v-model="searchForm.memberId" clearable filterable placeholder="请选择会员">
            <el-option
                v-for="item in memberList"
                :key="item.id"
                :label="item.memberName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
            label="日期 :"
            prop="name"
            style="width:25%"
        >
          <el-date-picker
              v-model="searchForm.dateRange"
              :shortcuts="shortcutsrange"
              end-placeholder="结束时间"
              range-separator="到"
              start-placeholder="开始时间"
              type="daterange"
              unlink-panels
              value-format="YYYY-MM-DD"
          />
        </el-form-item>
        <div class="action-groups">
          <el-button plain type="primary" @click="search">查询</el-button>
          <el-button plain type="primary" @click="research">重置</el-button>
        </div>
      </el-form>
    </el-card>
    <el-card>
      <template #header>
        <div class="card-header">
          <el-space><span>菜单管理</span></el-space>
          <el-space>
            <el-button plain type="primary" @click="showSaveForm">新增</el-button>
          </el-space>
        </div>
      </template>
      <el-table
          :data="tableData.records"
          :highlight-current-row="true"
          :stripe="true"
          empty-text="No Data"
          height="500"
          row-key="id"
          size="default"
          style="width: 100%"
      >
        <el-table-column
            label="教练"
            prop="coachId"
        >
          <template #default="scope">
            <div v-for="item in coachList">
              <span v-if="item.id === scope.row.coachId"> {{ item.coachName }} </span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            label="会员"
            prop="memberId"
        >
          <template #default="scope">
            <div v-for="item in memberList">
              <span v-if="item.id === scope.row.memberId">{{ item.memberName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            label="开始时间"
            prop="classBeginTime"
        />
        <el-table-column
            label="结束时间"
            prop="classEndTime"
        />
        <el-table-column
            label="课程备注"
            prop="classRemark"
        />
        <el-table-column
            :width="160"
            align="center"
            fixed="right"
            label="操作"
            prop="act"
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
    <el-dialog v-model="saveDialogFlag" :title="updateFlag ? '修改' : '新增'" center width="40%">
      <el-form :model="saveForm" :rules="rules" label-position="right" label-width="80px">
        <el-form-item label="教练">
          <el-select v-model="saveForm.coachId" filterable placeholder="请选择教练">
            <el-option
                v-for="item in coachList"
                :key="item.id"
                :label="item.coachName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
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
        <el-form-item
            label="上课日期"
            prop="classBeginTime"
        >
          <el-date-picker
              v-model="saveForm.classDate"
              :disabled-date="disabledDate"
              :shortcuts="shortcuts"
              format="YYYY-MM-DD"
              placeholder="请选择日期"
              type="date"
              value-format="YYYY-MM-DD"
          />
        </el-form-item>
        <el-form-item label="开始时间">
          <el-time-select
              v-model="saveForm.beginTime"
              :max-time="saveForm.endTime"
              class="mr-4"
              end="24:00"
              format="HH:mm"
              placeholder="请选择结束时间"
              start="06:00"
              step="00:15"
          />

        </el-form-item>
        <el-form-item label="结束时间">
          <el-time-select
              v-model="saveForm.endTime"
              :min-time="saveForm.beginTime"
              end="24:00"
              format="HH:mm"
              placeholder="请选择开始时间"
              start="06:00"
              step="00:15"
          />
        </el-form-item>
        <el-form-item label="课程备注">
          <el-input v-model="saveForm.classRemark"/>
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
  deleteJljsClassRecordById,
  getJljsClassRecordDetail,
  pageJljsClassRecordList,
  saveJljsClassRecord,
  updateJljsClassRecord
} from "@/api/jljs-class-record.js";
import {
  formatDate,
  getCurrentMonthFirstDay,
  getCurrentWeekFirstDay,
  lastMonthFirst,
  lastMonthLast
} from "@/util/DateUtil";
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
      },
      rules: [],
      shortcuts: [
        {
          text: '今天',
          value: new Date(),
        },
        {
          text: '昨天',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24)
            return date
          },
        },
        {
          text: '前天',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 3)
            return date
          },
        },
        {
          text: '一周前',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
            return date
          },
        },
      ],
      shortcutsrange: [
        {
          text: '今天',
          value: () => {
            const start = new Date()
            const end = new Date()
            return [start, end]
          },
        },
        {
          text: '本周',
          value: () => {
            const end = new Date()
            const start = getCurrentWeekFirstDay(end)
            return [start, end]
          },
        },
        {
          text: '本月',
          value: () => {
            const end = new Date()
            const start = getCurrentMonthFirstDay(end)
            return [start, end]
          },
        },
        {
          text: '上月',
          value: () => {
            const start = lastMonthFirst()
            const end = lastMonthLast()
            return [start, end]
          },
        },
      ]
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
      if (this.searchForm.dateRange) {
        this.searchForm.classBeginTime = this.searchForm.dateRange[0]
        this.searchForm.classEndTime = this.searchForm.dateRange[1]
      }
      pageJljsClassRecordList(this.searchForm).then(res => {
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
      const date = new Date()
      this.saveForm.classDate = formatDate(date, 'yyyy-MM-dd')
      this.saveForm.beginTime = formatDate(date, 'HH:mm')
      this.saveForm.endTime = formatDate(new Date(date.getTime() + 60 * 60 * 1000), 'HH:mm')
      this.saveDialogFlag = true
      this.updateFlag = false
    },
    showUpdate(id) {
      this.resetSaveForm()
      this.updateFlag = true
      getJljsClassRecordDetail(id).then((res) => {
        this.saveDialogFlag = true
        this.saveForm = res.data
        this.saveForm.classDate = this.saveForm.classBeginTime.substring(0, 10)
        this.saveForm.beginTime = this.saveForm.classBeginTime.substring(11, 16)
        this.saveForm.endTime = this.saveForm.classEndTime.substring(11, 16)
      })
    },
    saveData() {
      const promiseFn = this.updateFlag ? updateJljsClassRecord : saveJljsClassRecord;
      this.saveForm.classBeginTime = this.saveForm.classDate + ' ' + this.saveForm.beginTime + ':00'
      this.saveForm.classEndTime = this.saveForm.classDate + ' ' + this.saveForm.endTime + ':00'
      promiseFn(this.saveForm).then(res => {
        this.$message({
          message: res.message,
          type: 'success',
        })
        if (this.updateFlag) {
          this.search()
        } else {
          this.research()
        }
        this.saveDialogFlag = false
        this.updateFlag = false
      })
    },
    deleteInfo(id) {
      ElMessageBox.confirm('确定删除？').then(() => {
        deleteJljsClassRecordById(id).then(res => {
          this.research()
          this.$message({
            message: "删除成功",
            type: 'success'
          })
        })
      })
    },
    disabledDate(day) {
      return day.getTime() > Date.now()
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