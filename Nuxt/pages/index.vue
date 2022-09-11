<template>
	<!-- <div class="container">
		<div class="main ">
				ddd
		</div>
		<div class="side">
			<div class="catg">
				<articleCatg />
			</div>
			<div class="paihang">
				<ranking />
			</div>
		</div>
	</div> -->
	<div class="container">
		<!-- <div class="notice">
			<notice :noticeList="noticeList"/>
		</div> -->
		<div class="main" style="width:1300px;">
			<!-- <div class="left">
					<ranking />  <br>
			</div> -->
			<div class="middle">
				<!-- <carousel :carouselList="carouselList"/> -->
				<!-- <position title="个人博客"/> -->
				<blogList />
			</div>
			<!-- <div class="right">
				<sideBar :friendList="friendList" :classBarList="classBarList" :readTopList="readTopList"/>
			</div> -->
			<div class="side">
				<div class="catg">
					<articleCatg />
				</div>
				<div class="paihang">
					<ranking />
				</div>
			</div>
		</div>
    </div>
</template>


<script>
import {articleCatg, ranking, blogList }  from '~/components/block/index.js'

export default {
  name: 'IndexPage',
  components:{
	articleCatg,
	ranking,
	blogList
  }
}
</script>


<style lang="scss" scoped>
	// .container{
	// 	padding-top: 10px;
	// 	height: 100%;
	// 	display: flex;
    //     justify-content: center;
	// 	flex-direction: row;
	// 	border: 3px brown solid;
	// 	.main {
	// 		border: 1px red solid;
	// 		// top:0px;
	// 		width: 50%;
	// 		height: 60px;
	// 		padding-right: 30px;
	// 		// background-color: #BBB7B7;
	// 		border-bottom : 2px #BBB7B7 solid;
	// 	}
	// 	.side{
	// 		width: 20%;
	// 		// border: 1px blue solid;
	// 		// padding-left:100px ;
	// 		position: absolute;
	// 		right: 20px;
	// 		.catg{
	// 			// border: 1px blue solid;
	// 			// left: 0px;
	// 			// position: absolute;
	// 		}

	// 	}
	// }
	.container{
        display: flex;
        justify-content: center;
        .notice{ // 公告
            width: 1300px;
            height: 50px;
        }
        .main{ 
            width: 1300px;
            display: flex;
            justify-content: space-between;
            .left{
                width: 190px;
            }
            .middle{
                width: 830px;   
            }
            .right{
                width: 260px;
            }
        }
    }
</style>