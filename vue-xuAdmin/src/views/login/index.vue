<template>
  <div id="login">
    <div class="loginConbox">
      <div class="header">
        <!--<div class="logo">-->
        <!--<img src="../../assets/logo.png">-->
        <!--</div>-->
      </div>
      <div class="loginBox">
        <div class="loginCon">
          <p class="title">古汉语-现代汉语翻译系统</p>
<!--          <p class="title">前台: vue + element-ui</p>-->
          <el-card shadow="always" class="login-module" v-if="smdl">
            <div slot="header" class="clearfix formTitlt">
              <span>登录</span>
              <span class="titIconbox">
<!--              <i class="iconfont xu-saomadenglu2 fa-lg iconcolor"></i>-->
<!--              <i class="iconfont xu-saomadenglu01 el-icon&#45;&#45;right fa-lg pointer" @click="smdl = !smdl"></i>-->
            </span>
            </div>
            <el-form :model="loginForm" status-icon label-width="100px" class="demo-ruleForm">
              <el-form-item>
                <el-input type="text" v-model="loginForm.userName" auto-complete="off" placeholder="请输入登录手机号"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input type="password" v-model="loginForm.passWord" auto-complete="off"
                          placeholder="请输入登录密码"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button class="subBtn" type="primary" @click="submitForm">登录</el-button>
              </el-form-item>
              <p class="smalltxt">
                <el-button type="text" class="a" @click="smdl=false">忘记密码</el-button>
<!--                <router-link class="a" to="#">忘记会员名</router-link>-->
                <el-button type="text" class="a" @click="smdl=false">注册</el-button>
              </p>
            </el-form>
          </el-card>

          <el-card shadow="always" class="login-module" v-if="smdl==false">
            <div slot="header" class="clearfix formTitlt">
              <span>注册</span>
              <span class="titIconbox">
<!--              <i class="iconfont xu-saomadenglu2 fa-lg iconcolor"></i>-->
                <el-button type="text" class=" a " @click="smdl=true">返回登录</el-button>
<!--                <i class="iconfont xu-saomadenglu01 el-icon&#45;&#45;right fa-lg pointer" @click="smdl = !smdl"></i>-->
            </span>
            </div>
            <el-form :model="loginForm" status-icon label-width="100px" class="demo-ruleForm">
              <el-form-item>
                <el-input type="text" v-model="loginForm.re_userName" auto-complete="off" placeholder="用户名"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input type="password" v-model="loginForm.re_passWord" auto-complete="off"
                          placeholder="密码"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input type="password" v-model="loginForm.re_rpassWord" auto-complete="off"
                          placeholder="确认密码"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button class="subBtn" type="primary" @click="register">注册</el-button>
              </el-form-item>
            </el-form>
          </el-card>
<!--          <el-card shadow="always" class="login-module" v-else>-->
<!--            <div slot="header" class="clearfix formTitlt">-->
<!--              <span>扫码登录</span>-->
<!--              <span class="titIconbox">-->
<!--              <i class="iconfont xu-mimadenglu1 fa-lg iconcolor"></i>-->
<!--              <i class="iconfont xu-imagevector el-icon&#45;&#45;right fa-lg pointer" @click="smdl = !smdl"></i>-->
<!--            </span>-->
<!--            </div>-->
<!--            <div class="ewmbox">-->
<!--              <div class="ewm">-->
<!--                <img src="https://img.alicdn.com/tfscom/TB1ivYYyHvpK1RjSZFqwu3XUVXa.png">-->
<!--              </div>-->
<!--              <div class="ewmicon">-->
<!--                <i class="iconfont xu-saomadenglu fa-2x iconcolor"></i>-->
<!--                <p>打开 微信 扫一扫登录</p>-->
<!--              </div>-->
<!--              <p class="smalltxt">-->
<!--                <router-link class="a" to="#">免费注册</router-link>-->
<!--              </p>-->
<!--            </div>-->
<!--          </el-card>-->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      smdl: true,
      loginForm: {
        userName: 'admin',
        passWord: 'admin',
        re_userName: '',
        re_passWord: '',
        re_rpassWord: ''
      }
    }
  },
  methods: {
    submitForm () {
      let that = this
      if (this.loginForm.username === '' || this.loginForm.password === '') {
        this.$message({
          showClose: true,
          message: '账号或密码不能为空',
          type: 'error'
        })
        return false
      } else {
        // 向后台服务器发送请求
        this.$axios({
          method: 'post',
          url: '/api/login',
          params: {
            'userName': this.loginForm.userName,
            'passWord': this.loginForm.passWord
          }
        }).then(function (response) {
          // 录入token token 存储在 store
          that.$store.dispatch('setToken', response.data.token).then(() => {
            // 将用户名,角色,token记录
            localStorage.setItem('username', that.loginForm.userName)
            localStorage.setItem('roles', response.data.roles[0].authority)
            localStorage.setItem('description', response.data.roles[0].description)
            localStorage.setItem('Authorization', response.data.token)
            that.$router.push({name: 'home'})
          }).catch(res => {
            that.$message({
              showClose: true,
              message: res,
              type: 'error'
            })
          })
        }).catch(function (error) {
          console.log(error)
        })
      }
    },
    message() {
      const h = this.$createElement;
      this.$notify({
        title: '账号密码',
        message: h('i', { style: 'color: teal'}, '账号密码可以随意填写，为了测试效果填写的账号将会被存储为临时假 token'),
        duration: 6000
      });
    },
    register(){
      const that=this
      if (this.loginForm.re_userName=='' && this.loginForm.re_passWord=='' && this.loginForm.re_rpassWord==''){
        this.$message.error('部分信息未输入')
        this.$message.error("信息未填完")
      }else if(this.loginForm.re_passWord != this.loginForm.re_rpassWord){
        this.$message.error("两次输入密码不一致")
      }else {
        this.$axios({
          method: 'post',
          url: '/api/sys-user/register',
          params: {
            'userName': this.loginForm.re_userName,
            'passWord': this.loginForm.re_passWord
          }
        }).then(function (response) {
          // 返回status 代表是否成功
          if(response.data==1){
            that.$message.success("注册成功")
            that.smdl=true
            that.loginForm.userName=that.loginForm.re_userName
            that.loginForm.password=that.loginForm.re_passWord
          }else if(response.data==2){
            that.$message.error("用户名已存在!!!")
          }else {
            that.$message.error("创建用户错误")
          }
      })
      }
    }
  },
  mounted() {
    // this.message()
  }
}
</script>
<style lang="scss">
  #login {
    width: 100%;
    height: 100%;
    background-color: #2d3a4b;
    .loginConbox{
      background: #2d3a4b;
    }
    .header {
      height: 60px;
      position: relative;
      background: #2d3a4b;
      /*border-bottom: 1px solid rgba(255, 255, 255, 0.3);*/
      .logo{
        margin-left: 30px;
        width: 500px;
        float: left;
        height: 40px;
        padding-top: 10px;
        img{
          height: 100%;
        }
      }
    }

    .loginBox {
      .iconcolor {
        color: #409EFF;
      }

      padding: 74px 0 118px;

      .loginCon {
        width: 990px;
        margin: auto;
        position: relative;
        height: 388px;

        .el-card__header {
          border-bottom: 0px;
        }
        .title{
          font-size: 36px;
          font-weight: 600;
          color: #ffffff;
          width: 500px;
          float: left;
          margin-top: 0px;
          &:first-child{
            font-size: 34px;
            margin-top: 50px;
            margin-bottom: 30px;
          }
        }
        .login-module {
          width: 380px;
          height: 325px;
          margin-top: 60px;
          border: none;
          position: absolute;
          right: 0;

          .formTitlt {
            font-size: 18px;
            font-weight: 400;

            .titIconbox {
              float: right;

              .pointer {
                cursor: pointer;
              }
            }
          }

          .smalltxt {
            text-align: right;

            .a {
              text-decoration: none;
              color: #999999;
              font-size: 12px;
              margin-left: 8px;
            }
          }
        }

        .el-form-item__content {
          margin-left: 0px !important;

          .subBtn {
            width: 100%;
          }
        }
      }

      .el-input__inner, .el-button, .el-card, .el-message {
        border-radius: 0px !important;
      }

      .el-form-item__content .ico {
        position: absolute;
        top: 0px;
        left: 0px;
        z-index: 999;
        width: 40px;
        height: 39px;
        text-align: center;
        border-right: 1px solid #ccc;
      }

      .ewmbox {
        width: 100%;
        height: 240px;
        margin-top: -25px;

        .ewm {
          width: 140px;
          height: 140px;
          margin: 20px auto;

          p {
            font-size: 12px;
            padding-left: 40px;
            margin: 0;
          }
        }

        .ewmicon {
          width: 140px;
          margin: 20px auto 0;

          .iconfont {
            float: left;
          }

          p {
            font-size: 12px;
            padding-left: 40px;
            margin: 0;
          }
        }
      }
    }
  }
</style>
