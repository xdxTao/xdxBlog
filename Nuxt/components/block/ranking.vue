<template>
    <!-- 阅读排行 -->
    <div style="width: 260px">
        <div class="main">
            <!-- <sidebarHead title="点击排行榜"/> -->
            <div style="width: 180px">
                <div class="main_1">
                    <div class="icon">
                        <a-icon type="pushpin" />
                    </div>
                    <div class="title">
                        <span><b>点击排行</b></span> 
                    </div>
                </div>
            </div>
           
            <div class="box" v-for="(item ,i) in readTopList" :key="i">
                <div class="left">
                    <div :class=" i+1 < 4 ? 'div_1' : 'div_2'">{{i+1}}</div>
                </div>
                <div class="right">
                    <nuxt-link :to="{path: '/article/'+item.id}">{{item.title}}</nuxt-link>
                </div>
            </div>

        </div>
    </div>    
</template>
<script>
// import sidebarHead from '~/components/single/SidebarHead.vue'
import { getReadTopList } from '~/api/show/sidebar.js'
export default {
    // props:["readTopList"],
    components: {
        // sidebarHead
    },
    data(){
        return{
            readTopList:[
            //     {
            //         "id": 15, //id
            //         "title": "这是新的测试文章22222222222222222222222222222222222222222222222222222222222222222222222222222", //标题
            //         "readCount": 0 //阅读数
            //     },
            //     {
            //         "id": 19, //id
            //         "title": "幸福v", //标题
            //         "readCount": 0 //阅读数
            //     },
            //     {
            //         "id": 28, //id
            //         "title": "打算", //标题
            //         "readCount": 0 //阅读数
            //     },
            //     {
            //         "id": 29, //id
            //         "title": "打算", //标题
            //         "readCount": 0 //阅读数
            //     },
            //     {
            //         "id": 30, //id
            //         "title": "打算", //标题
            //         "readCount": 0 //阅读数
            //     },
            //     {
            //         "id": 31, //id
            //         "title": "打算", //标题
            //         "readCount": 0 //阅读数
            //     },
            //     {
            //         "id": 36, //id
            //         "title": "新增", //标题
            //         "readCount": 0 //阅读数
            //     },
            ],
        }
    },
    methods:{
        initDate(){
            this.getReadTopList();
        },
        async getReadTopList(){
            const result = await getReadTopList();
            this.$set(this,'readTopList',result.data);
        }
    },
    created(){
        this.initDate();
    }
}
</script>
<style lang="scss" scoped>
    .main{
        width: 260px;
        .main_1{
            width: 230px;
            height: 40px;
            background-color: #f8f8f8;
            display: flex;
            justify-content: flex-start;
            .icon{
                width: 40px;
                // border: 1px blue solid;
                text-align: center;
                i{
                    font-size: 20px;
                    padding-top: 10px;
                }
            }
            .title{
                width: 258px;
                // border: 1px green solid;
                height: 40px;
                line-height:40px;
                span{
                    // padding-left: 
            
                    font-size: 15px;
                    color: black;
                }
            }

        }
        .box{
            display: flex;
            justify-content: flex-start;
            height: 30px;
            .left{
                width: 35px;
                height: 35px;
                // border: 1px red solid;
                text-align: center;
                display: flex;
                justify-content: center;
                align-items: center;
                .div_1{
                    width: 16px;
                    height: 16px;
                    font-size: 12px;
                    background-color: #FF6600;
                }
                .div_2{
                    width: 16px;
                    height: 16px;
                    font-size: 12px;
                    background-color: #B1B1B1;
                }
            }
            .right{
                line-height: 35px;
                font-size: 14px;
                width: 260px;
                height: 35px;
                // border: 1px green solid;
                text-overflow: ellipsis;
                overflow: hidden;
                white-space: nowrap;
                a{
                    color: black;
                }
                a:hover{
                    color: #5cacee;
                }
            }
        }
    }
</style>
