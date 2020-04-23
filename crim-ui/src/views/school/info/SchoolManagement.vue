<template>
  <div class="pageMain">
    <el-row type="flex" :gutter="20">
      <el-col :span="12">
        <part title="学院管理" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-input placeholder="输入关键字进行过滤" v-model="filterText" class="marginB-15"></el-input>
          <el-tree
            class="filter-tree"
            :data="data"
            node-key="id"
            :props="defaultProps"
            default-expand-all
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
          >
            <span class="custom-tree-node" slot-scope="{ node, data }">
              <span>{{ node.label }}</span>
              <span>
                <el-button type="text" size="mini" @click="() => append(data)">增加</el-button>
                <el-button type="text" size="mini" @click="() => remove(node, data)">删除</el-button>
              </span>
            </span>
          </el-tree>
        </el-row>
      </el-col>
      <el-col :span="12">
        <part title="账户设置" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-button @click="dialogFormVisible = true">添加账户</el-button>
        </el-row>
        <el-row class="paddingLR-15 marginB-10">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="date" label="账户"></el-table-column>
            <el-table-column prop="date" label="权限"></el-table-column>
            <el-table-column label="操作" width="180">
              <template>
                <el-button type="text" size="mini" @click="dialogPassword = true">重置密码</el-button>
                <el-button type="text" size="mini" @click="dialogPermission = true">重置权限</el-button>
                <el-button type="text" size="mini" @click="delAccountWarning">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-col>
    </el-row>

    <!-- 弹窗 -->
    <el-dialog title="添加账户" width="30%" :visible.sync="dialogFormVisible">
      <el-form :model="form" label-width="60px">
        <el-form-item label="账户">
          <el-input v-model="form.name" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.name" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="权限">
          <el-select v-model="form.region" placeholder="请分配账户权限">
            <el-option label="主账户权限" value="school-all"></el-option>
            <el-option label="子账户权限" value="school-child"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="重置密码" width="30%" :visible.sync="dialogPassword">
      <el-form :model="form" label-width="60px">
        <el-form-item label="新密码">
          <el-input v-model="form.name" autocomplete="off" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPassword = false">取 消</el-button>
        <el-button type="primary" @click="resetPasswordWarning">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="重置权限" width="30%" :visible.sync="dialogPermission">
      <el-form :model="form" label-width="60px">
        <el-form-item label="新权限">
          <el-select v-model="form.region" placeholder="请分配账户权限">
            <el-option label="主账户权限" value="school-all"></el-option>
            <el-option label="子账户权限" value="school-child"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPermission = false">取 消</el-button>
        <el-button type="primary" @click="resetPermissionWarning">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      filterText: "",
      data: [
        {
          id: 1,
          label: "一级 1",
          children: [
            {
              id: 4,
              label: "二级 1-1",
              children: [
                {
                  id: 9,
                  label: "三级 1-1-1",
                  children: [
                    {
                      id: 20,
                      label: "仰天大笑出门去"
                    },
                    {
                      id: 21,
                      label: "我辈岂是蓬蒿人"
                    }
                  ]
                },
                {
                  id: 10,
                  label: "三级 1-1-2"
                }
              ]
            }
          ]
        },
        {
          id: 2,
          label: "一级 2",
          children: [
            {
              id: 5,
              label: "二级 2-1"
            },
            {
              id: 6,
              label: "二级 2-2"
            }
          ]
        },
        {
          id: 3,
          label: "一级 3",
          children: [
            {
              id: 7,
              label: "二级 3-1"
            },
            {
              id: 8,
              label: "二级 3-2"
            }
          ]
        }
      ],
      defaultProps: {
        children: "children",
        label: "label"
      },
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ],
      form: {
        user: "",
        region: ""
      },
      dialogFormVisible: false,
      dialogPassword: false,
      dialogPermission: false
    };
  },
  created() {},
  computed: {},
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    append(data) {
      console.log(data);
    },
    remove(node, data) {
      this.$confirm(`您正在删除${data.label}, 是否继续?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "删除成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    onSubmit() {
      console.log("submit!");
    },
    resetPasswordWarning() {
      this.$confirm("此操作将修改密码, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.dialogPassword = false
          this.$message({
            type: "success",
            message: "修改成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    },
    resetPermissionWarning() {
      this.$confirm("此操作将修改权限, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.dialogPermission = false
          this.$message({
            type: "success",
            message: "修改成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    },
    delAccountWarning() {
      this.$confirm("此操作将删除账户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.dialogPassword = false
          this.$message({
            type: "success",
            message: "删除成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  }
};
</script>