<template>
	<div class="main">
		<div class="navBar">
			<navigation />
		</div>
		<!-- <div class="search" >
			<search />   
		</div> -->

		<div class="content">
			<nuxt />
		</div>
		
		<!-- <div >
			<bottom />
		</div> -->
	</div>
</template>

<script>
// import navBar from '~/components/single/NavBar.vue'
// import search from '~/components/single/Search.vue'
// import bottom from '~/components/single/Bottom.vue'

import { navigation} from '~/components/block/index.js'
 
export default {
   components: {
        navigation,         // 导航栏
		// search,         // 搜索框
		// bottom,			// 底部
	},
}
</script>
<style lang="scss" scoped>
	// 背景图片颜色深度
	.main{
		// border: 10px blue solid;
		display: flex;
		height: 100%;
		flex-direction: column;
		//  background-color: rgba(255, 255, 255, 0.5);
		.navBar{
			height: 60px;
			width: 100%;
		}
		.content{
			height: 100%;
			// border: 5px green solid;
		}
	}
	.search{
		width: 1200px;
		margin: 0 auto;
		height: 100%;
		// background-color: #cbeafb; 
	}

</style>>
	

