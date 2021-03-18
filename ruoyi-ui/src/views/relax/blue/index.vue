<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否有码" prop="has_mosaic">
        <el-input
          v-model="queryParams.hasMosaic"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
      <el-form-item label="">
        <el-checkbox v-model="showPic">显示图片</el-checkbox>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['relax:blue:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['relax:blue:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['relax:blue:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['relax:blue:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="blueList" @selection-change="handleSelectionChange" :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" width="80" align="center" prop="id" >
        <template slot-scope="scope">
          <a @click="handleTorrentFile(scope.row.id)">{{scope.row.id}}</a>
        </template>
      </el-table-column>
      <el-table-column label="标题" width="200" align="center" prop="title" />
      <el-table-column label="链接" width="100" align="center" prop="href" />
      <el-table-column label="图片列表" align="center" prop="pics" >
        <template slot-scope="scope">
          <ul v-show="showPic">
            <div v-for="pic in scope.row.pics" :key="pic">
              <el-image
                :src="pic"
                fit="none"></el-image>
            </div>
          </ul>
        </template>
      </el-table-column>
      <el-table-column label="磁力" width="80" align="center" prop="torrent" >
        <template slot-scope="scope">
          <el-link :href="scope.row.torrent" type="primary" target="_blank">torrent</el-link>
        </template>
      </el-table-column>
      <el-table-column label="码" width="35" align="center" prop="hasMosaic" />
      <el-table-column label="操作" width="50" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['relax:blue:remove']"
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

    <!-- 添加或修改成人电影对话框 -->
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
import { listBlue, getBlue, delBlue, addBlue, updateBlue, exportBlue } from "@/api/relax/blue";
import axios from 'axios'
import { getToken } from '@/utils/auth'
import { resolveBlob } from '@/utils/zipdownload'

export default {
  name: "Blue",
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
      // 成人电影表格数据
      blueList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        hasMosaic: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
      },
      showPic: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询成人电影列表 */
    getList() {
      this.loading = true;
      listBlue(this.queryParams).then(response => {
        this.blueList = response.rows;
        for (let i = 0; i < this.blueList.length; i++) {
          let blue = this.blueList[i]
          if(blue.pics && blue.pics.length > 2) {
            this.blueList[i].pics = JSON.parse(blue.pics)
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
        title: null,
        href: null,
        pics: null,
        torrent: null,
        hasMosaic: [],
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
      this.title = "添加成人电影";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBlue(id).then(response => {
        this.form = response.data;
        this.form.hasMosaic = this.form.hasMosaic.split(",");
        this.open = true;
        this.title = "修改成人电影";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.hasMosaic = this.form.hasMosaic.join(",");
          if (this.form.id != null) {
            updateBlue(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBlue(this.form).then(response => {
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
      this.$confirm('是否确认删除成人电影编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBlue(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有成人电影数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportBlue(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    tableRowClassName({row, rowIndex}) {
      //console.log(row)
      if(row.hasMosaic === 1){
        return 'has-mosaic'
      }else {
        return 'no-mosaic'
      }
    },
    handleTorrentFile(id) {
      const mimeTorrent = 'application/x-bittorrent'
      axios({
        method: 'get',
        url: process.env.VUE_APP_BASE_API + '/relax/blue/torrent/' + id,
        responseType: 'blob',
        headers: { 'Authorization': 'Bearer ' + getToken() }
      }).then(res => {
        if (res.data.type === mimeTorrent) {
          resolveBlob(res, mimeTorrent)
        } else {
          res.data.text().then(result => {
            let msg = ''
            try {
              msg = JSON.parse(result).msg
            } catch (e) {
              msg = '系统未知错误'
            }

            this.$alert(msg, '粗错啦', {
              confirmButtonText: '确定',
            })
          })
        }
      })
    }
  }
};
</script>

<style lang="scss">
.el-table .has-mosaic {
  background: #e1e2f6;
}
.el-table .no-mosaic {
  background: #f8e4e4;
}
</style>