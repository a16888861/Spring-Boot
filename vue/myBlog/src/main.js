// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

// vue路由
import router from './router'
import Router from 'vue-router'
// 状态的配置
// import store from './store'
// element-ui组件的配置
import ElementUi from 'element-ui'
// 主题样式使用element-ui
import 'element-ui/lib/theme-chalk/index.css'
// 图标库样式使用font-awesome
import 'font-awesome/css/font-awesome.min.css'

// 全局注册引用的插件
Vue.use(ElementUi)
Vue.use(Router)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
