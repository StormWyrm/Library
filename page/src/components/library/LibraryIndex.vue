<template>
  <el-container>
    <el-aside>
      <side-menu @indexSelect="listByCategory" ref="sideMenu"></side-menu>
    </el-aside>
    <el-main>
      <books class="books-area" ref="booksArea"></books>
    </el-main>
  </el-container>
</template>

<script>
  import Books from './Books'
  import SideMenu from './SideMenu'

  export default {
    name: 'LibraryIndex',
    components: {Books, SideMenu},
    methods: {
      listByCategory() {
        let cid = this.$refs.sideMenu.cid
        let url = "";
        if (cid === "0") {
          url = "/library/books"
        } else {
          url = "/library/category/" + cid + "/books"
        }
        this.$axios.get(url).then(resp => {
            let result = resp.data;
            if (result.code === 200) {
              this.$refs.booksArea.books = result.data
            }
          }
        )
      }
    }
  }
</script>

<style scoped>
  .books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
