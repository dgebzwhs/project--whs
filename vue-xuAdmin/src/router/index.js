import en from '../i18n/lang/en'
import Vue from 'vue'
import Router from 'vue-router'
import CommerViews from '@/views/commerViews'
import Login from '@/views/login/index'
import Layout from '@/views/layout/layout'
import HomeMain from '@/views/index/mainIndex'

// 不是必须加载的组件使用懒加载
const Icon = () => import('@/views/icon/index')
const Erji = () => import('@/views/duoji/erji')
const Erji2 = () => import('@/views/duoji/erji2')
const Sanji = () => import('@/views/duoji/sanji')
const Sanji2 = () => import('@/views/duoji/sanji2')
const Siji = () => import('@/views/duoji/siji')
const Wuji = () => import('@/views/duoji/wuji')
const Transfer = () => import('@/views/transfer/transfer')
const DataTable = () => import('@/views/table/dataTables')
const FilterTable = () => import('@/views/table/filterTable')
const DragTable = () => import('@/views/table/dragTabe')
const Upload = () => import('@/views/upload/upload')
const Markdown = () => import('@/views/editor/markdownView')
const WangeditorView = () => import('@/views/editor/wangeditorView')
const NotFound = () => import('@/page404')
const listArticle = () => import('@/views/article/listArticle')

const articleText = () => import('@/views/article/articleText')
const AddArticleEditor = () => import('@/views/article/addArticleEditor')
const NavClassify = () => import('@/views/syssetting/navClassify')
const pagePermissions = () => import('@/views/permissions/pagePermissions')
const btnPermissions = () => import('@/views/permissions/btnPermissions')
const translate = () => import('@/views/translate/translate')
const participle = () => import('@/views/translate/participle')
const examineArticle = () => import('@/views/article/examineArticle')
const examineText = () => import('@/views/article/examineText')
const register = () => import('@/views/login/register')
const userList = () => import('@/views/user/userList')
const expertList = () => import('@/views/expert/expertHelp')
const expertMain = () => import('@/views/expert/expertMain')
const addBlog = () => import('@/views/Blog/addBlog')
const readBlog = () => import('@/views/Blog/readBlog')

Vue.use(Router)




let routeName = en.routeName
let defaultRouter = [
  { path: '/',
    redirect: '/index',
    hidden: true,
    children: [],
    meta:{
      requireAuth:false
    }
  },
  {
    path: '/login',
    component: Login,
    name: '',
    hidden: true,
    children: [],
    meta:{
      requireAuth:false
    }
  },
  {
    path: '/index',
    iconCls: 'fa fa-dashboard', // 图标样式class
    name: routeName.home,
    component: Layout,
    alone: true,
    children: [
      {
        path: '/index',
        iconCls: 'fa fa-dashboard', // 图标样式class
        name: '主页',
        component: HomeMain,
        children: [],
        meta:{
          requireAuth:false
        }
      }
    ],
    meta:{
      requireAuth:false
    }
  },
  {
    path: '/404',
    component: NotFound,
    name: '404',
    hidden: true,
    children: [],
    meta:{
      requireAuth:false
    }
  }

]

let addRouter = [
  {
    path: '/',
    iconCls: 'fa fa-exchange', // 图标样式class
    name: routeName.translateMain,
    component: Layout,
    children: [
      {
        path: '/translate',
        iconCls: 'el-icon-edit-outline', // 图标样式class
        name: routeName.translate,
        component: translate,
        children: [],
        meta:{
          requireAuth:false
        }
      },
      {
        path: '/participle',
        iconCls: 'el-icon-edit-outline', // 图标样式class
        name: routeName.particple,
        component: participle,
        children: [],
        meta:{
          requireAuth:false
        }
      },
    ],
    meta:{
      requireAuth:false
    }
  },




  {
    path: '/',
    iconCls: 'el-icon-tickets', // 图标样式class
    name: routeName.article,
    component: Layout,
    children: [
      {
        path: '/listArticle',
        iconCls: 'el-icon-edit-outline', // 图标样式class
        name: routeName.listArticle,
        component: listArticle,
        children: [],
        meta:{
          requireAuth:false
        }
      },{
        path: '/addArticleEditor',
        iconCls: 'el-icon-edit-outline', // 图标样式class
        name: routeName.publishArticleEditor,
        component: AddArticleEditor,
        children: [],
        meta:{
          requireAuth:false
        }
      },
      {
        path: '/articleText',
        name: routeName.articleText,
        component: articleText,
        hidden: true,
        children: [],
        meta:{
          requireAuth:false
        }
      },
      {
        path: '/examineArticle',
        iconCls: 'el-icon-edit-outline', // 图标样式class
        name: routeName.examineArticle,
        component: examineArticle,
        children: [],
        meta: {role: ['ROLE_admin'],requireAuth:true},
      },
      {
        path: '/examineText',
        iconCls: 'el-icon-edit-outline', // 图标样式class
        name: routeName.examineText,
        component: examineText,
        children: [],
        hidden: true,
        meta: {role: ['ROLE_admin'],requireAuth:true},
      },
    ],
    meta:{
      requireAuth:false
    }
  },
  {
    path: '/',
    iconCls: 'fa fa-cloud-upload', // 图标样式class
    name: routeName.expert,
    component: Layout,
    meta: { role: ['ROLE_admin','ROLE_expert'],requireAuth:false},
    children: [
      {
        path: '/expertList',
        iconCls: 'el-icon-upload2', // 图标样式class
        name: routeName.expertList,
        component: expertList,
        children: [],
        meta:{
          requireAuth:false
        }
      },
      {
        path: '/expertMain',
        iconCls: 'el-icon-upload2', // 图标样式class
        name: routeName.expertMain,
        component: expertMain,
        children: [],
        hidden: true,
        meta:{
          requireAuth:false
        }
      },
    ]
  },{
    path: '/',
    iconCls: 'fa fa-cloud-upload', // 图标样式class
    name: routeName.study,
    component: Layout,
    meta: { requireAuth:false},
    children: [
      {
        path: '/addBlog',
        iconCls: 'el-icon-edit-outline', // 图标样式class
        name: routeName.publishArticle,
        component: addBlog,
        children: [],
        meta:{
          requireAuth:false
        }
      },
      {
        path: '/readBlog',
        iconCls: 'el-icon-upload2', // 图标样式class
        name: routeName.readBlog,
        component: readBlog,
        children: [],
        hidden: false,
        meta:{
          requireAuth:false
        }
      },
    ]
  },


  // {
  //   path: '/',
  //   iconCls: 'fa fa-paw', // 图标样式class
  //   name: routeName.icon,
  //   component: Layout,
  //   children: [
  //     {
  //       path: '/icon',
  //       iconCls: 'fa fa-life-ring', // 图标样式class
  //       name: routeName.builtInIcon,
  //       component: Icon,
  //       children: []
  //     }
  //   ]
  // },
  // {
  //   path: '/',
  //   iconCls: 'fa fa-exchange', // 图标样式class
  //   name: routeName.shuttleBox,
  //   component: Layout,
  //   children: [
  //     {
  //       path: '/transfer',
  //       iconCls: 'fa fa-sign-in', // 图标样式class
  //       name: routeName.demoShuttle,
  //       component: Transfer,
  //       children: []
  //     }
  //   ]
  // },
  // {
  //   path: '/',
  //   iconCls: 'fa fa-universal-access', // 图标样式class
  //   name: routeName.permissions,
  //   component: Layout,
  //   children: [
  //     {
  //       path: '/pagePermissions',
  //       iconCls: 'fa fa-expeditedssl', // 图标样式class
  //       name: routeName.pageControl,
  //       component: pagePermissions,
  //       children: [],
  //       meta:{
  //         requireAuth:true
  //       }
  //     },
  //     {
  //       path: '/btnPermissions',
  //       iconCls: 'fa fa-toggle-on', // 图标样式class
  //       name: routeName.btnControl,
  //       component: btnPermissions,
  //       children: [],
  //       meta:{
  //         requireAuth:true
  //       }
  //     }
  //   ],
  //   meta:{
  //     requireAuth:false
  //   }
  // },
  // {
  //   path: '/',
  //   iconCls: 'fa fa-newspaper-o', // 图标样式class
  //   name: routeName.table,
  //   component: Layout,
  //   children: [
  //     {
  //       path: '/dataTable',
  //       iconCls: 'fa fa-sliders', // 图标样式class
  //       name: routeName.multiDataTable,
  //       component: DataTable,
  //       children: []
  //     },
  //     {
  //       path: '/filterTable',
  //       iconCls: 'fa fa-sort-amount-asc', // 图标样式class
  //       name: routeName.filterTable,
  //       component: FilterTable,
  //       children: []
  //     },
  //     {
  //       path: '/dragTabe',
  //       iconCls: 'fa fa-hand-stop-o', // 图标样式class
  //       name: routeName.dragSort,
  //       component: DragTable,
  //       children: []
  //     }
  //   ]
  // },
  //
  // {
  //   path: '/',
  //   iconCls: 'fa fa-server',
  //   name: routeName.multiDirectory,
  //   component: Layout,
  //   children: [
  //     {
  //       path: '/erji1',
  //       iconCls: 'fa fa-server',
  //       name: routeName['menu2-1'],
  //       component: Erji,
  //       children: []
  //     },
  //     {
  //       path: '/erji2',
  //       iconCls: 'fa fa-server',
  //       name: routeName['menu2-2'],
  //       component: Erji2,
  //       children: []
  //     },
  //     {
  //       path: '/erji3',
  //       iconCls: 'fa fa-server',
  //       name: routeName['menu2-3'],
  //       component: CommerViews, // 无限极菜单的容器
  //       children: [
  //         {
  //           path: '/sanji1',
  //           iconCls: 'fa fa-server',
  //           name: routeName['menu3-1'],
  //           component: Sanji,
  //           children: []
  //         },
  //         {
  //           path: '/sanji2',
  //           iconCls: 'fa fa-server',
  //           name: routeName['menu3-2'],
  //           component: Sanji2,
  //           children: []
  //         },
  //         {
  //           path: '/sanji3',
  //           iconCls: 'fa fa-server',
  //           name: routeName['menu3-3'],
  //           component: CommerViews,
  //           children: [
  //             {
  //               path: '/siji',
  //               iconCls: 'fa fa-server',
  //               name: routeName['menu4-1'],
  //               component: Siji,
  //               children: []
  //             },
  //             {
  //               path: '/siji1',
  //               iconCls: 'fa fa-server',
  //               name: routeName['menu4-2'],
  //               component: CommerViews,
  //               children: [
  //                 {
  //                   path: '/wuji',
  //                   iconCls: 'fa fa-server',
  //                   name: routeName['menu5-1'],
  //                   component: Wuji,
  //                   children: []
  //                 }
  //               ]
  //             }
  //           ]
  //         }
  //       ]
  //     }
  //   ]
  // },
  {
    path: '/',
    iconCls: 'fa fa-cloud-upload', // 图标样式class
    name: routeName.userManage,
    component: Layout,
    meta: { role: ['ROLE_admin'],requireAuth:false},
    children: [
      {
        path: '/userList',
        iconCls: 'el-icon-upload2', // 图标样式class
        name: routeName.userList,
        component: userList,
        children: [],
        meta:{
          requireAuth:false
        }
      }
    ]
  },
  {
    path: '/',
    iconCls: 'fa fa-cloud-upload', // 图标样式class
    name: routeName.upload,
    component: Layout,
    meta: {role: ['ROLE_admin'],requireAuth:true},
    children: [
      {
        path: '/upload',
        iconCls: 'el-icon-upload2', // 图标样式class
        name: routeName.fileUpload,
        component: Upload,
        children: [],
        meta:{
          requireAuth:true
        }
      }
    ]
  },
  // {
  //   path: '/',
  //   iconCls: 'el-icon-edit', // 图标样式class
  //   name: routeName.editor,
  //   component: Layout,
  //   meta: {role: ['superAdmin', 'admin']},
  //   children: [
  //     {
  //       path: '/markdown',
  //       iconCls: 'fa fa-file-code-o', // 图标样式class
  //       name: routeName.markdown,
  //       component: Markdown,
  //       children: []
  //     },
  //     {
  //       path: '/wangeditor',
  //       iconCls: 'fa fa-file-code-o', // 图标样式class
  //       name: routeName.wangeditor,
  //       component: WangeditorView,
  //       children: []
  //     }
  //   ]
  // },
  // {
  //   path: '/',
  //   iconCls: 'el-icon-setting', // 图标样式class
  //   name: routeName.systemSettings,
  //   component: Layout,
  //   meta: {role: ['superAdmin']},
  //   children: [
  //     {
  //       path: '/navClassifies',
  //       iconCls: 'el-icon-menu', // 图标样式class
  //       name: routeName.navMenu,
  //       component: NavClassify,
  //       children: []
  //     }
  //   ]
  // },
  { path: '*',
    redirect: '/404',
    hidden: true,
    children: [],
    meta:{
      requireAuth:false
    }
  },

]


export default new Router({
  routes: defaultRouter
})
export {defaultRouter, addRouter}
