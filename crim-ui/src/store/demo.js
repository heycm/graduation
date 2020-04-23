// import axios from 'axios'
import { get, post, del } from '@/request/api'

export default ({
    namespaced: true, // 开启命名空间
    state: {
        // 数据管理
    },
    mutations: {
        // 数据操作
    },
    actions: {
        // 异步请求
        sendGet(context, id) {
            console.log("sendGet方法")
            return new Promise((resolve, reject) => {
                get('/demo/' + id)
                .then(res => {
                    resolve(res)
                })
                .catch(err => {
                    reject(err)
                })
            })
        }
    }
})