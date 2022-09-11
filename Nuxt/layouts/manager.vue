<template>
  <a-layout style="min-height: 100vh" class="layout">
    <a-layout-sider v-model="collapsed" :trigger="null" collapsible >
      <!-- :trigger="null" -->
      <div class="logo" />
      <a-menu v-model="selectedKeys" theme="dark" mode="inline" @click="menClick">
        <template v-for="item in list" >
        <template v-if="!item.children.length">
          <a-menu-item :key="item.id">
            <nuxt-link :to="`${item.menuUrl}`">
              <span>
               <a-icon :type=item.icon v-if="item.icon" />
              <span>{{ item.name }}</span>
              </span>
            </nuxt-link>
          </a-menu-item>
        </template>
        <template v-else>
          <a-sub-menu :key="item.id">
              <span slot="title">
                <a-icon :type=item.icon v-if="item.icon" /><span>{{item.name}}</span>
              </span>
              <sub-menu :menu-info="item.children" :key="item.id" ></sub-menu>
            </a-sub-menu>
        </template>
      </template>
      </a-menu>
      <div class="ant-layout-sider-trigger trigger" :style="{padding: 0,height: collapsed ? '100px': '50px', width: collapsed ? '80px': '200px' }">
        <!-- asds -->
        <div class="menu-fold" >
          <a-icon type="menu-unfold" @click="() => (collapsed = !collapsed)" class="trigger"  v-if="collapsed"/>
        </div>
        <div class="usename" style="display: flex; flex-direction: row;">
          <a-popconfirm
            title="是否退出?"
            ok-text="是"
            cancel-text="否"
            @confirm="signOutClick"
            @cancel="cancel"
          > 
            <div class="useClick">
              <div class="photo">
                <span class="useStr"><font color="blue">{{this.nameStr}}</font></span>
              </div>
              <div v-if="!collapsed" style="margin-right: 12px;"> <strong>{{this.name}}</strong> </div>
            </div>
            
          </a-popconfirm>
          
          <div><a-icon type="menu-fold" @click="() => (collapsed = !collapsed)" class="trigger"  v-if="!collapsed"/></div>
        </div>
      </div>
    </a-layout-sider>
    <div class="other">
      <nuxt />
    </div>
    <!-- <div class="content"> -->
      <!-- <div class="topBored">
        <div class="right">
          <div class="usename">
            <strong>超级管理员</strong>
          </div>
          <div class="signout">
            <a-button type="link" @click="() => signOutClick()">退出</a-button>
          </div>
        </div>
      </div> -->
      <!-- <div class="other">
        <nuxt />
      </div> -->
    <!-- </div> -->
    <!-- 当用户信息需要展示多种信息时使用 -->
    <!-- <div class="popover">
      <a-popover placement="rightBottom" v-if="dis">
        <template slot="content">
          <p>Content</p>
          <p>Content</p>
        </template>
      </a-popover>
    </div> -->
    
  </a-layout>
</template>

<script>
import {
  PieChartOutlined,
  DesktopOutlined,
  UserOutlined,
  TeamOutlined,
  FileOutlined,
} from '@ant-design/icons-vue';
import {getUserInfo } from '~/api/manager/user.js';
// a-sub-menu 循环显示
const SubMenu = ({
  props: {
    menuInfo
  }
}) => (menuInfo).map(item => {
  if(!item.children.length) {
    return (<a-menu-item key={item.id} >
      <nuxt-link to={`${item.menuUrl}`}>
        <a-icon type={`${item.icon}`} v-if="item.icon" /><span>{item.name}</span>
      </nuxt-link >
    </a-menu-item>)
  }
  if (item.children.length) {
    return (
      <a-sub-menu key={item.id}>
        <span slot="title">
          <a-icon type={`${item.icon}`} v-if="item.icon" />
          <span>{item.name}</span>
        </span>
        <sub-menu menuInfo={item.children} key={item.id} ></sub-menu>
      </a-sub-menu>)
  } 
  
})

// const list = [
//     {
//     key: '1', 
//     title: '用户管理',
//     path: '/manager/user'
//   }, {
//     key: '2',
//     title: '角色管理',
//     path: '/manager/roles',
//     children: [{
//       key: '2.1',
//       title: 'Navigation 3',
//       path: '/',
//       children: [{
//         key: '2.1.1',
//         title: 'Option 2.1.1',
//         path: '/',
//       }],
//     },{
//       key: '2.2',
//       title: 'Navigation 4',
//     }],
//   },
//   {
//     key: '3',
//     title: '菜单管理',
//     path: '/manager/menu'
//   }
// ];

export default {

  components: {
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined,
    'sub-menu': SubMenu,
  },
  data() {
    return {
      collapsed: false,
      selectedKeys: ['1'],
      list:[],
      name:'',
      nameStr: ''
    };
  },
  methods:{
    menClick(item){
      console.log(item,1223456789876543);
    },
    init(){
        this.getMenuList();
    },
    //获取当前用户权限下菜单列表
    async getMenuList(){
            let param = {status:''}
            const result = await getUserInfo();
            this.list = result.data.menus;
            this.name = result.data.name;
            this.nameStr = this.name.charAt(0);
            console.log(this.nameStr,'this.nameStrthis.nameStrthis.nameStr');
    },
    signOutClick(){
      console.log('退出了');
      const that = this;
      // this.$confirm({
      //   title: '是否退出登录',
      //   okText:"确定",
      //   cancelText:"取消",
      //   onOk() {
      //       // next()
      //       window.localStorage.setItem('token','');
      //       that.$router.push('/login');
      //   },
      //   onCancel() {},
      // });
      window.localStorage.setItem('token','');
      that.$router.push('/login');
    },
    cancel(){
      console.log('取消');
    }

  },
  created(){
        this.init()
  }
};
</script>
<style lang="scss" scoped>
#components-nbe .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}
[data-theme='dark'] .site-layout .site-layout-background {
  background: #F2F3F5;
}
.layout{
  // border: 1px red solid;
  position:fixed;
  display: flex;
  flex-direction: row;
  width:100%
}
.nuxtLink{
  color: #F2F3F5;
}
.content{
  width:90%;
  // height: 80px;
   background-color: #f8f8f8;
  display: flex;
  flex-direction: column;
  // border: 1px red solid;
  .topBored{
    // width:100%;
    height: 40px;
    background: white;
    // border: 1px blue solid;
    margin: 10px 10px 0px 10px;
    .right{
      position:fixed;
      height: 35px;
      // border: 1px blue solid;
      padding-top: 10px;
      right:50px;
      // top:10px;
      display: flex;
      flex-direction: row;
      .usename{
        height: 35px;
        line-height: 35px;
        align-items: center;
        // border: 1px green solid;
        
      }
      .signout{
        height: 35px;
        line-height: 35px;
        align-items: center;
      }
    }
    
  }
  .other{
    width:100%;
    height: 100%;
    border: 1px green solid;
  }
}
.ant-layout-sider-trigger{
    // width:80px !important;
    // height: 100px;
    display: flex; 
    flex-direction: column;
    // border: 1px green solid;
   
    .usename{
      // border: 1px green solid;
      display: flex; 
      flex-direction: row;
      align-items: center;
        align-self: flex-start;
        justify-content: center;
        .useClick{
          display: flex; 
          flex-direction: row;
          .photo{
            // background-color: #f8f8f8;
            background-color: #e6f3ff;
            border-radius: 6px;
          border: 1px pink solid;
            display: flex;
            align-items: center;
            align-self: flex-start;
            justify-content: center;
            flex: auto 0 0;
            width: 28px;
            height: 28px;
            margin-top: 11px;
            margin-left: 26px;
            margin-right: 12px;
            .useStr{
              font-weight: 600;
              color: #198cff;
              font-size: 14px;
            }
          }
        }
      
    }
  }
.other{
  // border: 5px green solid;
  // position:fixed;
  width: 100%;
}
</style>