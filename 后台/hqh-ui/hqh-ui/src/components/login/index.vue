<template>
  <el-container>
    <el-main style="width: 100%; overflow: visible">
      <el-card
        class="box-card login-card"
        style="
          z-index: 3;
          font-size: em;
          width: 430px;
          height: 370px;
          top: 150px;
          right: 940px;
        "
      >
        <span class="login-title">开发助手-rickapi</span>
        <span class="login-tip">welcome 欢迎登陆</span>
        <el-form ref="user" :model="user" label-width="80px" :rules="rules">
          <el-form-item label="用户名" prop="userName">
            <el-input
              v-model="user.userName"
              placeholder="请输入用户"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="user.password"
              type="password"
              placeholder="请输入密码"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="doLogin">登陆</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
    <el-aside width="790px"
      ><el-image
        fit="fill"
        src="https://imgapi.cn/api.php?zd=zsy&fl=meizi&gs=images"
        style="visibility: hidden; position: relative"
      ></el-image
    ></el-aside>
  </el-container>
</template>

<script>
import { login } from "@/api/user";
import { ref, reactive } from "vue";

export default {
  name: "login",
  data() {
    return {
      // 用户信息
      user: {
        userName: "admin",
        password: "1111111",
      },
      rules: {
        userName: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur",
          },
          {
            min: 3,
            max: 10,
            message: "用户名的长度必须是3到10",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    doLogin() {
      this.$refs.user.validate((valid) => {
        if (valid) {
          // 他里边的代码是异步的
          this.$store.dispatch("LOGIN", this.user).then((res) => {
            console.log(res);
            if (res.status === 200) {
              // this.$store.dispatch("GET_INFO").then(()=>{
              this.$router.push({ name: "main" });
              // })
            }
          });
        } else {
          ElMessage("您的数据不合法");
        }
      });
      this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scope>
.el-container {
  background: url(https://api.ixiaowai.cn/mcapi/mcapi.php);
}
.el-image {
  height: 885px;
  width: 750px;
  z-index: 0;
}
.el-main {
  /* position: absolute; */
}
.login-card {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  width: 480px;
  height: 400px;
  padding: 50px;
}
.login-title {
  width: 459px;
  height: 70px;
  font-size: 40px;
  font-family: AlibabaPuHuiTiB;
  color: #333333;
  line-height: 90px;
  letter-spacing: 1px;
  font-weight: 800;
  display: block;
  text-align: left;
}
.login-tip {
  width: 319px;
  height: 70px;
  font-size: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 90px;
  letter-spacing: 1px;
  display: block;
  text-align: left;
  margin-bottom: 30px;
}
</style>
