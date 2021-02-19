<template>
  <el-row>
<!--    <el-col :span="2" class="write_border"></el-col>-->
    <el-col :span="24">
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
          label="文章名"
        >
          <template slot-scope="{row}">
            <span>{{ row.fileName }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="date"
          label="上传日期"
          >
          <template slot-scope="{row}">
            <span>{{ row.createTime  }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="examine"
          label="审核人"
          v-if="this.$store.getters.info.role=='ROLE_admin'"
        >
          <template slot-scope="{row}">
            <span>{{ row.examine}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="buttonColumn"
          label="操作"
          >
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看<i class="el-icon-view el-icon--right"></i></el-button>
            <el-button @click="handleClick(scope.row)" type="text" size="small" v-role-btn="['ROLE_admin']">修改<i class="el-icon-view el-icon-edit"></i></el-button>
            <el-button @click="deleteRow(scope.row)" type="text" size="small" v-role-btn="['ROLE_admin']" >删除<i class="el-icon-view el-icon-delete"></i></el-button>
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
    </el-col>
<!--    <el-col :span="2" class="write_border"></el-col>-->
  </el-row>
</template>
<style>
  .write_border {
    border-radius: 4px;
    min-height: 36px;
  }

</style>
<script>
export default {
  name: '',
  data () {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 20,
      totalCount: 1,
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
        url: 'api/article/getList',
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
    deleteRow: function (row) {
      const that = this
      console.log(row.fileName)
      this.$axios({
        method: 'post',
        url: 'api/article/delate/' + row.fileName
      }).then(function (response) {
        console.log(response.data)
        if (response.data == 1){
          that.$message.success('删除成功')
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
