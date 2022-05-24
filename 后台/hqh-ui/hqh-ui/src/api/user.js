import request from "@/api/index.js";
// 新增用户
// 查询用户列表

export function listUser(data) {
    return request({
        url: '/hqhUser',
        method: 'get',
        params: data
    })
}

// 新增用户
export function addUser(data) {
    return request({
        url: '/hqhUser',
        method: 'post',
        data: data
    })
}
//
export function getByUserId(id) {
    return request({
        url: '/hqhUser/' + id,
        method: 'get'
    })
}
// 修改用户
export function updateUser(data) {
    return request({
        url: '/hqhUser/',
        method: 'put',
        data: data
    })
}

// 删除用户
export function deleteUser(userId) {
    return request({
        url: '/hqhUser/' + userId,
        method: 'delete'
    })
}
export function login(data) {
    return request({
        url: '/login',
        method: 'post',
        data: data
    })
}
export function logout() {
    return request({
        url: '/logout',
        method: 'get',
    })
}

// export function getInfo() {
//     return request({
//         url: '/hqhUser/getInfo',
//         method: 'get'
//     })
// }