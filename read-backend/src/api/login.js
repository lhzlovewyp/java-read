import request from '@/utils/request'
import apiUrl from '@/api/apiUrl'

export function doLogin (data) {
  return request({
    url: apiUrl.doLogin,
    method: 'post',
    data: data
  })
}
