import { message } from 'ant-design-vue/lib'

export default function ({store, redirect, app: { $axios }})  {
	// $axios.defaults.baseURL = 'https://www.xdx97.com:8185/api/'
	$axios.defaults.baseURL = 'http://192.168.229.1:8888/xdx/'

	
	$axios.onRequest(config => {
		config.headers.common['X-Access-Token'] = store.state.token
	})

	$axios.onError(error => {
		

	})
    $axios.interceptors.response.use(response => {
		if(response.data.success == false){
			if('未登录,请进行登录' == response.data.errMsg) {
				redirect('/login')
			}	
			return message.error(response.data.errMsg);
		}else if(response.data.errDesc != null) {
			message.success(response.data.errMsg);
		}else {
			return response.data
		}
		
	})

}