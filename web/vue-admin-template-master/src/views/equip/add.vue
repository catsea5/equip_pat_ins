<template>
  <div class="app-container" style="text-align:center;">
    <el-radio-group v-model="labelPosition" size="small">
      <el-radio-button label="top">请输入设备信息</el-radio-button>
    </el-radio-group>
    <div style="margin: 20px" />
    <el-form
      :label-position="labelPosition"
      label-width="80px"
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
    >
      <el-form-item label="设备名称"  prop="equipName" :rules="rules.equipName">
        <el-input v-model="equipInfor.name" placeholder="请输入设备名称及编号"  style="width:200px;"/>
      </el-form-item>
      <el-form-item label="设备类型" prop="equipType" :rules="rules.equipType">
        <el-select v-model="equipInfor.type" placeholder="请选择设备类型">
          <el-option label="计算机" value="计算机"/>
          <el-option label="空调" value="空调"/>
          <el-option label="复印机" value="复印机"/>
          <el-option label="打印机" value="打印机"/>
          <el-option label="公共查询计算机" value="公共查询计算机"/>
        </el-select>
      </el-form-item>
      <el-form-item label="资产编号" prop="assetCode" :rules="rules.assetCode">
        <el-input v-model="equipInfor.assetCode" placeholder="请输入资产编号" style="width:200px;"/>
      </el-form-item>
      <el-form-item label="所在位置" prop="equipAnumber" :rules="rules.equipAnumber">
        <el-select v-model="equipInfor.aid" placeholder="请选择位置">
          <el-option v-for="(apart,index) in listAnumber" :key="index" :label="apart.anumber" :value="apart.aid"/>
        </el-select>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" :rows="4"  maxlength="50" show-word-limit v-model="equipInfor.commit" placeholder="请输入内容" style="width:200px;"/>
      </el-form-item>
      <el-button type="primary" size="mini" @click="addData()">确认</el-button>
      <el-button type="default" size="mini" @click="back()">返回</el-button>
    </el-form>
  </div>
</template>
<script>
import equipApi from '@/api/equip'
import apartApi from '@/api/apartment'
export default {
  // 定义数据
  data() {

    return {
      equipInfor: {
        name: '',
        type: null,
        aid: null,
        assetCode: '',
        commit: ''
      },
      labelPosition: 'top',
      listAnumber: null,
      rules:  {
      equipName: [{ required: true, message: ' ', trigger: 'blur' }],
      equipType: [{ required: true, message: ' ', trigger: 'blur' }],
      assetCode: [{ required: true, message: ' ', trigger: 'blur' }],
      equipAnumber: [{ required: true, message: ' ', trigger: 'blur' }]
    }
    }
  },

  // 当页面加载时获取数据
  created() {
    this.getAllAnumber()
  },

  methods: {
    getAllAnumber() {
      apartApi.findAnumber().then(
        response => {
          this.listAnumber = response.data
        })
    },
    addEquip() {
      equipApi.add(this.equipInfor)
    },
    addData() {
      if (this.equipInfor.name === '') {
        alert('请输入设备名', '添加提示')
      } else if (this.equipInfor.type === null) {
        alert('请选择设备类型', '添加提示')
      } else if (this.equipInfor.aid === null) {
        alert('请选择所在位置', '添加提示')
      }else if (this.equipInfor.assetCode === null) {
        alert('请输入资产编号', '添加提示')
      } else {
        this.addEquip()
        alert('添加成功，返回上一页', '修改提示')
        this.back()
      }
    
    },
    back() {
      window.history.back(-1)
    }
  }
}
</script>
