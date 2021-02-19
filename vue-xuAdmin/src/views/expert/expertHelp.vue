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
<!--        <el-table-column-->
<!--          prop="id"-->
<!--          label="id"-->
<!--          style="display: none"-->
<!--        >-->
<!--          <template slot-scope="{row}">-->
<!--            <span>{{ row.id }}</span>-->
<!--          </template>-->
<!--        </el-table-column>-->
        <el-table-column
          prop="text"
          label="内容"
        >
          <template slot-scope="{row}">
            <span>{{ row.text }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="date"
          label="时间"
        >
          <template slot-scope="{row}">
            <span>{{ row.createTime  }}</span>
          </template>
        </el-table-column>
<!--        <el-table-column-->
<!--          prop="roleName"-->
<!--          label="角色"-->
<!--        >-->
<!--          <template slot-scope="{row}">-->
<!--            <span>{{ row.authorities[0].description}}</span>-->
<!--          </template>-->
<!--        </el-table-column>-->
        <el-table-column
          prop="buttonColumn"
          label="操作"
        >
          <template slot-scope="scope">
            <!--            <el-button @click="handleClick(scope.row)" type="text" size="small">查看<i class="el-icon-view el-icon&#45;&#45;right"></i></el-button>-->
            <el-button @click="$router.push({name:'expertMain',params:{ 'id':scope.row.id}})" type="text" size="small" >修正<i class="el-icon-view el-icon-edit"></i></el-button>
<!--            <el-button @click="deleteRow(scope.row)" type="text" size="small" >删除用户<i class="el-icon-view el-icon-delete"></i></el-button>-->
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
<!--      <el-dialog title="选择角色" :visible.sync="dialogFormVisible">-->
<!--        <el-form :model="form">-->
<!--          <el-form-item label="角色" :label-width="formLabelWidth">-->
<!--            <el-select  v-model="form.role" placeholder="请选择角色">-->
<!--              <el-option-->
<!--                v-for="item in form.roles"-->
<!--                :key="item"-->
<!--                :label="item"-->
<!--                :value="item"-->
<!--              />-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--        <div slot="footer" class="dialog-footer">-->
<!--          <el-button @click="dialogFormVisible = false">取 消</el-button>-->
<!--          <el-button type="primary" @click="gaiRole">确 定</el-button>-->
<!--        </div>-->
<!--      </el-dialog>-->
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
        show: false
        // dialogFormVisible: false,
        // formLabelWidth: '120px',
        // chooseUser: '',
        // form: {
        //   roles:[],
        //   role: ''
        // }
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
          url: 'api/expert/list',
          params: {
            'pageNum': this.pageNum,
            'pageSize': this.pageSize
          }
        }).then(function (response) {
          that.tableData = response.data.list
          that.totalCount = response.data.total
        })
      },
      handleSizeChange: function (size) {
        this.pageSize = size
        this.getlist()
      },
      handleCurrentChange: function (currentPage) {
        console.log(currentPage)
        this.pageNum = currentPage
        this.getlist()
      }
    }
  }
</script>

<style scoped>

</style>
