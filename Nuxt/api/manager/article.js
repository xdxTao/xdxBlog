import request from '../request'
import http from '../http'

// 文章分类列表
export function getCatgList(params){
    return http.post(`/article-catg/list`,params)
}

// 新增文章分类
export function addCatg(params){
    return http.post(`/article-catg/add`,params)
}

// 修改文章分类
export function modifyCatg(params){
    return http.post(`/article-catg/modify`,params)
}