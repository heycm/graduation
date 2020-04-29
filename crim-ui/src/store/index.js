import Vue from 'vue'
import Vuex from 'vuex'

import demo from './demo'
import user from './user'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    demo,
    user
  }
})
