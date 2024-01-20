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
                            label="名称 :"
                            prop="name"
                            style="width:25%"
                    >
                        <el-input
                                v-model="searchForm.courseName"
                        />
                    </el-form-item>
                    <el-form-item
                            label="价格 :"
                            prop="name"
                            style="width:25%"
                    >
                        <el-input
                                v-model="searchForm.coursePrice"
                        />
                    </el-form-item>
                    <el-form-item
                            label="描述 :"
                            prop="name"
                            style="width:25%"
                    >
                        <el-input
                                v-model="searchForm.courseDescribe"
                        />
                    </el-form-item>
                    <el-form-item
                            label="有效天数 :"
                            prop="name"
                            style="width:25%"
                    >
                        <el-input
                                v-model="searchForm.courseValidDays"
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
                            prop="courseName"
                            label="名称"
                    />
                    <el-table-column
                            prop="coursePrice"
                            label="价格"
                    />
                    <el-table-column
                            prop="courseDescribe"
                            label="描述"
                    />
                    <el-table-column
                            prop="courseValidDays"
                            label="有效天数"
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
                    <el-form-item label="名称">
                        <el-input v-model="saveForm.courseName"/>
                    </el-form-item>
                    <el-form-item label="价格">
                        <el-input-number v-model="saveForm.coursePrice"/>
                    </el-form-item>
                    <el-form-item label="描述">
                        <el-input v-model="saveForm.courseDescribe"/>
                    </el-form-item>
                    <el-form-item label="有效天数">
                        <el-input v-model="saveForm.courseValidDays"/>
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
    import {deleteJljsCourseInfoById, getJljsCourseInfoDetail, pageJljsCourseInfoList, saveJljsCourseInfo, updateJljsCourseInfo} from "@/api/jljs-course-info.js";
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
                }
            }
        },
        mounted() {
            this.research()
        },
        methods: {
            search() {
                pageJljsCourseInfoList(this.searchForm).then(res => {
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
                getJljsCourseInfoDetail(id).then((res) => {
                    this.saveDialogFlag = true
                    this.saveForm = res.data
                })
            },
            saveData() {
                const promiseFn = this.updateFlag ? updateJljsCourseInfo : saveJljsCourseInfo;
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
                    deleteJljsCourseInfoById(id).then(res => {
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