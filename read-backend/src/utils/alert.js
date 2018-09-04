
export function errorAlert (_this, msg) {
  _this.$alert(msg, '错误提示', {
    confirmButtonText: '确定',
    callback: action => {
      this.$message({
        type: 'info',
        message: `action: ${action}`
      })
    }
  })
}
