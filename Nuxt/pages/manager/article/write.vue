<template>
    <div class="mavonEditor">
        <div class="top" style="height:6vh;">
            <div class="left">
                <a-input size="large" style="width: 100%;" v-model="form.bamTitle" placeholder="输入文章标题..."/>
            </div>
            <div class="center">
                 <a-button type="primary" size="large" @click="visible = true">发布文章</a-button>
            </div>
            <div class="right">
                <nuxt-link to="/blog/articleMgmt"><a-icon type="home" class="icon"/></nuxt-link> 
            </div>
          
        </div>
        <div class="bottom">
            <no-ssr>
                <mavon-editor
                    ref=form
                    :toolbars="markdownOption" 
                    v-model="form.bafMarkdown" 
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
            <div style=" display: flex; justify-content: flex-start;">
                <div style="width:80px; padding-top: 5px; font-size:15px;color:black;">文章标签:</div>
                <div style="padding-top: 5px;">
                    <template v-for="tag in tags">
                        <a-tag color="blue" :key="tag" :closable="true" :afterClose="() => handleClose(tag)">
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
                </div>
            </div> <br />
            <div style=" display: flex; justify-content: flex-start;">
                <div style="width:80px; padding-top: 5px; font-size:15px;color:black;">图片标题:</div>
                <div style="width:350px">
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
                </div>
            </div>   <br />
            <div style=" display: flex; justify-content: flex-start;">
                <div style="width:80px; padding-top: 5px; font-size:15px;color:black;">个人分类:</div>
                <div style="width:350px">
                    <a-cascader :options="blogCatg" style="width:350px" placeholder="请选择个人分类..." :defaultValue="defaultBlogCatg"  @change="onChangeCatg" />
                </div>  
            </div>   <br />
            <div style=" display: flex; justify-content: flex-start;">
                <div style="width:80px; padding-top: 5px; font-size:15px;color:black;">文章类型:</div>
                <div style="width:350px">
                    <a-select placeholder="请选择..." style="width: 350px" v-model="form.bamType">
                        <a-select-option :value="1">原创</a-select-option>
                        <a-select-option :value="2">转载</a-select-option>
                        <a-select-option :value="3">翻译</a-select-option>
                    </a-select>
                </div>  
            </div>  <br />
            <div style=" display: flex; justify-content: flex-start;">
                <div style="width:80px; padding-top: 5px; font-size:15px;color:black;">发布形式:</div>
                <div style="width:350px; ">
                    <a-radio-group  v-model="form.isPublic" style="padding-top: 5px;">
                        <a-radio :value="1">公开</a-radio>
                        <a-radio :value="0">私密</a-radio>
                    </a-radio-group>
                </div>  
            </div>  <br />
            <div style="height: 30px; text-align: right;">
                <span class="span" @click="handleCancel">取消</span>
                <span class="span" @click="release(1)">保存为草稿</span>
                <span class="span" @click="release(0)" style="color:#3399ea;">发布文章</span>
            </div>

        </a-modal>
    </div>
</template>

<script>
export default {
    layout: 'manager',
    data(){
        return{
            form:{
                bamTitle: '',
                bafMarkdown: '',
                bamType: '',
                isPublic: '',
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
            value: 1,
            blogCatg: [],
            defaultBlogCatg: [],
            // 文章标签
            tags: [],
            inputVisible: false,
            inputValue: '',
            fileList: [],
        }
    },
    created(){
        this.getCatg()
        // 判断是否为编辑
        if (this.$route.query.bamId !== undefined) {
            this.$axios.get("blogArticle/getById?bamId=" + this.$route.query.bamId)
            .then((res) => {  
                this.defaultBlogCatg = res.data.blogCatg
                this.form = res.data
                this.tags = res.data.tags
                if(res.data.imgTitle){
                    this.fileList[0] = {
                        uid: '-1',
                        name: 'xxx.png',
                        status: 'done',
                        url: this.form.bamImgTitle,
                    }
                }   
            })
        }  
    },
    mounted(){
        
    },
    methods:{

        // 上传博客标题图片
        handleChange (info) {
            this.fileList = info.fileList
            if(info.file.status === 'done'){
                this.fileList[0].url = info.file.response.data
                this.form.bamImgTitle = info.file.response.data
            }
        },
        // 绑定@imgAdd 上传图片  👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇
        $imgAdd(pos, $file) {
           // 第一步.将图片上传到服务器.
           var formdata = new FormData();
           formdata.append('ArticleImage', $file);
           formdata.append('bamId', this.form.bamId);
           axios({
            //    url: 'http://127.0.0.1:8185/api/blogArticle/uploadImg',
               url: 'https://www.xdx97.com:8185/api/blogArticle/uploadImg',
               method: 'post',
               data: formdata,
               headers: { 'Content-Type': 'multipart/form-data' },
           }).then((res) => {
               // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
               // $vm.$img2Url 详情见本页末尾
               this.$refs.md.$img2Url(pos, res.data.data);
           })
        },
        save(markdown ,html){
            this.$axios.post("blogArticle/save",this.form)
            .then((res) => { 
                if(this.visible){
                    this.visible = false
                    this.$message.success("发布文章成功")
                }else {
                    this.$message.success("保存文章成功")
                }
                this.form.bamId = res.data
            })
        },
        change(markdown ,html){
            this.form.bafHtml = html
            this.form.bafMarkdown = markdown
        },
        // 发布文章
        release(bamDraft) {
            this.form.bamDraft = bamDraft
            this.form.bamLabel = ''
            for(let i = 0 ; i < this.tags.length; i++){
                this.form.bamLabel += this.tags[i] + ","
            }
            // 发布的时候判断数据是否完整
            if(this.form.bamLabel == ''){
                return this.$message.error("请输入文章标签")
            } else if (this.form.blogCatgId == '' || this.form.blogCatgTwoId == ''){
                 return this.$message.error("请选择个人分类")
            } else if (this.form.bamType == ''){
                 return this.$message.error("请选择文章类型")
            } else if (this.form.isPublic === ''){
                return this.$message.error("请选择发布形式")
            } else if (this.form.bamTitle === ''){
                return this.$message.error("请输入文章标题")
            } else if (this.form.bafMarkdown === ''){
                return this.$message.error("请输入文章内容")
            }
            this.save()
        },
        handleCancel(e) {
            this.visible = false
        },
        onChange(value) {
            // console.log(value);
        },
        //  添加标签的方法 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇
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
        },
        //  获取分类的方法 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇
        getCatg(){
            this.$axios.get("blogCatg/addArticle/dropDown")
            .then((res) => {  this.blogCatg = res.data })
        },
        //  选中分类回调函数 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇
        onChangeCatg(value){
            this.form.blogCatgId = value[0]
            this.form.blogCatgTwoId = value[1]
            this.form.blogCatgThreeId = value[2]          
        }
    }
}
</script>

