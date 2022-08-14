<template>
    <a-modal
        v-model="visible"
        :title="mode === 'add' ? '添加角色':'修改角色'"
        width="540px"
        @cancel="cancelAddUser"
        @ok="addFormSubmit"
        ok-text="确定"
        cancel-text="取消"
        :mask-closable="false"
        :destroyOnClose="true"
        :confirm-loading="confirmLoading"
    >
        <a-form-model ref="form" :model="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
            <a-form-model-item label="角色名" prop="name" :rules="[{ required: true, message: '请输入必填项!' }]">
                <a-input placeholder="请输入角色名" v-model="form.name"></a-input>
            </a-form-model-item>
        </a-form-model>
    </a-modal>
</template>

<script>
import {addRole, } from '~/api/manager/role.js'
export default {
    data(){
        return{
            visible:false,
            confirmLoading:false,
            mode:'',
            form:{
                name:''
            }
        }
    },
    methods:{
        cancelAddUser(){
            this.visible = false
        },
        addFormSubmit(){
            console.log('xinz')
            this.confirmLoading = true
            if (this.isAddMode) {
                this.add();
            } else if (this.isEditMode) {
                // this.update();
            }
        },
        openAdd(){
            this.visible = true;
            this.mode = 'add';
            const that = this;
            setTimeout(function () {
                that.reset();
            }, 10);
        },
        openUpdate(data){
            this.visible = true;
            this.mode = 'edit';
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
        add(){
            let param = this.$refs.form
             param.validate(async valid => {
                if (valid) {
                    await addRole(this.form);
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
                    await modifyRole(this.form);
                    this.confirmLoading = false
                    this.cancelAddUser()
                    this.$message.success('修改用户成功！');
                    this.$emit('success');
                    // this.reset();
                }
            });
        },
        reset(){
            form:{
                name:''
            }
        }
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
