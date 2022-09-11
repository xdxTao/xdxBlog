<template>
    <!-- 分类专栏 -->
    <div style="width: 190px">
        <div class="main">
            <!-- <sidebarHead title="分类专栏"  style="width: 110px"/> -->

            <div style="width: 180px">
                <div class="main_1">
                    <div class="icon">
                        <a-icon type="pushpin" />
                    </div>
                    <div class="title">
                        <span><b>分类专栏</b></span> 
                    </div>
                </div>
            </div>
           
            <!-- <div class="box" v-for="(item,i) in articleCatgList" :key="i">
               <a-collapse accordion :bordered="false" class="collapse">
                    <a-collapse-panel :header="item.name" :key="i">
                        <nuxt-link v-for="(item1,j) in item.children" :key="j" :to="{ path: '/article/serach', query: { selectName: item1.value, blogCatg: item1.label, key : new Date(), type : 2}}">
                            <div class="sonBox" >   
                                <div style="padding-left:15px; color: black;"><span>{{item1.label}}</span></div>
                                <div><span style="color: #5cacee;">{{item1.number}}</span> 篇</div>    
                            </div>
                         </nuxt-link>
                    </a-collapse-panel>
                </a-collapse>
            </div> -->
            <div class="box">
                <a-tree
                    :tree-data="articleCatgList"
                    
                    :replace-fields="replaceFields"
                    class="dataTree"
                    
                />
                <!-- @select="onSelect"
                :default-expanded-keys="['0-0-0', '0-0-1']"
                    :default-selected-keys="['0-0-0', '0-0-1']"
                    :default-checked-keys="['0-0-0', '0-0-1']"
                    @check="onCheck" -->
            </div>

        </div>
    </div>    
</template>
<script>

import {getArticleCatgList } from '~/api/show/sidebar.js';

export default {
    // props:["classBarList"],
    components: {
      
    },
	data(){
		return{
			articleCatgList:[
        //         {
        //     id: 12,
        //     parentId: 0,
        //     name: "数据库",
        //     status: "ENABLE",
        //     articleCount: 14,
        //     sort: 1,
        //     children: [
        //         {
        //             id: 14,
        //             parentId: 12,
        //             name: "测试",
        //             status: "ENABLE",
        //             articleCount: 0,
        //             sort: 2,
        //             children: [
        //                 {
        //                     id: 15,
        //                     parentId: 14,
        //                     name: "312312",
        //                     status: "ENABLE",
        //                     articleCount: 0,
        //                     sort: 1,
        //                     children: null
        //                 }
        //             ]
        //         }
        //     ]
        // },
                    ],
            replaceFields: {
                title: 'name',
            },
		}
	},
	methods:{
		initData(){
            this.getlist();
		},
		async getlist(){
			const  result = await getArticleCatgList({});
            const catgList = this.filterCatgList(result.data.records);
            this.$set(this,'articleCatgList',catgList);
		},
        // 过滤空数据导致的cascader 报错
        filterCatgList(data){
            data.map(item=>{
                if( item.children){
                    this.filterCatgList(item.children); 
                }
                if(!item.children){
                    // item.children = undefined;
                    delete item.children
                }
            })
            return data;
        },
	},
	created(){
		this.initData();
	}
}
</script>
<style lang="scss" scoped>
    .main{
        width: 230px;
                    // border: 1px green solid;

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
            .dataTree{
                width: 190px;
            }
        }
        .sonBox{
            height: 30px;
            padding-top: 3px;
            display: flex;
            justify-content: space-between;
           
        }
        .sonBox:hover{
            background-color: #f8f8f8;
            cursor: pointer;
            span{
                color: #5cacee;
            }
        }
    }
</style>
