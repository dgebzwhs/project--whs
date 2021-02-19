<template>
  <div>
    <el-input
      type="textarea"
      id="test-result"
      :autosize="{ minRows: 5, maxRows: 100}"
      placeholder=""
      v-model="test">
    </el-input>
  </div>
</template>


<!--<script src=></script>-->
<script>
  import returnthing  from '../../../static/markdown/editormd.js'
  import initData from '../../markData.js'
  import scriptjs from 'scriptjs'
  export default {
    name: '',
    data () {
      // eslint-disable-next-line no-labels,no-unused-expressions
      return {
        test: initData,
      }
    },
    mounted: function () {
      this.md()
    },
    methods: {
      fetchScript: function (url) {
        return new Promise((resolve) => {
          scriptjs(url, () => {
            resolve()
          })
        })
      },
      md: function () {
        // let editormd = factory.editormd;
        console.log(returnthing.markdownToHTML())
        // let editor = editormd.markdownToHTML('test-result', {
        //   markdown        : markdown ,//+ "\r\n" + $("#append-test").text(),
        //   //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
        //   htmlDecode      : "style,script,iframe",  // you can filter tags decode
        //   //toc             : false,
        //   tocm            : true,    // Using [TOCM]
        //   //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
        //   //gfm             : false,
        //   //tocDropdown     : true,
        //   // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
        //   emoji           : true,
        //   taskList        : true,
        //   tex             : true,  // 默认不解析
        //   flowChart       : true,  // 默认不解析
        //   sequenceDiagram : true,  // 默认不解析
        // });
        // let result = editor.markdownToHTML('test-result', {
        //   htmlDecode      : "style,script,iframe",  // you can filter tags decode
        //   emoji           : true,
        //   taskList        : true,
        //   tex             : true,  // 默认不解析
        //   flowChart       : true,  // 默认不解析
        //   sequenceDiagram : true,  // 默认不解析
        // });
        console.log(editor)
      }
    }
  }
</script>

<style scoped>

</style>
