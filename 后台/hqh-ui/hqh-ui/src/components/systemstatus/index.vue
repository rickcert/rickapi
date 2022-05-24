<template>
  <div>
    <el-row :gutter="22">
      <el-col :span="8">
        <el-card
          shadow="always"
          class="mgb20"
          color="rgba(255,255,255,0.4)"
          body-style="background-color: rgba(255, 255, 255, 0.692);"
          style="height: 300px"
        >
          <div class="user-info">
            <el-avatar :size="100" :src="circleUrl"  />
            <div class="user-info-cont">
              <div class="user-info-name">{{ name }}</div>
              <div>{{ role }}</div>
            </div>
          </div>
          <div class="user-info-list">
            本次登录ip：<span>{{ $store.state.user.ipaddr }}</span>
          </div>
          <div class="user-info-list">
            本次登录时间：
            <div>{{ $store.state.user.time }}</div>
          </div>
        </el-card>
        <el-card shadow="always" style="height: 295px">
          <template #header>
            <div class="clearfix">
              <span>语言详情</span>
            </div>
          </template>
          java<el-progress :percentage="40.3" color="#42b983"></el-progress
          >vue<el-progress :percentage="30.7" color="#f1e05a"></el-progress>
          php<el-progress :percentage="13.5" color="#f56c6c"></el-progress>
          js<el-progress :percentage="6.7"></el-progress>css<el-progress
            :percentage="5.9"
            color="#346c6c"
          ></el-progress
          >html
          <el-progress :percentage="2.9" color="#b23c6c"></el-progress>
          <p></p>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-row :gutter="20" class="mgb20">
          <el-col :span="8">
            <el-card shadow="always" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-1">
                <i class="el-icon-user-solid grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ apicount }}</div>
                  <div>api调用量</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="always" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-2">
                <i class="el-icon-message-solid grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ numbercount }}</div>
                  <div>api数</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="always" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-3">
                <i class="el-icon-s-goods grid-con-icon"></i>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ numbercount2 }}</div>
                  <div>系统消息</div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-card shadow="always" style="height: 495px">
          <el-row :gutter="20">
            <el-col :span="24">
              <el-card shadow="hover">
                <schart
                  ref="bar"
                  class="schart"
                  canvasId="bar"
                  :width="width"
                  :height="height"
                  :options="options"
                ></schart>
              </el-card>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Schart from "vue-schart";
import { useStore } from "vuex";
import { statuslist } from "@/api/systemstatus.js";
import { ref } from "vue";
export default {
  name: "dashboard",
  components: { Schart },
  setup() {
    const $store = useStore();
    const name = $store.state.user.nickname;
    const role = name === "admin" ? "超级管理员" : "普通用户";
    const apicount = ref(0);
    const circleUrl=$store.state.user.avatar;
    const numbercount = ref(0);
    const numbercount2 = ref(0);
    const datasets1 = ref([]);
    const options = ref({});
    const getList = function () {
      statuslist().then((res) => {
        console.log(res.data);
        apicount.value = res.data["data"][0]["callcount"];
        numbercount.value = res.data["data"][1]["apicount"];
        console.log(res.data["data"]);
        numbercount2.value =
          parseInt(apicount.value) + parseInt(numbercount.value);
        datasets1.value = res.data["data"][2];
        console.log(datasets1.value[0].label);
        options.value = {
          type: "bar",
          title: {
            text: "API使用状况(调用数前六)",
          },
          // xRorate: 1,
          width: 600,
          height: 700,
          labels: ["总计"],
          datasets: [
            {
              label: datasets1.value[0].label,
              data: [datasets1.value[0].data],
            },
            {
              label: datasets1.value[1].label,
              data: [datasets1.value[1].data],
            },
            {
              label: datasets1.value[2].label,
              data: [datasets1.value[2].data],
            },
            {
              label: datasets1.value[3].label,
              data: [datasets1.value[3].data],
            },

            {
              label: datasets1.value[4].label,
              data: [datasets1.value[4].data],
            },
            {
              label: datasets1.value[5].label,
              data: [datasets1.value[5].data],
            },
          ],
        };
      });
    };
    getList();

    return {
      name,
      options,
      role,
      apicount,
      numbercount,
      numbercount2,
      circleUrl,
    };
  },
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.grid-content {
  display: flex;
  align-items: center;
  height: 100px;
}

.grid-cont-right {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: #999;
}

.grid-num {
  font-size: 30px;
  font-weight: bold;
}

.grid-con-icon {
  font-size: 50px;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  color: #fff;
}

.grid-con-1 .grid-con-icon {
  background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
  background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
  background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
  color: rgb(242, 94, 67);
}

.user-info {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 2px solid #ccc;
  margin-bottom: 20px;
}

.user-avator {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}

.user-info-cont {
  padding-left: 50px;
  flex: 1;
  font-size: 14px;
  color: #999;
}

.user-info-cont div:first-child {
  font-size: 30px;
  color: #222;
}

.user-info-list {
  font-size: 14px;
  color: #999;
  line-height: 25px;
}

.user-info-list span {
  margin-left: 70px;
}

.mgb20 {
  margin-bottom: 20px;
}

.todo-item {
  font-size: 14px;
}

.todo-item-del {
  text-decoration: line-through;
  color: #999;
}

.schart {
  width: 100%;
  height: 300px;
}
</style>
