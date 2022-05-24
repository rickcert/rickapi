import request from "@/api/index.js";
// 新增用户
// 查询用户列表
export function listUser(data) {
    return request({
        url: '/hqhQuestion',
        method: 'get',
        params: data
    })
}

// 新增用户
export function addUser(data) {
    return request({
        url: '/hqhQuestion',
        method: 'post',
        data: data
    })
}
//adduser fuctoin
export function getByUserId(id) {
    return request({
        url: '/hqhQuestion/' + id,
        method: 'get'
    })
}
// 修改用户
export function updateUser(data) {
    return request({
        url: '/hqhQuestion/',
        method: 'put',
        data: data
    })
}

// 删除用户
export function deleteUser(userId) {
    return request({
        url: '/hqhQuestion/' + userId,
        method: 'delete'
    })
}
export function login(data) {
    return request({
        url: '/hqhQuestion',
        method: 'get',
        params: data
    })
}