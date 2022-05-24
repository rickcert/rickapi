import axios from 'axios'
import store from '@/store'

// 创建axios实例
const baseUrl = '/api'
const request = axios.create({
    baseURL: baseUrl,
    timeout: 10000,
    // 设置Content-Type，规定了前后端的交互使用json
    headers: { 'Content-Type': 'application/json;charset=utf-8' }
})

request.interceptors.request.use(function(config) {

    if (store.state.user.token) {
        config.headers['Authorization'] = store.state.user.token // 让每个请求携带自定义token 请根据实际情况自行修改
        config.headers['username'] = store.state.user.username // 让每个请求携带自定义token 请根据实际情况自行修改

    }

    // 在发送请求之前做些什么
    return config;
}, function(error) {
    // 对请求错误做些什么
    console.log(error);
    if (error.response.status === 401) {
        //不能使用this.$router(因为this不是vue组件对象无法调用$router)
        //解决:this.$router为了拿到router路由对象,所以直接去上面引入@/router下router 对象
        Notify({ type: "danger", message: "身份已经过期" });
        console.log("身份已经过期");
        router.replace("/login");
    }

    return Promise.reject(error);
});


export default request