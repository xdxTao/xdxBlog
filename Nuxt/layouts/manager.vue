<template>
  <a-layout style="min-height: 100vh" class="layout">
    <a-layout-sider v-model="collapsed" collapsible class="layout11">
      <div class="logo" />
      <a-menu v-model="selectedKeys" theme="dark" mode="inline" @click="menClick">
        <template v-for="item in list" >
        <template v-if="!item.children.length">
          <a-menu-item :key="item.id">
            <nuxt-link :to="`${item.menuUrl}`">
               <a-icon :type=item.icon v-if="item.icon" />
              {{ item.name }}
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
    </a-layout-sider>
	<nuxt class="other"/>
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
        <a-icon type={`${item.icon}`} v-if="item.icon" />
        {item.name}
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
      list:[]
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
            // let param = {status:''}
            const result = await getUserInfo()
            this.list = result.data.menus
        },

  },
  created(){
        this.init()
    }
};
</script>
<style>
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
  /* border: 1px red solid; */
  position:fixed;
  display: flex;
  flex-direction: row;
  width:100%
}
.nuxtLink{
  color: #F2F3F5;
}
/* .layout11{
  border: 1px blue solid;
  position:fixed;
  width:20%
}
.other{
  border: 1px green solid;
  position:fixed;
  width: 80%;
} */
</style>