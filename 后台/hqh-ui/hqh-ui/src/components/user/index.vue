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
      >用户列表
      <p style="color: grey; font-size: 10px">用户列表内容已更新</p>
      <!-- <el-divider
    /> -->
    </el-header>

    <el-main style="width: 100%">
      <el-form
        :model="queryParem"
        label-width="100px"
        class="demo-ruleForm"
        size="mini"
        :inline="true"
      >
        <el-row>
          <el-col span="6">
            <el-form-item label="账号">
              <el-input v-model="queryParem.userName"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="姓名">
              <el-input v-model="queryParem.nickName"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="电话号码">
              <el-input v-model="queryParem.phonenumber"></el-input>
            </el-form-item>
          </el-col>
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
        <el-row>
          <el-col span="6">
            <el-form-item label="专业">
              <el-input v-model="queryParem.professional"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="班级">
              <el-input v-model="queryParem.sclass"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="邮箱">
              <el-input v-model="queryParem.email"></el-input>
            </el-form-item>
          </el-col>
          <div style="width: 40px"></div>
          <el-col span="6">
            <el-button type="danger" @click="addForm"
              >&nbsp;&nbsp;添加&nbsp;&nbsp;</el-button
            >
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
        <el-table-column prop="userId" label="id" width="100" />
        <el-table-column prop="userName" label="账号" />
        <el-table-column prop="nickName" label="姓名" />
        <el-table-column prop="professional" label="专业" width="100" />
        <el-table-column prop="sclass" label="班级" width="100" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="phonenumber" label="电话号码" />
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
      <el-dialog v-model="dialogVisible" title="修改用户" width="30%">
        <el-form ref="userFormRef" :model="userForm" label-width="120px">
          <el-form-item label="账号">
            <el-input v-model="userForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="userForm.nickName"></el-input>
          </el-form-item>
          <el-form-item label="专业">
            <el-input v-model="userForm.professional"></el-input>
          </el-form-item>
          <el-form-item label="班级">
            <el-input v-model="userForm.sclass"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="userForm.email"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="userForm.phonenumber"></el-input>
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

    <!-- <h1>这是{{ currentPage }}</h1> -->
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
} from "@/api/user.js";
import { onMounted } from "vue";
import { ElMessageBox, ElMessage } from "element-plus";
import { ElNotification } from "element-plus";
const loading = ref(true);
const queryParem = ref({
  userId: "",
  size: 7,
  page: 0,
  userName: "",
  nickName: "",
  sclass: "",
  phonenumber: "",
  professional: "",
  email: "",
  delFlag: "0",
});

const userForm = ref({
  size: 7,
  page: 0,
  userName: "",
  nickName: "",
  sclass: "",
  phonenumber: "",
  professional: "",
  email: "",
  delFlag: "0",
});

const tableData = ref([]);
const total = ref(0);
const dialogVisible = ref(false);
const getList = function () {
  listUser(queryParem.value).then((res) => {
    if (res.status == 202) {
      ElNotification({
        title: "错误",
        message: "请输入重新登录!",
        type: "Error",
      });
    } else {
      tableData.value = res.data.content;
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
  queryParem.value.userName = "";
  queryParem.value.nickName = "";
  queryParem.value.sclass = "";
  queryParem.value.phonenumber = "";
  queryParem.value.professional = "";
  queryParem.value.email = "";
  getList();
};
const addForm = function () {
  console.log(queryParem.value.userName);
  if (
    queryParem.value.userName == "" ||
    queryParem.value.nickName === null ||
    queryParem.value.sclass === null ||
    queryParem.value.professional === null
  ) {
    ElNotification({
      title: "错误",
      message: "请输入学生信息!",
      type: "Error",
    });
    // ElMessage.error('Oops, this is a error message.')
  } else {
    queryParem.value.delFlag = "0";
    addUser(queryParem.value).then((res) => {
      console.log(res);
    });
    resetForm();
  }
};

const handleEdit = function (index, row) {
  console.log(index);
  console.log("------");
  console.log(row);
  getByUserId(row.userId).then((res) => {
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
  getByUserId(row.userId).then((res) => {
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

          // ElMessage({
          //   type: "info",
          //   message: `删除成功!`,
          // });
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
