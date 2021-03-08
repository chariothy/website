<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" id="search-area">
      <el-form-item label="ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          class="search-short"
        />
      </el-form-item>
      <el-form-item label="中文标题" prop="titleCn">
        <el-input
          v-model="queryParams.titleCn"
          placeholder="请输入中文标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="英文标题" prop="titleEn">
        <el-input
          v-model="queryParams.titleEn"
          placeholder="请输入英文标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="IMDB分数" prop="imdb">
        <el-input
          v-model="queryParams.imdb"
          placeholder="请输入IMDB分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          class="search-short"
        />
      </el-form-item>
      <el-form-item label="豆瓣分数" prop="douban">
        <el-input
          v-model="queryParams.douban"
          placeholder="请输入豆瓣分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          class="search-short"
        />
      </el-form-item>
      <el-form-item label="国家" prop="country">
        <el-input
          v-model="queryParams.country"
          placeholder="请输入国家"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          class="search-short"
        />
      </el-form-item>
      <el-form-item label="类别" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          class="search-short"
        />
      </el-form-item>
      <el-form-item label="上映日期" prop="showDate">
        <el-input
          v-model="queryParams.showDate"
          placeholder="请输入上映日期"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['relax:movie:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['relax:movie:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['relax:movie:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['relax:movie:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="movieList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="20" align="center" />
      <el-table-column label="ID" width="50" align="center" prop="id" />
      <el-table-column label="中文标题" width="220" align="center" prop="titleCn" />
      <el-table-column label="英文标题" width="220" align="center" prop="titleEn" />
      <el-table-column label="内容简介" align="center" prop="desc" />
      <el-table-column label="IMDB" width="60" align="center" prop="imdb" />
      <el-table-column label="豆瓣" width="60" align="center" prop="douban" />
      <el-table-column label="电影海报" width="120" align="center" >
        <template slot-scope="scope">
          <el-link :href="scope.row.href" type="primary" target="_blank"><el-image
            style="width: 100px; height: 100px"
            :src="scope.row.poster"
            fit="contain"></el-image></el-link>
        </template>
      </el-table-column>
      <el-table-column label="国家" width="80" align="center" prop="country" />
      <el-table-column label="类别" width="80" align="center" prop="category" />
      <el-table-column label="上映日期" width="80" align="center" prop="showDate" />
      <el-table-column label="操作" width="50" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['relax:movie:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改最新电影对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMovie, getMovie, delMovie, addMovie, updateMovie, exportMovie } from "@/api/relax/movie";

export default {
  name: "Movie",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 最新电影表格数据
      movieList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        titleCn: null,
        titleEn: null,
        desc: null,
        imdb: null,
        douban: null,
        country: null,
        category: null,
        showDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        titleCn: [
          { required: true, message: "中文标题不能为空", trigger: "blur" }
        ],
        titleEn: [
          { required: true, message: "英文标题不能为空", trigger: "blur" }
        ],
        href: [
          { required: true, message: "电影链接不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询最新电影列表 */
    getList() {
      this.loading = true;
      listMovie(this.queryParams).then(response => {
        this.movieList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        titleCn: null,
        titleEn: null,
        desc: null,
        imdb: null,
        douban: null,
        href: null,
        country: null,
        category: null,
        showDate: null,
        poster: null,
        deletedAt: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加最新电影";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMovie(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改最新电影";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMovie(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMovie(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除最新电影编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMovie(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有最新电影数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMovie(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

<style lang="scss">
  .app-container {
    #search-area {
      .search-short {
        width: 100px;
      }
    }
  }
</style>
