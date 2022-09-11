<template>
    <div class="page-container">
        <a-card class="module-role"  title="角色列表">
            <a slot="extra" @click.stop="addRole(1)">+添加</a>
            <ul class="ant-tree">
                <li v-for="item in roleData" :key="item.id" @click="handleClickModuleMenu(item)" class="liSty" >
                    <div
                        class="ant-tree-node-content-wrapper"
                        :class="{
                            'ant-tree-node-selected': currentRoleId.id === item.id
                        }"
                    >
                        <span>{{ item.name }}</span>
                        <!-- <div style="display: inline-block;float: right;">
                            <a-tooltip :destroyTooltipOnHide="true">
                                <template slot="title">修改</template>
                                <a-button class="handle-edit" type="link" @click.self.stop="updateRole(item)">
                                    <a-icon type="edit" />
                                </a-button>
                            </a-tooltip>
                        </div> -->
                    </div>
                </li>
            </ul>
        </a-card>
        <a-card class="menuClass" title="菜单">
            <a-tree
                class="draggable-tree"
                draggable
                checkable
                block-node
                :tree-data="treeMenuData"
                
                :replaceFields="{
                    children: 'children',
                    title: 'name',
                    key: 'id'
                }"
                :expanded-keys="expandedKeys"
                :auto-expand-parent="autoExpandParent"
                :selected-keys="selectedKeys"
                :checked-keys="checkedKeys"
                :showLine="false"
                @expand="onExpand"
                @select="onSelect"
                @check="onCheck"
            >
            </a-tree>
            <div class="footer-container">
                <a-button type="primary" @click="handleOk" :loading="submitLoading">保存</a-button>
            </div>
        </a-card>
        <roleAdd ref="form" @success="addUserSuccess"> </roleAdd>
    </div> 
</template>
<script>
import {roleList, roleMenuList, roleMenuMod  } from '~/api/manager/role.js';
import {menuList } from '~/api/manager/menu.js';

import roleAdd from './components/roleAdd.vue'
export default {
    layout: 'manager',
    components:{
        roleAdd
    },
    data(){
        return{
            treeMenuData: [
                // {
                    
                //     "id": 26, //id
                //     "name": "主菜单", //name
                //     "parentId": 0, //父id
                //     "sort": 1, //排序
                //     "menuUrl": "", //跳转url
                //     "status": "ENABLE", //状态
                //     "children": [
                //         {
                //         "id": 28, //id
                //         "name": "主菜单33", //name
                //         "parentId": 26, //父id
                //         "sort": 0, //排序
                //         "menuUrl": "", //跳转url
                //         "status": "DISABLE", //状态
                //         "children": null //子节点
                //         },
                //         {
                //         "id": 29, //id
                //         "name": "主菜单44", //name
                //         "parentId": 26, //父id
                //         "sort": 2, //排序
                //         "menuUrl": "", //跳转url
                //         "status": "DISABLE", //状态
                //         "children": null //子节点
                //         },
                //     ] //子节点
                // }
            ],
            expandedKeys: [], // （受控）更多指定的树节点
            autoExpandParent: true,
            checkedKeys: [], // 选中的
            selectedKeys: [],
            currentRoleId:'',
            submitLoading:false,
            roleData:[
                // {
                // "id": 1, //角色id
                // "name": "超级管理员" //角色名称
                // },
                // {
                // "id": 2, //角色id
                // "name": "管理员" //角色名称
                // }
            ],
        }
    },
    methods:{
        addRole(){
            this.$refs.form.openAdd()
        },
        addUserSuccess() {
            this.getRoleList();
        },
        updateRole(data){
            this.$refs.form.openUpdate(data)
        },
        // 根据角色id 获取菜单
        async handleClickModuleMenu(value){
            this.currentRoleId = value
            let result = await roleMenuList(value.id);
            this.checkedKeys = result.data;

        },
        init(){
            this.getRoleList();
            this.getMenuList();
        },
        // 获取角色列表
        async getRoleList(){
            const result = await roleList();
            this.$set(this,'roleData',result.data);
            this.currentRoleId = result.data[0];
            this.handleClickModuleMenu(this.currentRoleId);
        },
        // 获取菜单列表
        async getMenuList(){
            let param = {status:''}
            const result = await menuList(param)
            this.treeMenuData = result.data
        },
        onSelect(selectedKeys, item){
            this.selectedKeys = selectedKeys;
        },
        onExpand(expandedKeys){
            this.expandedKeys = expandedKeys;
            this.autoExpandParent = false;
        },
        onCheck(checkedKeys,item) {
            this.checkedKeys = checkedKeys;
        },
        // 提交
        async handleOk(){
            this.submitLoading = true;
            // console.log(this.checkedKeys,'this.checkedKeys',this.selectedKeys,'this.selectedKeys',this.expandedKeys)
            let result = await roleMenuMod({
                roleId:this.currentRoleId.id,
                menuIds:this.checkedKeys
            })
            if (result.code == 200){
                this.$message.success('保存成功');
                this.init();
            }
            this.submitLoading = false;
        },
        async roleMenu(){
            let result = await roleMenuMod()
        }
    },
    created(){
        this.init()
    }
   
}
</script>

<style scoped lang="scss" >
    .page-container{
        // border: 1px blue solid;
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction:row;
        // padding: 10px 10px 10px 10px;        
    }
    // .module-role{
    //     width: 20%;
    //     width: 220px;

    // }
    .module-role {
        width: 20%;
        height: 100%;
        .ant-card-head-title::before {
            content: ' ';
            display: inline-block;
            width: 18px;
            height: 18px;
            margin-right: 6px;
        }
        .ant-tree-node-content-wrapper {
            width: 90%;
        }
        .handle-delete,
        .handle-edit {
            padding: 0;
            display: none;
            line-height: 1;
            height: inherit;
            margin-left: 0;
            // color: @main-color-blur;
            color: #979797;
            > em {
                font-size: 13px;
            }
        }
    }
    .menuClass{
        height: 100%;
        width:80%
    }
    .footer-container{
    position:fixed;
    right:30px;
    bottom:20px;

    }
</style>