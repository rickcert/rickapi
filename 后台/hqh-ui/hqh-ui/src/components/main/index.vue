<template>
  <el-container>
    <el-header style="height: 50px">
      <el-menu
        :default-active="activeIndex"
        background-color="rgb(60, 63, 65)"
        text-color="rgb(190,190,190)"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        style="height: 50px"
        active-text-color="rgb(64, 158, 255)"
      >
        <div style="width: 1080px"></div>
        <el-sub-menu index="2">
          <template #title> 系统管理 </template>
          <el-menu-item index="2-1" @click="open('user')"
            >用户管理</el-menu-item
          >
          <el-menu-item index="2-2" @click="open('systemstatus')"
            >系统状态</el-menu-item
          >
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>数据管理</template>
          <el-menu-item index="3-1" @click="open('oneword')"
            >一言查询</el-menu-item
          >
          <el-menu-item index="3-2" @click="open('dog')">舔狗语录</el-menu-item>
          <el-menu-item index="3-3" @click="open('pixiv')">pixiv</el-menu-item>
          <el-menu-item index="3-4" @click="open('anime')"
            >动漫图片</el-menu-item
          >
          <el-menu-item index="3-5" @click="open('wallpaper')"
            >随机壁纸</el-menu-item
          >
        </el-sub-menu>
        <el-menu-item index="4" @click="open('inter')">接口管理</el-menu-item>
        <el-sub-menu index="5">
          <template #title>日志管理</template>
          <el-menu-item index="5-1" @click="open('loginlog')"
            >登录日志</el-menu-item
          >
          <el-menu-item index="5-2" @click="open('systemlog')"
            >系统日志</el-menu-item
          >
        </el-sub-menu>
        <el-menu-item index="6" @click="doLogout">退出登录</el-menu-item>
      </el-menu>
    </el-header>
    <el-container style="height: 720px">
      <el-aside width="220px" style="background-color: rgb(60, 63, 65)">
        <div class="icon">
          <div class="icon-img">
             <el-avatar :size="50" :src="circleUrl" />
          </div>
          <div class="icon-con">
            <p
              style="
                font-size: 14px;
                position: relative;
                top: -45px;
                right: 27px;
                color: white;
              "
            >
              尊贵的管理员:
            </p>

            <p
              style="
                font-size: 17px;
                position: relative;
                top: -55px;
                right: 45px;
                color: white;
              "
            >
              {{ $store.state.user.nickname }}
            </p>
          </div>
          <span v-text="$store.state.user.username"></span>
        </div>
        <div class="line"></div>
        <el-row class="tac">
          <el-col :span="24">
            <el-menu
              default-active="2"
              active-text-color="white"
              text-color="rgb(188,188,188)"
              class="el-menu-vertical-demo"
              @close="handleClose"
              background-color="rgb(60, 63, 65)"
            >
              <el-sub-menu index="1">
                <template #title>
                  <img src="../../img/系统管理.png" style="width: 33px" />
                  <span style="position: relative; left: 35px">系统管理</span>
                </template>
                <el-menu-item index="1-1" @click="open('systemstatus')">
                  <img
                    src="../../img/系统-1.png"
                    style="width: 30px; position: relative; right: 15px"
                  />系统状态</el-menu-item
                >
                <el-menu-item index="1-2" @click="open('user')">
                  <img
                    src="../../img/用户.png"
                    style="width: 30px; position: relative; right: 15px"
                  />
                  用户管理</el-menu-item
                >
                
              </el-sub-menu>
              <el-sub-menu index="2">
                <template #title>
                  <img src="../../img/数据管理.png" style="width: 31px" />
                  <span style="position: relative; left: 35px">数据管理</span>
                </template>

                <el-menu-item index="2-1" @click="open('oneword')">
                  <img
                    src="../../img/PC每日一言.png"
                    style="width: 26px; position: relative; right: 15px"
                  />一言查询</el-menu-item
                >
                <el-menu-item index="2-2" @click="open('dog')">
                  <img
                    src="../../img/设施_小狗.png"
                    style="width: 30px; position: relative; right: 15px"
                  />舔狗语录</el-menu-item
                >
                <el-menu-item index="2-3" @click="open('pixiv')">
                  <img
                    src="../../img/图片.png"
                    style="width: 30px; position: relative; right: 15px"
                  />pixiv</el-menu-item
                >
                <el-menu-item index="2-4" @click="open('anime')">
                  <img
                    src="../../img/动漫.png"
                    style="width: 30px; position: relative; right: 15px"
                  />动漫图片</el-menu-item
                >
                <el-menu-item index="2-5" @click="open('wallpaper')">
                  <img
                    src="../../img/壁纸库.png"
                    style="width: 30px; position: relative; right: 15px"
                  />随机壁纸</el-menu-item
                >
              </el-sub-menu>

              <el-menu-item index="3" @click="open('inter')">
                <img
                  src="../../img/接口说明.png"
                  style="width: 37px; position: relative; right: 2px"
                />
                <span style="left: 30px">接口管理</span>
              </el-menu-item>
              <el-sub-menu index="4">
                <template #title>
                  <img src="../../img/DCS日志.png" style="width: 30px" />
                  <span style="position: relative; left: 35px">日志管理</span>
                </template>

                <el-menu-item index="4-1" @click="open('loginlog')">
                  <img
                    src="../../img/登录.png"
                    style="width: 30px; position: relative; right: 15px"
                  />登录日志</el-menu-item
                >
                <el-menu-item index="4-2" @click="open('systemlog')">
                  <img
                    src="../../img/API系统日志.png"
                    style="width: 30px; position: relative; right: 15px"
                  />系统日志</el-menu-item
                >
              </el-sub-menu>
              <el-menu-item index="5" @click="doLogout">
                <img src="../../img/退出.png" style="width: 30px" />
                <span style="left: 35px">退出登录</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main style="overflow: visible">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
let store = useStore();
const router = useRouter();
 const $store = useStore();
const circleUrl=$store.state.user.avatar;
let doLogout = function () {
  store.dispatch("LOGOUT").then((res) => {
    console.log(res);
    router.push({ name: "login" });
  });
};

onMounted(() => {
  router.push({ name: "systemstatus" });
});
let open = function (name) {
  router.push({ name });
};
</script>
<style scoped>
.el-container {
  background: url(https://api.rickblog.icu/mc);
  background-size: 1800px;
  background-repeat: no-repeat;
}

.el-header {
  background-color: rgb(60, 63, 65);
  color: var(--el-text-color-primary);
  text-align: left;
  height: 0px;
}

.el-header {
  border-bottom: 1px solid #efefef;
}

.header {
  float: left;
  background-color: rgb(212, 212, 212);
  width: 214px;
  height: 85px;
  position: relative;
  right: 27px;
  top: -7px;
  /* box-shadow: 2px 2px 2px 2px #b3c0d1; */
}
.header h2,
h5 {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}

.line {
  width: 210px;
  height: 1px;
  background-color: #ccc;
  z-index: 2;
}

.el-aside {
  background-color: white;
  color: var(--el-text-color-primary);
  text-align: center;
}

.el-main {
  color: var(--el-text-color-primary);
  text-align: center;

  background-color: #43444439;
}

.icon {
  height: 100px;
}
.icon-con {
  text-align: right;
}
.icon-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 5px solid rgba(128, 184, 248, 0.3);
  overflow: hidden;
  position: relative;
  top: 20px;
  left: 30px;
}
.icon-img img {
  height: 50px;
  position: relative;
}

.el-icon img {
  width: 27px;
}
.el-menu-item span {
  position: relative;
  left: 15px;
}

.el-sub-menu .el-menu-item {
  position: relative;
  left: 50px;
}
.el-aside::-webkit-scrollbar {
  display: none;
}
</style>
