<template>
  <div class="test">
    <!-- 按钮 -->
    <div type="flex" class="right" justify="end">
      <Row type="flex" justify="end" class="code-row-bg">
        <Button type="info" @click="listSessions()" style="margin-right: 5px;">查询</Button>
        <Button type="error" @click="releaseAllConfirm()" style="margin-right: -15px;">全部释放</Button>
      </Row>
    </div>

    <!-- 列表 -->
    <div>
      <Table border :loading="listLoading" :columns="columns" :data="dataList">
        <template slot-scope="{row}" slot="action">
          <Button type="error" size="small" @click="releaseConfirm(row)">释放</Button>
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

    <!-- 释放提示 -->
    <div>
      <Modal v-model="isDeleteShow" width="260">
        <p slot="header" style="color:#f60;text-align:center">
          <Icon type="ios-information-circle"></Icon>
          <span>释放确认</span>
        </p>
        <div style="text-align:center">
          <p>此操作将会释放当前会话 ID。</p>
          <p>是否继续释放？</p>
        </div>
        <div slot="footer">
          <Button type="error" size="large" long :loading="deleteloading" @click="releaseSession">释放</Button>
        </div>
      </Modal>
    </div>

    <!-- 释放提示 -->
    <div>
      <Modal v-model="isDeleteAllShow" width="260">
        <p slot="header" style="color:#f60;text-align:center">
          <Icon type="ios-information-circle"></Icon>
          <span>释放确认</span>
        </p>
        <div style="text-align:center">
          <p>此操作将会释放所有会话 ID。</p>
          <p>是否继续释放？</p>
        </div>
        <div slot="footer">
          <Button
            type="error"
            size="large"
            long
            :loading="deleteloading"
            @click="releaseAllSessions"
          >释放</Button>
        </div>
      </Modal>
    </div>
  </div>
</template>

<script>
import session from "../api/session";
import id from "../api/id";
import config from "../config/config";

export default {
  data() {
    return {
      isDeleteShow: false,
      isDeleteAllShow: false,
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
          align: "center",
          key: "id"
        },
        {
          title: "描述",
          align: "center",
          key: "description"
        },
        {
          title: "状态",
          align: "center",
          key: "state",
          render: (h, params) => {
            return h("span", this.getState(params));
          }
        },
        {
          title: "控制端IP",
          align: "center",
          key: "controlEndIP"
        },
        {
          title: "控制端用户",
          align: "center",
          key: "controlEndUser"
        },
        {
          title: "被控制端IP",
          align: "center",
          key: "controlledEndIP"
        },
        {
          title: "被控制端用户",
          align: "center",
          key: "controlledEndUser"
        },
        {
          title: "连接时间",
          align: "center",
          key: "connectTime"
        },
        {
          title: "操作",
          align: "center",
          slot: "action",
          width: 150
        }
      ],
      pageNum: 1,
      pageSize: config.pageSize,
      total: 0,
      dataList: []
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
    getState(params) {
      return this.isNotBlank(params.row.controlledEndIP)
        ? this.isNotBlank(params.row.controlEndIP)
          ? "会话中"
          : "待连接"
        : this.isNotBlank(params.row.controlEndIP)
        ? "无被控制端"
        : "无效会话";
    },
    handlePageNum(pageNum) {
      this.pageNum = pageNum;
      this.listSessions(this.pageNum);
    },
    handlePageSize(pageSize) {
      this.pageSize = pageSize;
      this.listSessions();
    },
    listSessions(pageNum) {
      this.listLoading = true;
      this.pageNum = this.isNotBlank(pageNum) ? pageNum : 1;
      session
        .listSessions({
          pageNum: this.pageNum,
          pageSize: this.pageSize
        })
        .then(res => {
          this.dataList = res.data.data;
          this.total = res.data.total;
          this.listLoading = false;
        });
    },
    releaseConfirm(item) {
      this.deleteItem = item;
      this.isDeleteShow = true;
    },
    releaseSession() {
      this.deleteloading = true;
      id.releaseSessionId(this.deleteItem.id).then(res => {
        this.listSessions(this.pageNum);
        this.deleteloading = false;
        this.isDeleteShow = false;
        this.$Message.success("释放成功。");
      });
    },
    releaseAllConfirm(item) {
      this.isDeleteAllShow = true;
    },
    releaseAllSessions() {
      this.deleteloading = true;
      session.releaseAllSessions().then(res => {
        this.listSessions(this.pageNum);
        this.deleteloading = false;
        this.isDeleteAllShow = false;
        this.$Message.success("释放成功。");
      });
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
    this.listSessions();
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