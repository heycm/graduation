<template>
  <div class="pageMain">
    <part title="Excel导入" type="main" />
    <el-row class="add_import_excel">
      <el-upload
        class="upload-demo inline-block"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :on-error="handleAvatarError"
        :before-upload="beforeAvatarUpload"
        :headers="headers"
      >
        <el-button type="primary">点击导入</el-button>
      </el-upload>
      <el-button type="info" plain class="marginL-10">下载模板</el-button>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <part title="新增楼号" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="校区">
              <el-select v-model="form.campusId" placeholder="请选择校区">
                <el-option
                  :label="item.campusName"
                  :value="item.id"
                  v-for="item in campusList"
                  :key="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="名称">
              <el-input v-model="form.buildingName"></el-input>
            </el-form-item>
            <el-form-item label="详细说明">
              <el-input type="textarea" v-model="form.buildingDesc"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit('building')">创建</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </el-row>
      </el-col>
      <el-col :span="12">
        <part title="新增场地" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="楼号">
              <el-select v-model="form.buildingId" placeholder="请选择楼号">
                <el-option
                  :label="item.buildingName"
                  :value="item.id"
                  v-for="item in buildingList"
                  :key="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="名称">
              <el-input v-model="form.siteName"></el-input>
            </el-form-item>
            <el-form-item label="详细说明">
              <el-input type="textarea" v-model="form.siteDesc"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit('site')">创建</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      form: {
        campusId: "",
        buildingName: "",
        buildingDesc: "",
        buildingId: "",
        siteName: "",
        siteDesc: ""
      },
      campusList: [
        {
          id: 1,
          campusName: "数学与信息科技学院"
        }
      ],
      buildingList: [
        {
          id: 1,
          buildingName: "逸夫楼"
        }
      ]
    };
  },
  computed: {
    headers() {
      return {
        Authorization: "token"
      };
    }
  },
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.geCampusList();
      this.getBuildingList();
    },
    geCampusList() {
      this.$get("/type/campus/list")
        .then(res => {
          if (res.data.ok) {
            this.campusList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getBuildingList() {
      this.$get("/building/list")
        .then(res => {
          if (res.data.ok) {
            this.buildingList = res.data.data;
          }
        })
        .catch(e => {});
    },
    onSubmit(url) {
      this.$post(`/${url}/save`, this.form)
        .then(res => {
          if(res.data.ok){
            this.$message({
              message: "添加成功",
              type: "success"
            });
            this.init();
            this.form = this.clearObj(this.form)
          }
        })
        .catch(e => {});
    },
    handleAvatarSuccess(res, file) {},
    handleAvatarError(err, file) {},
    beforeAvatarUpload(file) {
      console.log(file);
      let arr = file.name.split(".");
      let fileType = arr[arr.length - 1];
      let allowType = ["xls", "xlsx"];
      const isXls = allowType.indexOf(fileType) > -1;
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isXls) {
        this.$message.error("只能上传xls/xlsx文件");
        return;
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过2MB!");
        return;
      }
    }
  }
};
</script>