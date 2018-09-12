<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="用户名" v-model="listQuery.username">
      </el-input>
      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">新增</el-button>

    </div>
    <el-table  :data="list" v-loading="listLoading" 
     style="width: 100%;">
      <el-table-column align="center" label="id" width="65">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="100">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>        
      <el-table-column align="center" label="密码" width="150">
        <template slot-scope="scope">
          <span>{{scope.row.password}}</span>
        </template>
      </el-table-column>    
      <el-table-column align="center" label="创建时间" width="160">
        <template slot-scope="scope">
          <span>{{scope.row.createTime | moment("YYYY-MM-DD HH:mm:ss")}}</span>
        </template>
      </el-table-column>    
      <el-table-column align="center" label="修改时间" width="160">
        <template slot-scope="scope">
          <span>{{scope.row.updateTime | moment("YYYY-MM-DD HH:mm:ss")}}</span>
        </template>
      </el-table-column>   
      <el-table-column align="center" label="最后登录ip" width="150">
        <template slot-scope="scope">
          <span>{{scope.row.loginIp}}</span>
        </template>
      </el-table-column> 
      <el-table-column align="center" label="最后登录时间" width="160">
        <template slot-scope="scope">
          <span>{{scope.row.lastLoginTime | moment("YYYY-MM-DD HH:mm:ss")}}</span>
        </template>
      </el-table-column> 
      <el-table-column align="center" label="状态" width="100">
        <template slot-scope="scope">
          <el-tag>{{scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column> 
      <el-table-column align="center" label="操作" width="230" class-name="small-padding fixed-width">
         <template slot-scope="scope">
           <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
           <el-button type="success" v-if="scope.row.status == 1" size="mini" @click="handleStop(scope.row)">停用</el-button>
           <el-button v-if="scope.row.status == 0" size="mini" @click="handleStop(scope.row)">启用</el-button>
           <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>

         </template>   
      </el-table-column>  
    </el-table>


    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.size" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="temp.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="temp.password"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button  type="primary" @click="saveData">保存</el-button>
      </div>
    </el-dialog>  


  </div>
</template>

<script>

import waves from '@/directive/waves' // 水波纹指令
import adminUserService from '@/api/adminUser'
import qs from 'qs'
import { msgAlert } from '@/utils/alert'



export default {
    directives: {
      waves
    },
    data() {
        return{
            list:null,
            listLoading: true,
            total: null,
            listQuery:{
                username:null,
                page: 1,
                size: 20
            },
            dialogFormVisible: false,
            dialogStatus: '',
            textMap: {
              update: '修改',
              create: '新增'
            },
            temp: {
              id: undefined,
              username: '',
              password: ''
            },
            rules: {
              username: [{ required: true, message: '用户名不能为空', trigger: 'change' }],
              password: [{ required: true, message: '密码不能为空', trigger: 'change' }]
            },
        }
    },
    created() {
      this.getList()
    },
    methods:{
        getList(){
           this.listLoading = true
           adminUserService.list(qs.stringify(this.listQuery)).then(response => {
               if (response && response.rtnCode === '0') {
                   this.list = response.rtnData.list
                   this.total = response.rtnData.total

               }
           })
           this.listLoading = false
        },
        handleFilter(){
          this.listQuery.page = 1
          this.getList()
        },
        resetTemp() {
          this.temp = {
            id: undefined,
            username: '',
            password: ''
          }
        },
        handleCreate(){
          this.resetTemp()
          this.dialogFormVisible = true  
          this.dialogStatus = 'create'
          this.$nextTick(() => {
            this.$refs['dataForm'].clearValidate()
          })
        },
        handleSizeChange(val) {
          this.listQuery.size = val
          this.getList()
        },
        handleCurrentChange(val) {
          this.listQuery.page = val
          this.getList()
        },
        handleUpdate(row){
            this.temp = Object.assign({}, row) // copy obj
            this.dialogStatus = 'update'
            this.dialogFormVisible = true
            this.$nextTick(() => {
              this.$refs['dataForm'].clearValidate()
            })
        },
        handleStop(row){
          this.$alert('确定操作?', '提示', {
            confirmButtonText: '确定',
            showCancelButton: true,
            callback: action => {
              if(action === 'confirm'){
              let _this = this
              adminUserService.updateStatus(qs.stringify({"id":row.id,"status":row.status})).then(response => {
                if (response && response.rtnCode === '0') {
                  _this.$message({
                    type: 'info',
                    message: '操作成功'
                  });
                  _this.getList()
                }else{
                  msgAlert(_this,response.rtnMsg)
                }
              })
             } 
            }
          });  
        },
        handleDelete(row){
          this.$alert('确定删除?', '提示', {
            confirmButtonText: '确定',
            showCancelButton: true,
            callback: action => {
              if(action === 'confirm'){
                let _this = this
                adminUserService.delete(qs.stringify({"id":row.id})).then(response => {
                  if (response && response.rtnCode === '0') {
                    _this.$message({
                      type: 'info',
                      message: '操作成功'
                    });
                    _this.getList()
                  }else{
                    msgAlert(_this,response.rtnMsg)
                  }
                })
              }
            }
          });  
          


        },
        saveData(){
          //新增数据.
          if(this.dialogStatus === 'create'){
              let _this = this
              adminUserService.add(qs.stringify(_this.temp)).then(response => {
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
          }else if(this.dialogStatus === 'update'){
            let _this = this
            adminUserService.update(qs.stringify(_this.temp)).then(response =>{
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
        }

    },
    filters:{
      statusFilter(status){
        const statusMap = {
          '1': '有效',
          '0': '无效'
        }
        return statusMap[status]
      }
    }
}

</script>
