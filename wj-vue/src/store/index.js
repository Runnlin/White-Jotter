import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

/**
 * 实现登录拦截器
 * 需要一个记录用户信息的变量
 */
export default new Vuex.Store({
  state: {
    user: {
      // 项目打开时，判断 本地存储 中是否存在user这个对象，有的话取出username， 否则设置为空
      // 这样只要不清除缓存，登录的状态会一直保存
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
    }
  },
  mutations: {
    login (state, user) {
      state.user = user
      window.localStorage.setItem('user', JSON.stringify(user))
    }
  }
})
