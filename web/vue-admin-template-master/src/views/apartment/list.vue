<template>
  <div class="app-container">
    <el-button type="primary" @click="addInfo()">添加</el-button>
    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%">

      <el-table-column prop="aid" label="序号"/>
      <el-table-column prop="anumber" label="位置"/>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="updateInfor(scope.row.aid,scope.row.anumber)">修改</el-button>
        </template>
      </el-table-column>

    </el-table>
  </div>
</template>
<script>
import apartmentApi from '@/api/apartment'

export default {

  // 定义数据
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: null, // banner列表
      apartObj: {
        anumber: null, // 房间号
        aid: 1 // 房间id
      }
    }
  },

  // 当页面加载时获取数据
  created() {
    this.fetchData()
  },

  methods: {
    // 调用api层获取数据库中的数据
    fetchData() {
      // 异步获取远程数据（ajax）
      apartmentApi.findAnumber().then(
        response => {
          this.list = response.data
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },
    addInfo() {
      this.$prompt('请输入位置进行添加', '添加提示', {
        inputErrorMessage: '输入不能为空',
        inputValidator: (value) => {//input校验
          if(!value) {
            return '输入不能为空';
          }
        },
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '添加成功'
          })
          this.apartObj.anumber = value
          this.addApartment()
      })
    },
    addApartment() {
      apartmentApi.addApartment(this.apartObj.anumber)
      this.$router.go(0)
      location.reload()
    },
    updateInfor(id,anumber) {
      this.apartObj.aid = id
      this.$prompt('请输入位置进行修改', '修改提示',{
        inputValue: anumber,
        inputErrorMessage: '输入不能为空',
        inputValidator: (value) => {//input校验
          if(!value) {
            return '输入不能为空';
          }
        },
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '修改成功'
        })
        this.apartObj.anumber = value
        this.updateApartment()
      })
    },
    updateApartment() {
      console.log(this.apartObj)
      apartmentApi.updateApartment(this.apartObj)
      this.$router.go(0)
      location.reload()
    }
  }
}
</script>
