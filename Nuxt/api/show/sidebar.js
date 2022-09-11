import http from '../http'

export function getArticleCatgList(params){
   return http.post(`/article-catg/front/list`,params)
}

export function getReadTopList(params){
   return http.get(`/article/front/read-top`)
}

export function getBlogList(data){
   const  {current, size,params} = data
   return http.get(`/article/front/list/`,data)
}