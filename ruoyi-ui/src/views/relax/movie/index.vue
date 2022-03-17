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
          placeholder="IMDB分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          class="search-short"
        />
      </el-form-item>
      <el-form-item label="豆瓣分数" prop="douban">
        <el-input
          v-model="queryParams.douban"
          placeholder="豆瓣分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          class="search-short"
        />
      </el-form-item>
      <el-form-item prop="seen">
        <el-checkbox
          v-model="queryParams.seen"
          :true-label=1 :false-label=0
          label="已看过的" border
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
      <el-table-column label="标题" width="200" align="center">
        <template slot-scope="scope">
          ID={{ scope.row.id }} <br>
          <el-link :href="scope.row.href" type="primary" target="_blank">
            {{ scope.row.titleCn }} <br>
            {{ scope.row.titleEn }}
          </el-link>
        </template>
      </el-table-column>
      <el-table-column label="内容简介" align="center" prop="desc" />
      <el-table-column label="IMDB" width="60" align="center" prop="imdb" />
      <el-table-column label="豆瓣" width="60" align="center" prop="douban" />
      <el-table-column label="电影海报" width="340" align="center" >
        <template slot-scope="scope">
          <viewer :images="scope.row.poster">
            <img v-for="src in scope.row.poster" :key="src" :src="src" width="100%" height="100%">
          </viewer>
        </template>
      </el-table-column>
      <el-table-column label="其它信息" width="200" align="center" prop="magnets" >
        <template slot-scope="scope">
          <div>
            {{ scope.row.country }} <br>
            {{ scope.row.category }} <br>
            {{ scope.row.showDate }} <br>
            <ul>
              <li v-for="magnet in scope.row.magnets" :key="magnet">
                <el-link :href="magnet" type="primary" target="_blank">
                  {{ parseMagnetDesc(magnet) }}
                </el-link>
              </li>
            </ul>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="50" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <div>
            <el-tooltip class="item" effect="dark" content="标志为已看" placement="left">
            <el-switch
              v-model="scope.row.seen"
              active-value="0"
              inactive-value="1"
              @change="handleSeen(scope.row)"
            ></el-switch>
            </el-tooltip>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['relax:movie:remove']"
            >删除</el-button>
          </div>
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
        <el-form-item label="已看" prop="seen">
          <el-checkbox v-model="form.seen"
                       :true-label="1" :false-label="0"
                       label="已看" />
        </el-form-item>
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

import 'viewerjs/dist/viewer.css'
import VueViewer from 'v-viewer'
import Vue from 'vue'
Vue.use(VueViewer)

import { parseMagnetDesc } from "@/utils/henry"

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
        seen: 0
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
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    parseMagnetDesc,
    /** 查询最新电影列表 */
    getList() {
      this.loading = true;
      listMovie(this.queryParams).then(response => {
        this.movieList = response.rows;
        for (let i = 0; i < this.movieList.length; i++) {
          let movie = this.movieList[i]
          if(movie.magnets && movie.magnets.length > 2) {
            this.movieList[i].magnets = JSON.parse(movie.magnets)
          }
          if(movie.poster && movie.poster.length > 2) {
            this.movieList[i].poster = JSON.parse(movie.poster)
          }
        }
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
        seen: null
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
    /** 已看按钮操作 */
    handleSeen(row) {
      this.$confirm('是否确认将"' + row.titleCn + '"标志为已看?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        row.seen = 1
        row.poster = JSON.stringify(row.poster)
        row.magnets = JSON.stringify(row.magnets)
        return updateMovie(row);
      }).then(() => {
        this.getList();
        this.msgSuccess("标志为已看");
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
