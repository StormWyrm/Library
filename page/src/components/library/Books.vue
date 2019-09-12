<template>
  <div>
    <div v-if="books.length > 0">
      <el-row style="height: 840px;">
        <el-tooltip effect="dark" placement="right"
                    v-for="item in books.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                    :key="item.id">
          <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.title}}</p>
          <p slot="content" style="font-size: 13px;margin-bottom: 6px">
            <span>{{item.author}}</span> /
            <span>{{item.date}}</span> /
            <span>{{item.press}}</span>
          </p>
          <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
          <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px"
                   class="book"
                   bodyStyle="padding:10px" shadow="hover">
            <div class="cover" @click="editBook(item)">
              <img :src="item.cover" alt="封面">
            </div>
            <div class="info">
              <div class="title">
                <a href="">{{item.title}}</a>
              </div>
              <i class="el-icon-delete" @click="deleteBook(item.id)"></i>
            </div>
            <div class="author">{{item.author}}</div>
          </el-card>
        </el-tooltip>
        <edit-form @onSubmit="loadBooks()" ref="edit"></edit-form>
      </el-row>
      <el-row>
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pagesize"
          :total="books.length">
        </el-pagination>
      </el-row>
    </div>
    <div v-else>
      <p>
        该分类下没有任何书籍,点击右边+号，可以添加书籍
        <edit-form @onSubmit="loadBooks()" ref="edit"></edit-form>
      </p>
    </div>

  </div>
</template>

<script>
  import EditForm from './EditForm'
  import SearchBar from './SearchBar'

  export default {
    name: 'Books',
    components: {EditForm, SearchBar},
    data() {
      return {
        books: [],
        currentPage: 1,
        pagesize: 17
      }
    },
    mounted: function () {
      this.loadBooks()//挂载的时候vue代码替换成html时候加载所有图书
    },
    methods: {
      loadBooks() {
        this.$axios.get('/library/books').then(resp => {
          debugger
          let result = resp.data;
          if (result.code === 200) {
            this.books = result.data
          }
        })
      },
      handleCurrentChange: function (currentPage) {
        this.currentPage = currentPage
        console.log(this.currentPage)
      },
      searchResult() {
        this.$axios.post('/library/search', {
          keywords: this.$refs.searchBar.keywords
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.books = resp.data
          }
        })
      },
      deleteBook(id) {
        this.$confirm('此操作将永久删除该书籍, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios.post('/library/delete', {id: id}).then(resp => {
              if (resp && resp.status === 200) {
                this.loadBooks()
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
        // alert(id)
      },
      editBook(item) {
        this.$refs.edit.dialogFormVisible = true
        this.$refs.edit.form = {
          id: item.id,
          cover: item.cover,
          title: item.title,
          author: item.author,
          date: item.date,
          press: item.press,
          abs: item.abs,
          category: {
            id: item.category.id.toString(),
            name: item.category.name
          }
        }
      }
    }
  }
</script>
<style scoped>

  .cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 115px;
    height: 172px;
    /*margin: 0 auto;*/
  }

  .title {
    font-size: 14px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }

  .el-icon-delete {
    cursor: pointer;
    float: right;
  }

  .switch {
    display: flex;
    position: absolute;
    left: 780px;
    top: 25px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }

</style>
