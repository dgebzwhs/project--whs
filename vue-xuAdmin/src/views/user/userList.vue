<template>
  <el-row>
    <el-col :span="2" style="max-height: 36px"></el-col>
    <el-col :span="20">
      <el-table
        :data="tableData"
        border
        stripe
      >
        <el-table-column
          prop="number"
          label="序号"
          width="50">
          <template scope="scope">
            <span>{{ scope.$index + 1 }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="用户名"
        >
          <template slot-scope="{row}">
            <span>{{ row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="date"
          label="注册时间"
        >
          <template slot-scope="{row}">
            <span>{{ row.createTime  }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色"
        >
          <template slot-scope="{row}">
            <span>{{ row.authorities[0].description}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="buttonColumn"
          label="操作"
        >
          <template slot-scope="scope">
<!--            <el-button @click="handleClick(scope.row)" type="text" size="small">查看<i class="el-icon-view el-icon&#45;&#45;right"></i></el-button>-->
            <el-button @click="getRoles(scope.row)" type="text" size="small" v-role-btn="['ROLE_admin']">修改权限<i class="el-icon-view el-icon-edit"></i></el-button>
            <el-button @click="deleteRow(scope.row)" type="text" size="small" v-role-btn="['ROLE_admin']" >删除用户<i class="el-icon-view el-icon-delete"></i></el-button>
            <!--          </template>-->
            <!--          <template slot-scope="scope">-->
            <!--            -->
            <!--          </template>-->
            <!--          <template slot-scope="scope">-->
            <!--            -->
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[20, 30, 40, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount">
      </el-pagination>
      <el-dialog title="选择角色" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="角色" :label-width="formLabelWidth">
            <el-select  v-model="form.role" placeholder="请选择角色">
              <el-option
              v-for="item in form.roles"
              :key="item"
              :label="item"
              :value="item"
              />
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="gaiRole">确 定</el-button>
        </div>
      </el-dialog>
    </el-col>
        <el-col :span="2" style="max-height: 36px"></el-col>
  </el-row>
</template>

<script>
  export default {
    name: '',
    data(){
      return{
        tableData: [],
        pageNum: 1,
        pageSize: 20,
        totalCount: 1,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        chooseUser: '',
        form: {
          roles:[],
          role: ''
        }
      }
    },
    mounted: function () {
      this.getlist()
    },
    methods: {
      getlist: function () {
        const that = this
        this.$axios({
          method: 'post',
          url: 'api/sys-user/getList',
          params: {
            'pageNum': this.pageNum,
            'pageSize': this.pageSize
          }
        }).then(function (response) {
          that.tableData = response.data.list
          that.totalCount = response.data.total
        })
      },
      handleClick: function (row) {
        this.$router.push({name: 'articleText', params: { fileName: row.fileName}})
      },
      handleSizeChange: function (size) {
        this.pageSize = size
        this.getlist()
      },
      handleCurrentChange: function (currentPage) {
        console.log(currentPage)
        this.pageNum = currentPage
        this.getlist()
      },
      getRoles: function(row){
        this.chooseUser=row.username
        let roleName=row.authorities[0].name
        this.dialogFormVisible=true;
        const that = this
        this.$axios({
          method: 'post',
          url: 'api/sys-user/getRoles/',
          params: {
            "roleName": roleName
          }
        }).then(function (response) {
          that.form.roles=response.data
        })
      },
      gaiRole: function(){
        this.dialogFormVisible = false
        const that = this
        this.$axios({
          method: 'post',
          url: 'api/sys-user/gaiRole/',
          params: {
            "roleDes": this.form.role,
            "userName" :this.chooseUser
          }
        }).then(function (response) {
          if (response.data==1){
            that.getlist()
          }
        })
      },
      deleteRow: function (row) {
        const that = this
        this.$axios({
          method: 'post',
          url: 'api/sys-user/delate/' + row.username
        }).then(function (response) {
          console.log(response.data)
          if (response.data == 1){
            that.$message.success('删除成功')
            that.getlist()
          } else {
            that.$message.error('删除失败')
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
