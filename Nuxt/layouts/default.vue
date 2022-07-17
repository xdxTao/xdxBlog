<template>
	<div class="main">
		<div>
			<div class="navBar">
				<navigation />
			</div>
			<!-- <div class="search" >
				<search />   
			</div> -->
		
			<nuxt />
			<!-- <div >
				<bottom />
			</div> -->
		</div>
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
		//  background-color: rgba(255, 255, 255, 0.5);
	}
	.search{
		width: 1200px;
		margin: 0 auto;
		// background-color: #cbeafb; 
	}

</style>>
	

