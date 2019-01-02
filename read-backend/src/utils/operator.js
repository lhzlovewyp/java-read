//通用的操作工具类,比如新增修改操作，所有后台系统代码基本一致.
import { msgAlert } from '@/utils/alert'
import qs from 'qs'

const operator = {
    saveOrUpdate(vue,service){
        let _this = vue
        if(_this.dialogStatus === 'create'){
            service.add(qs.stringify(_this.temp)).then(response => {
              if (response && response.rtnCode === '0') {
                  _this.$message({
                  type: 'info',
                  message: '操作成功'
                });
                  _this.dialogFormVisible = false
                  _this.getList()
              }else{
                msgAlert(_this,response.rtnMsg)
              }
          })
        }else if(_this.dialogStatus === 'update'){
            service.update(qs.stringify(_this.temp)).then(response =>{
            if (response && response.rtnCode === '0') {
                  _this.$message({
                  type: 'info',
                  message: '操作成功'
                });
                  _this.dialogFormVisible = false
                  _this.getList()
              }else{
                msgAlert(_this,response.rtnMsg)
              }
          })
        }
    },
    //初始化data必要的参数.
    initParams(){
        return {
            dialogFormVisible: false,
            dialogStatus: '',
            textMap: {
              update: '修改',
              create: '新增'
            }
        }
    }
}

export default operator