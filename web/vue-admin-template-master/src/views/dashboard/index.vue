<template>
    <div class="dashboard-container">
      <div class="dashboard-text">欢迎管理员: {{ name }}</div>
    <br>
<!-- 列表 -->
<el-table style="width: 100%;font-size: 20px;" :data="list">
  <el-table-column type="expand" >
    <template slot-scope="scope">
      <el-form label-position="left" inline class="demo-table-expand" >
        <el-form-item label="描述">
          <br>
          <span v-for="(context,index) in scope.row.context" :key="index">{{ context.context }} <br> </span>
        </el-form-item>
      </el-form>
    </template>
  </el-table-column>
  <el-table-column label="使用手册" prop="mokuai"> </el-table-column>

</el-table>


</div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  data() {
    return {
      list: [
        
      {"mokuai":"用户模块","context" : [{"context":"用户列表:  可以对用户信息进行列表查看，点击添加输入对应信息添加用户，点击修改可以修改对应用户信息，锁定与解锁功能可以修改用户状态"},
                            {"context":"认证审核列表:  能展示出当前在微信小程序注册申请的用户，管理员可对这些用户进行审核。"}
                            ]},
      {"mokuai":"设备模块","context" : [{"context":"设备列表:  能够以列表形式罗列出设备的信息，点击添加输入对应信息添加设备，点击修改可以修改对应用户信息（包括状态），锁定与解锁功能可以选择设备是否使用，点击二维码可以生成有关设备信息的二维码（用于巡检人员巡检时进行设备扫描）。"},
                            {"context":"位置列表:  能够展示当前已经添加的位置信息，如果要添加新的位置信息就点击添加按钮，如果要修改原来的位置信息可以点击修改。"}
                            ]},
      {"mokuai":"巡检模块","context" : [{"context":"计划列表:  能够以列表形式展示所有巡检计划信息，使用状态表明当前巡检计划是否开始或者是否已经过期，如果要添加巡检列表可以点击添加按钮然后输入或选择相应信息。"},
                            {"context":"分配巡检人员:  在添加巡检计划之后，可以选择对应的巡检计划，然后点击添加就能够分配巡检人员（人员 - 位置），巡检人员与位置的关系是一对多。"}
                            ]},
      {"mokuai":"维修模块"},
      {"mokuai":"历史模块","context" : [{"context":"历史列表:  能够展示出巡检人员以及上传的设备巡检信息，可以对这些信息进行筛查管理，如果需要导出文档直接点击导出按钮。"}
                            ]}]
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
