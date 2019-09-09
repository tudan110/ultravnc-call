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
          key: "name"
        },
        {
          title: "状态",
          key: "state",
          render: (h, params) => {
            return h(
              "span",
              {
                style: {
                  color: "green",
                  fontSize: "bold"
                }
              },
              "正常"
            );
          }
        },
        {
          title: "IP",
          key: "ip"
        },
        {
          title: "UltraVNC Server 监听端口",
          key: "serverPort"
        },
        {
          title: "UltraVNC Viewer 监听端口",
          key: "viewerPort"
        }
      ],
      data1: [
        {
          name: "中继服务器",
          state: "正常",
          ip: "10.20.16.48",
          serverPort: "5500",
          viewerPort: "5901"
        }
      ]
    };
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
