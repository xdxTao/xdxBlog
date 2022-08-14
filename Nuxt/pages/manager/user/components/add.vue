<template>
    <a-modal
        v-model="visible"
        :title="mode === 'add' ? '添加用户':'修改用户'"
        width="540px"
        @cancel="cancelAddUser"
        @ok="addFormSubmit"
        ok-text="确定"
        cancel-text="取消"
        :mask-closable="false"
        :destroyOnClose="true"
        :confirm-loading="confirmLoading"
    >
    <!-- ="dialog" -->
        <a-form-model ref="form" :model="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
            <a-form-model-item label="用户名" prop="name" :rules="[{ required: true, message: '请输入必填项!' }]">
                <a-input placeholder="请输入用户名" v-model="form.name" ></a-input>
            </a-form-model-item>
            <a-form-model-item label="所属角色" prop="roleId" :rules="[{ required: true, message: '请输入必填项!' }]">
                <a-select
                    placeholder="请选择所属角色"
                    :filter-option="filterOption"
                    v-model="form.roleId"
                    @change="roleChange"
                >
                    <!-- :fieldNames="fieldNames" -->
                    <a-select-option
                        v-for="(item, index) in rolesList"
                        :value="item.id"
                        :key="index"
                    >
                    {{item.name}}
                    </a-select-option>
                </a-select>
            </a-form-model-item>
        </a-form-model>
        
    </a-modal>
</template>
<script>
import {addUser, modifyUser} from '~/api/manager/user.js'
import {roleList} from '~/api/manager/role.js'

export default {
    data(){
        return{
            mode:'',
            visible:false,
            confirmLoading:false,
            fieldNames:{
                label: 'name', 
                value: 'name', 
                options: 'id'
            },
            rolesList:[
                // {
                //     "id": 1,
                //     "name": "超级管理员"
                // },
                // {
                //     "id": 2, 
                //     "name": "管理员" 
                // }
            ],
            form:{
                name: '',
                roleId:''
            },
            rules: {
                username: [{ required: true, message: '请输入用户名' }],
                password: [{ required: true, message: '请输入密码!' }],
            }
        }
    },
    methods:{
        initData(){
            this.getRoleList()
        },
        async getRoleList(){
            const result = await roleList()
            this.rolesList = result.data
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
            // console.log('dianjii提交')
            // addUser
            // let param = this.$refs.form
            //  param.validate(async valid => {
            //     if (valid) {
            //         await addUser(this.form);
            //         this.$message.success('添加用户成功！');
            //         this.$emit('success');
            //         // this.reset();
            //     }
            // });
           this.confirmLoading = true;
            if (this.isAddMode) {
                this.add();
            } else if (this.isEditMode) {
                this.update();
            }

        },
        reset() {
            Object.assign(this.form, {
                password: '',
                username: '',
                userType: '',
                name: '',
                roleId:''
            });
        },
        add(){
            console.log('dianji xinzeng 了')
            let param = this.$refs.form
             param.validate(async valid => {
                if (valid) {
                    await addUser(this.form);
                    this.confirmLoading = false
                    this.cancelAddUser()
                    this.$message.success('添加用户成功！');
                    this.$emit('success');
                    // this.reset();
                }
            });
        },
        update(){
            
            let param = this.$refs.form
             param.validate(async valid => {
                if (valid) {
                    await modifyUser(this.form);
                    this.confirmLoading = false
                    this.cancelAddUser()
                    this.$message.success('修改用户成功！');
                    this.$emit('success');
                    // this.reset();
                }
            });
        },
        openCreateMode() {
            // console.log(1);
            this.visible = true;
            this.mode = 'add';
            this.targetInstId = -1;
            const that = this;
            setTimeout(function () {
                that.reset();
            }, 10);
        },
        openEditMode(data) {
            this.mode = 'edit';
            this.visible = true;
            this.load(data);
        },
        load(data){
            this.reset();
            if ( data == '') {
                return;
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