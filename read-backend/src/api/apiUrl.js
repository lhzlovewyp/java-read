const interfaceDomain = 'http://api.rsfight.com/'

const data = {
  interfaceDomain: interfaceDomain,
  captchaCodeUrl: interfaceDomain + 'backend/defaultKaptcha',
  doLogin: interfaceDomain + 'backend/doLogin',
  adminUser:{
    list: interfaceDomain + 'backend/adminUser/list',
    add: interfaceDomain + 'backend/adminUser/add',
    update: interfaceDomain + 'backend/adminUser/update',
    delete: interfaceDomain + 'backend/adminUser/delete',
    updateStatus: interfaceDomain + 'backend/adminUser/updateStatus',

  },
  category:{
    list: interfaceDomain + 'backend/category/list',
    add: interfaceDomain + 'backend/category/add',
    update: interfaceDomain + 'backend/category/update',
    delete: interfaceDomain + 'backend/category/delete',
    updateStatus: interfaceDomain + 'backend/category/updateStatus',

  }
}

export default data
