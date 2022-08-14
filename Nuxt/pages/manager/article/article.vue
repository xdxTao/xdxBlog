<template>
    <div class="page-container">
        <a-card :bordered="false" class="page-container-card">
            <div class="table-top-handle-wrapper">
                <a-cascader 
                    :options="treeData" 
                    style="width:220px" 
                    v-model="search.catgIdList" 
                    placeholder="请选择分类..." 
                    changeOnSelect @change="onChangeCatg"
                    :field-names="{label: 'name', value: 'id', children: 'children' }"
                    
                />
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a-input placeholder="关键词搜索..." style=" width: 220px;" v-model="search.name"  /> &nbsp;&nbsp;&nbsp;&nbsp;
                <a-button @click="getArticleList()" type="primary">搜&nbsp;&nbsp;&nbsp;&nbsp;索</a-button>
            </div>
            <!-- <wefa-search-form :data.sync="queryForm" :items="formItems" @searchReset="reset" @searchQuery="onSearch" ref="searchForm"></wefa-search-form> -->
            <!-- <div class="table-top-handle-wrapper">
                <a-button type="primary" @click="addUser" icon="plus">添加</a-button>
            </div> -->
            <a-table class="tableDiv " 
                :footerOption="{ detailOption: false }" 
                :columns="columns" 
                :dataSource="ArticleListData" 
                rowKey="id" 
                ref="table"
                :loading="loading"
                :pagination="false"
                @change="handleTableChange"
                >
                <template slot="titled" slot-scope="text, row">
                    <a-tooltip slot="titled" :title="row.title"> 
                        {{row.title}}
                    </a-tooltip>
                    
                </template>
                <template slot="typeName" slot-scope="row">
                   <a-tag slot="typeName" v-if="row != ''" :color="row === '原创' ? '#108ee9' : row === '翻译'  ? '#00FA9A' : 'green'">{{row}}</a-tag>
                </template>
                <template slot="top" slot-scope="text, row">
                    <a-switch :checked="row.topstatus" v-model="row.topstatus" @change="openTopChange(text, row, 'top')" slot="top" />
                </template>
                <template slot="open" slot-scope="text, row">
                    <a-switch :checked="row.openstatus"  v-model="row.openstatus" @change="openTopChange(text, row, 'open')" slot="open" />
                </template>
                <div class="right-handle" slot="handle" slot-scope="text, row">
                    <!-- <a-button type="link" size="small" @click.stop="modifyUser(row)">编辑</a-button>                     -->
                    <nuxt-link  class="edit" :to="{path: '/manager/article/write', query:{id: row.id}}">编辑</nuxt-link>&nbsp; &nbsp; &nbsp;
                </div>
            </a-table>
            <a-pagination v-model="current" :total="total" show-less-items class="antd-pagination" @change="pageChange"></a-pagination>

            <!-- <add-user ref="form" @success="addUserSuccess"  ></add-user> -->
            <!-- <modify-user @success="modifyUserSuccess" @cancel="modifyUserDialog = false" :selectId="currentSelectId" :modifyUserDialog="modifyUserDialog"></modify-user>
            <user-detail :userId="currentSelectId" :visible.sync="userDetailDialog" @cancel="userDetailDialog = false"></user-detail> -->
        </a-card>
    </div>
</template>
<script>
import {articleList, modifyArticle, getCatgList, fastModifyArticle } from '~/api/manager/article.js';
// import { usePagination } from 'vue-request';
// import AddUser from './components/add.vue';

export default {
    components: {
        // AddUser,
        // ModifyUser,
        // UserDetail
    },
    layout: 'manager',
    data(){
        return{
            // pagination:{
            //     current:1,
            //     pageSize:10,
            //     total:0
            // },
            search:{
                catgIdList:[],
                catgId:'',
                name:'',
            },
            treeData:[],
            current:1,
            pageSize:10,
            total:0,
            ArticleListData:[
                // {
                //     "id": 1, //角色id
                //     "name": "张三丰", //角色名称
                //     "roleId": "1", //角色id
                //     "roleName": "超级管理员",
                //     "status": "ENABLE"
                //     },
                //     {
                //     "id": 2, //角色id
                //     "name": "张三丰", //角色名称
                //     "roleId": "1", //角色id
                //     "roleName": "超级管理员",
                //     "status": "ENABLE"
                //     },
            ],
            loading:false,
            columns: [
                {
                    title: '序号',
                    dataIndex: 'indexs',
                    key: 'indexs',
                    width: '5%',
                },
                {title:'文章标题',dataIndex:'title', scopedSlots: { customRender: 'titled' },width: 200,ellipsis: true,},
                {title:'文章分类',dataIndex:'catgName', ellipsis: true,},
                {title:'文章类型',dataIndex:'typeName', scopedSlots: { customRender: 'typeName' }, align: "center"},
                {title:'是否草稿',dataIndex:'draftName',width: 100,},
                // {title:'是否草稿',dataIndex:'draft'},
                {title:'是否置顶',dataIndex:'top', scopedSlots: { customRender: 'top' }, align: "center"},
                {title:'是否公开',dataIndex:'open', scopedSlots: { customRender: 'open' }, align: "center"},
                {title:'创建时间',dataIndex:'createAt'},
                {title:'修改时间',dataIndex:'updateAt'},
                {
                    title: '操作',
                    width: 130,
                    align: 'center',
                    dataIndex: 'handle',
                    scopedSlots: { customRender: 'handle' }
                }

            ],
            modifyUserDialog: false,
            userDetailDialog: false,
        }

    },
    methods:{
        initData(){
            this.getArticleList();
            this.getCatgList()
        },
        // 分类下拉框加载
        async getCatgList(){
            let param = {current: 1,size: 10000 }
            const result = await getCatgList(param)
            // this.treeData = result.data.records
            const catgList = this.filterCatgList(result.data.records);
            this.$set(this,'treeData',catgList);
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
        // 获取用户列表
        async getArticleList(){
            // let param ={current: this.pagination.current,size: this.pagination.pageSize}
            let param ={current: this.current,size: this.pageSize,catgId: this.search.catgId,name:this.search.name}
             const result = await articleList(param);
             if(result){
                this.total= result.data.total
                result.data.records.map((item,index) => {
                    if (item.top == "YES"){
                        item.topstatus = true
                    }
                    if (item.top == "NO"){
                        item.topstatus = false
                    }
                    if (item.open == "YES"){
                        item.openstatus = true
                    }
                    if (item.open == "NO"){
                        item.openstatus = false
                    }
                    item.indexs = index +1;
                })
                this.$set(this, 'ArticleListData',result.data.records);
             }else{
                 this.$set(this, 'ArticleListData',[]);
             }
            //  this.$set(this,'pagination',{total:result.data.total})
            // this.pagination.total= result.data.total
             this.$set(this,'loading',false)
        },

        // 分类搜索
        onChangeCatg(value){
            console.log(value) 
            // this.serach.blogCatgId = value[0]
            // this.serach.blogCatgTwoId = value[1]
            // this.search.catgId = value[2]
            let i = value.length;
            this.search.catgId = value[i-1];
        },

        // 开关操作
        async openTopChange(row, record, val){
            console.log(row,'rowrow',record,'recordrecord',val,'valval');
            let params = {
                id:record.id,
                top:'',
                open:'',
            };
            if(val == 'top') {
                params.top = record.topstatus == true ? 'YES' : 'NO'
            } else if(val == 'open') {
                params.open = record.openstatus == true ? 'YES' : 'NO'
            }           
            const result = await fastModifyArticle(params);
            if (result.code == 200){
                this.$message.success(`修改成功！`);
            }
            this.getArticleList();
        },
        /**
         * 添加用户
         */
        addUser(){
            this.$refs.form.openCreateMode();
        },
        /**
         * 添加用户成功事件处理
         */
        addUserSuccess() {
            this.getArticleList();
        },
        /**
         * 修改用户
         */
        modifyUser(data){
            this.$refs.form.openEditMode(data);
        },
        // 修改状态
        async update(data){
            console.log('datadatadatadata',data)
            if (data.statusEd == false){
                data.status = 'DISABLE';
            }
            if (data.statusEd == true){
                data.status = 'ENABLE';
            }
            const result = await modifyUser(data);
            if (result.code == 200){
                this.$message.success(`状态修改成功！`);
                this.getArticleList();
            }
            //  param.validate(async valid => {
            //     if (valid) {
            //         await modifyUser(this.form);
            //         this.confirmLoading = false
            //         this.cancelAddUser()
            //         this.$message.success('修改用户成功！');
            //         this.$emit('success');
            //         // this.reset();
            //     }
            // });
        },
        handleTableChange(pag){
            // this.pagination.current = pag.current
            // this.pagination.pageSize = pag.pageSize
            this.current = pag.current
            this.pageSize = pag.pageSize
            this.getArticleList()
        },
        // 分页change事件
        pageChange(val){
            this.current = val
            this.getArticleList()
        },
    },
    created() {
        this.initData()
    },


}
</script>

<style lang="scss" scoped>
.page-container{
        // border: 1px blue solid;
        width: 100%;
        display: flex;
        height: 100%;
        flex-direction:row;
        padding: 10px 10px 10px 10px;
        // margin-left: 10px;
        .table-top-handle-wrapper{
            padding-bottom: 10px;

        }
        .page-container-card{
            // border: 1px pink solid;
            width: 100%;
            height: 100%;
            min-height: 715px;
            .ant-card-body{
                padding: 4px
            }
        }
    }
    
.tableDiv{
    // border: 1px red solid;
    height: 100%;
    // min-height: 550px;
    width: 100%;
    
}
.antd-pagination{
    position:fixed;
    right:50px;
    bottom:20px;
}

</style>