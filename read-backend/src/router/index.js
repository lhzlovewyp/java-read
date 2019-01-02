import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/login'
import Index from '@/views/index/index'
import backendUser from '@/views/user/backendUser.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '',
      name: 'index',
      redirect: 'dashboard',
      children: [{
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
      }],
      component: Index
    },
    {
      path: '',
      redirect: 'backendUser',
      children: [{
        path: 'backendUser',
        component: () => import('@/views/user/backendUser'),
        name: 'backendUser',
        meta: { title: '后台用户管理', icon: 'dashboard', noCache: true }
      }],
      component: Index
    },
    {
      path: '',
      redirect: 'category',
      children: [{
        path: 'category',
        component: () => import('@/views/category/index'),
        name: 'category',
        meta: { title: '类目管理', icon: 'component', noCache: true }
      }],
      component: Index
    },
    {
      path: '',
      redirect: 'test',
      children: [{
        path: 'test',
        component: () => import('@/views/test/index'),
        name: 'test',
        meta: { title: '类目管理', icon: 'component', noCache: true }
      }],
      component: Index
    }

  ]
})
