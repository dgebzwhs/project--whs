<template>
<!--  <el-row>-->
<!--    <el-col :span="18">-->
<!--      <div >-->
<!--        原文：-->
<!--        <el-input-->
<!--          type="textarea"-->
<!--          :autosize="{ minRows: 5, maxRows: 100}"-->
<!--          placeholder=""-->
<!--          v-model="YuanText">-->
<!--        </el-input>-->
<!--        译文：-->
<!--        <el-input-->
<!--          type="textarea"-->
<!--          :autosize="{ minRows: 5, maxRows: 100}"-->
<!--          placeholder=""-->
<!--          v-model="YiText">-->
<!--        </el-input>-->
<!--      </div>-->
<!--    </el-col>-->
<!--    <el-col :span="6" class="write_border">-->
<!--    </el-col>-->
<!--  </el-row>-->
  <el-row>
    <el-col :span="2" class="write_border"></el-col>
    <el-col :span="9">
      <div>
        原文：
        <el-input
          type="textarea"
          :autosize="{ minRows: 5, maxRows: 100}"
          placeholder=""
          v-model="YuanText">
        </el-input>
      </div>
    </el-col>
    <el-col :span="2" class="write_border"/>
    <el-col :span="9">
      <div>
        译文：
        <el-input
          type="textarea"
          :autosize="{ minRows: 5, maxRows: 100}"
          placeholder=""
          v-model="YiText"
        >
        </el-input>
      </div>
    </el-col>
    <el-col :span="2" class="write_border"/>
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
  name: 'artivleText',
  data () {
    // eslint-disable-next-line no-labels,no-unused-expressions
    return {
      ArticleText: '',
      YuanText: '',
      YiText: ''
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
    }
  }
}
</script>

<style scoped>

</style>
