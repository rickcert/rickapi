<template>
  <el-container style="position: relative; top: 15px; height: 670px">
    <el-header style="width: 100%; left: 10px; top: 10px; float: left"
      >接口管理
      <p style="color: grey; font-size: 10px">api总数:{{ mycount }}</p>
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
          <el-col span="5">
            <el-form-item label="api名">
              <el-input v-model="queryParem.apiName"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="5">
            <el-form-item label="作者">
              <el-input v-model="queryParem.author"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="5">
            <el-form-item label="状态">
              <el-input v-model="queryParem.status"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="9">
            <el-row>
              <el-button type="primary" @click="submitForm"
                >&nbsp;&nbsp;查询&nbsp;&nbsp;</el-button
              >
              <el-button type="warning" @click="resetForm"
                >&nbsp;&nbsp;重置&nbsp;&nbsp;</el-button
              >
              <!-- <el-button type="danger" @click="addForm"
              >&nbsp;&nbsp;添加&nbsp;&nbsp;</el-button
            > -->
            </el-row>
          </el-col>
        </el-row>
      </el-form>
      <el-divider />

      <el-table
        ref="tableRef"
        row-key="width"
        stripe
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
        element-loading-text="Loading..."
      >
        <el-table-column prop="id" label="id" width="80px" />
        <el-table-column prop="apiName" label="接口名"  width="100px"/>
        <el-table-column prop="description" label="描述" width="100px" />
        <el-table-column prop="method" label="接口地址" width="200px" />
        <el-table-column prop="author" label="作者"  width="100px"  />
        <el-table-column prop="exampleurl" label="示范" />
        <el-table-column prop="calltimes" label="调用次数" width="80px">
          <template #default="scope">
            <el-tag>{{ scope.row.calltimes }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="80px">
          <template #default="scope">
            <el-tag type="success" v-if="scope.row.status == 0">正常</el-tag>
            <el-tag type="danger" v-if="scope.row.status == 2">停用中</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" fixed="right" width="200px">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="small"
              type="primary"
              @click="handleUse(scope.$index, scope.row)"
              >启用</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >停用</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-dialog v-model="dialogVisible" title="修改用户" width="45%">
        <el-form ref="userFormRef" :model="userForm" label-width="120px">
          <el-form-item label="api名字">
            <el-input v-model="userForm.apiName"></el-input>
          </el-form-item>
          <el-form-item label="api描述">
            <el-input v-model="userForm.dscription"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="userForm.author"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="userForm.method"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-input v-model="userForm.status"></el-input>
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
} from "@/api/inter.js";
import { onMounted } from "vue";
import { ElMessageBox, ElMessage } from "element-plus";
import { ElNotification } from "element-plus";
const loading = ref(true);
const mycount = ref(1);
const queryParem = ref({
  id: "",
  description: "",
  method: "",
  calltimes: "",
  example: "",
  author: "",
  size: 5,
  page: 0,
  status: "",
  apiName: "",
});
const address = "";
const userForm = ref({
  id: "",
  description: "",
  method: "",
  calltimes: "",
  example: "",
  author: "",
  size: 5,
  page: 0,
  status: "",
  apiName: "",
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
      mycount.value = res.data.totalElements;
      tableData.value = res.data.content;
      total.value = res.data.totalElements;
      queryParem.value.size = res.data.size;
      console.log(tableData.value);
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
  tableData.value = [];
  total.value = 0;
  queryParem.value.size = 6;
  getList();
};
const addForm = function () {
  console.log(queryParem.value.userName);
  if (queryParem.value.apiName === null || queryParem.value.method === null) {
    ElNotification({
      title: "错误",
      message: "请输入信息!",
      type: "Error",
    });
  } else {
    queryParem.value.status = "0";
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
  // console.log(row.content);
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
  ElMessageBox.confirm("您确定要停用此api吗？", "Warning", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      userForm.value.status = "2";
      updateUser(userForm.value).then((res) => {
        if (res.status === 200) {
          // 更新列表
          getList();
          ElNotification({
            title: "Success",
            message: "停用成功!",
            type: "Success",
          });
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消停用",
      });
      ElNotification({
        title: "cancel",
        message: "取消删除!",
        type: "Success",
      });
    });
};
const handleUse = function (index, row) {
  getByUserId(row.id).then((res) => {
    userForm.value = res.data;
  });
  ElMessageBox.confirm("您确定要启用此条api吗？", "Warning", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      userForm.value.status = "0";
      updateUser(userForm.value).then((res) => {
        if (res.status === 200) {
          // 更新列表
          getList();
          ElNotification({
            title: "Success",
            message: "启用成功!",
            type: "Success",
          });
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消启用",
      });
      ElNotification({
        title: "cancel",
        message: "取消启用!",
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

.el-table-column .img {
  background-size: 90px;
  width: 100px;
  height: auto;
  background-repeat: no-repeat;
  border: 10px solid white;
}
</style>
