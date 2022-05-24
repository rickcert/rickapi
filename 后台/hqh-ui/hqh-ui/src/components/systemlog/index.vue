<template>
  <el-container
    class="dog"
    style="position: relative; top: 15px; height: 670px"
  >
    <el-header
      style="
        width: 100%;
        left: 10px;
        top: 40px;
        float: left;
        border-top: 15px solid rgba(0, 0, 0, 0);
      "
      >系统日志
      <p style="color: grey; font-size: 10px">系统日志总数:{{ mycount }}</p>
    </el-header>

    <el-main style="width: 100%; overflow: visible">
      <el-form
        :model="queryParem"
        label-width="100px"
        class="demo-ruleForm"
        size="mini"
        :inline="true"
      >
        <el-row>
          <el-col span="6">
            <el-form-item label="操作模块">
              <el-input v-model="queryParem.title"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="请求人员">
              <el-input v-model="queryParem.operName"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="api方法">
              <el-input v-model="queryParem.requestMethod"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="6">
            <el-form-item label="请求url">
              <el-input v-model="queryParem.operUrl"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="操作状态">
              <el-input v-model="queryParem.status"></el-input>
            </el-form-item>
          </el-col>
          <div style="width: 65px"></div>
          <el-col span="6">
            <el-row>
              <div style="width: 40px"></div>
              <el-button type="primary" @click="submitForm"
                >&nbsp;&nbsp;查询&nbsp;&nbsp;</el-button
              >
              <div style="width: 30px"></div>
              <el-button type="warning" @click="resetForm"
                >&nbsp;&nbsp;重置&nbsp;&nbsp;</el-button
              >
            </el-row>
          </el-col>
        </el-row>
      </el-form>
      <el-divider />
      <el-table
        ref="tableRef"
        row-key="username"
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
        element-loading-text="Loading..."
      >
        <el-table-column prop="operId" label="id" width="100" />
        <el-table-column prop="title" label="操作模块" />
        <el-table-column prop="businessType" label="业务类型" />
        <el-table-column prop="requestMethod" label="api方法" width="100">
          <template #default="scope">
            <el-tag>{{ scope.row.requestMethod }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="method" label="请求方式" width="140" />
        <el-table-column prop="operName" label="请求人员" />
        <el-table-column prop="operIp" label="请求ip" />
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <el-tag type="success">{{ scope.row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="errormsg" label="错误信息" />
        <el-table-column prop="opertime" label="请求时间" width="150" />

        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer style="width: 200">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="queryParem.size"
        @current-change="changePage"
        style="width: 100%"
      />
    </el-footer>
  </el-container>
</template>

<script setup>
import { ref } from "vue";
import {
  listUser,
  addUser,
  getByUserId,
  updateUser,
  deleteUser,
} from "@/api/log.js";
import { onMounted } from "vue";
import { ElMessageBox, ElMessage } from "element-plus";
import { ElNotification } from "element-plus";
const loading = ref(true);
const mycount = ref(1);
const queryParem = ref({
  operId: "",
  size: 5,
  page: 0,
  operId: "",
  title: "",
  businessType: "用户操作",
  method: "",
  requestMethod: "",
  operName: "",
  operUrl: "",
  operIp: "",
  status: "",
  errormsg: "",
  opertime: "",
});

const userForm = ref({
  operId: "",
  size: 5,
  page: 0,
  operId: "",
  title: "",
  businessType: "",
  method: "",
  requestMethod: "",
  operName: "",
  operUrl: "",
  operIp: "",
  status: "",
  errormsg: "",
  opertime: "",
});

const tableData = ref([]);
const total = ref(0);
const dialogVisible = ref(false);
const getList = function () {
  listUser(queryParem.value).then((res) => {
    if (res.status == 202) {
      ElNotification({
        title: "错误,登录已失效",
        message: "请重新登录!",
        type: "Error",
      });
    } else {
      tableData.value = res.data.content;
      total.value = res.data.totalElements;
      queryParem.value.size = res.data.size;
      mycount.value = res.data.totalElements;
    }
  });
};
onMounted(() => {
  getList();
  loading.value = false;
});
const changePage = async function (current) {
  queryParem.value.page = current - 1;
  getList();
  // loading.value = false;
};

const submitForm = function () {
  getList();
};

const resetForm = function () {
  // indexvue();
  tableData.value = [];
  total.value = 0;
  queryParem.value.size = 4;
  queryParem.value.title = "";
  queryParem.value.businessType = "";
  queryParem.value.requestMethod = "";
  queryParem.value.operName = "";
  queryParem.value.operUrl = "";
  queryParem.value.status = "";
  getList();
};

const submitFrom = function () {
  updateUser(userForm.value).then((res) => {
    if (res.status === 200) {
      dialogVisible.value = false;
    }
    // 更新列表
    getList();
  });
};
const handleDelete = function (index, row) {
  getByUserId(row.operId).then((res) => {
    userForm.value = res.data;
  });
  ElMessageBox.confirm("您确定要删除此条信息吗？", "Warning", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      deleteUser(userForm.value.operId).then((res) => {
        if (res.status === 200) {
          // 更新列表
          getList();
          ElNotification({
            title: "Success",
            message: "删除成功!",
            type: "Success",
          });
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消删除",
      });
      ElNotification({
        title: "cancel",
        message: "取消删除!",
        type: "Success",
      });
    });
};
</script>
<style scoped>
.el-container {
  background-color: rgba(255, 255, 255, 0.692);
  background-repeat: no-repeat;
  background-image: none;
  box-shadow: rgba(0, 0, 0, 0.6) 5px 5px 10px 5px;
  height: 800px;
}

.el-pagination {
  justify-content: center;
}
body {
  margin: 0;
}
</style>
