import axios from 'axios'
import ElementUI from 'element-ui'
import router from '../router'

// 环境的切换
if (process.env.NODE_ENV === 'development') {
    axios.defaults.baseURL = 'http://localhost:9999/api/v1'
} else if (process.env.NODE_ENV === 'testing') {
    axios.defaults.baseURL = ''
} else if (process.env.NODE_ENV === 'production') {
    axios.defaults.baseURL = ''
}
axios.defaults.timeout = 10000
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8'
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8;multipart/form-data'

// 请求拦截器
axios.interceptors.request.use(
  config => {
    // 每次发送请求之前判断是否存在token，如果存在，则统一在http请求的header都加上token，不用每次请求都手动添加了
    // 即使本地存在token，也有可能token是过期的，所以在响应拦截器中要对返回状态进行判断
    let userInfo = JSON.parse(localStorage.getItem("crim_current_user_info"))
    if(userInfo){
      config.headers.Authorization = userInfo.token
    }
    console.log(config)
    return config
  },
  error => {
    console.log("请求拦截器走到了error")
    console.log(error)
    return Promise.error(error)
  })
//
// 响应拦截器
axios.interceptors.response.use(
  // 服务器状态码是200的情况
  response => {
    console.log(response)
    if (response.status === 200) {
      if(!response.data.ok){
        ElementUI.Message.error(response.data.msg);
      }
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  // 服务器状态码不是200的情况
  error => {
    console.log("服务器状态码不是200的情况")
    console.log(error.response)
    if (error.response.status) {
      switch (error.response.status) {
        // 401: 未登录/token失效
        case 401:
          if(error.response.data.resCode === "50005") {
            ElementUI.Message.error('您未登录，请先登录');
          }
          if(error.response.data.resCode === "50006"){
            ElementUI.Message.error('登录过期，请重新登录');
            localStorage.removeItem('crim_current_user_info')
          }
          router.replace({
            path: '/login',
            query: { redirect: router.currentRoute.fullPath }
          })
          break
        // 403：无权限
        case 403:
          ElementUI.Message.error('您无此权限！');
          break
        // 404请求不存在
        case 404:
            ElementUI.Message.error('请求路径不存在！');
          break
        case 408:
          ElementUI.Message.error('请求超时');
          break
        // 其他错误，直接抛出错误提示
        default:
          ElementUI.Message.error('系统繁忙，请稍后重试！');
      }
      return Promise.reject(error.response)
    }
  }
)

/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function sendGet(url, params) {
    return new Promise((resolve, reject) => {
        axios.get(url, {params : params})
            .then(res => {
                resolve(res)
            }).catch(err => {
                reject(err)
            })
    })
}
/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function sendPost(url, params) {
    return new Promise((resolve, reject) => {
        // axios.post(url, Qs.stringify(params))
        axios.post(url, params)
        // axios.post(url, JSON.stringify(params))
            .then(res => {
                resolve(res)
            })
            .catch(err => {
                reject(err)
            })
    })
}
/**
 * del方法，对应delete请求
 * @param {String} url [请求的url地址]，例:'/api/del/1'
 */
export function sendDelete(url) {
    return new Promise((resolve, reject) => {
        axios.delete(url)
            .then(res => {
                resolve(res)
            })
            .catch(err => {
                reject(err)
            })
    })
}

