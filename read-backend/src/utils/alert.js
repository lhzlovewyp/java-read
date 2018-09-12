
export function errorAlert (_this, msg,callback) {
  _this.$alert(msg, '错误提示', {
    confirmButtonText: '确定',
    callback: action => {
      if(callback){
        callback()
      }
    }
  })
}

export function msgAlert (_this, msg,callback) {
  _this.$alert(msg, '提示', {
    confirmButtonText: '确定',
    callback: action => {
      if(callback){
        callback()
      }
    }
  })
}
