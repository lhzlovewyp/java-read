<template>
  <el-scrollbar wrapClass="scrollbar-wrapper">
    <el-menu
      mode="vertical"
      :show-timeout="200"
      :default-active="$route.path"
      :collapse="isCollapse"
      background-color="#304156"
      text-color="#bfcbd9"
      active-text-color="#409EFF"
    >

    <sidebar-item v-for="menu in menuMap" :key="menu.name" :item="menu" :base-path="menu.path"></sidebar-item>
    </el-menu>
  </el-scrollbar>
</template>

<script>
import SidebarItem from './SidebarItem.vue'
import menuMap from '@/router/menu'
import { mapGetters } from 'vuex'

export default {
  name: 'Sidebar',
  components: {
    SidebarItem
  },
  data () {
    return {
      menuMap: menuMap
    }
  },
  methods: {},
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    isCollapse () {
      return !this.sidebar.opened
    }
  }
}
</script>
