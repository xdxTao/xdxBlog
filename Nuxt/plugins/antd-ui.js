import Vue from 'vue'
// import Antd from 'ant-design-vue/lib'
import { Layout, Menu, Card,message, Tree, Tooltip, FormModel,Form, Empty, InputNumber, 
         Input, Switch, Button,Icon,Table,Modal, Select, Pagination, TreeSelect, Tag, Upload, 
         Cascader, Radio, Collapse,Popconfirm  } from 'ant-design-vue/lib'

Vue.use(Menu)
Vue.use(Layout)
Vue.use(Card)
Vue.use(Tree)
Vue.use(Tooltip)
Vue.use(FormModel)
Vue.use(Form)
Vue.use(Empty)
Vue.use(InputNumber)
Vue.use(Input)
Vue.use(Switch)
Vue.use(Button)
Vue.use(Icon)
Vue.use(Table)
Vue.use(Modal)
Vue.use(Select)
Vue.use(Pagination)
Vue.use(TreeSelect)
Vue.use(Tag)
Vue.use(Upload)
Vue.use(Cascader) 
Vue.use(Radio)
Vue.use(Collapse)
Vue.use(Popconfirm)



Vue.prototype.$message = message;
Vue.prototype.$confirm = Modal.confirm

import zh_CN from 'ant-design-vue/lib/locale-provider/zh_CN';
import moment from 'moment';
import 'moment/locale/zh-cn';
moment.locale('zh-cn');
  export default {
    data() {
      return {
        zh_CN,
      };
    },
  };
