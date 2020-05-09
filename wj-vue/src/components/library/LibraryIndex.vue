<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <!-- ref 设置了个引用名，所以能在method中通过这个名称获取到这个子组件里的数据
           而 @indexSelect 是触发事件，可以任意起名， 在SideMenu中被触发了 -->
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <books class="books-area" ref="booksArea"></books>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Books from './Book'

export default {
  name: 'AppLibrary',
  components: {Books, SideMenu},
  methods: {
    listByCategory () {
      var _this = this
      // 这里获得了其他组件（SideMenu）里的数据
      var cid = this.$refs.sideMenu.cid
      var url = 'categories/' + cid + '/books'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          _this.$refs.booksArea.books = resp.data
          _this.$refs.currentPage = 1
        }
      })
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
