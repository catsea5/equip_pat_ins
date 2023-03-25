<template>
  <div class="app-container">

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.keyword" placeholder="关键字"/>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="searchObj.type" placeholder="请选择设备类型">
          <el-option v-for="(type,index) in listType" :key="index" :label="type" :value="type"/>
        </el-select>
      </el-form-item>
      <el-form-item label="位置">
        <el-select v-model="searchObj.anumber" placeholder="请选择所在位置">
          <el-option v-for="(apart,index) in listAnumber" :key="index" :label="apart.anumber" :value="apart.anumber"/>
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>

      <router-link :to="'/equip/add'">
        <!-- 设备详细包括设备的基本信息、设备所在房间的信息 -->
        <el-button type="primary" style="margin-left: 10px;">添加</el-button>
    </router-link>
  </el-form>
    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="scope">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="设备名">
            <span>{{ scope.row.ename }}</span>
          </el-form-item>
          <el-form-item label="设备类型">
            <span>{{ scope.row.type }}</span>
          </el-form-item>
          <el-form-item label="资产编码">
            <span>{{ scope.row.assetCode }}</span>
          </el-form-item>
          <el-form-item label="设备状态">
            <span v-if="scope.row.state==0">未使用</span>
            <span v-if="scope.row.state==1">使用中</span>
            <span v-if="scope.row.state==2">故障中</span>
            <span v-if="scope.row.state==3">维修中</span>
          </el-form-item>
          <el-form-item label="录入时间">
            <span>{{ scope.row.createTime }}</span>
          </el-form-item>
          <el-form-item label="变动时间">
            <span>{{ scope.row.updateTime }}</span>
          </el-form-item>
          <el-form-item label="备注">
            <span>{{ scope.row.commit }}</span>
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

      <el-table-column prop="ename" label="设备名"/>
      <el-table-column prop="type" label="设备类型"/>
      <el-table-column label="状态" width="200" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.state==0">未启用</span>
          <span v-if="scope.row.state==1">已启用</span>
          <span v-if="scope.row.state==2">故障中</span>
          <span v-if="scope.row.state==3">维修中</span>
        </template>
      </el-table-column>
      <el-table-column prop="apartment.anumber" label="所在位置"/>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <!-- <router-link :to="'/equip/update/'+scope.row.eid">
            <el-button type="primary" size="mini">修改</el-button>
          </router-link> -->
          <el-button type="primary" size="mini" @click="openUpdate(scope.row.eid)">修改</el-button>

          <el-button style="margin-left: 7px;" v-if="scope.row.state == 1" type="primary" size="mini" @click="lock(scope.row.eid, 0)">锁定</el-button>
          <el-button style="margin-left: 7px;" v-if="scope.row.state == 0" type="danger" size="mini" @click="lock(scope.row.eid, 1)">启用</el-button>
          <el-button style="margin-left: 7px;" v-if="scope.row.state == 2" type="danger" size="mini" @click="getUserList(scope.row.eid)">维修</el-button>
          <el-button style="margin-left: 7px;" v-if="scope.row.state == 3" type="info" size="mini" >维修中</el-button>
          <el-button type="primary" size="mini" @click="creatQrCode(scope.row.eid)">二维码</el-button>
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
    <el-container style="text-align: center;">
      <el-header>二维码</el-header>
      <el-main><div ref="qrcode"/></el-main>
    </el-container>
     <!-- 分配维修人员弹窗 -->
     <el-dialog :visible.sync="dialogFormVisible" title="选择维修人员">
      <el-form :model="addObj">
        <el-row>
          <el-col :span="12">
            <el-form-item :label-width="formLabelWidth" label="维修人员">
              <el-select v-model="uid" placeholder="请选择维修人员">
                <el-option v-for="(user,index) in userList" :key="index" :label="user.uname" :value="user.uid"/>
              </el-select>
            </el-form-item>
          </el-col>
            <el-col :span="12">
            <el-form-item :label-width="formLabelWidth" label="备注">
              <el-input type="textarea" :rows="10"  maxlength="200" show-word-limit v-model="commit" placeholder="请输入内容" style="width:200px;"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRepairInfo()">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改设备-抽屉 -->
    <el-drawer
      title="设备信息修改"
      :visible.sync="updatedrawer"
      :direction="direction"
      :before-close="handleClose">
      <el-form :label-position="right" label-width="80px">
        <el-form-item label="设备名称" >
          <el-input v-model="equipInfor.ename" style="width:200px;"/>
        </el-form-item>
         <el-form-item label="设备类型">
          <el-select v-model="equipInfor.type" placeholder="请选择设备类型">
            <el-option label="计算机" value="计算机"/>
            <el-option label="空调" value="空调"/>
            <el-option label="复印机" value="复印机"/>
            <el-option label="打印机" value="打印机"/>
            <el-option label="公共查询计算机" value="公共查询计算机"/>
          </el-select>
        </el-form-item>
        <el-form-item label="所在位置">
          <el-select v-model="anumber" placeholder="请选择位置">
             <el-option v-for="(apart,index) in listAnumber" :key="index" :label="apart.anumber" :value="apart.aid"></el-option> 
          </el-select>
        </el-form-item>
        <el-form-item label="设备状态" prop="state" >
          <el-select v-model="state" placeholder="请选择状态">
            <el-option label="未启用" value="未启用"/>
            <el-option label="已启用" value="已启用"/>
            <el-option label="故障中" value="故障中"/>
            <el-option label="维修中" value="维修中"/>
          </el-select>
        </el-form-item>
        <el-form-item label="录入时间">{{ equipInfor.createTime }}</el-form-item>
        <el-form-item label="更新时间">{{ equipInfor.updateTime }}</el-form-item> 
      </el-form> 
      <el-row style="text-align:center;">
        <el-button type="primary" @click="updateConfim()">修改</el-button>
      </el-row>
    </el-drawer>
  </div>

</template>
<script>
import equipApi from '@/api/equip'
import QRCode from 'qrcodejs2'
import apartApi from '@/api/apartment'
import userApi from '@/api/userInfo'

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
      userList: {}, // 成员列表
      dialogFormVisible: false,
      searchObj: {
        page: 1,
        limit: 5,
        keyword: '',
        type: '',
        anumber: ''
      },
      listType: null, // 类型列表
      listAnumber: null, // 房间列表
      eid: null,
      uid: null,
      commit: null,
      updatedrawer: false,
      anumber: null,
      equipInfor: {}, // 后端获取的设备信息
      state: '',
      equipUpdateInfor: {
        id: 1,
        name: '',
        type: null,
        aid: null
      }, // 设备修改的信息
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
      // 异步获取远程数据（ajax）
      this.searchObj.page = page
      equipApi.getPageList(this.searchObj).then(
        response => {
          console.log(response.data.list)
          this.list = response.data.list
          this.total = response.data.total
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },
    // 修改设备信息 
    openUpdate(eid){
      this.eid = eid
      this.getEquipInfoById()
      this.updatedrawer = true 
    },
    handleClose(done) {
        this.updatedrawer = false
        this.eid = null
        this.fetchData(1)
    },
    updateConfim() {
      if (this.equipInfor.ename === '') {
        this.$message({
          message: '请输入设备名',
          type: 'warning'
        })
      } else if (Object.is(this.equipInfor.apartment.anumber, this.anumber)) {
        this.equipUpdateInfor.aid = this.equipInfor.apartment.aid
        this.updateData()
        this.$message({
          message: '修改成功',
          type: 'success'
        })
      } else {
        this.equipUpdateInfor.aid = this.anumber
        this.updateData()
        this.$message({
          message: '修改成功',
          type: 'success'
        })
      }
    },
    updateData() {
      this.equipUpdateInfor.id = this.eid
      this.equipUpdateInfor.name = this.equipInfor.ename
      this.equipUpdateInfor.type = this.equipInfor.type
      if(this.state == "未启用"){
        this.equipUpdateInfor.state = 0
      } else if(this.state == "已启用"){
        this.equipUpdateInfor.state = 1
      } else if(this.state == "故障中"){
        this.equipUpdateInfor.state = 2
      } else if(this.state == "维修中"){
        this.equipUpdateInfor.state = 3
      }
      equipApi.update(this.equipUpdateInfor)
    },
    getEquipInfoById() {
      equipApi.getEquipById(this.eid).then(response => {
        this.equipInfor = response.data
        console.log(this.equipInfor)
        this.anumber = response.data.apartment.anumber
        if(this.equipInfor.state == 0) {
          this.state = '未启用'
        } else if(this.equipInfor.state == 1) {
          this.state = '已启用'
        } else if(this.equipInfor.state == 2) {
          this.state = '故障中'
        } else if(this.equipInfor.state == 3) {
          this.state = '维修中'
        }
      }) 
    },
    //分配维修人员
    addRepairInfo(){
      equipApi.addRepairInfo(this.uid,this.eid,this.commit).then(() => { 
        this.dialogFormVisible = false
        this.eid = null
        this.uid = null
      })
      equipApi.lock(this.eid, 3).then(() => {
        this.fetchData(this.page)
      })
    },
    getUserList(eid){
      this.eid = eid
      userApi.getUserIdAndName().then(response => {
        this.userList = response.data
      })
      this.dialogFormVisible = true
    },
    // 根据id生成二维码提供给前端扫描
    creatQrCode(id) {
      this.$refs.qrcode.innerHTML = ''
        var qrcode = new QRCode(this.$refs.qrcode, {
          text: 'eid:' + id, // 需要转换为二维码的内容
          width: 200,
          height: 200,
          colorDark: '#000000',
          colorLight: '#ffffff',
          correctLevel: QRCode.CorrectLevel.H,
        });
    },
    // 当页码发生改变的时候
    changeSize(size) {
      this.searchObj.limit = size
      this.fetchData(1)
    },
    //获取所有设备类型
    getAllType() {
      equipApi.findType().then(response => {
        console.log(response)
        this.listType = response.data
      })
    },
    //获取所有位置
    getAllAnumber() {
      apartApi.findAnumber().then(
        response => {
          this.listAnumber = response.data
        })
    },
    // 重置查询表单
    resetData() {
      console.log('重置查询表单')
      this.searchObj = {
        page: 1,
        limit: 5,
        keyword: '',
        type: '',
        anumber: ''
      }
      this.fetchData()
    },
    // 锁定
    lock(id, state) {
      this.$confirm('确定该操作吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return equipApi.lock(id, state)
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
<style>
  .el-header{
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    width: 250px;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 250px;
    width: 250px;
  }
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
