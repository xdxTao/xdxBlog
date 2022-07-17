<template>
    <a-modal
        v-model="visible"
        :title="mode === 'add' ? '添加分类':'修改分类'"
        width="560px"
        @cancel="cancelAddUser"
        @ok="addFormSubmit"
        ok-text="确定"
        cancel-text="取消"
        :mask-closable="false"
        :destroyOnClose="true"
        :confirm-loading="confirmLoading"
    >
    <!-- ="dialog" -->
        <a-form-model ref="form" :model="form" :label-col="{ span: 8 }" :wrapper-col="{ span: 16 }">
            <a-form-model-item label="分类名" prop="name" :rules="[{ required: true, message: '请输入必填项!' }]">
                <a-input placeholder="请输入分类名" v-model="form.name"  style="width: 200px"></a-input>
            </a-form-model-item>
            <a-form-model-item label="所属分类" prop="parentId" :rules="[{ required: true, message: '请输入必填项!' }]">
                <a-tree-select
                    placeholder="请选择所属分类"
                    style="width: 200px"
                    :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                    :filter-option="filterOption"
                    v-model="form.parentId"
                    :tree-data="treeData"
                    :replaceFields="{children:'children', title:'name', key:'id', value: 'id' }"
                    @change="roleChange"
                >
                </a-tree-select>
            </a-form-model-item>
            <a-form-model-item label="排序" prop="sort">
                    <a-input-number v-model="form.sort" auto-complete="off" placeholder="请输入排序" :min="0" style="width: 200px" />
                </a-form-model-item>
            <a-form-model-item label="是否公开" prop="statusEd" >
                <a-switch v-model="form.statusEd" @change="handleChangeShow" :checked="form.statusEd"/>
            </a-form-model-item>
        </a-form-model>
        
    </a-modal>
</template>
<script>
import {getCatgList,addCatg, modifyCatg } from '~/api/manager/article.js'
import {roleList} from '~/api/manager/role.js'

export default {
    data(){
        return{
            mode:'',
            visible:false,
            confirmLoading:false,
            treeData:[],
            form:{
                name: '',
                parentId:'',
                statusEd:true,
                sort:'',
            },
            rules: {
                username: [{ required: true, message: '请输入用户名' }],
                password: [{ required: true, message: '请输入密码!' }],
            }
        }
    },
    methods:{
        initData(){
            this.getCatgList()
        },
        async getCatgList(){
            let param = {current: 1,size: 10000 }
            const result = await getCatgList(param)
            this.treeData = result.data.records
        },
        filterOption(input, option){
            return option.value.toLowerCase().indexOf(input.toLowerCase()) >= 0;
        },
        cancelAddUser() {
            this.$emit('cancel');
            this.visible = false
            // this.reset();
        },
        addFormSubmit(){
            // console.log('dianjii提交',this.form)
            if(this.form.statusEd == false){
                this.$set(this.form,'status','DISABLE');
            }
            if(this.form.statusEd == true){
                this.$set(this.form,'status', 'ENABLE');
            }
           this.confirmLoading = true;
           console.log(33)
            if (this.isAddMode) {
                console.log(111)
                this.add();
            } else if (this.isEditMode) {
                this.update();
            }

        },
        reset() {
            Object.assign(this.form, {
                name: '',
                parentId:'',
                statusEd:true,
                sort:'',
            });
        },
        add(){
            console.log(222)
            let param = this.$refs.form
             param.validate(async valid => {
                if (valid) {
                  const result = await addCatg(this.form);
                  if (result.code == 200){
                    this.confirmLoading = false
                    this.cancelAddUser()
                    this.$message.success('添加分类成功！');
                    this.$emit('success');
                  }                    
                }
            });
        },
        handleChangeShow(){

        },
        update(){
            let param = this.$refs.form
             param.validate(async valid => {
                if (valid) {
                    const result = await modifyCatg(this.form);
                    if (result.code == 200){
                        this.confirmLoading = false
                        this.cancelAddUser()
                        this.$message.success('修改分类成功！');
                        this.$emit('success');
                    }
                    // this.reset();
                }
            });
        },
        openCreateMode() {
            this.visible = true;
            this.mode = 'add';
            this.targetInstId = -1;
            const that = this;
            setTimeout(function () {
                that.reset();
            }, 10);
        },
        openEditMode(data) {
            console.log(1);
            this.mode = 'edit';
            this.visible = true;
            this.load(data);
        },
        load(data){
            console.log(111,data)
            this.reset();
            if ( data == '') {
                return;
            }
            if(data.status == 'DISABLE'){
                this.$set(data,'statusEd',false);
            }
            if(data.status == 'ENABLE'){
                this.$set(data,'statusEd', true);
            }
            if(data.parentId == 0){
                data.parentId = null
            }
             const that = this;
            Object.assign(that.form, data);
        },
        roleChange(option){
            console.log(option,'option')
        }
    },
    created() {
        this.initData()
    },
    computed: {
        isAddMode() {
            return this.mode === 'add';
        },
        isEditMode() {
            return this.mode === 'edit';
        }
    },
}
</script>