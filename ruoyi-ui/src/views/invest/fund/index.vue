<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="基金名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入基金名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="基金代码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入基金代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['invest:fund:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['invest:fund:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['invest:fund:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['invest:fund:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fundList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="基金名称" align="center" prop="name" />
      <el-table-column label="分类ID" align="center" prop="categoryId" />
      <el-table-column label="晨星ID" align="center" prop="classId" />
      <el-table-column label="基金代码" align="center" prop="code" />
      <el-table-column label="一个月回报" align="center" prop="m1Return" />
      <el-table-column label="一个月基准指数" align="center" prop="m1Index" />
      <el-table-column label="一个月同类平均" align="center" prop="m1Cat" />
      <el-table-column label="成立日期" align="center" prop="regDate" />
      <el-table-column label="开放日期" align="center" prop="openDate" />
      <el-table-column label="上市日期" align="center" prop="saleDate" />
      <el-table-column label="可以申购" align="center" prop="canBuy" />
      <el-table-column label="可以赎回" align="center" prop="canSale" />
      <el-table-column label="最低投资额" align="center" prop="minBuy" />
      <el-table-column label="费用明细" align="center" prop="fee" />
      <el-table-column label="基金经理" align="center" prop="manager" />
      <el-table-column label="收藏基金" align="center">
        <template v-slot="scope">
          <div>
            <el-tooltip class="item" effect="dark" content="收藏基金" placement="left">
              <el-switch
                v-model="scope.row.favorite"
                @change="handleFav(scope.row)"
                v-hasPermi="['invest:fund:edit']"
              ></el-switch>
            </el-tooltip>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['invest:fund:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['invest:fund:remove']"
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

    <!-- 添加或修改基金基础数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="基金名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入基金名称" />
        </el-form-item>
        <el-form-item label="分类ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入分类ID" />
        </el-form-item>
        <el-form-item label="晨星ID" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入晨星ID" />
        </el-form-item>
        <el-form-item label="基金代码" prop="code">
          <el-input v-model="form.code" placeholder="请输入基金代码" />
        </el-form-item>
        <el-form-item label="一个月回报" prop="m1Return">
          <el-input v-model="form.m1Return" placeholder="请输入一个月回报" />
        </el-form-item>
        <el-form-item label="一个月基准指数" prop="m1Index">
          <el-input v-model="form.m1Index" placeholder="请输入一个月基准指数" />
        </el-form-item>
        <el-form-item label="一个月同类平均" prop="m1Cat">
          <el-input v-model="form.m1Cat" placeholder="请输入一个月同类平均" />
        </el-form-item>
        <el-form-item label="成立日期" prop="regDate">
          <el-input v-model="form.regDate" placeholder="请输入成立日期" />
        </el-form-item>
        <el-form-item label="开放日期" prop="openDate">
          <el-input v-model="form.openDate" placeholder="请输入开放日期" />
        </el-form-item>
        <el-form-item label="上市日期" prop="saleDate">
          <el-input v-model="form.saleDate" placeholder="请输入上市日期" />
        </el-form-item>
        <el-form-item label="可以申购" prop="canBuy">
          <el-input v-model="form.canBuy" placeholder="请输入可以申购" />
        </el-form-item>
        <el-form-item label="可以赎回" prop="canSale">
          <el-input v-model="form.canSale" placeholder="请输入可以赎回" />
        </el-form-item>
        <el-form-item label="最低投资额" prop="minBuy">
          <el-input v-model="form.minBuy" placeholder="请输入最低投资额" />
        </el-form-item>
        <el-form-item label="费用明细" prop="fee">
          <el-input v-model="form.fee" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="基金经理" prop="manager">
          <el-input v-model="form.manager" type="textarea" placeholder="请输入内容" />
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
import { listFund, getFund, delFund, addFund, updateFund } from "@/api/invest/fund";

export default {
  name: "Fund",
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
      // 基金基础数据表格数据
      fundList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        code: null,
        favorite: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "基金名称不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "基金代码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询基金基础数据列表 */
    getList() {
      this.loading = true;
      listFund(this.queryParams).then(response => {
        this.fundList = response.rows;
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
        name: null,
        categoryId: null,
        classId: null,
        code: null,
        m1Return: null,
        m1Index: null,
        m1Cat: null,
        regDate: null,
        openDate: null,
        saleDate: null,
        canBuy: null,
        canSale: null,
        minBuy: null,
        fee: null,
        manager: null,
        favorite: "0"
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
      this.title = "添加基金基础数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFund(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改基金基础数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFund(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFund(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
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
      this.$modal.confirm('是否确认删除基金基础数据编号为"' + ids + '"的数据项？').then(function() {
        return delFund(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('invest/fund/export', {
        ...this.queryParams
      }, `fund_${new Date().getTime()}.xlsx`)
    },
    /** 收藏按钮操作 */
    handleFav(row) {
      this.$confirm('是否收藏 "' + row.code + ' - ' + row.name + '"?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        row.favorite = 1
//        row.poster = JSON.stringify(row.poster)
//        row.magnets = JSON.stringify(row.magnets)
        return updateFund(row);
      }).then(() => {
        this.getList();
        this.msgSuccess("已收藏");
      })
    },
  }
};
</script>
