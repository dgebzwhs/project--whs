<template>
  <el-row >
<!--    <el-col :span="2"><div class="grid-content"></div></el-col>-->
    <el-col :span="18">
      <el-container style="height: 100%;  border: 1px solid lightsteelblue">
        <el-container>
          <!--      <el-aside width="20%" >-->
          <!--        left-->
          <!--      </el-aside>-->
          <el-header style="text-align: right; font-size: 24px">
            古汉语-汉语翻译系统
          </el-header>
          <el-main style="height: 100%">
            <el-button type="primary" @click.native.prevent="totranslate">翻译</el-button>
            <el-row :span="24">

              <el-col :span="12">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 10, maxRows: 40}"
                  placeholder="请输入翻译文本"
                  v-model="textarea1">
                </el-input>
              </el-col>
              <el-col :span="12" v-loading="loading">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 10, maxRows: 40}"
                  placeholder="翻译结果显示"
                  v-model="textarea2">
                </el-input>
              </el-col>
<!--              <el-col :span="1">-->
<!--                <div class="grid-content"></div>-->
<!--              </el-col>-->
            </el-row>
<!--            <p></p>-->
<!--            <el-row :span="24">-->
<!--              <el-col :span="2">-->
<!--                <div class="grid-content"></div>-->
<!--              </el-col>-->
<!--              <el-col :span="9">-->
<!--                <el-input-->
<!--                  type="textarea"-->
<!--                  :autosize="{ minRows: 10, maxRows: 40}"-->
<!--                  placeholder="调用百度翻译接口"-->
<!--                  v-model="textarea3">-->
<!--                </el-input>-->
<!--              </el-col>-->
<!--              <el-col :span="2">-->
<!--                <div class="grid-content"></div>-->
<!--              </el-col>-->
<!--              <el-col :span="9">-->
<!--                <el-input-->
<!--                  type="textarea"-->
<!--                  :autosize="{ minRows: 10, maxRows: 40}"-->
<!--                  placeholder="调用百度翻译接口"-->
<!--                  v-model="textarea4">-->
<!--                </el-input>-->
<!--              </el-col>-->
<!--              <el-col :span="2">-->
<!--                <div class="grid-content"></div>-->
<!--              </el-col>-->
<!--            </el-row>-->
          </el-main>
        </el-container>
      </el-container>
    </el-col>
    <el-col :span="2"><div class="grid-content"></div></el-col>
  </el-row>

</template>
  <style>
    .grid-content {
      border-radius: 4px;
      min-height: 36px;
    }
    .el-aside {
      color: #333;
    }
  </style>

<script>
// import { translate } from '@/api/translate/trans'
export default {
  data () {
    return {
      textarea1: '',
      textarea2: '',
      loading: false
      // textarea3: '',
      // textarea4: ''
    }
  },
  methods: {
    totranslate () {
      // eslint-disable-next-line standard/object-curly-even-spacing
      // translate({ 'words': 'test'})
      const that = this
      this.loading = true
      this.$axios({
        method: 'post',
        url: 'api/translate',
        params: {
          'words': this.textarea1
        }
      }).then(function (response) {
        console.log(response.data)
        that.textarea2 = response.data
        that.loading=false
      })
    }
  }
}
</script>

<style scoped>

</style>
