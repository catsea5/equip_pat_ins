import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/userInfo/list',
    name: 'userInfo',
    meta: { title: '用户管理', icon: 'user' },
    alwaysShow: true,
    children: [
      {
        path: 'userInfo/list',
        name: '用户列表',
        component: () => import('@/views/user/userInfo/list'),
        meta: { title: '用户列表', icon: 'table' }
      },
      {
        path: 'userInfo/authList',
        name: '认证审批列表',
        component: () => import('@/views/user/userInfo/authList'),
        meta: { title: '认证审批列表', icon: 'table' }
      }
    ]
  },
  {
    path: '/equip',
    component: Layout,
    redirect: '/equip/list',
    name: 'equip',
    meta: { title: '设备管理', icon: 'nested' },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: '设备列表',
        component: () => import('@/views/equip/list'),
        meta: { title: '设备列表', icon: 'table' }
      },
      {
        path: 'add',
        name: '添加设备',
        component: () => import('@/views/equip/add'),
        meta: { title: '添加设备' },
        hidden: true
      },
      {
        path: 'apartmentList',
        name: '位置列表',
        component: () => import('@/views/apartment/list'),
        meta: { title: '位置列表', icon: 'table' }
      }
    ]
  },
  {
    path: '/plan',
    component: Layout,
    redirect: '/plan/list',
    name: 'plan',
    meta: { title: '巡检计划', icon: 'form' },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: '计划列表',
        component: () => import('@/views/plan/list'),
        meta: { title: '计划列表', icon: 'table' }
      },
      {
        path: 'change',
        name: '添加计划',
        component: () => import('@/views/plan/change'),
        meta: { title: '添加计划' },
        hidden: true
      },
      {
        path: 'change/:id',
        name: '修改计划',
        component: () => import('@/views/plan/change'),
        meta: { title: '修改计划' },
        hidden: true
      },
      {
        path: 'scheduleList',
        name: '分配巡检人员',
        component: () => import('@/views/schedule/list'),
        meta: { title: '分配巡检人员', icon: 'form' }
      }
    ]
  },
  {
    path: '/result',
    component: Layout,
    redirect: '/result/list',
    name: 'result',
    meta: { title: '巡检历史', icon: 'table' },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: '历史列表',
        component: () => import('@/views/result/list'),
        meta: { title: '历史列表', icon: 'table' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
