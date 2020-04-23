<template>
  <div
    id="part"
    :class="['comm', type==='info'?'info':type==='main'?'main':type==='warning'?'warning':type==='danger'?'danger':type==='success'?'success':'info']"
  >
    <h4>{{title}}</h4>
  </div>
</template>

<script>
export default {
  name: "Part",
  props: {
    title: String,
    type: String
  },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  created() {
    undefined;
  },
  methods: {}
};
</script>
<style scoped>
.comm {
  font-size: 16px;
  position: relative;
  padding-left: 15px;
  margin-bottom: 15px;
}
.comm::before {
  content: "";
  width: 5px;
  height: 100%;
  position: absolute;
  left: 0;
}
.info {
  color: #909399;
}
.info::before {
  background: #909399;
}
.main {
  color: #409eff;
}
.main::before {
  background: #409eff;
}
.warning {
  color: #e6a23c;
}
.warning::before {
  background: #e6a23c;
}
.danger {
  color: #f56c6c;
}
.danger::before {
  background: #f56c6c;
}
.success {
  color: #67c23a;
}
.success::before {
  background: #67c23a;
}
</style>