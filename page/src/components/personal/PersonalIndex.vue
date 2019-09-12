<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <personal-nav></personal-nav>
      </el-aside>
      <el-main style="padding-top: 50px;padding-left: 50px">
        <module></module>
        <module></module>
        <module></module>
        <div style="float: left;width: 280px;height: 150px;margin: 30px"><i class="el-icon-circle-plus-outline"
                                                                            style="margin: 50px 100px;font-size: 100px"></i>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import PersonalNav from './PersonalNav'
  import Module from './Module'

  export default {
    name: 'PersonalIndex',
    components: {
      PersonalNav, Module
    }
  }
</script>

<style scoped>

</style>
