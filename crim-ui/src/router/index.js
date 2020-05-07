import Vue from 'vue'
import Router from 'vue-router'

const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
}

Vue.use(Router)

import Layout from '@/layout'

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      component: Layout,
      redirect: '/home/demo',
      children: [{
        path: 'demo',
        name: 'Home',
        component: () => import('@/views/Home')
      }]
    },
    {
      path: '/',
      name: 'Main',
      component: () => import('@/views/main/Index'),
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/main/Login')
    },
    {
      path: '/detail',
      name: 'NoticeDetail',
      component: () => import('@/views/main/NoticeDetail')
    },
    // school
    {
      path: '/school',
      component: Layout,
      redirect: '/school/info',
      children: [{
        path: 'info',
        name: 'schInfo',
        component: () => import('@/views/school/info/Info'),
        meta: { title: '个人中心', role: 'school', index: '1' }
      }]
    },
    {
      path: '/school',
      component: Layout,
      children: [{
        path: 'info/edit',
        name: 'schInfoEdit',
        component: () => import('@/views/school/info/Edit'),
        meta: {title: '编辑', role: 'school'}
      }]
    },
    {
      path: '/school',
      component: Layout,
      children: [{
        path: 'management',
        name: 'schSchoolManagement',
        component: () => import('@/views/school/info/SchoolManagement'),
        meta: {title: '学院管理', role: 'school'}
      }]
    },
    {
      path: '/school/site',
      component: Layout,
      redirect: '/school/site/add',
      name: 'schSite',
      meta: { title: '场地管理', role: 'school', index: '2-1' },
      children: [
        {
          path: 'add',
          name: 'schAddSite',
          component: () => import('@/views/school/site/AddSite'),
          meta: { title: '场地录入', role: 'school', index: '2-1' }
        },
        {
          path: 'list',
          name: 'schSiteList',
          component: () => import('@/views/school/site/SiteList'),
          meta: { title: '场地列表', role: 'school', index: '2-2' }
        }
        ,
        {
          path: 'apportion',
          name: 'schSiteApportion',
          component: () => import('@/views/school/site/SiteApportion'),
          meta: { title: '场地列表', role: 'school', index: '2-3' }
        }
      ]
    },
    {
      path: '/school/activity',
      component: Layout,
      redirect: '/school/activity/add',
      name: 'schActivity',
      meta: { title: '招聘会管理', role: 'school', index: '3-1' },
      children: [
        {
          path: 'add',
          name: 'schAddActivity',
          component: () => import('@/views/school/activity/AddActivity'),
          meta: { title: '发布招聘会', role: 'school', index: '3-1' }
        },
        {
          path: 'list',
          name: 'schActivityList',
          component: () => import('@/views/school/activity/ActivityList'),
          meta: { title: '招聘会列表', role: 'school', index: '3-2' }
        }
        ,
        {
          path: 'company',
          name: 'schActivityCompanyList',
          component: () => import('@/views/school/activity/CompanyList'),
          meta: { title: '参与企业列表', role: 'school', index: '3-3' }
        }
      ]
    },
    {
      path: '/school/student',
      component: Layout,
      redirect: '/school/student/list',
      name: 'schStudent',
      meta: { title: '毕业生管理', role: 'school', index: '4-1' },
      children: [
        {
          path: 'list',
          name: 'schStudentList',
          component: () => import('@/views/school/student/StudentList'),
          meta: { title: '毕业生列表', role: 'school', index: '4-1' }
        },
        {
          path: 'add',
          name: 'schAddStudent',
          component: () => import('@/views/school/student/AddStudent'),
          meta: { title: '信息录入', role: 'school', index: '4-2' }
        }
      ]
    },
    {
      path: '/school/company',
      component: Layout,
      redirect: '/school/company/list',
      name: 'schCompany',
      meta: { title: '企业管理', role: 'school', index: '5-1' },
      children: [
        {
          path: 'list',
          name: 'schCompanyList',
          component: () => import('@/views/school/company/CompanyList'),
          meta: { title: '企业列表', role: 'school', index: '5-1' }
        },
        {
          path: 'pending',
          name: 'schPendingCompany',
          component: () => import('@/views/school/company/PendingCompany'),
          meta: { title: '待审核企业', role: 'school', index: '5-2' }
        },
        {
          path: 'deny',
          name: 'schDenyCompany',
          component: () => import('@/views/school/company/DenyCompany'),
          meta: { title: '审核未通过企业', role: 'school', index: '5-3' }
        },
        {
          path: 'disable',
          name: 'schDisableCompany',
          component: () => import('@/views/school/company/DisableCompany'),
          meta: { title: '黑名单', role: 'school', index: '5-4' }
        }
      ]
    },
    {
      path: '/school/chart',
      component: Layout,
      redirect: '/school/chart/bar',
      name: 'schStatement',
      meta: { title: '数据报表', role: 'school', index: '6-1' },
      children: [
        {
          path: 'bar',
          name: 'schBarChart',
          component: () => import('@/views/school/statement/BarChart'),
          meta: { title: '柱状图', role: 'school', index: '6-1' }
        },
        {
          path: 'line',
          name: 'schLineChart',
          component: () => import('@/views/school/statement/LineChart'),
          meta: { title: '曲线图', role: 'school', index: '6-2' }
        },
        {
          path: 'pie',
          name: 'schPieChart',
          component: () => import('@/views/school/statement/PieChart'),
          meta: { title: '南丁格尔', role: 'school', index: '6-2' }
        }
      ]
    },
    {
      path: '/school',
      component: Layout,
      children: [{
          path: 'accessory',
          name: 'schAccessory',
          component: () => import('@/views/school/accessory/Accessory'),
          meta: { title: '附件', role: 'school', index: '7' }
      }]
    },
    // student
    {
      path: '/student',
      component: Layout,
      redirect: '/student/info',
      children: [{
        path: 'info',
        name: 'stuInfo',
        component: () => import('@/views/student/Info'),
        meta: { title: '个人中心', role: 'student', index: '8' }
      }]
    },
    {
      path: '/student',
      component: Layout,
      children: [{
        path: 'resume',
        name: 'stuResume',
        component: () => import('@/views/student/Resume'),
        meta: { title: '简历', role: 'student', index: '9' }
      }]
    },
    {
      path: '/student/company',
      component: Layout,
      redirect: '/student/company/list',
      children: [{
        path: 'list',
        name: 'stuCompanyList',
        component: () => import('@/views/student/CompanyList'),
        meta: { title: '企业列表', role: 'student', index: '10' }
      }]
    },
    // {
    //   path: '/student',
    //   component: Layout,
    //   children: [{
    //     path: 'company/star',
    //     name: 'stuCompanyStar',
    //     component: () => import('@/views/student/CompanyStar'),
    //     meta: { title: '关注', role: 'student', index: '11' }
    //   }]
    // },
    {
      path: '/student',
      component: Layout,
      children: [{
        path: 'company/interview',
        name: 'stuCompanyInterview',
        component: () => import('@/views/student/CompanyInterview'),
        meta: { title: '求职', role: 'student', index: '12' }
      }]
    },
    {
      path: '/student',
      component: Layout,
      children: [{
        path: 'chat',
        name: 'stuChat',
        component: () => import('@/views/student/Chat'),
        meta: { title: '私信', role: 'student', index: '13' }
      }]
    },
    // Company
    {
      path: '/company',
      component: Layout,
      redirect: '/company/info',
      children: [{
        path: 'info',
        name: 'comInfo',
        component: () => import('@/views/company/Info'),
        meta: { title: '个人中心', role: 'company', index: '14' }
      }]
    },
    {
      path: '/company',
      component: Layout,
      children: [{
        path: 'info/edit',
        name: 'comInfoEdit',
        component: () => import('@/views/company/Edit'),
        meta: { title: '编辑', role: 'company'}
      }]
    },
    {
      path: '/company/activity',
      component: Layout,
      redirect: '/company/activity/jobfair',
      name: 'comStatement',
      meta: { title: '招聘管理', role: 'company', index: '15-1' },
      children: [
        {
          path: 'jobfair',
          name: 'comJobFair',
          component: () => import('@/views/company/activity/JobFair'),
          meta: { title: '招聘会', role: 'company', index: '15-1' }
        },
        {
          path: 'careertalk',
          name: 'comCareerTalk',
          component: () => import('@/views/company/activity/CareerTalk'),
          meta: { title: '宣讲会', role: 'company', index: '15-2' }
        },
        {
          path: 'student/list',
          name: 'comStudentList',
          component: () => import('@/views/company/activity/StudentList'),
          meta: { title: '参加学生', role: 'company', index: '15-3' },
          children: [
            {
              path: 'resume/:studentid',
              name: 'comResume',
              component: () => import('@/views/student/Resume'),
              meta: { title: '学生简历', role: 'company'}
            }
          ]
        },
        {
          path: 'apply',
          name: 'comApply',
          component: () => import('@/views/company/activity/Apply'),
          meta: { title: '申请入会', role: 'company', index: '15-4' }
        }
      ]
    },
    {
      path: '/company',
      component: Layout,
      children: [{
        path: 'chat',
        name: 'comChat',
        component: () => import('@/views/company/Chat'),
        meta: { title: '私信', role: 'company', index: '16' }
      }]
    },
    {
      path: '/company',
      component: Layout,
      children: [{
          path: 'accessory',
          name: 'comAccessory',
          component: () => import('@/views/company/Accessory'),
          meta: { title: '附件', role: 'company', index: '17' }
      }]
    },
    {
      path: '/company',
      component: Layout,
      children: [{
          path: 'register',
          name: 'comRegisterStep',
          component: () => import('@/views/company/RegisterStep'),
          meta: { title: '完善信息', role: 'company', index: '19' }
      }]
    },
    {
      path: '*',
      name: 404,
      component: () => import('@/components/404')
    }
  ]
})
