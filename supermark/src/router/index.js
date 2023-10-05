import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/login/Login'
import Regiter from "@/views/register/Regiter";
import Home from "@/views/frontdesk/home"
import NowHome from "@/views/frontdesk/nowHome"
import Order from "@/views/frontdesk/userinfo/Order";
import djs from "@/views/frontdesk/userinfo/djs";
import ReleaseGoods from "@/views/frontdesk/userinfo/ReleaseGoods"
import HomePage from "@/views/backstage/homepage";
import Goods from "@/views/frontdesk/goods"
import Product from "@/views/frontdesk/product"
import Index from "@/views/frontdesk"
import Checkstand from "@/views/frontdesk/Checkstand"
import OrderDetails from "@/views/frontdesk/userinfo/OrderDetails"
import ReleaseMember from "@/views/frontdesk/userinfo/ReleaseMember"
import Detail from "@/views/frontdesk/detail";
import NowConfirmcart from "@/views/frontdesk/nowConfirmcart";
import UserInfo from "@/views/frontdesk/userinfo/userInfo"
Vue.use(VueRouter)

const routes = [
  {
    path: "/nowConfirmcart/:id",
    component: NowConfirmcart
  },
  {
    path: "/checkstand",
    component: Checkstand
  },
  {
    path: '/',
    redirect: "/home",
    meta: {
      keepAlive: false, //此组件不需要被缓存
    }
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Regiter
  },
  {
    path: "/home",
    component: Home,
    meta: {
      keepAlive: false, //此组件不需要被缓存
    },
    children: [
      {
        path: "/",
        redirect: "/index",
        meta: {
          keepAlive: false, //此组件不需要被缓存
        }
      },
      {
        path: "/index",
        component: Index,
        meta: {
          keepAlive: false, //此组件不需要被缓存
        }
      },
      {
        path: "/goods",
        component: () => import("@/views/frontdesk/goods")
      },
      {
        path: "/detail/:gid",
        component: Detail
      },
      {
        path: "/product/:pid",
        component: Product
      },
      {
        path: "/activityNotic",
        component: () => import("@/views/frontdesk/activityNotic")
      },
      {
        path: "/noticeShow",
        component: () => import("@/views/frontdesk/noticeShow")
      }
    ]
  },
  {
    path: "/homepage",
    component: HomePage, meta: {
      keepAlive: false, //此组件不需要被缓存
    },
    children: [
      {
        path: "/",
        redirect: "/homeList"
      },
      {
        path: "/homeList",
        component: () => import("@/views/backstage/homeList")
      },
      {
        path: "/addProduct",
        component: () => import("@/views/backstage/pms/add")
      },
      {
        path: "/commonProduct",
        component: () => import("@/views/backstage/pms/commonProduct")
      },
      {
        path: "/productList",
        component: () => import("@/views/backstage/pms/productList")
      },
      {
        path: "/orderList",
        component: () => import("@/views/backstage/oms/orderList")
      },
      {
        path: "/killtable",
        component: () => import("@/views/backstage/oms/activityshow")
      },
      {
        path: "/marketing",
        component: () => import("@/views/backstage/mms/Marketing")
      },
      {
        path: "/logs",
        component: () => import("@/views/backstage/lms/Log")
      }
    ]
  },

  {
    path: "/userInfo",
    component: UserInfo,
    meta: {
      keepAlive: false, //此组件不需要被缓存
    },
    children: [
      {
        path: "/",
        redirect: "/baseInfo",
        meta: {
          keepAlive: false, //此组件不需要被缓存
        }
      },
      {
        path: "/baseInfo",
        component: () => import("@/views/frontdesk/userinfo/MyInfo"),
        meta: {
          keepAlive: false, //此组件不需要被缓存
        }
      },
      {
        path: "/updatePass",
        component: () => import("@/views/frontdesk/userinfo/MyPass")
      },
      {
        path: "/djs",
        component: djs
      },
      {
        path: "/order",
        component: Order
      },
      {
        path: "/releaseGoods",
        component: ReleaseGoods
      },
      {
        path: "/OrderDetails",
        component: OrderDetails,
        meta: {
          keepAlive: false, //此组件不需要被缓存
        }
      },
      {
        path: "/ReleaseMember",
        component: ReleaseMember
      }
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
//挂载路由导航守卫
// router.beforeEach((to,from,next)=>{
//   if(to.path==="/login") return next()
//   //获取token
//   const tokenStr=window.sessionStorage.getItem("token")
//   if(!tokenStr) return  next("/login")
//   next()
// })

export default router
