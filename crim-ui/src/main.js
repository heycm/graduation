// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/css/public.css'
import App from './App'
import router from './router'
import store from './store'
import { get, post, del } from '@/request/api'
import Qs from 'qs'
import func from '@/utils/function'
import globalComponents from './components/index';

Vue.use(ElementUI)
Vue.use(globalComponents)
Vue.use(func)
Vue.config.productionTip = false

Vue.prototype.$get = get;
Vue.prototype.$post = post;
Vue.prototype.$delete = del;
Vue.prototype.$qs = Qs;



router.beforeEach((to, from, next) => {
  let path =  to.path.split("/");
  if(path.indexOf("school")>-1 || path.indexOf("company")>-1 || path.indexOf("student")>-1){
    let userInfo = JSON.parse(localStorage.getItem("crim_current_user_info"))
    if(!userInfo){
      ElementUI.Message.error('您未登录，请登录！');
      next("/login")
    }else{
      let roles = userInfo.roles
      if(path.indexOf("school")>-1 && (roles.indexOf("school")>-1 || roles.indexOf("school-child"))){
        next()
      }else if(path.indexOf("student")>-1 && roles.indexOf("student")>-1){
        next()
      }else if(path.indexOf("company")>-1 && roles.indexOf("company")>-1){
        next()
      }else{
        ElementUI.Message.error('非法操作！');
        next(from.path)
      }
    }
  }else{
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
