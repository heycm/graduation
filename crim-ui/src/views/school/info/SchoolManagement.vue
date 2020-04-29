<template>
  <div class="pageMain">
    <el-row type="flex" :gutter="20" class="marginB-30">
      <el-col :span="12">
        <part title="学院管理" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-input placeholder="输入关键字进行过滤" v-model="filterText" class="marginB-15"></el-input>
          <el-tree
            class="filter-tree"
            :data="tree"
            node-key="id"
            :props="treeDefaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            accordion
            ref="tree"
          >
            <span class="custom-tree-node" slot-scope="{ node, data }">
              <span>{{ node.label }}</span>
              <span>
                <el-button
                  type="text"
                  size="mini"
                  v-if="node.level<3"
                  @click="() => append(node, data)"
                >增加</el-button>
                <el-button
                  type="text"
                  size="mini"
                  v-if="node.level>1"
                  @click="() => remove(node, data)"
                >删除</el-button>
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
          <el-table :data="schoolUserList" border style="width: 100%">
            <el-table-column prop="username" label="账户"></el-table-column>
            <el-table-column label="类型">
              <template slot-scope="scope">{{scope.row.main ? '主账户' : '子账户'}}</template>
            </el-table-column>
            <el-table-column prop="roleName" label="权限"></el-table-column>
            <el-table-column label="操作" width="180">
              <template slot-scope="scope">
                <el-button type="text" size="mini" @click="resetPwd(scope.row)">重置密码</el-button>
                <el-button
                  type="text"
                  size="mini"
                  v-if="!scope.row.main"
                  @click="resetPermissionWarning(scope.row)"
                >重置权限</el-button>
                <el-button
                  type="text"
                  size="mini"
                  v-if="!scope.row.main"
                  @click="delChild(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-col>
    </el-row>

    <part title="代号列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-30">
      <el-table :data="schoolCode" border>
        <el-table-column prop="deptId" label="代号" width="70" show-overflow-tooltip></el-table-column>
        <el-table-column prop="deptName" label="学院" show-overflow-tooltip></el-table-column>
        <el-table-column prop="proId" label="代号" width="70" show-overflow-tooltip></el-table-column>
        <el-table-column prop="proName" label="专业" show-overflow-tooltip></el-table-column>
        <el-table-column prop="yearId" label="代号" width="70" show-overflow-tooltip></el-table-column>
        <el-table-column prop="yearName" label="年级" show-overflow-tooltip></el-table-column>
        <el-table-column prop="classId" label="代号" width="70" show-overflow-tooltip></el-table-column>
        <el-table-column prop="className" label="班级" show-overflow-tooltip></el-table-column>
      </el-table>
    </el-row>

    <!-- 弹窗 -->
    <el-dialog title="添加账户" width="30%" :visible.sync="dialogFormVisible" @close="clearChile">
      <el-form :model="child" label-width="60px">
        <el-form-item label="账户">
          <el-input v-model="child.username" placeholder="请输入账户名" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="child.password" placeholder="请输入密码" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="权限">
          <el-select v-model="child.roleId" placeholder="请分配账户权限">
            <el-option label="主账户权限" :value="1"></el-option>
            <el-option label="子账户权限" :value="4"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addChild">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="重置密码" width="30%" :visible.sync="dialogPassword" @close="clearNewPwd">
      <el-form>
        <el-form-item label="新密码">
          <el-input
            v-model="newPwd"
            show-password
            placeholder="请输入新密码"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPassword = false;">取 消</el-button>
        <el-button type="primary" @click="chooseResetObj">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="重置权限" width="30%" :visible.sync="dialogPermission" @close="clearNewPerm">
      <el-form>
        <el-form-item label="新权限">
          <el-select v-model="newRole" placeholder="请分配账户权限">
            <el-option label="主账户权限" :value="1"></el-option>
            <el-option label="子账户权限" :value="4"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPermission = false">取 消</el-button>
        <el-button type="primary" @click="resetPerm">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      :title="node.level===1?'新增学院':node.level===2?'新增专业':'学院管理'"
      width="30%"
      :visible.sync="dialogNodeVisible"
      @close="clearNode"
    >
      <el-form>
        <el-form-item label="名称">
          <el-input v-model="node.addNodeName" placeholder="请输入名称" autocomplete="off" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogNodeVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNode">确 定</el-button>
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
      tree: [
        {
          id: 0,
          label: "树根",
          children: []
        }
      ],
      treeDefaultProps: {
        children: "children",
        label: "label"
      },
      dialogFormVisible: false,
      dialogPassword: false,
      dialogPermission: false,
      dialogNodeVisible: false,
      child: {
        username: "",
        password: "",
        roleId: ""
      },
      schoolUserList: [
        {
          id: "",
          username: "",
          isMain: false, // 不知道为什么，后端传过来变成了main
          roleName: ""
        }
      ],
      newPwd: "",
      isResetMainPwd: "",
      childUserId: "",
      newRole: "",
      node: {
        campusId: "",
        deptId: "",
        level: "",
        addNodeName: ""
      },
      addNodeUrl: "",
      delNodeUrl: "",
      schoolCode: [
        {
          deptId: "",
          deptName: "",
          proId: "",
          proName: "",
          yearId: "",
          yearName: "",
          classId: "",
          className: ""
        }
      ]
    };
  },
  created() {
    this.init();
  },
  computed: {},
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
  methods: {
    init() {
      this.getSchoolUserList();
      this.getTree();
      this.getCode();
    },
    getSchoolUserList() {
      this.$get("/school/userList")
        .then(res => {
          if (res.data.ok) {
            this.schoolUserList = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(e => {});
    },
    getTree() {
      this.$get("/school/dept")
        .then(res => {
          if (res.data.ok) {
            this.tree = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(e => {});
    },
    getCode() {
      this.$get("/school/code")
        .then(res => {
          if (res.data.ok) {
            this.schoolCode = res.data.data;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(e => {});
    },
    // 增加子账户
    addChild() {
      this.dialogFormVisible = false;
      this.$post("/school/addChild", this.child)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "添加成功",
              type: "success"
            });
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch(e => {});
    },
    // -- 弹窗关闭回调 -----------
    clearNewPwd() {
      this.newPwd = "";
    },
    clearChile() {
      this.child = this.clearObj(this.child);
    },
    clearNewPerm() {
      this.newPerm = "";
    },
    clearNode() {
      this.node = this.clearObj(this.node);
    },
    // -- 重置密码 ------------------
    resetPwd(row) {
      this.dialogPassword = true;
      if (row.main) {
        this.isResetMainPwd = true;
      } else {
        this.isResetMainPwd = false;
        this.childUserId = row.id;
      }
    },
    chooseResetObj() {
      if (this.isResetMainPwd) {
        this.schoolResetPass();
      } else {
        this.schoolChildResetPass();
      }
    },
    // 重置密码
    schoolResetPass() {
      let pwd = this.newPwd;
      this.$confirm("此操作将修改密码, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.dialogPassword = false;
          this.$post("/user/password", this.$qs.stringify({ password: pwd }))
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  message: "修改成功",
                  type: "success"
                });
                this.removeUserInfo();
                this.$router.push("/");
              }
            })
            .catch(e => {});
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    },
    // 重置子账户密码
    schoolChildResetPass() {
      let pwd = this.newPwd;
      this.$confirm("此操作将修改密码, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.dialogPassword = false;
          this.$post(
            "/user/password/" + this.childUserId,
            this.$qs.stringify({ password: this.newPwd })
          )
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  message: "修改成功",
                  type: "success"
                });
              }
            })
            .catch(e => {});
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    },
    // 重置权限
    resetPermissionWarning(row) {
      this.childUserId = row.id;
      this.dialogPermission = true;
    },
    resetPerm() {
      let role = this.newRole;
      this.$confirm("此操作将修改权限, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.dialogPermission = false;
          this.$get("/user/perm/" + this.childUserId + "/" + role)
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  message: "操作成功",
                  type: "success"
                });
                this.getSchoolUserList();
              }
            })
            .catch(e => {});
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    },
    // 删除子账户
    delChild(row) {
      this.$confirm("此操作将永久删除该账户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          this.$delete("/user/" + row.id)
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  message: "删除成功",
                  type: "success"
                });
                this.getSchoolUserList();
              }
            })
            .catch(e => {});
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    // 树过滤
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    append(node, data) {
      this.node.level = node.level;
      this.node.campusId = data.id;
      this.node.deptId = data.id;
      if (node.level === 1) {
        this.addNodeUrl = "/department/add";
      }
      if (node.level === 2) {
        this.addNodeUrl = "/profession/add";
      }
      this.dialogNodeVisible = true;
    },
    remove(node, data) {
      if (node.level === 2) {
        this.delNodeUrl = "/department/" + data.id;
      }
      if (node.level === 3) {
        this.delNodeUrl = "/profession/" + data.id;
      }
      this.$confirm(`此操作将永久删除${data.label}, 是否继续?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          this.delNode();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    addNode() {
      let node = this.copyObj(this.node);
      this.dialogNodeVisible = false;
      this.$post(this.addNodeUrl, node)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "添加成功",
              type: "success"
            });
            this.getTree();
            this.getCode();
          }
        })
        .catch(e => {});
    },
    delNode() {
      this.$delete(this.delNodeUrl)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getTree();
            this.getCode();
          }
        })
        .catch(e => {});
    }
  }
};
</script>