<template>
    <div class="page-container">
        <a-card :bordered="false" class="page-container-card">
            
            <!-- <wefa-search-form :data.sync="queryForm" :items="formItems" @searchReset="reset" @searchQuery="onSearch" ref="searchForm"></wefa-search-form> -->
            <div class="table-top-handle-wrapper">
                <a-button type="primary" @click="addUser" icon="plus">添加</a-button>
            </div>
            <a-table class="tableDiv " 
                :footerOption="{ detailOption: false }" 
                :columns="columns" 
                :dataSource="UserListData" 
                rowKey="id" 
                ref="table"
                :loading="loading"
                :pagination="false"
                @change="handleTableChange"
                >
                <div  slot="status" slot-scope="text, row">
                    <!-- <a-button type="link" size="small" @click.stop="modifyUser(row)">修改</a-button>                     -->
                    <a-switch v-model="row.statusEd" @change="update(row)" slot="status" :checked="row.statusEd"/>
                </div>
                <div class="right-handle" slot="handle" slot-scope="text, row">
                    <a-button type="link" size="small" @click.stop="modifyUser(row)">修改</a-button>                    
                </div>
            </a-table>
            <a-pagination v-model="current" :total="total" show-less-items class="antd-pagination" @change="pageChange"></a-pagination>

            <add-user ref="form" @success="addUserSuccess"  ></add-user>
            <!-- <modify-user @success="modifyUserSuccess" @cancel="modifyUserDialog = false" :selectId="currentSelectId" :modifyUserDialog="modifyUserDialog"></modify-user>
            <user-detail :userId="currentSelectId" :visible.sync="userDetailDialog" @cancel="userDetailDialog = false"></user-detail> -->
        </a-card>
    </div>
</template>
<script>
import {userList, modifyUser } from '~/api/manager/user.js';
// import { usePagination } from 'vue-request';
import AddUser from './components/add.vue';

export default {
    components: {
        AddUser,
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

            current:1,
            pageSize:10,
            total:0,
            UserListData:[
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
                    width: '10%',
                },
                // {title:'用户ID',dataIndex:'id'},
                {title:'用户名称',dataIndex:'name'},
                {title:'所属角色',dataIndex:'roleName'},
                {title:'状态',dataIndex:'status',
                    // customRender: value => (value == 'ENABLE' ? '启用' : value == 'DISABLE'?'禁用' : '')
                    scopedSlots: { customRender: 'status' }
                },
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
            this.getUserList()
        },
        // 获取用户列表
        async getUserList(){
            // let param ={current: this.pagination.current,size: this.pagination.pageSize}
            let param ={current: this.current,size: this.pageSize}
             const result = await userList(param);
             if(result){
                this.total= result.data.total
                result.data.records.map((item,index) => {
                    if (item.status == "ENABLE"){
                        item.statusEd = true
                    }
                    if (item.status == "DISABLE"){
                        item.statusEd = false
                    }
                    item.indexs = index + 1;
                })
                this.$set(this, 'UserListData',result.data.records);
             }else{
                 this.$set(this, 'UserListData',[]);
             }
            //  this.$set(this,'pagination',{total:result.data.total})
            // this.pagination.total= result.data.total
             this.$set(this,'loading',false)
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
            this.getUserList();
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
                this.getUserList();
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
            this.getUserList()
        },
        // 分页change事件
        pageChange(val){
            this.current = val
            this.getUserList()
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
        // padding: 10px 10px 10px 10px;
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