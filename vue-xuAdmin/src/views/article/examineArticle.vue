<template>
 <div>
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
         <span>{{ row.name }}</span>
       </template>
     </el-table-column>
     <el-table-column
       prop="upload"
       label="上传人"
     >
       <template slot-scope="{row}">
         <span>{{ row.upload }}</span>
       </template>
     </el-table-column>
     <el-table-column
       prop="date"
       label="上传日期"
     >
       <template slot-scope="{row}">
         <span>{{ row.uploadTime  }}</span>
       </template>
     </el-table-column>
     <el-table-column
       prop="examine"
       label="审核人"
     >
       <template slot-scope="{row}">
         <span>{{ row.examine}}</span>
         <span v-if="row.examine==null">未审核</span>
       </template>
     </el-table-column>
     <el-table-column
       prop="examineTime"
       label="审核日期"
     >
       <template slot-scope="{row}">
         <span>{{ row.examineTime }}</span>
         <span v-if="row.examineTime==null">未审核</span>
       </template>
     </el-table-column>
     <el-table-column
       prop="status"
       label="状态"
     >
       <template slot-scope="{row}">
         <span v-if="row.status==2" style="color: red">未通过</span>
         <span v-if="row.status==1" style="color: green">已审核</span>
         <span v-if="row.status==0" style="color: cornflowerblue">待审核</span>
       </template>
     </el-table-column>
     <el-table-column
       prop="buttonColumn"
       label="操作"
     >
       <template slot-scope="scope">
         <el-button @click="handleClick1(scope.row)" v-if="scope.row.status==1" type="text" size="small">查看<i class="el-icon-view el-icon--right"></i></el-button>
         <el-button @click="handleClick2(scope.row)" v-if="scope.row.status==0" type="text" size="small">审核<i class="el-icon-view el-icon--right"></i></el-button>
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
 </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      tableData: [],
      pageNum: 1,
      pageSize: 20,
      totalCount: 1
    }
  },
  mounted () {
    this.getExamine()
  },
  methods: {
    getExamine: function () {
      const that = this
      this.$axios({
        method: 'post',
        url: 'api/article/examineList',
        params: {
          'pageNum': this.pageNum,
          'pageSize': this.pageSize
        }
      }).then(function (response) {
        console.log(response.data)
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
    },
    handleClick1: function (row) {
      this.$router.push({name: 'articleText', params: { fileName: row.name}})
    },
    handleClick2: function (row) {
      this.$router.push({name: 'examineText', params: { fileName: row.name}})
    }
  }
}
</script>

<style scoped>

</style>
