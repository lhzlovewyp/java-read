import request from '@/utils/request'
import apiUrl from '@/api/apiUrl'

const adminUserService = {
  list(data) {
    return request({
      url: apiUrl.adminUser.list,
      method: 'post',
      data: data
    })
  },
  add(data){
    return request({
      url: apiUrl.adminUser.add,
      method: 'post',
      data: data
    })
  },
  update(data){
    return request({
      url: apiUrl.adminUser.update,
      method: 'post',
      data: data
    })
  },
  delete(data){
    return request({
      url: apiUrl.adminUser.delete,
      method: 'post',
      data: data
    })
  },
  updateStatus(data){
    return request({
      url: apiUrl.adminUser.updateStatus,
      method: 'post',
      data: data
    })
  }

}

export default adminUserService