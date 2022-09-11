<template>
    <!-- 文章展示页面 -->
    <div :class="curWidth == 'phone' ? 'container_phone' : 'container_window'">
        <div>
            <div class="main" >
                <div class="left">
                    <div class="blog">
                        <div class="title">
                            <strong>{{bamFrom.bamTitle}}</strong>
                        </div>
                        <div class="desc">
                            <a-icon type="clock-circle" />&nbsp;{{bamFrom.gmtCreate}} &nbsp;&nbsp;
                            <a-icon type="user" />&nbsp;小道仙 &nbsp;&nbsp;
                            <a-icon type="eye" />&nbsp;{{bamFrom.bamRead}}阅读 &nbsp;&nbsp;
                            <a-icon type="message" />&nbsp;{{bamFrom.commentNumber}}评论
                        </div>
                        <div class="content" >
                            <no-ssr>
                                <mavon-editor
                                    v-model="bamFrom.bafMarkdown"
                                    :subfield="false"
                                    :boxShadow="false"
                                    defaultOpen="preview"
                                    codeStyle="dracula"
                                    :toolbarsFlag="false"
                                    style="z-index: 9;"
                                    />
                            </no-ssr>
                        </div>
                        <div class="good">
                            <a-button type="primary" size="large" @click="userGood(0)">点个赞({{bamFrom.bamGood}})</a-button>
                        </div>
                        <div class="nextRead">
                            上一篇：
                            <nuxt-link v-if="upAndDownList[0] != null" target="_blank" :to="{path: '/article/' + upAndDownList[0].bamId }">{{upAndDownList[0].bamTitle}}</nuxt-link>
                            <span v-else>没有了</span> 
                            <br/><br/>
                            下一篇：
                            <nuxt-link v-if="upAndDownList[1] != null" target="_blank" :to="{path: '/article/' + upAndDownList[1].bamId }">{{upAndDownList[1].bamTitle}}</nuxt-link>
                            <span v-else>没有了</span> 
                        </div>
                        <!-- <div class="nextRead">
                            上一篇：
                            <nuxt-link v-if="upAndDownList[0] != null" target="_blank" :to="{path: '/article', query:{bamId: upAndDownList[0].bamId}}">{{upAndDownList[0].bamTitle}}</nuxt-link>
                            <span v-else>没有了</span> 
                            <br/><br/>
                            下一篇：
                            <nuxt-link v-if="upAndDownList[1] != null" target="_blank" :to="{path: '/article',query:{bamId: upAndDownList[1].bamId}}">{{upAndDownList[1].bamTitle}}</nuxt-link>
                            <span v-else>没有了</span> 
                        </div> -->

           
                    </div>
                    <div class="comment" v-show="curWidth == 'window'">
                        <newComment :bamId="bamFrom.bamId"/>
                    </div>
                </div>
            </div>

        </div>
    </div>    
</template>

<script>
// 局部引入mavonEditor，减少首页打包大小
import Vue from 'vue';
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
Vue.use(mavonEditor);

import {getBlogInfo } from '~/api/show/article.js';

// import sideBar from '~/components/multiple/SideBar.vue'
// import newComment from '~/components/single/newComment.vue'
export default {
    // asyncData (context) {     
    //     return context.$axios.get('blogArticle/getById?bamId=' + context.params.id)
    //     .then((res) => {
    //         return { bamFrom: res.data }
    //     })
    // },
    components: {
    },
    data(){
        return {
            // 当前文章信息
            bamFrom:{},
            // 上下一篇文章信息
            upAndDownList:[],
            // 当前窗口
            curWidth:'window',
        }
    },
    // 放在created里面会请求两次
    mounted(){
         // 增加阅读数
        this.userGood(1)
         // 获取上一篇和下一篇文章
        this.getUpAndDown()
        if(document.documentElement.clientWidth > 1000){
            this.curWidth = 'window'
        }else {
            this.curWidth = 'phone'
        }        
    },
    methods:{
        // 用户点赞 ✌  ✌  ✌  ✌  ✌  ✌  ✌
        userGood(operate) {
            let params = {
                bamId : this.bamFrom.bamId,
                operateType : operate
            }   
            if(operate === 0){
                params.ip = returnCitySN["cip"]
            }
            this.$axios.post("blogArticle/operateBlog", params)            
        	.then((res) => {                
        		if(res.data !== '200') {
                    if(res.data === '201'){
                        this.$message.success('你已经点过赞了噢，不需要重复点赞。')
                    } else if(res.data === '202'){
                        this.$message.success('谢谢支持。')
                        this.bamFrom.bamGood += 1
                    }
                }else {
                    this.bamFrom.bamRead += 1
                }    
        	})       
        },
        // 获取上一篇和下一篇文章
        getUpAndDown(){            
            let params = {
                gmtCreate : this.bamFrom.gmtCreate,
                blogCatgThreeId : this.bamFrom.blogCatgThreeId
            }
            this.$axios.post("blogArticle/getUpAndDown", params)            
        	.then((res) => {                
                this.upAndDownList = res.data               
        	})   
        },
        async getBlogInfo(){
            const result = getBlogInfo({
                id: $route.query.id
            });
            this.bamFrom = result.data;
        }
        // // 预览图片
        // previewImg(url){
        //     console.log("inmginmg");
        //     console.log(url);
        // }
    },
    head () {
        return {
            script: [
                { src: 'https://pv.sohu.com/cityjson?ie=utf-8' }
            ],
            title: this.bamFrom.bamTitle,
            meta: [
                { hid: this.bamFrom.bamId, name: 'description', content: this.bamFrom.bamDesc },
                { hid: this.bamFrom.bamId+"123", name: 'keywords', content: this.bamFrom.bamLabel }
            ]
        }
    }
}
</script>

<style lang="scss" scoped>
    .container_window{
        display: flex;
        justify-content: center;
        .main{ 
            width: 1200px;
            display: flex;
            justify-content: space-between;
            .left{
                width: 1200px;   
                .blog { 
                    // height: 800px;
                    margin-top: 15px;
                    // border: 1px red solid;
                     img {
                            
                        }
                    .title{
                        // border: 1px green solid;
                        height: 50px;
                        font-size: 25px;
                        color: black;
                    }
                    .desc{
                        font-size: 15px;
                        height: 35px;
                        border-bottom: 1px gainsboro solid;
                        // border: 1px blue solid;
                    }
                    .content{
                    }
                    .good{
                        margin-top: 30px;
                        // border: 1px blue solid;
                        height: 70px;
                        text-align: center;
                        padding-top: 20px;
                        button{
                            padding-left: 50px;
                            padding-right: 50px;
                        }
                    }
                    // 下一篇文章部分
                    .nextRead{
                        // border: 1px red solid;
                        height: 80px;
                        font-size: 15px;
                        margin-top: 15px;
                        color: black;
                        a{
                            color: black;
                        }
                        a:hover{
                            border-bottom: 1px black solid;
                            cursor: pointer;   
                        }
                    }

                }
                // 留言板部分
                .comment{
                    width: 1200px;   
                    margin-top: 15px;
                }
            }
        }
    }
    .container_phone{
        .main{ 
            display: flex;
            justify-content: space-between;
            .left{
                width: 100%;
                margin-left: 2%;
                margin-right: 2%;
                .blog { 
                    margin-top: 15px;
                    .title{
                        // border: 1px green solid;
                        height: 50px;
                        font-size: 25px;
                        color: black;
                    }
                    .desc{
                        font-size: 15px;
                        height: 35px;
                        border-bottom: 1px gainsboro solid;
                        // border: 1px blue solid;
                    }
                    .content{
                        
                        .markdown-body{
                            font-size: 30px;
                        }
                    }
                    .good{
                        margin-top: 30px;
                        height: 70px;
                        text-align: center;
                        padding-top: 20px;
                        button{
                            padding-left: 50px;
                            padding-right: 50px;
                        }
                    }
                    // 下一篇文章部分
                    .nextRead{
                        // border: 1px red solid;
                        height: 80px;
                        font-size: 15px;
                        margin-top: 15px;
                        color: black;
                        a{
                            color: black;
                        }
                        a:hover{
                            border-bottom: 1px black solid;
                            cursor: pointer;   
                        }
                    }

                }
            }
        }
    }
</style>