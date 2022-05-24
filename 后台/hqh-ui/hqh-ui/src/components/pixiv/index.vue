<template>
  <el-container style="position: relative; top: 15px; height: 670px">
    <el-header style="width: 100%; left: 10px; top: 10px; float: left"
      >pixiv
      <p style="color: grey; font-size: 10px">图片总数:{{ mycount }}</p>
      <!-- <el-divider
    /> -->
    </el-header>
    <el-main style="width: 100%; overflow: visible">
      <el-form
        :model="queryParem"
        label-width="100px"
        class="demo-ruleForm"
        size="mini"
        :inline="true"
      >
        <el-row style="hieght: 30px"> </el-row>
        <el-row>
          <el-col span="6">
            <el-form-item label="id">
              <el-input v-model="queryParem.id"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="关键字">
              <el-input v-model="queryParem.tags"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="作者">
              <el-input v-model="queryParem.author"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-row>
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
            <el-form-item label="标题">
              <el-input v-model="queryParem.title"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="链接">
              <el-input v-model="queryParem.original"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="高度">
              <el-input v-model="queryParem.height"></el-input>
            </el-form-item>
          </el-col>

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
        row-key="width"
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
        element-loading-text="Loading..."
      >
        <el-table-column prop="id" label="id" width="100" />
        <el-table-column prop="author" label="作家" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="width" label="宽度" />
        <el-table-column prop="height" label="高度" />
        <el-table-column prop="uploaddate" label="上传日期" />
        <el-table-column label="图片">
          <template #default="scope">
            <el-image
              style="width: 80px; height: 80px"
              :src="scope.row.original"
              :preview-src-list="[scope.row.original]"
              :initial-index="6"
              fit="cover"
              preview-teleported="true"
            />
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
        <el-form ref="userFormRef" :model="userForm" label-width="120px">
          <el-form-item label="标题">
            <el-input v-model="userForm.title"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="userForm.author"></el-input>
          </el-form-item>
          <el-form-item label="标签">
            <el-input v-model="userForm.tags"></el-input>
          </el-form-item>
          <el-form-item label="网址">
            <el-input v-model="userForm.original"></el-input>
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
import { listUser, addUser, getByUserId, updateUser } from "@/api/pixiv.js";
import { onMounted } from "vue";
import { ElMessageBox, ElMessage } from "element-plus";
import { ElNotification } from "element-plus";
const loading = ref(true);
const mycount = ref(1);
const queryParem = ref({
  id: "",
  width: "",
  height: "",
  title: "",
  author: "",
  tags: "",
  original: "",
  delFlag: "0",

  size: 3,
  page: 0,
  title: "",
});
const userForm = ref({
  id: "",
  width: "",
  height: "",
  title: "",
  author: "",
  tags: "",
  original: "",
  delFlag: "0",
  size: 3,
  page: 0,
  title: "",
});

const tableData = ref([]);
const total = ref(0);
const dialogVisible = ref(false);
const getList = function () {
  listUser(queryParem.value).then((res) => {
    tableData.value = res.data.content;
    total.value = res.data.totalElements;
    queryParem.value.size = res.data.size;
  });
};
onMounted(() => {
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
    }
  });
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
  queryParem.value.size = 3;
  queryParem.value.width = "";
  queryParem.value.height = "";
  (queryParem.value.id = ""),
    (queryParem.value.width = ""),
    (queryParem.value.height = ""),
    (queryParem.value.title = ""),
    (queryParem.value.author = ""),
    (queryParem.value.tags = ""),
    (queryParem.value.original = ""),
    getList();
};
const addForm = function () {
  console.log(queryParem.value.author);
  if (
    queryParem.value.width === null ||
    queryParem.value.height === null ||
    queryParem.value.original === null
  ) {
    ElNotification({
      title: "错误",
      message: "请输入学生信息!",
      type: "Error",
    });
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
