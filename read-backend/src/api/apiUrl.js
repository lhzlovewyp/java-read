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

  }
}

export default data
