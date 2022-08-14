<template>
    <div class="main">
        <div class="loginLeft">
            <p>欢迎回来</p>
            <div class="loginFooter">
                <a-icon type="check-circle" />
                个人博客系统
            </div>
        </div>

        <div class="loginRight">
            <div class="topText">登录</div>
            <a-form  :form="form" >
                <br>
                <a-form-item  :label-col="{ span: 5 }"  :wrapper-col="{ span: 12 }">
                    <div class="label">账号</div>
                    <a-input class="input" 
                    placeholder="请输入账号..." 
                    v-decorator="[
                    'account',
                    {rules: [{ required: true, message: '请输入账号！' }]}
                    ]"
                    />
                </a-form-item>
                <a-form-item  :label-col="{ span: 5 }"  :wrapper-col="{ span: 12 }">
                    <div class="label">密码</div>
                    <a-input class="input" 
                    type="password"
                    placeholder="请输入密码..."
                    v-decorator="[   
                        'password',{rules: [{ required: true, message: '请输入密码!' }]}
                    ]"
                    />
                      </a-form-item>
                <a-button type="primary" @click="submit">
                    登陆
                </a-button>
            </a-form>
        </div>
    </div>
</template>

<script >
export default{
    layout: 'block',
    data () {
        return {
            form: this.$form.createForm(this),
        };
    },
    methods: {
        submit() { // 提交表单
            this.form.validateFields((err, values) => {
                if (!err) {
                    let val = this.form.getFieldsValue();
                    this.$axios.post("user/login", val)
                    .then((res) => { 
                        this.$store.commit('setToken',res.token) 
                        window.localStorage.setItem("storeState",JSON.stringify(this.$store.state))
                        this.$router.push('/')
                    })
                }
            });
        },
    },
}
</script>

<style lang="scss" scoped>
    .main{
        width: 700px;
        height: 400px;
        display: flex;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translateX(-46%) translateY(-50%);
        box-shadow: 0 0 30px #e5e5e5;
        background-color: #fff;
        opacity: .8;
        .loginLeft{
            width: 50%;
            height: 100%;
            position: relative;
            background: url('../static/img/login.png');
            p{
                position: absolute;
                top: 100px;
                left: 40px;
                color: #fff;
                font-size: 24px;
            }
            .loginFooter{
                position: absolute;
                top: 360px;
                left: 95px;
                font-size: 16px;
                color: #fff;
            }
        }

        .loginRight{
            width: 50%;
            height: 100%;
            .topText{
                margin-top: 50px;
                font-size: 24px;
                color: #9350ff;
                text-align: center;
            }
            form{
                width: 350px;
                height: 233.6px;
                position: absolute;
                top: 100px;
                left: 400px;
                // color: #fff;
                font-size: 24px;
                .input{
                    width: 250px;
                    height: 28px;
                    line-height: 28px;
                    border-radius: 0%;
                    font-size: 13px;
                    border: 1px solid #dcdfe6;
                    border-radius: 0;
                    border-top: 0;
                    border-left: 0;
                    border-right: 0;
                    margin-top: -100px;
                    padding: 0;
                    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
                    outline:none;
                    box-shadow: none;
                    margin-top: -20px;
                    //  border-bottom: black;
                }
                .input:focus{
                    border: 1px solid #9350ff;
                    border-radius: 0;
                    border-top: 0;
                    border-left: 0;
                    border-right: 0;   
                }
                .label{
                   
                    height: 28px;
                    // border: 1px red solid;
                    padding-top: 8px;
                    line-height: 28px;
                    // margin-top: 20px;
                }
                button{
                    width: 250px;
                    background-color: #9350ff;
                    border:#9350ff;
                    font-size: 13px;
                }
            }
        }
    }
</style>

