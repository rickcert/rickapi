import request from "@/api/index.js";
// 新增用户
// 查询用户列表


export function listUser(data) {
    return request({
        url: '/hqhYyyl',
        method: 'get',
        params: data
    })
}

// 新增用户
export function addUser(data) {
    return request({
        url: '/hqhYyyl',
        method: 'post',
        data: data
    })
}
//
export function getByUserId(id) {
    return request({
        url: '/hqhYyyl/' + id,
        method: 'get'
    })
}
// 修改用户
export function updateUser(data) {
    return request({
        url: '/hqhYyyl/',
        method: 'put',
        data: data
    })
}

// 删除用户
export function deleteUser(userId) {
    return request({
        url: '/hqhYyyl/' + userId,
        method: 'delete'
    })
}
export function login(data) {
    return request({
        url: '/hqhYyyl',
        method: 'get',
        params: data
    })
}