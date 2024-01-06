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
            label="权限名称 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.name"
          />
        </el-form-item>
        <el-form-item
            label="权限类型 :"
            prop="name"
            style="width:25%"
        >
          <el-input
              v-model="searchForm.permissionType"
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
            label="权限名称"
            prop="name"
        />
        <el-table-column
            label="权限KEY"
            prop="permissionKey"
        />
        <el-table-column
            label="权限类型"
            prop="permissionType"
        >
          <template #default="scope">
            <div v-for="item in permissionTypeList" :v-if="item.value === scope.row.permissionType">
              {{ item.label }}
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
          :page-sizes="[15,30,50,80,100]"
          :small="true"
          :total="tableData.total"
          layout="total,prev,pager,next,sizes"
          @current-change="changeCurrentPage"
          @size-change="changePageSize"
      />
    </el-card>
    <el-dialog v-model="saveDialogFlag" :title="updateFlag ? '修改' : '新增'" center width="40%">
      <el-form :model="saveForm" label-position="right" label-width="80px">
        <el-form-item label="权限名称">
          <el-input v-model="saveForm.name"/>
        </el-form-item>
        <el-form-item label="权限类型">
          <el-select v-model="saveForm.permissionType" placeholder="请选择权限类型" @change="changePermissionType">
            <el-option
                v-for="item in permissionTypeList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
          <el-form-item label="权限KEY">
            <el-input v-model="saveForm.permissionKey"/>
          </el-form-item>
        </el-form-item>
        <el-form-item label="资源">
          <el-select
              v-model="saveForm.resourceIdList"
              collapse-tags
              multiple
              placeholder="请选择资源"
              style="width: 240px"
          >
            <el-option
                v-for="item in resourceList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
            />
          </el-select>
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
import {deleteById, detail, pagePermissionList, save, update} from "@/api/sys-permission.js";
import {pageMenuList} from "@/api/sys-menu";
import {ElMessageBox} from "element-plus";

export default {
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
      permissionTypeList: [
        {label: '菜单', value: '1'}
      ],
      resourceList: []
    }
  },
  mounted() {
    this.research()
  },
  methods: {
    search() {
      pagePermissionList(this.searchForm).then(res => {
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
    },
    showUpdate(id) {
      this.resetSaveForm()
      this.updateFlag = true
      detail(id).then((res) => {
        this.saveDialogFlag = true
        this.saveForm = res.data
        this.changePermissionType(res.data.permissionType)
      })
    },
    saveData() {
      const promiseFn = this.updateFlag ? update : save;
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
        deleteById(id).then(res => {
          this.research()
          this.$message({
            message: "删除成功",
            type: 'success'
          })
        })
      })
    },
    changePermissionType(value) {
      console.log(value)
      pageMenuList({size: -1}).then(res => {
        this.resourceList = res.data.records
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