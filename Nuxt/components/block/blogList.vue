<template>
    <!-- 博客卡片 -->
    <div style=" width: 830px;">
        <div class="main">
            <div class="box" :style="item.imgTitle == true ? 'height: 225px;':''" v-for="item in blogList" :key="item.bamId">
                <a-spin tip="疯狂加载中..." size="large" v-if="isLoad">
                    <div class="spin-content">
                        <!-- 这里可以写描述 -->
                    </div>
                </a-spin>
                <div class="blogCard" v-if="!isLoad">
                    <div class="top">
                        <div class="blog_img" v-if="item.imgTitle">
                            <img :src="item.bamImgTitle" alt="">
                        </div>
                        <div :class="item.imgTitle? 'blog_content_img' : 'blog_content_text'">
                            <div :class="item.imgTitle? 'title title_img' : 'title title_text'">
                                <span :class=" item.typeName === '原创' ? 'type_1 type' : item.typeName === '转载' ? 'type_2 type' : 'type_3 type'">{{item.typeName}}</span>
                                <span v-if="item.top === 'YES'" class="isTop type">置顶</span>
                                <nuxt-link target="_blank" :to="{path: '/article/', query:{id: item.id}}"><b v-html="item.title"></b></nuxt-link>
                            </div>
                            <div class="describe" v-html="item.articleDesc">
                                <!-- {{item.articleDesc}} -->
                            </div>
                        </div>
                    </div>
                    <div class="under">
                        <div style="width:550px;display: flex; justify-content: flex-star;">
                            <div class="tag">
                                <a-icon type="tag" style="color:hotpink"/>
                                <nuxt-link :to="{ path: '/article/serach', query: { selectName: item.blogCatgThreeId == null ? item.blogCatgTwoId : item.blogCatgThreeId , blogCatg:item.blogCatgName, key : new Date(), type : 2}}">{{item.catgName}}</nuxt-link>
                            </div>
                            <div class="tag">
                                <a-icon type="clock-circle" style="color:#096;"/>
                                <span to="/" style="color:grey">{{item.createAt}}</span>
                            </div>
                            <div class="tag">
                                <a-icon type="eye" style="color:grey"/>
                                <!-- <nuxt-link to="/" style="color:grey">{{item.bamRead}}浏览</nuxt-link> -->
                                <span to="/" style="color:grey">{{item.readCount}}浏览</span>
                            </div>
                            <!-- <div class="tag">
                                <a-icon type="message" style="color:grey"/>
                                <span to="/" style="color:#096;">{{item.commentNumber}}评论</span>
                            </div> -->
                            <div class="tag">
                                <a-icon type="heart" style="color:hotpink"/>
                                <!-- <nuxt-link to="/" style="color:hotpink">点赞{{item.bamGood}}</nuxt-link> -->
                                <span to="/" style="color:hotpink">点赞{{item.goodCount}}</span>
                            </div>
                        </div>
                        <div style="width:300px; padding-top:13px;"> 
                             <!-- <nuxt-link  target="_blank" :to="{path: '/article', query:{bamId: item.bamId}}" class="readtext">阅读原文 >></nuxt-link> -->
                            <nuxt-link  target="_blank" :to="{path: 'show/article/'+item.id}" class="readtext">阅读原文 >></nuxt-link>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pagination">
                <div>
                    <a-pagination  @change="onShowSizeChange" :defaultCurrent="1" :total="blogTotal" />
                </div>          
            </div>
        </div>
      
    </div>    
    
</template>
<script>
import {getBlogList } from '~/api/show/sidebar.js'

export default {
    data(){
        return{
            // load 加载
            isLoad: false,
            pageSize: 20,
            current: 1,
            blogList:[],
            blogTotal: null,
            blogCatgId: null
        }
    },
    props:{
        // blogList:{
        //     type:Array,
        //     required: true
        // },
        // blogTotal:{
        //     type:Number,
        //     required: true
        // },
        // blogCatgId:{
        //     type:Number,
        //     required: false
        // }
    },
    methods: {
        initDate(){
            this.getReadTopList();
        },
        async getReadTopList(){
            let parms = {
                current: this.current,
                size: this.pageSize,
                blogCatgId: this.blogCatgId,
                selectName: this.$route.query.selectName,
                type: this.$route.query.type,
            }
            const result = await getBlogList(parms);
            this.$set(this,'blogList',result.data.records);
            this.$set(this,'blogTotal',result.data.total);
        },

        onShowSizeChange(current) {
            console.log(this.blogCatgId);
            
            //回到顶部
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
            this.getReadTopList();
            // let parms = {
            //     current: this.current,
            //     size: this.pageSize,
            //     blogCatgId: this.blogCatgId,
            //     selectName: this.$route.query.selectName,
            //     type: this.$route.query.type,
            // }
            // this.$axios.post('blogArticle/frontList',parms)
            // .then((res) => {
            //     this.blogList = res.data,
            //     this.blogTotal = res.total * 1
            // })
        }
    },
    created(){
        this.initDate();
    }
}
</script>

<style lang="scss" scoped>
    .main{
        .box{
            width: 830px;
            height: 180px;
            // border: 1px #5cacee solid;
            // border-bottom:1px #5cacee dashed;
            border-bottom:1px #5cacee solid;
            .spin-content{
                border: 1px solid #91d5ff;
                background-color: #e6f7ff;
                width: 830px;
                height: 220px;
            }
            .blogCard{
                padding: 10px;
                .top{ // 上面部分
                    width: 810px;
                    // height: 160px;
                    // border: 1px green solid;
                    display: flex;
                    justify-content: space-between;
                    .blog_img { // 图片部分
                        width: 300px;
                        height: 150px;
                        padding-top: 5px;
                        // border: 1px yellow solid;
                        img{
                            width: 265px;
                            height: 145px;
                            border-radius: 5px;
                        }
                    }
                    .blog_content_img { // 博客卡片内容部分
                        width: 550px;
                        height: 160px;
                        // border: 1px saddlebrown solid;
                        .describe {                        
                            height: 70px;
                            padding-top:10px;
                            overflow: hidden;
                            text-overflow: ellipsis;
                            display: -webkit-box;   // 用来显示后面三个 ...
                            -webkit-line-clamp: 3;
                            -webkit-box-orient: vertical;
                            width: 550px;
                            color: gray;
                            font-size: 14px; 
                        }
                        .title_img{
                            width: 550px;
                            height: 45px;
                            // border: 1px black solid;
                            text-overflow: ellipsis;
                            overflow: hidden;
                            white-space: nowrap;
                        }
                    }
                    .blog_content_text { // 博客卡片内容部分
                        width: 810px;
                        // height: 160px;
                        // border: 1px saddlebrown solid;
                        .describe {                        
                            height: 70px;
                            padding-top:10px;
                            overflow: hidden;
                            text-overflow: ellipsis;
                            display: -webkit-box;   // 用来显示后面三个 ...
                            -webkit-line-clamp: 3;
                            -webkit-box-orient: vertical;
                            width: 810px;
                            color: gray;
                            font-size: 14px; 
                        }
                        .title_text{
                            width: 820px;
                            height: 45px;
                            // border: 1px black solid;
                            text-overflow: ellipsis;
                            overflow: hidden;
                            white-space: nowrap;
                        }
                    }
                    .title{
                        padding-top: 2px;
                        a{
                            font-size: 20px;
                            text-decoration: none;
                            color: black;
                            // margin-top: 10px;
                        }
                        a:hover{
                            color: #5CACEE;
                        }
                        .type{  //文章类型
                            font-size: 15px;
                            color: white;
                            margin-right: 10px;      
                            padding-left: 4px;
                            padding-right: 3px;
                            padding-bottom: 3px;
                            border-radius: 3px;
                        }
                        .type_2{
                            background-color: #009688;    
                        }
                        .type_1{
                            background-color: #1E9FFF;
                        }
                        .type_3{
                            background-color: #00FA9A;
                        }
                        .isTop{
                            background-color: red;
                            // padding: 5px 8px 5px 8px;
                            // border-radius: 100px;
                            // font-size: 13px;
                        }
                    }
                    
                }
                .under{ // 下面部分
                    width: 810px;
                    height: 40px;
                    // border: 1px blue solid;
                    // border-top: 1px blue solid;
                    display: flex;
                    justify-content: space-between;
                    .tag { // 下面的标签
                        // padding: 14px;
                        padding-top: 14px;
                        padding-right: 15px;
                        font-size: 13px;
                        // width: 100px;
                        // border: 1px red solid;
                    }
                    .readtext{   //阅读原文 文字
                        // align-self: center ;
                        text-decoration: none;
                        font-size: 15px;
                        color: #5CACEE;
                        margin-left: 200px;                    
                    }
                    .readtext:hover{
                        border-bottom: 1px #5CACEE solid;
                    }

                }
            }
        }
        .box:hover{
            // box-shadow: -10px -10px 100px gainsboro;
            -moz-box-shadow:3px 5px 10px gainsboro; 
            -webkit-box-shadow:3px 5px 10px gainsboro; 
            box-shadow:3px 5px 10px gainsboro;
        }
        .pagination{ // 分页
            width: 830px;
            height: 40px;
            margin-top: 35px;
            // border: 1px red solid;
            display: flex;
            justify-content: center;
        }
        
    }
</style>
