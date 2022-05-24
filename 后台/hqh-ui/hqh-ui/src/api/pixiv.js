import request from "@/api/index.js";
// 新增用户
// 查询用户列表

export function listUser(data) {
    return request({
        url: '/hqhPixiv',
        method: 'get',
        params: data
    })
}

// 新增用户
export function addUser(data) {
    return request({
        url: '/hqhPixiv',
        method: 'post',
        data: data
    })
}
//
export function getByUserId(id) {
    return request({
        url: '/hqhPixiv/' + id,
        method: 'get'
    })
}
// 修改用户
export function updateUser(data) {
    return request({
        url: '/hqhPixiv/',
        method: 'put',
        data: data
    })
}