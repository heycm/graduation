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
import func from '@/utils/function'
import globalComponents from './components/index';

Vue.use(ElementUI)
Vue.use(globalComponents)
Vue.use(func)
Vue.config.productionTip = false

Vue.prototype.$get = get;
Vue.prototype.$post = post;
Vue.prototype.$delete = del;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
