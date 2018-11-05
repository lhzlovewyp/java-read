import request from '@/utils/request'
import apiUrl from '@/api/apiUrl'

const categoryService = {
    list(data) {
        return request({
          url: apiUrl.category.list,
          method: 'post',
          data: data
        })
    }
}


export default categoryService