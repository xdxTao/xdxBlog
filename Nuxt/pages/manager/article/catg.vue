
<template>
    <div class="page-container">
        <a-card :bordered="false" class="page-container-card">
            <div class="table-top-handle-wrapper">
                <a-button type="primary" @click="addCatg" icon="plus">添加</a-button>
            </div>
            <a-table
                class="tableDiv"
                :columns="columns"
                :data-source="catgList"
                :expanded-row-keys.sync="expandedRowKeys"
                rowKey="id" 
                ref="table"
                :loading="loading"
                :pagination="false"
                @change="handleTableChange"
            >
                
                <div class="right-handle" slot="handle" slot-scope="text, row">
                    <a-button type="link" size="small" @click.stop="modifyCatg(row)">修改</a-button>                    
                </div>
            </a-table>
            <a-pagination v-model="current" :total="total" show-less-items class="antd-pagination" @change="pageChange"></a-pagination>
            <add-catg ref="form" @success="addCatgSuccess" ></add-catg>
        </a-card>
    </div>
</template>

<script>
import { cloneDeep } from 'lodash';
import {getCatgList } from '~/api/manager/article';
import addCatg from './addCatg.vue';
  export default {
    layout: 'manager',
    components:{addCatg },
    data() {
        return {
            current:1,
            pageSize:10,
            total:0,
            loading:false,
            expandedKeys: [], // （受控）更多指定的树节点
            autoExpandParent: true,
            checkedKeys: [], // 选中的
            selectedKeys: [], // （受控）设置选中的树节点
            expandedRowKeys:[],
            labelCol: { span: 4 },
            wrapperCol: { span: 14 },
            handleType: 0,
            submitLoading: false,
            catgList:[
            ],
            columns:[
                {
                    title: '序号',
                    dataIndex: 'indexs',
                    key: 'indexs',
                    width: '10%',
                },
                // {
                //     title: '分类ID',
                //     dataIndex: 'id',
                //     key: 'id',
                //     width: '20%',
                // },
                {
                    title: '分类名称',
                    dataIndex: 'name',
                    key: 'name',
                    width: '20%',
                },
                {
                    title: '文章数量',
                    dataIndex: 'articleCount',
                    key: 'articleCount',
                },
                {
                    title: '状态',
                    dataIndex: 'status',
                    key: 'age',
                    width: '20%',
                    customRender: value => (value == 'ENABLE' ? '启用' : value == 'DISABLE'?'禁用' : '')
                },
                {
                    title: '操作',
                    width: '20%',
                    align: 'center',
                    dataIndex: 'handle',
                    scopedSlots: { customRender: 'handle' }
                }
            ],
            modifyUserDialog: false,
            userDetailDialog: false,
        };
    },
    methods: {
        initData(){
            this.getCatgList();
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
        async getCatgList(){
            let param ={current: this.current,size: this.pageSize}
            const result = await getCatgList(param);
            result.data.records.map((item,index) => {
                    item.indexs = index + 1;
                })
            this.$set(this, 'catgList',result.data.records);
            this.total = result.data.total
            this.$set(this,'loading',false)
        },
        /**
         * 添加用户
         */
        addCatg(){
            this.$refs.form.openCreateMode();
        },
        /**
         * 添加用户成功事件处理
         */
        addCatgSuccess() {
            this.getCatgList();
        },
        /**
         * 修改用户
         */
        modifyCatg(data){
            this.$refs.form.openEditMode(data);
        },
        handleTableChange(pag){
            // this.pagination.current = pag.current
            // this.pagination.pageSize = pag.pageSize
            this.current = pag.current
            this.pageSize = pag.pageSize
            this.catgList()
        },
        // 分页change事件
        pageChange(val){
            this.current = val
            this.catgList()
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
        // height: 100%;
        display: flex;
        flex-direction:row;
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
    .table-top-handle-wrapper{
        padding-bottom: 10px;

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