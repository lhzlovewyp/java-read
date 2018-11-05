
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
      icon: 'people'
    },
    children: [
      {
        name: '后台用户管理',
        path: '/backendUser',
        hidden: false,
        meta: {
          title: '后台用户管理',
          icon: 'people'
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

  },
  {
    name: '读书管理',
    path: '/',
    hidden: false,
    meta: {
      title: '内容管理',
      icon: 'documentation'
    },
    children: [
      {
        name: '分类管理',
        path: '/category',
        hidden: false,
        meta: {
          title: '分类管理',
          icon: 'component'
        }
      },
      {
        name: '书籍管理',
        path: '/book',
        hidden: false,
        meta: {
          title: '书籍管理',
          icon: 'list'
        }
      }
    ]

  }

]

export default menuMap
