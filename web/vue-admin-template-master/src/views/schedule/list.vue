<template>
  <div class="app-container">

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="巡检计划">
        <el-select v-model="pid" placeholder="请选择巡检计划" @change="findScheduleList">
          <el-option v-for="(plan,index) in planList" :key="index" :label="plan.pname" :value="plan.pid" @click.native="findScheduleList(plan.pid)"/>
        </el-select>
      </el-form-item>
      <!-- 设备详细包括设备的基本信息、设备所在房间的信息 -->
      <el-button v-if="pid != null" type="primary" @click="getInfo()">添加</el-button>
    </el-form>
    <!-- 弹窗 -->
    <el-dialog :visible.sync="dialogFormVisible" title="分配巡检人员">
      <el-form :model="addObj">
        <el-row>
          <el-col :span="12">
            <el-form-item :label-width="formLabelWidth" label="巡检人员">
              <el-select v-model="addObj.uid" placeholder="请选择巡检人员">
                <el-option v-for="(user,index) in userList" :key="index" :label="user.uname" :value="user.uid"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label-width="formLabelWidth" label="房间号">
              <el-select v-model="addObj.aid" placeholder="请选择房间号">
                <el-option v-for="(apart,index) in apartmentList" :key="index" :label="apart.anumber" :value="apart.aid"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label-width="formLabelWidth" label="备注">
              <el-input type="textarea" :rows="10"  maxlength="200" show-word-limit v-model="addObj.commit" placeholder="请输入内容" style="width:200px;"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addScheduleInfo()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 列表 -->
    <el-table
      :data="scheduleList"
      stripe
      style="width: 100%">
      <el-table-column prop="uname" label="巡检人员"/>
      <el-table-column prop="anumber" label="巡检房间号"/>
      <el-table-column label="状态" width="200" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.state==0">未巡检</span>
          <span v-if="scope.row.state==1">已巡检</span>
        </template>
      </el-table-column>
      <el-table-column prop="commit" label="备注"/>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button v-if="pid != null" type="primary" size="mini" @click="deleteSchedule(scope.row.aid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>
<script>
import planApi from '@/api/plan'
import scheduleApi from '@/api/schedule'
import userApi from '@/api/userInfo'
import apartmentApi from '@/api/apartment'
export default {
  // 定义数据
  data() {
    return {
      planList: {}, // 巡检计划列表
      userList: {}, // 成员列表
      apartmentList: {}, // 房间号列表
      pid: null,
      state: 1, // 巡检计划状态
      scheduleList: [{}], // 安排表列表
      dialogFormVisible: false,
      formLabelWidth: '120px',
      addObj: {
        pid: null,
        uid: null,
        aid: null,
        commit: ''
      }
    }
  },

  // 当页面加载时获取数据
  created() {
    this.getPlanList()
  },

  methods: {
    getPlanList() {
      planApi.findPlanByState(this.state).then(response => {
        this.planList = response.data
      })
    },
    findScheduleList(pid) {
      scheduleApi.findScheduleList(pid).then(response => {
        this.scheduleList = response.data
      })
    },
    deleteSchedule(aid) {
      console.log(aid)
      scheduleApi.deleteSchedule(aid, this.pid).then(() => {
        return this.findScheduleList(this.pid)
      })
    },
    getInfo() {
      userApi.getUserIdAndName().then(response => {
        this.userList = response.data
        return apartmentApi.getAidAndAnumber(this.pid).then(response => {
          this.apartmentList = response.data
        })
      })
      this.dialogFormVisible = true
    },
    // 添加安排表数据
    addScheduleInfo() {
      this.addObj.pid = this.pid
      scheduleApi.addInfo(this.addObj).then(() => {
        this.dialogFormVisible = false
        this.addObj = {
          pid: null,
          uid: null,
          aid: null
        }
        return this.findScheduleList(this.pid)
      })
    }
  }
}
</script>

