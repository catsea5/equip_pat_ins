<template>
  <div class="app-container">

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.keyword" placeholder="巡检计划/巡检人员/设备"/>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="searchObj.type" placeholder="请选择设备类型">
          <el-option v-for="(type,index) in listType" :key="index" :label="type" :value="type"/>
        </el-select>
      </el-form-item>
      <el-form-item label="房间">
        <el-select v-model="searchObj.anumber" placeholder="请选择房间号">
          <el-option v-for="(apart,index) in listAnumber" :key="index" :label="apart.anumber" :value="apart.anumber"/>
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
      <el-button type="primary" icon="el-icon-download" style="float: right;" @click="download()">导出</el-button>
    </el-form>

    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="scope" >
        <el-form label-position="left" inline class="demo-table-expand" v-for="(child,index) in scope.row.childList" :key="index">
          <el-form-item label="设备状态">
            <span v-if="child.state==1">设备正常</span>
            <span v-if="child.state==2">设备异常</span>
          </el-form-item>
          <el-form-item label="上传时间">
            <span>{{ child.uploadTime }}</span>
          </el-form-item>
          <el-form-item label="备注">
            <span>{{ child.commit }}</span>
          </el-form-item>
        </el-form>
      </template>
      </el-table-column>
      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (searchObj.page - 1) * searchObj.limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="pname" label="巡检计划"/>
      <el-table-column prop="uname" label="巡检人员"/>
      <el-table-column prop="ename" label="设备"/>
      <el-table-column prop="type" label="设备类型"/>
      <el-table-column prop="anumber" label="房间号"/>
      <el-table-column label="状态" width="200" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.childList[scope.row.childList.length-1].state==1">
            <el-button type="success" size="mini">设备正常</el-button>
          </span>
          <span v-if="scope.row.childList[scope.row.childList.length-1].state==2">
            <el-button type="warning" size="mini">设备异常</el-button>
          </span>
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
import resultApi from '@/api/result'
import equipApi from '@/api/equip'
import apartApi from '@/api/apartment'

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
        limit: 5
        // keyword: ''
      },
      listType: null, // 类型列表
      listAnumber: null // 房间列表
    }
  },

  // 当页面加载时获取数据
  created() {
    this.fetchData(1)
    this.getAllType()
    this.getAllAnumber()
  },

  methods: {
    // 调用api层获取数据库中的数据
    fetchData(page = 1) {
      console.log('翻页。。。' + page)
      // 异步获取远程数据（ajax）
      this.searchObj.page = page
      resultApi.getPageList(this.searchObj).then(
        response => {
          console.log(response)
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
        limit: 5
      }
      this.fetchData()
    },
    getAllType() {
      equipApi.findType().then(response => {
        console.log(response)
        this.listType = response.data
      })
    },
    getAllAnumber() {
      apartApi.findAnumber().then(
        response => {
          this.listAnumber = response.data
        })
    },
    download() {
      window.location.href = 'http://localhost:8201/result/exportData'
    }
  }
}
</script>
<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

</style>