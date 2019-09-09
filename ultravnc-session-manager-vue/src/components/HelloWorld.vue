<template>
  <div class="outline">
    <h1>{{ msg }}</h1>
    <br />
    <h2>{{ msgStr }}</h2>
    <br />
    <Table :columns="columns1" :data="data1"></Table>
    <br />
    <p>
      在这里查看当前会话状态
      <a href="/usm/session" target="_blank" rel="noopener">会话管理</a>
    </p>
  </div>
</template>

<script>
import outline from "../api/outline";

export default {
  name: "HelloWorld",
  props: {
    msg: String
  },
  data() {
    return {
      msgStr: "概要信息",
      columns1: [
        {
          title: "名称",
          align: "center",
          key: "name"
        },
        {
          title: "状态",
          align: "center",
          key: "state",
          render: (h, params) => {
            return h(
              "span",
              {
                style: {
                  color: params.row.state ? "green" : "red",
                  fontSize: "bold"
                }
              },
              params.row.state ? "正常" : "异常"
            );
          }
        },
        {
          title: "IP",
          align: "center",
          key: "ip"
        },
        {
          title: "UltraVNC Server 监听端口",
          align: "center",
          key: "serverPort"
        },
        {
          title: "UltraVNC Viewer 监听端口",
          align: "center",
          key: "viewerPort"
        }
      ],
      data1: [
        {
          name: "",
          state: "",
          ip: "",
          serverPort: "",
          viewerPort: ""
        }
      ]
    };
  },
  created() {
    this.getOutlineInfo();
  },
  methods: {
    getOutlineInfo() {
      outline.getOutlineInfo().then(res => {
        this.data1[0].name = res.data.name;
        this.data1[0].state = res.data.state;
        this.data1[0].ip = res.data.ip;
        this.data1[0].serverPort = res.data.serverPort;
        this.data1[0].viewerPort = res.data.viewerPort;
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.outline {
  margin: 20px 300px;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
