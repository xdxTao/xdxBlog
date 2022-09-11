import http from '../http'

export function getBlogInfo(data){
    return http.get(`/article/front/detail/`,data,)
 }