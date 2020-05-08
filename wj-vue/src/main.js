// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 设置反向代理，前端请求默认发送到http://localhost:8443/api
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'
// 全局注册，之后可以在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios

// Ban create tip when producting
Vue.config.productionTip = false

Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  // 挂载目标
  el: '#app',
  // 该对象包含vue router，并使用项目中定义的路由
  router,
  store,
  // 该对象包含vue组件
  components: { App },
  // 用一个字符串模板作为vue实例的标识符，类似一个html标签
  template: '<App/>'
})

/**
 * 钩子函数，即某些时机会被调用的函数
 * beforeEach 意思是在访问每个路由前调用
 */
router.beforeEach((to, from, next) => {
  // 判断访问的路径是否需要登录
  if (to.meta.requireAuth) {
    if (store.state.user.username) {
      next()
    } else {
      next({
        path: 'login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
})
