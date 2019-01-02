<template>
  <div class="app-container">

    <div class="filter-container">
      <el-input  style="width: 200px;" class="filter-item" placeholder="类目id" v-model="listQuery.id">
      </el-input>
      <el-input  style="width: 200px;" class="filter-item" placeholder="类目名称" v-model="listQuery.categoryName">
      </el-input>
      <el-input  style="width: 200px;" class="filter-item" placeholder="上级类目id" v-model="listQuery.parentId">
      </el-input>
      <el-button class="filter-item" type="primary"  icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">新增</el-button>

    </div>  
    <tree-table :data="data" :columns="columns" :loadChildrenFunc="loadChildrenFunc"  border>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </tree-table>

     <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="100px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="类目名称" prop="categoryName">
          <el-input v-model="temp.categoryName"></el-input>
        </el-form-item>
        <el-form-item label="上级类目" prop="parentId">
          <el-input v-model="temp.parentName" 
            placeholder="选择上级类目" 
            @click.native="isShowSelect = !isShowSelect"
            suffix-icon="el-icon-arrow-down"></el-input>
          <el-tree v-if="isShowSelect && dialogFormVisible"
            empty-text="暂无数据"
            :expand-on-click-node="false"
            :data="inspectionObjectOptions"
            :props="defaultProps"
            :load="loadNode"
            lazy
            @node-click="handleNodeClick"
            class="objectTree" />
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
/**
  Auth: Lei.j1ang
  Created: 2018/1/19-14:54
*/
import treeTable from "@/components/TreeTable";
import waves from '@/directive/waves' // 水波纹指令
import categoryService from '@/api/category'
import qs from 'qs'
import { msgAlert } from '@/utils/alert'
import operator from '@/utils/operator'

export default {
  components: { treeTable },
  data() {
    return {
      listQuery: {
        id: null,
        categoryName: null,
        parentId:null
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '新增'
      },
      isShowSelect : false,
      inspectionObjectOptions:[],
      defaultProps: {
        id: "id",
        label: 'categoryName',
        children: 'children'
      },
      temp:{
        id:null,
        categoryName:null,
        parentId:null,
        parentName:null
      },
      rules: {
        categoryName: [{ required: true, message: '类目名称不能为空', trigger: 'change' }]
      },
      columns: [
        {
          text: "ID",
          value: "id"
        },
        {
          text: "类目名称",
          value: "categoryName"
        },
        {
          text: "上级类目id",
          value: "parentId"
        },
        {
          text: "状态",
          value: "status",
          filter: this.statusFilter
        }
      ],
      data: []
    };
  },
  created(){
    this.getList()
    this.args = [null, null, 'parentId']
  },
  methods: {
    getList(){
      if(!this.listQuery.id && !this.listQuery.categoryName && !this.listQuery.parentId){
        this.listQuery.parentId = 0
      }else{
        if(this.listQuery.parentId === 0 ){
          this.listQuery.parentId = null
        }
      }
      categoryService.list(qs.stringify(this.listQuery)).then(response => {
          if (response && response.rtnCode === '0') {
              this.data = response.rtnData.list
          }
      })
    },

    handleFilter() {
      this.getList()
    },
    handleCreate() {
      this.resetTemp();
      this.dialogFormVisible = true  
      this.isShowSelect = false
      this.dialogStatus = 'create'
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleUpdate(row){
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.isShowSelect = false
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
    },
    handleDelete(row){
      this.$alert('确定删除?', '提示', {
        confirmButtonText: '确定',
        showCancelButton: true,
        callback: action => {
          if(action === 'confirm'){
            let _this = this
            categoryService.delete(qs.stringify({"id":row.id})).then(response => {
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
    handleNodeClick(data, Node){
      // 如果是顶级父节点
      if (Node.parent.level !== 0) {
        this.temp.parentName = data.categoryName;
        this.temp.parentId = data.id;
        // 关闭选择器
        this.isShowSelect = false;
      }
    },
    // 加载树结点
    loadNode(node, resolve) {
      // 如果是顶级的父节点
      if (node.level === 0) {
        categoryService.list(qs.stringify({parentId:0})).then(response => {
          if (response && response.rtnCode === '0') {
              return resolve(response.rtnData.list);
          }
        })
      }else{
        categoryService.list(qs.stringify({parentId:node.data.id})).then(response => {
          if (response && response.rtnCode === '0') {
              return resolve(response.rtnData.list);
          }
        })
      }

    
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        categoryName: null,
        parentId: null
      }
    },
    saveData(){
      operator.saveOrUpdate(this,categoryService)
    },
    loadChildrenFunc(record){
      return new Promise(function(resolve, reject){
        categoryService.list(qs.stringify({"parentId":record.id})).then(response => {
            if (response && response.rtnCode === '0') {
                resolve(response.rtnData.list)
            }
        })
      })
    },
    statusFilter(status){
      const statusMap = {
        '1': '有效',
        '0': '无效'
      }
      return statusMap[status]
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.objectTree {
    position: absolute;
    overflow: auto;
    z-index: 100;
    width: 100%;
    height: 300px;
}
</style>