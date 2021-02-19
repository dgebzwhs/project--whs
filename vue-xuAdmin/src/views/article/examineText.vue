<template>
  <div>
    <p style="font-size: 20px,margin:0 0 150px 0 ">{{ this.$route.params.fileName }}</p>
    <el-row v-loading="loading">
      <el-col :span="2" class="write_border"></el-col>
      <el-col :span="9">
        <div >
          原文：
          <el-input
            type="textarea"
            autosize
            placeholder=""
            v-model="YuanText">
          </el-input>
        </div>
      </el-col>
      <el-col :span="2" style="min-height: 36px"/>
      <el-col :span="9">
        <div>
          译文：
          <el-input
            type="textarea"
            autosize
            placeholder=""
            v-model="YiText"
          >
          </el-input>
        </div>
      </el-col>
      <el-col :span="2" style="min-height: 36px"/>
    </el-row>
    <el-row>
      <el-col :span="2" style="min-height: 36px"/>
      <el-button type="primary" @click="submit(1)">通过</el-button>
      <el-col :span="2" style="min-height: 36px"/>
      <el-button type="primary" @click="result='no'">不通过</el-button>
    </el-row>
    <el-row v-if="result=='no'">
      请输入不通过理由
      <el-col :span="2" style="min-height: 36px"/>
      <el-col :span="12">
        <el-input
          type="textarea"
          :autosize ={minRows:5}
          placeholder=""
          v-model="reason"
        >
        </el-input>
      </el-col>
      <el-col :span="1" style="min-height: 36px"/>
      <el-col :span="4">
        <el-button type="primary" @click="submit(0)">提交</el-button>
        <el-button type="primary" @click="result='yes'">关闭</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<style>
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
</style>
<script>
  export default {
    name: '',
    data () {
      // eslint-disable-next-line no-labels,no-unused-expressions
      return {
        ArticleText: '',
        YuanText: '',
        YiText: '',
        reason: '',
        result: '',
      }
    },
    mounted: function () {
      this.getArticle()
    },
    methods: {
      getArticle: function () {
        const that = this
        this.$axios({
          method: 'post',
          url: 'api/article/getArticle',
          params: {
            'fileName': this.$route.params.fileName
          }
        }).then(function (response) {
          that.YuanText = response.data[1]
          that.YiText = response.data[2]
        })
      },
      submit: function (result) {
        const that=this
        let query
        if (result==1){
          query={
            "fileName":this.$route.params.fileName,
            "status" : result,
            "reason":null
          }
        }else {
          if(this.reason==''){
            this.$message.error("不通过理由未输入!!!")
          }else
          query={
            "fileName":this.$route.params.fileName,
            "status":2,
            "reason":this.reason
          }
        }
        // 发送请求
        this.$axios({
          method: 'post',
          url: 'api/article/examine',
          params: query
        }).then(function (response) {
          console.log(response)
          that.$router.push({name: 'examineArticle'})
        })

      }
    }
  }
</script>

<style scoped>

</style>
