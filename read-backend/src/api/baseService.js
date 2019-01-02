
import request from '@/utils/request'
import apiUrl from '@/api/apiUrl'

export default class baseService{
    constructor(moduleName){
        this.moduleName = moduleName
    }

    list(data) {
        return  this._request(apiUrl[this.moduleName].list,'post',data)
    }
    add(data){
    return  this._request(apiUrl[this.moduleName].add,'post',data)
    }
    update(data){
    return  this._request(apiUrl[this.moduleName].update,'post',data)
    }
    delete(data){
        return  this._request(apiUrl[this.moduleName].delete,'post',data)
    }

    _request(url,method,data){
       return request({
            url: url,
            method: method,
            data: data
        }) 
    }
}