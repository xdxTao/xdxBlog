import request from '../request'
import http from '../http'

// 用户列表
// export function userList(parmars){
//     return request({
//         url:'/user/list',
//         method: 'post',
//         data: parmars
//     })
// }

// 新增用户
// export function addUser(parmars){
//     return request({
//         url:'/user/add',
//         method: 'post',
//         data: parmars
//     })
// }

// 修改用户
// export function modifyUser(parmars){
//     return request({
//         url:'/user/modify',
//         method: 'post',
//         data: parmars
//     })
// }

//获取当前用户
// export function getUserInfo(parmars){
//     return request({
//         url:'/user/information',
//         method: 'get',
//         data: parmars
//     })
// }


// 用户列表
export function userList(params){
    return http.post(`/user/list`,params)
}

// 新增用户
export function addUser(params){
    return http.post(`/user/add`,params)
}

// 修改用户
export function modifyUser(params){
    return http.post(`/user/modify`,params)
}

//获取当前用户
export function getUserInfo(params){
    return http.get(`/user/information`,params)
}