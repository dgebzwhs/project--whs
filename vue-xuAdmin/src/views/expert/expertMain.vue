<template>
  <div>
    <el-row style="height: 50%">
      <el-col :span="2" style="max-height: 36px"></el-col>

      <el-col :span="9">
        原文
        <div style="border: #00a8c6;">
          <el-input
            type="textarea"
            :autosize="{ minRows: 5, maxRows: 100}"
            placeholder=""
            v-model="yuan">
          </el-input>
        </div>
      </el-col>
      <el-col :span="1" style="max-height: 36px"></el-col>
      <el-col :span="9" >
        译文
        <div style="border: #00a8c6;">
          <el-input
            type="textarea"
            :autosize="{ minRows: 5, maxRows: 100}"
            placeholder=""
            v-model="yi">
          </el-input>
        </div>
        <div></div>
      </el-col>

      <el-col :span="2" style="max-height: 36px"></el-col>
    </el-row>

    <el-row style="">
      <el-col :span="18" style="max-height: 36px;width: 20px"></el-col>
      <el-col :span="2">
            <el-button  type="primary" @click="queren()">确认修改</el-button>
      </el-col>
      <el-col :span="2" style="max-height: 36px;width: 20px"></el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: '',
    data() {
      return{
        yuan : '',
        yi : ''
      }
    },
    mounted: function () {
        this.getdata()
    },
    methods: {
        getdata: function () {
          const that = this
          this.$axios({
            method: 'post',
            url: 'api/expert/main',
            params: {
              'id': this.$route.params.id
            }
          }).then(function (response) {
            that.yuan = response.data[0]
            that.yi = response.data[1]
          })
        },
      queren: function () {
        const that = this
        this.$axios({
          method: 'post',
          url: 'api/expert/yes',
          params: {
            'yuan': this.yuan,
            'yi': this.yi,
            'id': this.$route.params.id
          }
        }).then(function (response) {

        })
      }
      }
  }
</script>

<style scoped>

</style>
