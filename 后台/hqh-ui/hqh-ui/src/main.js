import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/assets/style/common.css'

let app = createApp(App);
// 全局安装路由组讲
app.use(router).use(store).use(ElementPlus);



app.mount('#app')