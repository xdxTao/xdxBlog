<template>
    <div class="write">
        <div class="mavonEditor" :style="{width:editorWidth == '1' ? '100%': editorWidth == '2' ? '80%' : editorWidth == '3' ? '60%' : '100%'} ">
                <div class="top" style="height:6vh;">
                    <div class="left">
                        <a-input size="large" style="width: 100%;" v-model="form.title" placeholder="输入文章标题..."/>
                    </div>
                    <div class="center">
                        <a-button type="primary" size="large" @click="visible = true">发布文章</a-button>
                    </div>
                    <div class="right">
                        <nuxt-link to="/manager/article/article"><a-icon type="home" class="icon"/></nuxt-link> 
                        <a-select placeholder="请选择..." class="selectWidth" v-model="editorWidth">
                            <a-select-option value="1">100%</a-select-option>
                            <a-select-option value="2">80%</a-select-option>
                            <a-select-option value="3">60%</a-select-option>
                        </a-select>
                    </div>
                
                </div>
                <div class="bottom">
                    <no-ssr>
                        <mavon-editor
                            ref=form
                            :toolbars="markdownOption" 
                            v-model="form.markdownContext" 
                            tabSize:4 
                            codeStyle="dracula"
                            @save="save"
                            @change="change"
                            :class="visible==true?'editor':''" 
                            style="height:94vh;"
                            @imgAdd="$imgAdd" />
                    </no-ssr>
                </div>
                <a-modal
                    title="发布文章"
                    :visible="visible"
                    :width="550"
                    @cancel="handleCancel"
                    :maskClosable="false"
                    style="top: 200px;"
                    :footer="null"
                    >
                    <a-form-model ref="form" :model="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
                        <a-form-model-item label="文章标签" prop="label" :rules="[{ required: true, message: '请输入必填项!' }]">
                            <template v-for="tag in tags" >
                                <a-tag 
                                    color="blue"
                                    :key="tag"
                                    :closable="true"
                                    :close="() => handleClose(tag)"
                                >
                                    {{tag}}
                                </a-tag>
                            </template>
                            <!-- 添加标签的input框 -->
                            <a-input
                                v-if="inputVisible" ref="input"  type="text" size="small"
                                :style="{ width: '78px' }"
                                :value="inputValue"
                                @change="handleInputChange"
                                @blur="handleInputConfirm"
                                @keyup.enter="handleInputConfirm"
                            />
                            <!-- 点击添加标签 -->
                            <a-tag v-if="inputVisible == false && this.tags.length < 5"  @click="showInput" style="background: #fff; borderStyle: dashed;">
                                <a-icon type="plus" /> 添加标签
                            </a-tag>

                        </a-form-model-item>
                        <!-- <a-form-model-item label="图片标题" prop="fileList" >
                            <a-upload
                                action="http://127.0.0.1:8185/api/blogArticle/uploadImg"
                                listType="picture-card"
                                :fileList="fileList"
                                name="ArticleImage"
                                @change="handleChange"
                            >
                                <div v-if="fileList.length < 1">
                                    <a-icon type="plus" />
                                    <div class="ant-upload-text">Upload</div>
                                </div>
                            </a-upload>
                        </a-form-model-item> -->
                        <a-form-model-item label="文章分类" prop="catg" :rules="[{ required: true, message: '请输入必填项!' }]">
                            <a-cascader :options="blogCatg" v-model="form.catg" style="width:350px" :field-names="fieldNames" placeholder="请选择文章分类..." :default-value="defaultBlogCatg" changeOnSelect  @change="onChangeCatg" />
                        </a-form-model-item>
                        <a-form-model-item label="文章类型" prop="type" :rules="[{ required: true, message: '请输入必填项!' }]">
                            <a-select placeholder="请选择..." style="width: 350px" v-model="form.type">
                                <a-select-option value="ORIGINAL">原创</a-select-option>
                                <a-select-option value="REPRINT">转载</a-select-option>
                                <a-select-option value="TRANSLATE">翻译</a-select-option>
                            </a-select>                        
                        </a-form-model-item>
                        <a-form-model-item label="发布形式" prop="open" :rules="[{ required: true, message: '请输入必填项!' }]">
                            <a-radio-group  v-model="form.open" style="padding-top: 5px;">
                                <a-radio value="YES">公开</a-radio>
                                <a-radio value="NO">私密</a-radio>
                            </a-radio-group>
                        </a-form-model-item>
                        <a-form-model-item label="是否置顶" prop="top" :rules="[{ required: true, message: '请输入必填项!' }]">
                            <a-radio-group  v-model="form.top" style="padding-top: 5px;">
                                <a-radio value="YES">置顶</a-radio>
                                <a-radio value="NO">不置顶</a-radio>
                            </a-radio-group>
                        </a-form-model-item>
                    </a-form-model>
                    <div style="height: 30px; text-align: right;">
                        <span class="span" @click="handleCancel">取消</span>
                        <span class="span" @click="release('YES')">保存为草稿</span>
                        <span class="span" @click="release('NO')" style="color:#3399ea;">发布文章</span>
                    </div>
                </a-modal>
        </div>
    </div>
    
</template>

<script>
import {getCatgList, addArticle, modifyArticle,getarticleInfo  } from '~/api/manager/article';
export default {
    layout: 'block',
    data(){
        return{
            form:{
                title: '',
                markdownContext: '',
                type: '',
                open: '',
                top:'',
                label:'',
                id:''
            },
            tags:[],
            markdownOption: {
                bold: true, // 粗体
                italic: true, // 斜体
                header: true, // 标题
                underline: true, // 下划线
                strikethrough: true, // 中划线
                mark: true, // 标记
                superscript: true, // 上角标
                subscript: true, // 下角标
                quote: true, // 引用
                ol: true, // 有序列表
                ul: true, // 无序列表
                link: true, // 链接
                imagelink: true, // 图片链接
                code: true, // code
                table: true, // 表格
                fullscreen: true, // 全屏编辑
                readmodel: true, // 沉浸式阅读
                htmlcode: true, // 展示html源码
                help: true, // 帮助
                /* 1.3.5 */
                undo: true, // 上一步
                redo: true, // 下一步
                trash: true, // 清空
                save: true, // 保存（触发events中的save事件）
                /* 1.4.2 */
                navigation: true, // 导航目录
                /* 2.1.8 */
                alignleft: true, // 左对齐
                aligncenter: true, // 居中
                alignright: true, // 右对齐
                /* 2.2.1 */
                subfield: true, // 单双栏模式
                preview: true, // 预览
            },
            handbook: "#### how to use mavonEditor in nuxt.js",
            visible: false,
            saveed:'1',
            value: 1,
            blogCatg: [
        //         {
        //   value: 'zhejiang',
        //   label: 'Zhejiang',
        //   children: [
        //     {
        //       value: 'hangzhou',
        //       label: 'Hangzhou',
        //       children: [
        //         {
        //           value: 'xihu',
        //           label: 'West Lake',
        //         },
        //       ],
        //     },
        //   ],
        // },
        // {
        //   value: 'jiangsu',
        //   label: 'Jiangsu',
        //   children: 
        //   [
        //     {
        //       value: 'nanjing',
        //       label: 'Nanjing',
        //       children: [
        //         {
        //           value: 'zhonghuamen',
        //           label: 'Zhong Hua Men',
        //         },
        //       ],
        //     },
        //   ],
        // },
            ],
            defaultBlogCatg: [],
            // 文章标签
            tags: [],
            inputVisible: false,
            inputValue: '',
            isEdit: 1,
            fileList: [],
            editorWidth:'1',
            fieldNames:{
                label: 'name', 
                value: 'id', 
                children: 'children'
            }
        }
    },
    created(){
        this.getCatg()
        // 判断是否为编辑
        if (this.$route.query.id !== undefined) {
            this.articleInfo();
            // this.$axios.get("blogArticle/getById?bamId=" + this.$route.query.bamId)
            // .then((res) => {  
            //     this.defaultBlogCatg = res.data.blogCatg
            //     this.form = res.data
            //     this.tags = res.data.tags
            //     if(res.data.imgTitle){
            //         this.fileList[0] = {
            //             uid: '-1',
            //             name: 'xxx.png',
            //             status: 'done',
            //             url: this.form.bamImgTitle,
            //         }
            //     }   
            // })

        }  
    },
    mounted(){
         window.addEventListener('beforeunload', e => this.beforeunloadHandler(e))
    },
    beforeRouteLeave(to, form, next) {
        // 此处为个人项目条件判断，当条件成立时才执行路由守卫
        console.log(this.$confirm,'this.$confirmthis.$confirm')
        this.$confirm({
        title: '是否离开当前页面',
        okText:"确定",
        cancelText:"取消",
        onOk() {
            next()
        },
        onCancel() {},
      });
    },
    methods:{
         beforeunloadHandler(e) {
            this._beforeUnload_time = new Date().getTime()
            e = e || window.event
            if (e) {
                e.returnValue = '关闭提示'
            }
            return '关闭提示'
        },


        // 上传博客标题图片
        handleChange (info) {
            this.fileList = info.fileList
            if(info.file.status === 'done'){
                this.fileList[0].url = info.file.response.data
                this.form.bamImgTitle = info.file.response.data
            }
        },
        // 绑定@imgAdd 上传图片
        $imgAdd(pos, $file) {
           // 第一步.将图片上传到服务器.
           var formdata = new FormData();
           formdata.append('ArticleImage', $file);
           formdata.append('bamId', this.form.bamId);
        //    axios({
        //     //    url: 'http://127.0.0.1:8185/api/blogArticle/uploadImg',
        //        url: 'https://www.xdx97.com:8185/api/blogArticle/uploadImg',
        //        method: 'post',
        //        data: formdata,
        //        headers: { 'Content-Type': 'multipart/form-data' },
        //    }).then((res) => {
        //        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        //        // $vm.$img2Url 详情见本页末尾
        //        this.$refs.md.$img2Url(pos, res.data.data);
        //    })
        },
        //  获取分类的方法
        async getCatg(){
            let param ={current: 1,size: 1000}
            const result = await getCatgList(param)
            const catgList = this.filterCatgList(result.data.records);
            this.$set(this,'blogCatg',catgList);
        },

        // 编辑数据获取
        async articleInfo(){
            // this.$route.query.bamId
            // const result = await getarticleInfo({
            //     id: this.$route.query.id
            // })
            const result = await getarticleInfo(this.$route.query.id)
            if (result.code == 200){
                this.defaultBlogCatg = result.data.catgIds;
                this.form = result.data;
                this.tags = result.data.labels;
                this.isEdit = 2;
            }
        },

        async save(){
            if (this.isEdit == 1 && this.form.id == ''){
                const result = await addArticle(this.form);
                if(result.code == 200){
                    if(this.form.draft == 'NO'){
                        this.visible = false
                        this.$message.success("发布文章成功");
                    }else {
                        this.$message.success("保存文章成功");
                    }
                    this.form.id = result.data;
                }
            } else if (this.isEdit == 2 || this.saveed == '2'){
                const result = await modifyArticle(this.form);
                if(result.code == 200){
                    if(this.form.draft == 'NO'){
                        this.visible = false
                        this.$message.success("发布文章成功")
                    }else {
                        this.$message.success("保存文章成功")
                    }
                }
            }
            
        },
        change(markdown ,html){
            this.form.htmlContext = html
            this.form.markdownContext = markdown
        },
        // 发布文章
        release(draft) {
            this.form.draft = draft
            if (draft == 'YES'){
                this.saveed = '2';
            }
            this.form.label = ''
            for(let i = 0 ; i < this.tags.length; i++){
                this.form.label += this.tags[i] + ","
            }
            // 发布的时候判断数据是否完整
            // if(this.form.label == ''){
            //     return this.$message.error("请输入文章标签")
            // } else if (this.form.catgId == '' || this.form.blogCatgTwoId == ''){
            //      return this.$message.error("请选择个人分类")
            // } else if (this.form.type == ''){
            //      return this.$message.error("请选择文章类型")
            // } else if (this.form.open === ''){
            //     return this.$message.error("请选择发布形式")
            // } else if (this.form.title === ''){
            //     return this.$message.error("请输入文章标题")
            // } else if (this.form.markdownContext === ''){
            //     return this.$message.error("请输入文章内容")
            // }
            this.save()
        },
        handleCancel(e) {
            this.visible = false
        },
        onChange(value) {
            // console.log(value);
        },
        //  添加标签的方法
        handleClose (removedTag) { // 删除标签
            console.log("removedTag : " + removedTag);
            
            const tags = this.tags.filter(tag => tag !== removedTag)
            console.log("tags : " + tags);
            this.tags = tags
        },

        showInput () {
            this.inputVisible = true
            this.$nextTick(function () {
                this.$refs.input.focus()
            })
        },

        handleInputChange (e) {
            this.inputValue = e.target.value
        },

        handleInputConfirm () { // 添加标签
            const inputValue = this.inputValue
            let tags = this.tags
            if (inputValue && tags.indexOf(inputValue) === -1) {
                tags = [...tags, inputValue]
            }
            Object.assign(this, {
                tags,
                inputVisible: false,
                inputValue: '',
            })
            console.log(this.tags,'this.tagsthis.tagsthis.tags')
            this.form.label = this.tags.join(",");
        },
        
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
        //  选中分类回调函数 
        onChangeCatg(value){
            console.log(value,'valuevaluevalue')
            // this.form.catgId = value[0]
            // this.form.blogCatgTwoId = value[1]
            // this.form.blogCatgThreeId = value[2]  
            const i = value.length;
            this.form.catgId = value[i - 1];
        }
    }
}
</script>

<style lang="scss" scoped>
.write{
    width: 100%;
    // height: 100%;
    display: flex;
    justify-content:center;
    // border: 1px blue solid;
    // padding-left: 10px;
    // padding-right: 10px;
    .mavonEditor {
        // width: 60%;
        height: 100%;
        // border: 1px blue solid;
        // padding: 10px;
        display: flex;
        flex-direction: column;
        .top{
            height: 60px;
            width: 100%;
            z-index: 100;
            // border: 1px red solid;
            background-color: #f8f8f8;
            line-height:60px;
            display: flex;
            justify-content: space-between;
            padding-left: 10px;
            padding-right: 10px;
            .left{
                width: 100%;
                // padding-left: 20px;
                // height: 46px;
                // border: 3px black solid;
            }
            .center{
                width: 180px;
                // height: 42px;
                // margin-top: 8px;
                // border: 1px blue solid;
                text-align: center;
            }
            .right{
                width: 8%;
                // height: 42px;
                line-height:60px;
                margin-top: 5px;
                // border: 1px green solid;
                display: flex;
                flex-direction: row;
                .icon{
                    font-size: 35px;
                }
                .selectWidth{
                    width: 80px;
                    margin-left: 15px;
                    margin-top: 4px;
                    text-align: center;
                    z-index: 100;
                }
            }
        }
        .bottom{
            // border: 1px pink solid;
            z-index: 99;
        }
        .editor{
            z-index: -1;
            .v-show-content {
                background-color: white;
            }
        }
    }
}
    .modelDiv{
        display: flex; 
        justify-content: flex-start;
        // border: 1px pink solid;
        .spanClass{
            color:red; 
            line-height: 34px; 
            height: 34px; 
            padding-right: 5px;
            margin-left: 10px;
        }
        .modelCon{
            width:80px; 
            padding-top: 5px; 
            font-size:15px;
            color:black;
        }
    }
    
    .span{
        font-size: 15px;
        padding-top: 3px;
        padding-bottom: 3px;
        padding-left: 8px;
        padding-right: 8px;
        // border: 1px red solid;
        margin-right: 10px;
        color: black;
    }
    .span:hover{
        background-color: #f8f8f8;
        cursor: pointer;
        border-radius: 5px;
    }
</style>
