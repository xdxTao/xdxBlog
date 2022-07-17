<template>
	<div class="container">
         <header class="header" />
		<div class="navigation ">
			<div class="left">
                <div class>
                <span style="font-size: 30px; font-family: '方正舒体';  color: black; ">小道仙的博客</span>
                </div>
            </div>
             <div class="right">
                 <ul>
                     <nuxt-link to="/manager">
                        <li @click="cur_menu = '/manager'" :class="cur_menu == '/manager' ? 'selection' : ''" >系统管理</li>
                    </nuxt-link>
                 </ul>
             </div>
		</div>
	</div>
</template>

<script>
export default {
  name: 'Navigation',

  data() {
		return {
			// flag_middle: true, //平板大小的css切换
			// flag_button: false, //改变右边按钮的 样式
			// flag_change: true, //显示/关闭   右边的三个横杠的按钮
			// flag_meun: true, //是否显示 菜单
			// flag_or: true, //表示当前菜单式 横着的还是竖着的   true 表示横着的
			// 当前菜单
			cur_menu: ''
		}
	},
	created(){
		this.cur_menu = this.$route.path
	},
 	methods: {


		// clickClose() {
		// 	//点击了某个路由关闭竖着的菜单
		// 	if (this.flag_or == true) return;
		// 	this.flag_meun = false;
		// 	this.flag_button = false;
		// },
		// clickSearch(){
		// 	//回到顶部
		// 	document.body.scrollTop = 0;
		// 	document.documentElement.scrollTop = 0;
		// 	this.$store.commit('setIsSearch')
		// }
	},
}
</script>

<style lang="scss" scoped>
	.container{
		opacity: 0.7;
		// 导航栏的外框
		width: 100%;
		height: 50px;
		box-shadow: 0 0 3px gainsboro;
		background-color: white;
		border-left: none;
		border-right: none;
		//让其固定在最开始的位置
		position: fixed;
		top: 0px;
		left: 0px;
		z-index: 2000 !important;
		display: flex;
		justify-content: center;
        .header {
			// 头部的彩色
			background: #000
			linear-gradient(
				to left,
				#4cd964,
				#5ac8fa,
				#007aff,
				#34aadc,
				#5856d6,
				#ff2d55
			);
			content: "";
			height: 1.5px;
			position: absolute;
			top: 0;
			width: 100%;
		}
		.navigation {
			// border: 1px red solid;
			display: flex;
			justify-content: space-between;
			top:0px;
			width: 100%;
			height: 60px;
			// background-color: #BBB7B7;
			border-bottom : 2px #BBB7B7 solid;
			.left{
				width: 180px;
			}
			.right {
				width: 1050px;
				// border: 1px green solid;
				// 选中样式
				.selection { 
					color: #5cacee;
				}
				ul {
					padding-top: 15px;
					//   border: 1px red solid;
				}
				li {
					display: inline; //让li在一行显示
					// font-family: "方正舒体";
					font-size: 14px;
					color: black;
					transition: all 0.3s linear;
					padding-left: 45px;
					font-weight: 600;
				}
				li:hover {
					color: #5cacee;
					transition: all 0.3s linear;
					transform: scale(1.1);
				}
				a {
					//去掉每个标签的下划线  router-link  底层是 a
					text-decoration: none;
				}
				i:hover{
					cursor: pointer;
					color: #5cacee;
					transition: all 0.2s linear;
					// transform: scale(1.5);
				}
			}
		}
	}
</style>