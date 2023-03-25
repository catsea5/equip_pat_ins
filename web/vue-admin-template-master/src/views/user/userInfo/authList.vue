<template>
  <div class="app-container">

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.keyword" placeholder="姓名/邮箱"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%">

      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (searchObj.page - 1) * searchObj.limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="uemail" label="邮箱"/>
      <el-table-column prop="uname" label="姓名"/>
      <el-table-column prop="upassword" label="密码"/>
      <el-table-column label="状态" width="200" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.ustate==2">申请中</span>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="approval(scope.row.uid, 1)">同意</el-button>
          <el-button type="danger" size="mini" @click="approval(scope.row.uid, 3)">拒绝</el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="searchObj.page"
      :total="total"
      :page-size="searchObj.limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center;"
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="fetchData"
      @size-change="changeSize"
    />
  </div>
</template>
<script>
import userInfoApi from '@/api/userInfo'

export default {

  // 定义数据
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: null, // banner列表
      total: 0, // 数据库中的总记录数
      // page: 1, // 默认页码
      // limit: 5, // 每页记录数
      // keyword: '', // 查询表单对象
      searchObj: {
        page: 1,
        limit: 5,
        keyword: ''
      }
    }
  },

  // 当页面加载时获取数据
  created() {
    this.fetchData(1)
  },

  methods: {
    // 调用api层获取数据库中的数据
    fetchData(page = 1) {
      console.log('翻页。。。' + page)
      // 异步获取远程数据（ajax）
      this.searchObj.page = page
      userInfoApi.getApplyList(this.searchObj).then(
        response => {
          this.list = response.data.list
          this.total = response.data.total
          // 数据加载并绑定成功
          this.listLoading = false
          console.log(response)
        }
      )
    },

    // 当页码发生改变的时候
    changeSize(size) {
      console.log(size)
      this.limit = size
      this.fetchData(1)
    },

    // 重置查询表单
    resetData() {
      console.log('重置查询表单')
      this.searchObj = {
        page: 1,
        limit: 5,
        keyword: ''
      }
      this.fetchData()
    },
    // 审核
    approval(id, status) {
      this.$confirm('确定该操作吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return userInfoApi.approval(id, status)
      }).then((response) => {
        this.fetchData(this.page)
        if (response.code) {
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
        }
      })
    }
  }
}
</script>
