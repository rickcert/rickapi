import axios from 'axios'
// 创建axios实例
const request = axios.create({
    // 超时
    timeout: 10000,
    // 设置Content-Type，规定了前后端的交互使用json
    headers: { 'Content-Type': 'application/json;charset=utf-8' }
})
export function statuslist() {
    return request({
        url: 'https://api.rickblog.icu/systemstatus/',
        method: 'get',
    })
}