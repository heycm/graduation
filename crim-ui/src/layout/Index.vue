<template>
  <div class="crim-flex layout-container">
    <div class="layout-aside">
      <Aside :menuSwitch="isCollapse" />
    </div>
    <div class="crim-flex-1 crim-flex crim-flex-col">
      <div class="layout-header">
        <Header @menuSwitch="menuSwitchToAside" />
      </div>
      <el-scrollbar class="layout-main-bg">
        <div class="crim-flex-1 layout-main">
          <router-view />
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import Aside from "./Aside";
import Header from "./Header";
export default {
  components: {
    Header: Header,
    Aside: Aside
  },
  data() {
    return {
      isCollapse: false
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    menuSwitchToAside(data) {
      this.isCollapse = data;
    }
  }
};
</script>