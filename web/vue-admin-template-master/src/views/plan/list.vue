<template>
  <div class="app-container">

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.keyword" placeholder="巡检计划名称"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchObj.startTime" type="date" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始日期"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="searchObj.endTime" type="date" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择截止日期"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
      <!-- <router-link :to="'/plan/change'">
        <el-button type="primary" style="margin-left: 10px;">添加</el-button>
      </router-link> -->
      <el-button type="primary" style="margin-left: 10px;" @click="adddrawer = true">添加</el-button>
    </el-form>

    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%">

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (searchObj.page - 1) * searchObj.limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="pname" label="名称"/>
      <el-table-column prop="startTime" label="开始时间"/>
      <el-table-column prop="endTime" label="结束时间"/>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/plan/change/'+scope.row.pid">
            <el-button v-if="scope.row.state == 1" type="primary" size="mini">修改</el-button>
          </router-link>
          <el-button v-if="scope.row.state == 1" type="primary" size="mini" @click="deletePlan(scope.row.pid)">删除</el-button>
          <el-button v-if="scope.row.state == 2" type="success" size="mini">进行中</el-button>
          <el-button v-if="scope.row.state == 0" type="info" size="mini">已过期</el-button>
        </template>
      </el-table-column>

    </el-table>
    <el-drawer
      title="添加巡检计划"
      :visible.sync="adddrawer"
      :direction="direction"
      :before-close="handleClose">
      <el-form
      :label-position="right"
      label-width="80px">
      <el-form-item label="巡检名称">
        <el-input v-model="planInfo.pname" style="width:200px;"/>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker v-model="planInfo.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"/>
      </el-form-item>
      <el-form-item label="截止时间">
        <el-date-picker v-model="planInfo.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"/>
      </el-form-item>
    </el-form>

      <el-row style="text-align:center;">
      <el-button type="primary" @click="addData()">添加</el-button>
    </el-row>
    </el-drawer>
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
import planApi from '@/api/plan'

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
        keyword: null,
        state: null,
        startTime: null,
        endTime: null
      },
      adddrawer: false,
      planInfo: {
        pname: '',
        state: '',
        isdelete: '',
        startTime: '',
        endTime: ''
      }, // 巡检计划信息
      planUpdateInfo: {
        pid: '',
        name: '',
        startTime: '',
        endTime: ''
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
      this.searchObj.page = page
      planApi.findPlanList(this.searchObj).then(response => {
        console.log(response.data)
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    addData() {
      if (this.planInfo.pname === '') {
        this.$message({
          message: '请输入巡检计划名称',
          type: 'warning'
        })
      } else if (this.planInfo.startTime === '') {
        this.$message({
          message: '请选择开始时间',
          type: 'warning'
        })
      } else if (this.planInfo.endTime === '') {
        this.$message({
          message: '请选择截止时间',
          type: 'warning'
        })
      } else {
        this.planUpdateInfo.name = this.planInfo.pname
        this.planUpdateInfo.startTime = this.planInfo.startTime
        this.planUpdateInfo.endTime = this.planInfo.endTime
        planApi.add(this.planUpdateInfo)
        this.$message({
          message: '添加成功',
          type: 'success'
        })
        this.back()
      }
    },
    handleClose(done) {
        this.adddrawer = false;
        this.fetchData(1)
      },
    deletePlan(id) {
      this.$confirm('确定该操作吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        planApi.delete(id).then(() => {
          this.fetchData(1)
        })
      })
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
        keyword: null,
        state: null,
        startTime: null,
        endTime: null
      }
      this.fetchData()
    }
  }
}
</script>
