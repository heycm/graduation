<template>
  <div>
    <div class="pageMain marginB-15">
      <part title="宣讲会" type="main" />
      <el-row type="flex" class="paddingLR-15 marginB-30">
        <el-table :data="careerTalkList" tooltip-effect="dark">
          <el-table-column prop="companyName" label="企业名称" width="200" show-overflow-tooltip></el-table-column>
          <el-table-column prop="careerTalk" label="宣讲会" width="200" show-overflow-tooltip></el-table-column>
          <el-table-column prop="time" label="时间" width="160"></el-table-column>
          <el-table-column prop="place" label="地点" width="150" show-overflow-tooltip></el-table-column>
          <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
          <el-table-column label="操作" width="120">
            <template slot-scope="scope">
              <el-button type="text" size="mini" @click="look(scope.row)">查看</el-button>
              <el-button type="text" size="mini" @click="cancelJoin(scope.row)">取消参加</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </div>
    <div class="pageMain marginB-15">
      <part title="面试" type="main" />
      <el-row class="paddingLR-15 marginB-10">
        <!-- <el-form v-model="form" :inline="true" class="demo-form-inline">
          <el-form-item label="名称">
            <el-input v-model="form.companyName" placeholder="例如：阿里巴巴" clearable></el-input>
          </el-form-item>
          <el-form-item label="热招职位">
            <el-input v-model="form.hotJob" placeholder="例如：UI设计师" clearable></el-input>
          </el-form-item>
          <el-form-item label="关注">
            <el-select v-model="form.isStart">
              <el-option label="不限" value></el-option>
              <el-option label="已关注" :value="1"></el-option>
              <el-option label="未关注" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
        </el-form>-->
      </el-row>
      <el-row type="flex" class="paddingLR-15 marginB-10">
        <el-table :data="interViewList" tooltip-effect="dark">
          <el-table-column prop="companyName" label="企业名称" width="200" show-overflow-tooltip></el-table-column>
          <el-table-column prop="jobName" label="职位" width="150" show-overflow-tooltip></el-table-column>
          <el-table-column label="转正薪资" width="150">
            <template
              slot-scope="scope"
            >{{scope.row.minRegularPay +' - '+ scope.row.maxRegularPay +'K'}}</template>
          </el-table-column>
          <el-table-column prop="welfare" label="福利待遇" show-overflow-tooltip></el-table-column>
          <el-table-column prop="workPlace" label="工作地点" width="100" show-overflow-tooltip></el-table-column>
          <el-table-column prop="officeStatus" label="Office进度" width="150" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-select v-model="scope.row.officeStatus" size="mini">
                <el-option label="待回复" :value="0"></el-option>
                <el-option label="已收到" :value="1"></el-option>
                <el-option label="被拒绝" :value="2"></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="最终去向" width="80">
            <template slot-scope="scope">
              <!-- <el-switch
                v-model="scope.row.isFinalChioce"
                active-color="#13ce66"
                inactive-color="#C0C4CC"
                :active-value="1"
                :inactive-value="0"
                @click="finalChioceChange(scope.row)"
              ></el-switch>-->
              <i
                :class="[scope.row.isFinalChioce===1?'el-icon-star-on':'el-icon-star-off']"
                @click="finalChioceChange(scope.row)"
                :style="{fontSize:(scope.row.isFinalChioce===1?35:25)+'px', cursor:'pointer', color:'#E6A23C'}"
              ></i>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      careerTalkList: [
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        },
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        },
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        },
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        }
      ],
      form: {
        companyName: "",
        hotJob: "",
        isStart: ""
      },
      interViewList: [
        {
          id: 1,
          companyName: "alibaba",
          jobName: "数据库运营",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，965，弹性工作，零食下午茶",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 0
        },
        {
          id: 2,
          companyName: "字节跳动",
          jobName: "Java开发",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，美女多",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 1
        },
        {
          id: 3,
          companyName: "美团",
          jobName: "Java开发",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，美女多",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 0
        },
        {
          id: 4,
          companyName: "饿了么",
          jobName: "Java开发",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，美女多",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 0
        }
      ]
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    // 查看
    look(row) {
      console.log(row);
    },
    // 取消参加
    cancelJoin(row) {
      console.log(row);
    },
    // 改变最终去向
    finalChioceChange(row) {
      console.log(row);
      this.$confirm("您确定要改变最终去向吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "修改成功!"
          });
        //   do re select
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    }
  }
};
</script>