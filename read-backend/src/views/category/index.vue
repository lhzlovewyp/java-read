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
    <tree-table :data="data" :columns="columns"  border></tree-table>
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

export default {
  components: { treeTable },
  data() {
    return {
      listQuery: {
        id: null,
        categoryName: null,
        parentId:0
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
          value: "status"
        }
      ],
      data: []
    };
  },
  created(){
    this.getList()
  },
  methods: {
    getList(){
      categoryService.list(qs.stringify(this.listQuery)).then(response => {
          if (response && response.rtnCode === '0') {
              this.data = response.rtnData.list
          }
      })
    },
    handleFilter() {},
    handleCreate() {}
  }
};
</script>
