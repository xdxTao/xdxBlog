import request from '../request'
import Axios from 'axios'
import http from '../http'
// export function menuList(parmars) {
//     console.log(parmars,'parmarsparmarsparmars')
//     return Axios({
//         url: 'http://192.168.2.5:8888/xdx/menu/list',
//         method: 'post',
//         data: parmars
//     })
// }

// 菜单列表
// export function menuList(parmars){
//     return request({
//         url:'/menu/list',
//         method: 'post',
//         data: parmars
//     })
// }

// 新增菜单
// export function addMenu(parmars){
//     return request({
//         url:'/menu/add',
//         method: 'post',
//         data: parmars
//     })
// }

// 修改菜单
// export function modMenu(parmars){
//     return request({
//         url:'/menu/modify',
//         method: 'post',
//         data: parmars
//     })
// }

// 菜单列表
export function menuList(parmars){
    return http.post(`/menu/list`,parmars)
}

// 新增菜单
export function addMenu(parmars){
    return http.post(`/menu/add`,parmars)
}

// 修改菜单
export function modMenu(parmars){
    return http.post(`/menu/modify`,parmars)
}