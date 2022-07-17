import request from '../request'

export function roleList(parmars){
    return request({
        url:'/role/list',
        method: 'post',
        data: parmars
    })
}

export function roleMenuList(parmars){
    return request({
        url:`/role/menu-id-list/${parmars}`,
        method: 'post',
        data: parmars
    })
}

export function addRole(parmars){
    return request({
        url:'/role/add',
        method: 'post',
        data: parmars
    })
}

export function roleMenuMod(parmars){
    return request({
        url:'/role/role-menu',
        method: 'post',
        data: parmars
    })
}

