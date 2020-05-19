<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form v-model="pageReq.data" :inline="true" class="demo-form-inline">
        <el-form-item label="信用代码">
          <el-input v-model="pageReq.data.creditCode" placeholder="请输入社会统一信用代码" clearable></el-input>
        </el-form-item>
        <el-form-item label="企业名称">
          <el-input v-model="pageReq.data.companyName" placeholder="请输入企业名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getWaitAuditPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="企业列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="waitAuditPage"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="type" label="申请类型" width="80">
          <template
            slot-scope="scope"
          >{{scope.row.auditType===0?'注册申请':scope.row.auditType===1?'入会申请':''}}</template>
        </el-table-column>
        <el-table-column prop="applyTime" label="申请时间" width="160" show-overflow-tooltip></el-table-column>
        <el-table-column prop="companyName" label="企业名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="creditCode" label="社会统一信用代码" width="200" show-overflow-tooltip></el-table-column>
        <el-table-column label="附件">
          <template slot-scope="scope">
            <el-button type="text" v-if="scope.row.logoUrl" @click="lookImg('企业LOGO' ,scope.row.logoUrl)">Logo</el-button>
            <el-button
              type="text"
              v-for="item in scope.row.license"
              :key="item.id"
              @click="lookImg('营业执照' ,item.fileUrl)"
            >营业执照</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="linkMan" label="联系人" width="80" show-overflow-tooltip></el-table-column>
        <el-table-column prop="linkPhone" label="电话" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button type="text">查看</el-button>
            <el-button type="text" @click="openAuditDialogVisible(true, scope.row)">通过</el-button>
            <el-button type="text" @click="openAuditDialogVisible(false, scope.row)">拒绝</el-button>
            <el-button type="text">禁用</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row class="paddingLR-15 marginB-30">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-sizes="[5, 10, 20]"
        :page-size="pagination.pageSize"
        layout="sizes, prev, pager, next"
        :total="pagination.total"
        class="text-right"
      ></el-pagination>
    </el-row>

    <!-- 弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="50%">
      <el-image :src="src"></el-image>
    </el-dialog>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="auditDialogVisible"
      width="50%"
      @closed="auditFeedBack=''"
    >
      <el-input v-model="auditFeedBack" placeholder="请输入反馈信息"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="auditDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAudit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      pageReq: {
        page: 1,
        rows: 5,
        data: {
          companyName: "",
          creditCode: ""
        }
      },
      waitAuditPage: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      multipleSelection: [],
      dialogVisible: false,
      dialogTitle: "查看",
      src: "",
      auditDialogVisible: false,
      isPass: false,
      auditRow: "",
      auditFeedBack: ""
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.getWaitAuditPage();
    },
    getWaitAuditPage() {
      this.$post("/auditRecord/wait", this.pageReq)
        .then(res => {
          if (res.data.ok) {
            this.waitAuditPage = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    selectChange(value) {
      console.log(value);
    },
    handleSizeChange(val) {
      this.pageReq.rows = val;
      this.getWaitAuditPage();
    },
    handleCurrentChange(val) {
      this.pageReq.page = val;
      this.getWaitAuditPage();
    },
    lookImg(title, url) {
      this.dialogTitle = title;
      this.src = url;
      this.dialogVisible = true;
    },
    openAuditDialogVisible(type, row) {
      this.dialogTitle = "审核反馈";
      this.isPass = type;
      this.auditRow = row;
      this.auditDialogVisible = true;
    },
    handleAudit() {
      const back = this.auditFeedBack;
      this.auditDialogVisible = false;
      const auditType = this.auditRow.auditType;
      const id = this.auditRow.id;
      if (this.isPass) {
        if (auditType === 0) {
          this.$post(
            "/auditRecord/register/pass/" + id,
            this.$qs.stringify({ auditFeedBack: back })
          )
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  type: "success",
                  message: "操作成功"
                });
                this.getWaitAuditPage();
              }
            })
            .catch(e => {});
        }
        if (auditType === 1) {
          this.$post(
            "/auditRecord/joinJobFair/pass/" + id,
            this.$qs.stringify({ auditFeedBack: back })
          )
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  type: "success",
                  message: "操作成功"
                });
                this.getWaitAuditPage();
              }
            })
            .catch(e => {});
        }
      } else {
        if (auditType === 0) {
          this.$post(
            "/auditRecord/register/deny/" + id,
            this.$qs.stringify({ auditFeedBack: back })
          )
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  type: "success",
                  message: "操作成功"
                });
                this.getWaitAuditPage();
              }
            })
            .catch(e => {});
        }
        if (auditType === 1) {
          this.$post(
            "/auditRecord/joinJobFair/deny/" + id,
            this.$qs.stringify({ auditFeedBack: back })
          )
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  type: "success",
                  message: "操作成功"
                });
                this.getWaitAuditPage();
              }
            })
            .catch(e => {});
        }
      }
    }
  }
};
</script>