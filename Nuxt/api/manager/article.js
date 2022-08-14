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

// 新增文章
export function addArticle(params){
    return http.post(`/article/add`,params)
}

// 修改文章
export function modifyArticle(params){
    return http.post(`/article/modify`,params)
}

// 文章列表
export function articleList(params){
    return http.post(`/article/list`,params)
}

// 获取详情
export function getarticleInfo(params){
    return http.get(`/article/get/${params}`)
}

// 公开、置顶table修改
export function fastModifyArticle(params){
    return http.post(`/article/fastModify`,params)
}