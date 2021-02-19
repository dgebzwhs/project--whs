// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from './btnPermission'
import ElementUI from 'element-ui'

import 'element-ui/lib/theme-chalk/index.css'
import './assets/iconfont/iconfont.css'
import 'font-awesome/css/font-awesome.css'

import App from './App.vue'
import router from './router'
import store from './vuex'
import i18n from './i18n/i18n'
import axios from 'axios'


Vue.config.productionTip = false
Vue.use(ElementUI)


//  获取角色信息，根据用户权限动态加载路由
router.beforeEach((to, from, next) => {
  // debugger
  if(from.path === '/login'){
    location.reload()
  }
  if (store.getters.token) {
    store.dispatch('setToken', store.getters.token)
    if (to.path === '/login') {
      next({path: '/'})
    } else {
      if (!store.getters.info.role&&store.getters.info.role!="tourist") {
        !async function getAddRouters () {
          console.log(localStorage.getItem('roles'))
          await store.dispatch('getInfo', {
            role: localStorage.getItem('roles'),
            permissions: localStorage.getItem('description')
          })
          await store.dispatch('newRoutes', store.getters.info.role)
          console.log(store.getters.addRouters)
          await router.addRoutes(store.getters.addRouters)
          next({path: to.fullPath})
          // next({path: '/index'})

        }()
      } else {
        let is404 = to.matched.some(record => {
          if(record.meta.role){
            return record.meta.role.indexOf(store.getters.info.role) === -1
          }
        })
        if(is404){
          next({path: '/404'})
          return false
        }
        next()
      }
    }
  } else {
    // 如果此时没有角色信息,为游客添加路由
    if (!store.getters.info.role) {
      !async function getAddRouters () {
        // 省略 axios 请求代码 通过 token 向后台请求用户权限等信息，这里用假数据赋值
        await store.dispatch('getInfo', {
          role: "tourist",
          permissions: ""
        })
        await store.dispatch('newRoutes',"tourist")
        console.log(store.getters.addRouters)
        await router.addRoutes(store.getters.addRouters)
        next({path: '/index'})
      }()
    }


    if (to.matched.some(res => res.meta.requireAuth)==false){
      // 如果该页面不需要权限，可以访问
      next();
    }else {
      if (to.path === '/login') {
        next();
      }
      next({path: '/login'})
    }

    // if (to.path === '/login') {
    //   next();
    // }
    // next({path: '/login'})

  }
})

Vue.prototype.$axios = axios

axios.interceptors.request.use(
  config => {
    // 判断是否存在token，如果存在的话，则每个http header都加上token
    let token = localStorage.getItem('Authorization')
    if (!config.headers.hasOwnProperty('Authorization') && token) {
      config.headers.Authorization = token
    }
    return config
  },
  error => {
    return Promise.reject(error)
  })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  axios,
  render: h => h(App),
  components: {App},
  template: '<App/>'
})
