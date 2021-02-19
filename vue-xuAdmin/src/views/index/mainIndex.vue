<template>
  <div>
<!--    <el-row :gutter="20">-->
<!--      <el-col :span="16">-->
<!--        <el-container style="height: 100%;  border: 1px solid lightsteelblue">-->
<!--          <el-container>-->
<!--            <el-header style="text-align: right; font-size: 24px">-->
<!--              古汉语-汉语翻译系统-->
<!--            </el-header>-->
<!--            <el-main style="height: 100%">-->
<!--              <el-button type="primary" @click.native.prevent="totranslate">翻译</el-button>-->
<!--              <el-row :span="24">-->
<!--                <el-col :span="2">-->
<!--                  <div class="grid-content"></div>-->
<!--                </el-col>-->
<!--                <el-col :span="9">-->
<!--                  <el-input-->
<!--                    type="textarea"-->
<!--                    :autosize="{ minRows: 10, maxRows: 40}"-->
<!--                    placeholder="请输入翻译文本"-->
<!--                    v-model="textarea1">-->
<!--                  </el-input>-->
<!--                </el-col>-->
<!--                <el-col :span="2">-->
<!--                  <div class="grid-content"></div>-->
<!--                </el-col>-->
<!--                <el-col :span="9">-->
<!--                  <el-input-->
<!--                    type="textarea"-->
<!--                    :autosize="{ minRows: 10, maxRows: 40}"-->
<!--                    placeholder="翻译结果显示"-->
<!--                    v-model="textarea2">-->
<!--                  </el-input>-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--            </el-main>-->
<!--          </el-container>-->
<!--        </el-container>-->
<!--      </el-col>-->
<!--      <el-col :span="4">-->
<!--        <div class="card kjfs">-->
<!--          <p class="title"><i class="fa fa-th-large fa-lg"></i>快捷方式</p>-->
<!--          <ul>-->
<!--            <li><router-link to="/addArticle" class="kjfs kjfs-bluee"><span><i class="el-icon-tickets fa-2x"></i></span><span>发表文章</span></router-link></li>-->
<!--            <li><router-link to="/dragTabe" class="kjfs kjfs-pinkk"><span><i class="fa fa-hand-stop-o fa-2x"></i></span><span>拖拽排序</span></router-link></li>-->
<!--            <li><router-link to="/pagePermissions" class="kjfs kjfs-yelloww"><span><i class="fa fa-universal-access fa-2x"></i></span><span>权限测试</span></router-link></li>-->
<!--          </ul>-->
<!--          <ul>-->
<!--            <li><router-link to="/upload" class="kjfs kjfs-grennn"><span><i class="fa fa-cloud-upload fa-2x"></i></span><span>文件上传</span></router-link></li>-->
<!--            <li><router-link to="/navClassifies" class="kjfs kjfs-purplee"><span><i class="fa el-icon-menu fa-2x"></i></span><span>前台菜单</span></router-link></li>-->
<!--            <li><router-link to="/markdown" class="kjfs kjfs-lightBluee"><span><i class="fa fa-file-code-o fa-2x"></i></span><span>编辑器</span></router-link></li>-->
<!--          </ul>-->
<!--        </div>-->
<!--      </el-col>-->
<!--&lt;!&ndash;      <el-col :span="8">&ndash;&gt;-->
<!--&lt;!&ndash;        <div class="card dbsx">&ndash;&gt;-->
<!--&lt;!&ndash;          <p class="title"><i class="fa fa-file-text-o"></i>待办事项</p>&ndash;&gt;-->
<!--&lt;!&ndash;          <ul>&ndash;&gt;-->
<!--&lt;!&ndash;            <li><router-link to="#"><span>待审评论</span><span class="num">66</span></router-link></li>&ndash;&gt;-->
<!--&lt;!&ndash;            <li><router-link to="#"><span>待审帖子</span><span class="num">66</span></router-link></li>&ndash;&gt;-->
<!--&lt;!&ndash;          </ul>&ndash;&gt;-->
<!--&lt;!&ndash;          <ul>&ndash;&gt;-->
<!--&lt;!&ndash;            <li><router-link to="#"><span>待审商品</span><span class="num">66</span></router-link></li>&ndash;&gt;-->
<!--&lt;!&ndash;            <li><router-link to="#"><span>代发货</span><span class="num">66</span></router-link></li>&ndash;&gt;-->
<!--&lt;!&ndash;          </ul>&ndash;&gt;-->
<!--&lt;!&ndash;        </div>&ndash;&gt;-->
<!--&lt;!&ndash;      </el-col>&ndash;&gt;-->
<!--&lt;!&ndash;      <el-col :span="8">&ndash;&gt;-->
<!--&lt;!&ndash;        <div class="card bbxx">&ndash;&gt;-->
<!--&lt;!&ndash;          <p class="title"><i class="fa fa-server"></i>版本信息</p>&ndash;&gt;-->
<!--&lt;!&ndash;          <div class="table">&ndash;&gt;-->
<!--&lt;!&ndash;            <p><span class="tit">当前版本</span>v1.0.0</p>&ndash;&gt;-->
<!--&lt;!&ndash;            <p><span class="tit">基于框架</span>vue2.0全家桶 + element-ui</p>&ndash;&gt;-->
<!--&lt;!&ndash;            <p><span class="tit">主要特色</span>单页面 / 响应式 / 无限级菜单 / 权限管理 / 极简</p>&ndash;&gt;-->
<!--&lt;!&ndash;            <p><span class="tit">获取渠道</span>&ndash;&gt;-->
<!--&lt;!&ndash;                <span class="gitbox">&ndash;&gt;-->
<!--&lt;!&ndash;                  <a target="_blank" href="https://github.com/Nirongxu/vue-xuAdmin.git"><el-button type="primary" icon="el-icon-download" size="small">github</el-button></a>&ndash;&gt;-->
<!--&lt;!&ndash;                  <a target="_blank" href="https://gitee.com/nirongxu/xu-webplatform.git"><el-button type="primary" icon="el-icon-download" size="small">码云</el-button></a>&ndash;&gt;-->
<!--&lt;!&ndash;                </span>&ndash;&gt;-->
<!--&lt;!&ndash;            </p>&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--&lt;!&ndash;        </div>&ndash;&gt;-->
<!--&lt;!&ndash;      </el-col>&ndash;&gt;-->

<!--    </el-row>-->
<!--    <el-row>-->
<!--      <el-col :span="24">-->
<!--        <line-echarts id="lineEcharts" height="300px" ref="echarts"></line-echarts>-->
<!--      </el-col>-->
<!--    </el-row>-->
    <el-row>
      <el-col :span="24">
        <maintable id="maintable"></maintable>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import LineEcharts from '../../components/ECharts/lineEcharts'
import Maintable from '../table/maintable'
export default {
  name: 'mainIndex',
  components: {Maintable, LineEcharts},
  mounted () {
    this.selfAdaption()
  },
  methods: {
    // echart自适应
    selfAdaption () {
      let that = this
      setTimeout(() => {
        window.onresize = function () {
          if (that.$refs.echarts) {
            that.$refs.echarts.chart.resize()
          }
        }
      }, 10)
    }
  }
}
</script>

<style lang="scss">
  $top:top;
  $bottom:bottom;
  $left:left;
  $right:right;
  $leftright: ($left, $right);
  $pinkk: #eec2d3;
  $bluee: #409eff;
  $yelloww: #f4d884;
  $grennn: #89dda0;
  $purplee: #78a2ea;
  $lightBluee: #b8d6ff;

  $list: bluee pinkk yelloww grennn purplee lightBluee;
  $list1: $bluee $pinkk $yelloww $grennn $purplee $lightBluee;
  %shadow{
    background: #fff;
    -webkit-box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.2);
    box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.2);
    border-color: rgba(0, 0, 0, 0.2);
    .title{
      font-size: 14px;
      padding: 10px;
      i{
        margin-#{$right}: 5px;
      }
    }
  }

  @mixin flex($direction:row,$content:space-between){
    display: flex;
    flex-direction: $direction;
    justify-content: $content;
  }
  .card{
    color: #666;
    @extend %shadow;

    ul{
      @include flex;
      li{
        flex: 1;
        a{
          color: #666666;
          align-items:center;
          padding-#{$top}: 20px;
          padding-#{$bottom}: 20px;
          @include flex(column);
          span{
            height: 44px;
          }
          .num{
            line-height: 44px;
            font-size: 42px;
            color: $bluee;
            margin: 0px;
          }
        }
        .kjfs-bluee{
          color: $bluee;
        }
        .kjfs-pinkk{
          color: $pinkk;
        }
        .kjfs-yelloww{
          color: $yelloww;
        }
        .kjfs-grennn{
          color: $grennn;
        }
        .kjfs-purplee{
          color: $purplee;
        }
        .kjfs-lightBluee{
          color: $lightBluee;
        }
        &:hover{
          .kjfs-bluee{
            color: #ffffff;
            background: $bluee;
          }
          .kjfs-pinkk{
            color: #ffffff;
            background: $pinkk;
          }
          .kjfs-yelloww{
            color: #ffffff;
            background: $yelloww;
          }
          .kjfs-grennn{
            color: #ffffff;
            background: $grennn;
          }
          .kjfs-purplee{
            color: #ffffff;
            background: $purplee;
          }
          .kjfs-lightBluee{
            color: #ffffff;
            background: $lightBluee;
          }
        }
      }
    }
    .table{
      padding: 21px;
      p{
        height: 52px;
        line-height: 52px;
        border: 1px solid #cccccc;
        overflow: hidden;
        border-#{$top}: none;
        @include flex( null,start);
        &:first-child{
          border-#{$top}: 1px solid #cccccc;
        }
        span{
        }
        .tit{
          width: 90px;
          min-width: 90px;
          height: 100%;
          text-align: center;
          border-#{$right}: 1px solid #cccccc;
          margin-#{$right}: 18px;
        }
        span.gitbox{
          flex: 1;
          height: 100%;
          overflow: hidden;
          @include flex(row,start);
          a{
            &:first-child{
              margin-#{$right}: 30px;
            }
          }
        }
      }
    }
  }
  #lineEcharts{
    margin-#{$top}: 30px;
    padding-#{$top}: 30px;
    @extend %shadow;
  }
  #maintable{
    margin-#{$top}: 30px;
    padding-#{$top}: 10px;
    @extend %shadow;
  }
  /*.el-header {*/
  /*   background-color: dodgerblue;*/
  /*   color: white;*/
  /*   line-height: 60px;*/
  /* }*/
  /*.grid-content {*/
  /*  border-radius: 4px;*/
  /*  min-height: 36px;*/
  /*}*/
  /*.el-aside {*/
  /*  color: #333;*/
  /*}*/
</style>
