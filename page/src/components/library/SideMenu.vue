<template>
  <el-menu
    class="categories"
    :default-active="cid"
    @select="handleSelect"
    active-text-color="red">
    <el-menu-item index="0">
      <i class="el-icon-menu"></i>
      <span slot="title">全部</span>
    </el-menu-item>
    <el-menu-item v-for="category in categorys" :index="category.id">
      <i class="el-icon-menu"></i>
      <span slot="title">{{category.name}}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    name: 'SideMenu',
    data() {
      return {
        cid: '0',
        categorys: []
      }
    },
    mounted() {
      this.getCategorys()
    },
    methods: {
      handleSelect(key, keyPath) {
        this.cid = key
        this.$emit('indexSelect')
      },
      getCategorys() {
        this.$axios.get("/category/all")
          .then(resp => {
            if (resp && resp.status === 200) {
              this.categorys = resp.data
            }
          })
      }
    }
  }
</script>

<style scoped>
  .categories {
    position: fixed;
    margin-left: 20px;
    top: 100px;
    width: 150px;
  }
</style>

