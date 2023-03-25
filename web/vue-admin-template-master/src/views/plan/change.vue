<template>
  <div class="app-container" style="text-align:center;">
    <el-radio-group v-model="labelPosition" size="small">
      <el-radio-button label="top">请输入巡检计划信息</el-radio-button>
    </el-radio-group>
    <div style="margin: 20px" />
    <el-form
      :label-position="labelPosition"
      label-width="80px"
    >
      <el-form-item label="巡检计划名称">
        <el-input v-model="planInfo.pname" style="width:200px;"/>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker v-model="planInfo.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"/>
      </el-form-item>
      <el-form-item label="截止时间">
        <el-date-picker v-model="planInfo.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"/>
      </el-form-item>
      <el-button v-if="id == null" type="primary" size="mini" @click="addData()">添加</el-button>
      <el-button v-if="id != null" type="primary" size="mini" @click="updateData()">修改</el-button>
      <el-button type="default" size="mini" @click="back()">返回</el-button>
    </el-form>
  </div>
</template>
<script>
import planApi from '@/api/plan'
export default {
  // 定义数据
  data() {
    return {
      id: this.$route.params.id,
      labelPosition: 'top',
      planInfo: {
        pid: '',
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
    console.log(this.id)
    // eslint-disable-next-line valid-typeof
    if (typeof (this.id) === "undefined") {
      console.log('添加')
    } else {
      console.log('修改')
      this.fetchDataById()
    }
  },

  methods: {
    // 根据id查询巡检计划记录
    fetchDataById() {
      planApi.query(this.id).then(response => {
        console.log(response.data)
        this.planInfo = response.data
      })
    },
    updateData() {
      if (this.planInfo.pname === '') {
        alert('请输入巡检计划名称', '提示')
      } else if (this.planInfo.startTime === '') {
        alert('请选择开始时间', '提示')
      } else if (this.planInfo.endTime === '') {
        alert('请选择截止时间', '提示')
      } else {
        this.planUpdateInfo.pid = this.id
        this.planUpdateInfo.name = this.planInfo.pname
        this.planUpdateInfo.startTime = this.planInfo.startTime
        this.planUpdateInfo.endTime = this.planInfo.endTime
        console.log(this.planUpdateInfo)
        planApi.update(this.planUpdateInfo)
        alert('修改成功，返回上一页', '提示')
        this.back()
      }
    },
    addData() {
      if (this.planInfo.pname === '') {
        alert('请输入巡检计划名称', '提示')
      } else if (this.planInfo.startTime === '') {
        alert('请选择开始时间', '提示')
      } else if (this.planInfo.endTime === '') {
        alert('请选择截止时间', '提示')
      } else {
        this.planUpdateInfo.name = this.planInfo.pname
        this.planUpdateInfo.startTime = this.planInfo.startTime
        this.planUpdateInfo.endTime = this.planInfo.endTime
        planApi.add(this.planUpdateInfo)
        alert('添加成功，返回上一页', '提示')
        this.back()
      }
    },
    back() {
      window.history.back(-1)
    }
  }
}
</script>
