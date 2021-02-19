<template>
  <el-tabs type="border-card">
    <el-tab-pane label="分词">
      <el-button round @click.native.prevent="participle">分词</el-button>
      <el-button round @click.native.prevent="cibiao">词性标注</el-button>
      <p/>
      <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 90}"
        placeholder="请输入内容"
        v-model="textarea1">
      </el-input>
      分词结果
      <p/><p/>
      <div>
        <span v-for="re in result1" :key="re">{{re}} <el-divider direction="vertical"></el-divider></span>
      </div>
      词性标注结果
      <p/><p/>
      <div>
        <span v-for="re in result2" :key="re">{{re}} <el-divider direction="vertical"></el-divider></span>
      </div>

<!--      <div  v-for="re in result1" :key="re">-->
<!--        <span v-for="re in result1" :key="re">{{re}}</span>-->
<!--        <el-divider direction="vertical"/>-->
<!--      </div>-->
<!--      <el-input-->
<!--        type="textarea"-->
<!--        :autosize="{ minRows: 10, maxRows: 90}"-->
<!--        placeholder=""-->
<!--        :disabled="true"-->
<!--        v-model="result1">-->
<!--      </el-input>-->
    </el-tab-pane>
    <el-tab-pane label="断句">
      <el-button round @click.native.prevent="biaodian">断句</el-button>
      <el-button round @click.native.prevent="biao=true">标点</el-button>
      <p/>
      <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 90}"
        placeholder="请输入内容"
        v-model="textarea2">
      </el-input>
      断句结果
      <p></p><p></p>
      <div>
        <span v-for="re in result3" :key="re">{{re}} <el-divider direction="vertical"></el-divider></span>
      </div>
      标点结果
      <p></p><p></p>
      <div v-if="biao==true">
        {{result4}}
      </div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      textarea1: '',
      textarea2: '',
      result1: [],
      result2: [],
      result3: [],
      result4: [],
      biao: false
    }
  },
  methods: {
    participle: function () {
      const that = this
      this.$axios({
        method: 'post',
        url: 'api/Participle',
        params: {
          'words': this.textarea1
        }
      }).then(function (response) {
        that.result1 = response.data.fenci
        that.result2 = response.data.cixin
      })
    },
    cibiao: function () {
      const that = this
      this.$axios({
        method: 'post',
        url: 'api/cibiao',
        params: {
          'wordList': this.result1
        }
      }).then(function (response) {
        that.result2 = response.data
      })
    },
    biaodian: function () {
      const that = this
      this.$axios({
        method: 'post',
        url: 'api/biaodian',
        params: {
          'text': this.textarea2
        }
      }).then(function (response) {
        that.result4 = response.data.result
        that.result3 = response.data.resultList
      })
    }

  }
}
</script>

<style scoped>

</style>
