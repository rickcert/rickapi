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
      >舔狗语录
      <p style="color: grey; font-size: 10px">语录总数:{{ mycount }}</p>
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
            <el-form-item label="id">
              <el-input v-model="queryParem.id"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="关键字">
              <el-input v-model="queryParem.content"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="分类">
              <el-input v-model="queryParem.classification"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-row>
              <el-button type="primary" @click="submitForm"
                >&nbsp;&nbsp;查询&nbsp;&nbsp;</el-button
              >
              <div style="width: 30px"></div>
              <el-button type="danger" @click="addForm"
                >&nbsp;&nbsp;添加&nbsp;&nbsp;</el-button
              >
            </el-row>
          </el-col>
        </el-row>
      </el-form>
      <el-divider />
      <el-table
        ref="tableRef"
        row-key="width"
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
        element-loading-text="Loading..."
      >
        <el-table-column prop="id" label="id" width="100px" />
        <el-table-column prop="content" label="内容" width="800px" />
        <el-table-column prop="classification" label="分类" width="89px">
          <template #default="scope">
            <el-tag type="warning">{{ scope.row.classification }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-dialog v-model="dialogVisible" title="修改信息" width="45%">
        <el-form ref="userFormRef" :model="userForm" label-width="80px">
          <el-form-item label="分类">
            <el-input v-model="userForm.classification"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <el-input
              v-model="userForm.content"
              type="textarea"
              :rows="7"
            ></el-input>
          </el-form-item>
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="submitFrom">提交</el-button>
          </span>
        </template>
      </el-dialog>
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
} from "@/api/dog.js";
import { onMounted } from "vue";
import { ElMessageBox, ElMessage } from "element-plus";
import { ElNotification } from "element-plus";
const loading = ref(true);
const mycount = ref(1);
const queryParem = ref({
  id: "",
  content: "",
  classification: "",
  size: 5,
  page: 0,
  delFlag: "0",
});

const userForm = ref({
  content: "",
  classification: "",
  id: "",

  size: 5,
  page: 0,
  delFlag: "0",
});

const centen = ref();
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
      mycount.value = res.data.totalElements;
      total.value = res.data.totalElements;
      queryParem.value.size = res.data.size;
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
};

const submitForm = function () {
  getList();
};

const resetForm = function () {
  // indexvue();
  tableData.value = [];
  total.value = 0;
  queryParem.value.size = 5;
  queryParem.value.id = "";
  queryParem.value.content = "";
  queryParem.value.classification = "";
  getList();
};
const addForm = function () {
  if (
    queryParem.value.content == "" ||
    queryParem.value.classification === null
  ) {
    ElNotification({
      title: "错误",
      message: "请输入句子信息!",
      type: "Error",
    });
  } else {
    queryParem.value.delFlag = "0";
    addUser(queryParem.value).then((res) => {
      console.log(res);
    });
    resetForm();
  }
  getList();
};

const handleEdit = function (index, row) {
  console.log(index);
  console.log("------");
  console.log(row);
  getByUserId(row.id).then((res) => {
    userForm.value = res.data;
    dialogVisible.value = true;
  });
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
  getByUserId(row.id).then((res) => {
    userForm.value = res.data;
  });
  ElMessageBox.confirm("您确定要删除此条信息吗？", "Warning", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      userForm.value.delFlag = "2";
      updateUser(userForm.value).then((res) => {
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
.el-header {
  margin-top: 15px;
}
.el-main {
}
.el-footer {
}
.el-pagination {
  justify-content: center;
}
body {
  margin: 0;
}
.yyan {
  float: left;
}
</style>
