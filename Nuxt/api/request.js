/**  * 封装Axios  * 处理请求、响应错误信息  */
import { message } from 'ant-design-vue'
import axios from 'axios' //引用axios  
// create an axios instance 
const service = axios.create({
    baseURL: 'http://192.168.2.5:8888/xdx/',
    // nginx转发到后端Springboot 
    withCredentials: true,
    // send cookies when cross-domain requests 
    timeout: 500*100
    // request timeout
}) // request interceptor 
// service.interceptors.request.use(config => {
//     // do something before request is sent  
//     //
//     config.headers['Content-Type'] = 'application/json'
//     // config.headers['-Token'] = getToken()
//     return config
// }, error => {
//     // do something with request error 
//     console.log(error)
//     // for debug  
//     return Promise.reject(error)
// }) 
// 2.请求拦截器
service.interceptors.request.use(config => {
  //发请求前做的一些处理，数据转化，配置请求头，设置token,设置loading等，根据需求去添加
   config.data = JSON.stringify(config.data); //数据转化,也可以使用qs转换
   config.headers = {
     'Content-Type':'application/json' //配置请求头
   }
   //如有需要：注意使用token的时候需要引入cookie方法或者用本地localStorage等方法，推荐js-cookie
   //const token = getCookie('名称');//这里取token之前，你肯定需要先拿到token,存一下
   //if(token){
      //config.params = {'token':token} //如果要求携带在参数中
      //config.headers.token= token; //如果要求携带在请求头中
    //}
  return config
}, error => {
  Promise.reject(error)
})
// response interceptor 
service.interceptors.response.use(
  /**  * If you want to get http information such as headers or status  * Please return  response => response  */
  /**  * Determine the request status by custom code  * Here is just an example  * You can also judge the status by HTTP Status Code  */

  response => {
    const res = response.data
    //res is my own data  
    if (res.code === 200) {
      // message.success(res.message);
      // do somethings when response success  
      //   Message({  
      //     message: res.message || '操作成功',  
      //     type: 'success', 
      //     duration: 1 * 1000 
      //   })  return res } else { 
      // if the custom code is not 200000, it is judged as an error.  
      return Promise.resolve(res)
      
    }
    // return response
    else{
        // Message({
        //     message: res.message || 'Error',
        //     type: 'error',
        //     duration: 2 * 1000
        //  })
        message.error(res.message);
        return Promise.resolve(res)
    }
  },
  error => {
       /***** 接收到异常响应的处理开始 *****/
      // Message.error(error.message)
      message.error(error.message)
      /***** 处理结束 *****/
      //如果不需要错误处理，以上的处理过程都可省略
      return Promise.resolve(error.response)
  }
)



// 3.响应拦截器
// service.interceptors.response.use(response => {
//   //接收到响应数据并成功后的一些共有的处理，关闭loading等
  
//   return response
// }, error => {
//    /***** 接收到异常响应的处理开始 *****/
//   if (error && error.response) {
//     // 1.公共错误处理
//     // 2.根据响应码具体处理
//     // switch (error.response.status) {
//     //   case 400:
//     //     error.message = '错误请求'
//     //     break;
//     //   case 401:
//     //     error.message = '未授权，请重新登录'
//     //     break;
//     //   case 403:
//     //     error.message = '拒绝访问'
//     //     break;
//     //   case 404:
//     //     error.message = '请求错误,未找到该资源'
//     //     window.location.href = "/NotFound"
//     //     break;
//     //   case 405:
//     //     error.message = '请求方法未允许'
//     //     break;
//     //   case 408:
//     //     error.message = '请求超时'
//     //     break;
//     //   case 500:
//     //     error.message = '服务器端出错'
//     //     break;
//     //   case 501:
//     //     error.message = '网络未实现'
//     //     break;
//     //   case 502:
//     //     error.message = '网络错误'
//     //     break;
//     //   case 503:
//     //     error.message = '服务不可用'
//     //     break;
//     //   case 504:
//     //     error.message = '网络超时'
//     //     break;
//     //   case 505:
//     //     error.message = 'http版本不支持该请求'
//     //     break;
//     //   default:
//     //     error.message = `连接错误${error.response.status}`
//     // }
//   } else {
//     // 超时处理
//     if (JSON.stringify(error).includes('timeout')) {
//       Message.error('服务器响应超时，请刷新当前页')
//     }
//     error.message = '连接服务器失败'
//   }

//   // Message.error(error.message)
//   Message.error(error.response.message)
//   /***** 处理结束 *****/
//   //如果不需要错误处理，以上的处理过程都可省略
//   return Promise.resolve(error.response)
// })

export default service //导出封装后的axios