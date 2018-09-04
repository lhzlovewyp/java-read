
const menuMap = [
  {
    children: [
      {
        name: 'index',
        path: '',
        hidden: false,
        meta: {
          title: '首页',
          icon: 'dashboard'
        }
      }
    ]
  },
  {
    name: '用户管理',
    path: '/',
    hidden: false,
    meta: {
      title: '用户管理',
      icon: 'documentation'
    },
    children: [
      {
        name: '后台用户管理',
        path: '/backendUser',
        hidden: false,
        meta: {
          title: '后台用户管理',
          icon: 'documentation'
        }
      },
      {
        name: '前台用户管理',
        path: '/frontUser',
        hidden: false,
        meta: {
          title: '前台用户管理',
          icon: 'documentation'
        }
      }
    ]

  }

]

export default menuMap
