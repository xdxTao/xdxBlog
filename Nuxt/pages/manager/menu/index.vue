<template>
    <div class="page-container">
        <a-card class="menuClass" title="菜单">
            
            <a slot="extra" @click="handleClickAddModuleChildrenMenu">
                <a-icon type="plus"  class="icon"/>添加</a>
            <a-tree
                class="draggable-tree"
                draggable
                block-node
                :tree-data="treeMenuData"
                v-model="checkedKeys"
                :checkable="false"
                :replaceFields="{
                    children: 'children',
                    title: 'name',
                    key: 'id'
                }"
                :expanded-keys="expandedKeys"
                :auto-expand-parent="autoExpandParent"
                :selected-keys="selectedKeys"
                :showLine="false"
                @expand="onExpand"
                @select="onSelect"
            >
                <template slot="custom" slot-scope="item">
                    <div class="tree-item-title">
                        <span>{{ item.name }}</span>
                        <div class="handle-button">
                            <a-tooltip :destroyTooltipOnHide="true">
                                <template slot="title">新增</template>
                                <a-button class="handle-add" type="link" @click.self.stop="handleAddTreeItem(item)">
                                    <!-- <plus-circle-outlined /> -->
                                    <!-- <a-icon type="plus-outlined"  class="icon"/> -->
                                    <a-icon type="plus" />
                                    <!-- <plus-outlined /> -->
                                </a-button>
                            </a-tooltip>
                        </div>
                    </div>
                </template>
            </a-tree>
        </a-card>
        <div class="handle-content">
             <a-form-model v-show="handleType" ref="formRef" :model="form" :label-col="labelCol" :wrapper-col="wrapperCol">
                <a-form-model-item label="上级菜单" v-if="form.parentId">{{ form.parentName || parentMenuItem.name }}</a-form-model-item>
                <a-form-model-item label="菜单名称" prop="name" :rules="[{ required: true, message: '请输入必填项!' }]">
                    <a-input v-model="form.name" placeholder="请输入菜单名称" />
                </a-form-model-item>
                <a-form-model-item label="菜单路径" prop="menuUrl" :rules="[{ required: true, message: '请输入必填项!' }]">
                    <a-input v-model="form.menuUrl" placeholder="请输入路径" />
                </a-form-model-item>
                <!-- <a-form-model-item label="权限标识" prop="permission">
                    <wefa-select-search-input
                        ref="WefaSelectSearchInput"
                        v-model="form.permission"
                        title="请选择权限标识码"
                        customReturnField="code"
                        inputText="code"
                        :tree-data="permissionTreeData"
                        :is-visible-reset-data="true"
                        :multi="false"
                        placeholder="点击选择权限标识"
                        @change="handleChangePermission"
                    />
                </a-form-model-item> -->
                <a-form-model-item label="图标" prop="icon" >
                    <!-- <a-input v-model="form.icon" placeholder="请输入图标字符">
                        <a-icon type="pie-chart" />
                    </a-input> -->
                    <a-input v-model="form.icon" placeholder="请输入图标字符">
                    <a-tooltip slot="suffix" title="https://1x.antdv.com/components/icon-cn/">
                        <a-icon :type=form.icon style="color: rgba(0,0,0,.45)" />
                        <!-- <Icon icon="plus" ></Icon> -->
                    </a-tooltip>
                    </a-input>
                </a-form-model-item>
                <a-form-model-item label="排序" prop="sort">
                    <a-input-number v-model="form.sort" auto-complete="off" placeholder="请输入排序" :min="0" />
                </a-form-model-item>
                <a-form-model-item label="是否启用" prop="statusED" >
                    <a-switch v-model="form.statusED" @change="handleChangeShow" :checked="form.statusED"/>
                </a-form-model-item>
                <a-form-model-item label=" " class="empty-str">
                    <a-button type="primary" @click="onSubmit" :loading="submitLoading">确认{{ handleType == 1 ? '新增' : '修改' }}</a-button>
                </a-form-model-item>
            </a-form-model>
            <a-empty v-show="!handleType" style="margin-top: 50px" description="请先点击左侧操作"></a-empty>
        </div>
    </div>
</template>

<script>
import { cloneDeep } from 'lodash';
import {menuList, addMenu, modMenu } from '~/api/manager/menu.js';
  export default {
    layout: 'manager',
    components:{ },
    data() {
        return {
            iconED: 'plus',
            treeMenuData: [
                // {
                //     children: [],
                //     id: 26,
                //     menuUrl: "menuUrl",
                //     name: "name",
                //     parentId: 0,
                //     sort: 1,
                //     status: "DISABLE",
                //     icon: 'search'
                // },
                // {
                //     children: [],
                //     id: 27,
                //     menuUrl: "menuUrl",
                //     name: "name1",
                //     parentId: 0,
                //     sort: 1,
                //     status: "ENABLE",
                //     icon: 'plus'
                // }
            ],
            expandedKeys: [], // （受控）更多指定的树节点
            autoExpandParent: true,
            checkedKeys: [], // 选中的
            selectedKeys: [], // （受控）设置选中的树节点

            labelCol: { span: 4 },
            wrapperCol: { span: 14 },
            handleType: 0,
            submitLoading: false,
            treeData: [
            //     {
            //         name: 1,
            //         key: 1,//唯一标识
            //         area: 100,
            //         scopedSlots: {
            //             title: 'custom'
            //         },
            //         children: [{
            //                 name: 101,
            //                 key: 11,//唯一标识
            //                 area: 110,
            //                 scopedSlots: {
            //                     title: 'custom'
            //                 },
            //         },{
            //                 name: 102,
            //                 key: 12,//唯一标识
            //                 area: 310,
            //                 scopedSlots: {
            //                     title: 'custom'
            //                 },
            //         }]
            // }
            ],

            // formRules: {
            //     name: [
            //         {
            //             required: true,
            //             message: '请输入名称',
            //             trigger: 'change'
            //         }
            //     ],
            //     path: [
            //         {
            //             required: true,
            //             message: '请输入访问路径',
            //             trigger: 'change'
            //         }
            //     ]
            // },
            form: {
                parentId: '',
                menuId: '',
                name: '',
                menuUrl: '',
                sort:1,
                permission: '',
                statusED: true,
                status: 'ENABLE'
            },
            parentMenuItem: null,
        };
    },
    methods: {
        initData(){
            this.menuList();
        },
        onSelect(selectedKeys, item) {
            this.handlEditTreeItem({
                dataRef: item.node.dataRef,
                pos: item.node.pos
            });
            this.selectedKeys = selectedKeys;
        },
        // 更多/收起节点时触发
        onExpand(expandedKeys) {
            this.expandedKeys = expandedKeys;
            this.autoExpandParent = false;
        },
        onCheck(checkedKeys) {
            this.checkedKeys = checkedKeys;
        },
        // 获取菜单列表
        async menuList() {
            let param = {status:''}
            const result = await menuList(param);
            const data = this.reWriterWithSlot(result.data || []);
            this.$set(this, 'treeMenuData',result.data);
        },
        reWriterWithSlot(arr) {
            for (const item of arr) {
                item.scopedSlots = {
                    title: 'custom'
                };
                if (item.children && item.children.length > 0) {
                    this.reWriterWithSlot(item.children, item);
                }
            }
            return arr;
        },

        handlEditTreeItem(item) {
            this.form = cloneDeep(item.dataRef);
            this.parentMenuItem = {
                ...item.dataRef,
                pos: item.pos.split('-')
            };
            if(item.dataRef.status == 'DISABLE'){
                // this.form.statusED = false
                this.$set(this.form,'statusED',false);
            }
            if(item.dataRef.status == 'ENABLE'){
                // this.form.statusED = true
                this.$set(this.form,'statusED',true);
            }
            if (item.dataRef.icon == '' || !item.dataRef.icon){
                this.$set(this.form,'icon','info-circle');
            }
            this.handleType = 2;
            this.$nextTick(() => {
                // this.$refs.WefaSelectSearchInput.initData();
            });
        },
        handleClickAddModuleChildrenMenu(){
            this.selectedKeys = []
            this.parentMenuItem = [];
            // this.parentMenuItem = JSON.parse(JSON.stringify(this.currentModuleMenuItem));
            this.parentMenuItem.pos = [];
            this.form = cloneDeep(this.$options.data().form);
            this.form.parentId = this.parentMenuItem.id;
            if (this.form.icon == ''|| !this.form.icon){
                this.$set(this.form,'icon','info-circle');
            }

            this.handleType = 1;
            // this.$refs.WefaSelectSearchInput.handleEmpty();
        },
        // 点击新增二级菜单
        handleAddTreeItem(item){
            this.form = cloneDeep(this.$options.data().form);
            this.form.parentId = item.dataRef.id;
            this.form.menuUrl = item.dataRef.menuUrl
            this.parentMenuItem = {
                ...item.dataRef,
                pos: item.pos.split('-')
            };
             if (this.form.icon == ''|| !this.form.icon){
                this.$set(this.form,'icon','info-circle');
            }
            this.handleType = 1;
            this.$nextTick(() => {
                // this.$refs.WefaSelectSearchInput.handleEmpty();
            });
        },
        handleChangeShow(bool) {
            const num = Number(bool);
            this.form.statusED =  bool;
            this.$set(this.form,'statusED',bool);
        },
        // 提交
        onSubmit() {
            if (this.submitLoading) return false;
            if(this.form.statusED == false){
                // this.form.statusED = false
                this.$set(this.form,'status','DISABLE');
            }
            if(this.form.statusED == true){
                // this.form.statusED = true
                this.$set(this.form,'status', 'ENABLE');
            }
            this.$refs.formRef.validate(async bool => {
                if (bool) {
                    this.submitLoading = true;
                    if (this.handleType === 1) {
                      const result = await addMenu(this.form);
                        if (result.code == 200){
                            this.$message.success(`菜单【${this.form.name}】新增成功！`);
                            this.menuList();
                            this.handleType = 0;
                        }
                    } else if (this.handleType === 2) {
                        const result1 =  await modMenu(this.form);
                        if (result1.code == 200){
                            this.$message.success(`菜单【${this.form.name}】修改成功！`);
                            this.menuList();
                            this.handleType = 0;
                        }
                    }
                    this.submitLoading = false;
                    
                }
            });
        },
    },
    created() {
        this.initData()
    },
    computed: {
        isVisible: {
            get() {
                const data = Number(this.form.show);
                return Boolean(data);
            },
            set(value) {
                const num = Number(value);
                this.form.show = '' + num;
                return value;
            }
        }
    }


  }
</script>
<style scoped lang="scss">
    .page-container{
        // border: 1px blue solid;
        width: 100%;
        display: flex;
        flex-direction:row;
    }

    .menuClass{
        // border: 1px red solid;
        // box-shadow: 0 1px 8px 0 rgba(0, 0, 0, 0.15);
        width: 20%;
        // height: 100%;
        // margin: 10px 0px 10px 10px;
        padding: 10px;
        .tree-item-title{
            display: flex;
            justify-content: space-between;
            &:hover {
                .ant-btn {
                    display: inline-block;
                }
            }
            .handle-button {
                margin-left: 30px;
            }
            .ant-btn {
                display: none;
                margin-left: 0;
                padding: 0;
                height: inherit;
                line-height: 1;
                // color: @main-color-blur;
                color: #979797;
                > em {
                    font-size: 13px;
                }
                &.ant-btn > .anticon + span {
                    margin-left: 4px;
                }
            }
        }
        // .icon{
        //     font-size: 16px;
        // }
    }
    .draggable-tree{
        width: 100%;
        height: 100%;
        // border: 1px green solid;
    }
    .handle-content{
        width: 0%;
        flex: 1;
        // margin: 10px 10px 10px 0;
        padding: 10px;
        background-color: #fff;
        // border-bottom: 1px solid #e4e3e3;
        // border-top: 1px solid #e4e3e3;
        // border-right: 1px solid #e4e3e3;
        border: 1px solid #e4e3e3;
        border-left: 0 solid #e4e3e3;
        
        // box-shadow: 0 1px 8px 0 rgba(0, 0, 0, 0.15);
        .empty-str {
            ::after {
                display: none;
            }
        }
    }
</style>