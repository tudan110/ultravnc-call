<template>
  <div class="test">
    <!-- 按钮 -->
    <div type="flex" class="right" justify="end">
      <Row type="flex" justify="end" class="code-row-bg">
        <Button type="info" @click="listUsers()" style="margin-right: 5px;">查询</Button>
        <Button type="success" @click="AddBtnShow()">新增</Button>
      </Row>
    </div>

    <!-- 列表 -->
    <div>
      <Table border :loading="listLoading" :columns="columns" :data="dataList">
        <template slot-scope="{row}" slot="action">
          <Button type="primary" size="small" style="margin-right: 5px" @click="EditBtnShow(row)">修改</Button>
          <Button type="error" size="small" @click="deleteConfirm(row)">删除</Button>
        </template>
      </Table>
      <Page
        :current="pageNum"
        show-sizer
        :page-size="pageSize"
        :page-size-opts="[5, 10, 20, 50]"
        :total="total"
        @on-page-size-change="handlePageSize"
        @on-change="handlePageNum"
        style="margin: 20px 0 0 0;"
      />
    </div>

    <!-- 增加用户 -->
    <div>
      <Modal v-model="isAddShow" draggable footer-hide scrollable title="增加用户">
        <Form ref="addFormList" :model="addFormList" :rules="ruleValidate" :label-width="80">
          <FormItem label="编号" prop="id" style="display: none;">
            <Input v-model="addFormList.id" placeholder="请输入你的编号" number />
          </FormItem>
          <FormItem label="姓名" prop="name">
            <Input v-model="addFormList.name" placeholder="请输入你的姓名" />
          </FormItem>
          <FormItem label="邮箱" prop="email">
            <Input v-model="addFormList.email" placeholder="请输入你的邮箱" />
          </FormItem>
          <FormItem>
            <Button type="primary" @click="HandleSubmit('addFormList')">提交</Button>
            <Button @click="HandleReset('addFormList')" style="margin-left: 8px">重置</Button>
          </FormItem>
        </Form>
      </Modal>
    </div>

    <!-- 修改用户 -->
    <div>
      <Modal v-model="isEditShow" draggable footer-hide scrollable title="修改用户">
        <Form ref="editFormList" :model="editFormList" :rules="ruleValidate" :label-width="80">
          <FormItem label="编号" prop="id" style="display: none;">
            <Input disabled v-model="editFormList.id" placeholder="请输入你的编号" />
          </FormItem>
          <FormItem label="姓名" prop="name">
            <Input v-model="editFormList.name" placeholder="请输入你的姓名" />
          </FormItem>
          <FormItem label="邮箱" prop="email">
            <Input v-model="editFormList.email" placeholder="请输入你的邮箱" />
          </FormItem>
          <FormItem>
            <Button type="primary" @click="HandleSubmit('editFormList')">提交</Button>
            <Button @click="HandleReset('editFormList')" style="margin-left: 8px">重置</Button>
          </FormItem>
        </Form>
      </Modal>
    </div>

    <!-- 删除提示 -->
    <div>
      <Modal v-model="isDeleteShow" width="260">
        <p slot="header" style="color:#f60;text-align:center">
          <Icon type="ios-information-circle"></Icon>
          <span>删除确认</span>
        </p>
        <div style="text-align:center">
          <p>此操作将会删除数据库记录。</p>
          <p>是否继续删除？</p>
        </div>
        <div slot="footer">
          <Button type="error" size="large" long :loading="deleteloading" @click="deleteUser">删除</Button>
        </div>
      </Modal>
    </div>
  </div>
</template>

<script>
import user from "../api/user";
import config from "../config/config";
export default {
  data() {
    return {
      isAddShow: false,
      isEditShow: false,
      isDeleteShow: false,
      deleteloading: false,
      deleteItem: {},
      listLoading: false,
      columns: [
        {
          // type: "index",
          width: 65,
          title: "序号",
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              params.index + 1 + (this.pageNum - 1) * this.pageSize
            );
          }
        },
        {
          title: "编号",
          key: "id"
        },
        {
          title: "名称",
          key: "name"
        },
        {
          title: "邮箱",
          key: "email"
        },
        {
          title: "创建人",
          key: "createUser"
        },
        {
          title: "创建时间",
          key: "createTime"
        },
        {
          title: "修改人",
          key: "updateUser"
        },
        {
          title: "修改时间",
          key: "updateTime"
        },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center"
        }
      ],
      pageNum: 1,
      pageSize: config.pageSize,
      total: 0,
      dataList: [],
      addFormList: {
        id: "",
        name: "",
        email: ""
      },
      editFormList: {
        id: "",
        name: "",
        email: ""
      },
      ruleValidate: {
        // id: [{
        //   required: true,
        //   type: "number",
        //   message: "编号只能为数字！",
        //   trigger: "blur"
        // }],
        name: [
          {
            required: true,
            message: "姓名不能为空！",
            trigger: "blur"
          }
        ],
        email: [
          {
            required: true,
            message: "邮箱不能为空！",
            trigger: "blur"
          },
          {
            type: "email",
            message: "不合法的邮箱！",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    isBlank(object) {
      return (
        object === null ||
        object === "null" ||
        object === undefined ||
        object === "undefined" ||
        object === ""
      );
    },
    isNotBlank(object) {
      return !this.isBlank(object);
    },
    handlePageNum(pageNum) {
      this.pageNum = pageNum;
      this.listUsers(this.pageNum);
    },
    handlePageSize(pageSize) {
      this.pageSize = pageSize;
      this.listUsers();
    },
    listUsers(pageNum) {
      this.listLoading = true;
      this.pageNum = this.isNotBlank(pageNum) ? pageNum : 1;
      user
        .listUsers({
          pageNum: this.pageNum,
          pageSize: this.pageSize
        })
        .then(res => {
          // this.dataList = res.data;
          // 使用 pagehelper 插件
          this.dataList = res.data.list;
          this.total = res.data.total;
          this.listLoading = false;
        });
    },
    addUser() {
      let flag = this.FindUser();
      if (flag) {
        this.$Message.error("编号已经存在！");
        return;
      }
      user.addUser(this.addFormList).then(res => {
        this.AddBtnNotShow();
        this.listUsers(this.total / this.pageSize + 1);
        this.$Message.success("增加成功！");
      });
    },
    updateUser() {
      user.updateUser(this.editFormList).then(res => {
        this.EditBtnNotShow();
        this.listUsers(this.pageNum);
        this.$Message.success("修改成功！");
      });
    },
    deleteConfirm(item) {
      this.deleteItem = item;
      this.isDeleteShow = true;
    },
    deleteUser() {
      this.deleteloading = true;
      user.deleteUser(this.deleteItem.id).then(res => {
        this.listUsers(this.pageNum);
        this.deleteloading = false;
        this.isDeleteShow = false;
        this.$Message.success("删除成功。");
      });
    },
    HandleSubmit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          if (this.isAddShow) {
            this.addUser();
          } else if (this.isEditShow) {
            this.updateUser();
          }
        } else {
          this.$Message.error("填写信息错误!");
        }
      });
    },
    AddBtnShow() {
      this.HandleReset("addFormList");
      this.isAddShow = true;
    },
    AddBtnNotShow() {
      this.isAddShow = false;
    },
    EditBtnShow(item) {
      this.isEditShow = true;
      this.editFormList.id = item.id;
      this.editFormList.name = item.name;
      this.editFormList.email = item.email;
    },
    EditBtnNotShow() {
      this.isEditShow = false;
    },
    HandleReset(name) {
      this.$refs[name].resetFields();
    },
    FindUser() {
      let flag = false;
      this.dataList.forEach(item => {
        if (item.id == this.addFormList.id) flag = true;
      });
      return flag;
    }
  },
  mounted() {
    this.listUsers();
  }
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.test {
  margin: 20px;
}

.right {
  margin: 15px;
}
</style>