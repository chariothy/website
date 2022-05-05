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
      <el-form-item prop="favorite">
        <el-checkbox
          v-model="queryParams.favorite"
          :true-label=1 :false-label=0
          label="已收藏的" border
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
      <el-table-column label="基金名称" align="center" >
        <template v-slot="scope">
          <table class="fund-val-table">
            <tr>
              <td>
                <el-link :href="'http://fund.eastmoney.com/' + scope.row.code + '.html' " type="primary" target="_blank">
                  {{ scope.row.code }}
                </el-link>
              </td>
              <td>
                ID={{ scope.row.id }}
              </td>
            </tr>
            <tr>
              <td colspan="2" class="fund-name">
                <el-link :href="'http://www.morningstar.cn/quicktake/' + scope.row.classId " type="primary" target="_blank">
                  {{ scope.row.name }}
                </el-link>
              </td>
            </tr>
            <tr>
              <td colspan="2">
                  {{ scope.row.catName }}
              </td>
            </tr>
            <tr>
              <td colspan="2">
                {{ scope.row.banchmark }}
              </td>
            </tr>
            <tr>
              <td colspan="2" class="date">
                  {{ scope.row.regDate }}
              </td>
            </tr>
          </table>
        </template>
      </el-table-column>
      <el-table-column label="晨星评级" align="center" >
        <template v-slot="scope">
          <table class="fund-val-table">
            <tr>
              <td class="cell-desc"></td>
              <td class="cell-desc">y5</td>
              <td class="cell-desc">y3</td>
            </tr>
            <tr>
              <td class="cell-desc">夏普</td>
              <td v-bind:class="[getSharpClass(scope.row.y5Sharp)]">
                {{ scope.row.y5Sharp | per }}
              </td>
              <td v-bind:class="[getSharpClass(scope.row.y3Sharp)]">
                {{ scope.row.y3Sharp | per }}
              </td>
              <td>
                <i v-bind:class="getUpDownClass(scope.row.y3Sharp, scope.row.y5Sharp)"></i>
              </td>
            </tr>
            <tr>
              <td class="cell-desc">风险</td>
              <td v-bind:class="[getRiskClass(scope.row.y5Risk)]">
                {{ scope.row.y5Risk | per }}
              </td>
              <td v-bind:class="[getRiskClass(scope.row.y3Risk)]">
                {{ scope.row.y3Risk | per }}
              </td>
              <td>
                <i v-bind:class="getUpDownClass(scope.row.y3Risk, scope.row.y5Risk)"></i>
              </td>
            </tr>
            <tr>
              <td class="cell-desc">波动</td>
              <td v-bind:class="[getStdClass(scope.row.y5Std)]">
                {{ scope.row.y5Std | per }}
              </td>
              <td v-bind:class="[getStdClass(scope.row.y3Std)]">
                {{ scope.row.y3Std | per }}
              </td>
              <td>
                <i v-bind:class="getUpDownClass(scope.row.y3Std, scope.row.y5Std)"></i>
              </td>
            </tr>
            <tr>
              <td colspan="3" class="date">
                {{scope.row.ratingDate}}
              </td>
            </tr>
          </table>
        </template>
      </el-table-column>
      <el-table-column label="回报" align="center">
        <template v-slot="scope">
          <table class="fund-val-table">
            <tr>
              <td class="cell-desc">
                m1r
              </td>
              <td colspan="3">
                {{ scope.row.m1Return | per }} /
                <span v-bind:class="[getCatReturnClass(scope.row.m1CatReturn)]" >{{ scope.row.m1CatReturn | catPer }} </span>
              </td>
            </tr>
            <tr>
              <td class="cell-desc">
                <i v-bind:class="getUpDownClass(scope.row.m3Return, scope.row.m6Return)"></i>
                m3r
              </td>
              <td v-bind:class="[getReturnClass(scope.row.m3Return)]">
                {{ scope.row.m3Return | per }}
              </td>
              <td class="cell-desc">m6r</td>
              <td v-bind:class="[getReturnClass(scope.row.m6Return)]">
                {{ scope.row.m6Return | per }}
              </td>
            </tr>
            <tr>
              <td class="cell-desc">
                <i v-bind:class="getUpDownClass(scope.row.ytdReturn, scope.row.y1Return)"></i>
                ytd
              </td>
              <td v-bind:class="[getReturnClass(scope.row.ytdReturn)]">
                {{ scope.row.ytdReturn | per }}
              </td>
              <td class="cell-desc">y1r</td>
              <td v-bind:class="[getReturnClass(scope.row.y1Return)]">
                {{ scope.row.y1Return | per }}
              </td>
            </tr>
            <tr>
              <td class="cell-desc">
                <i v-bind:class="getUpDownClass(scope.row.y3Return, scope.row.y5Return)"></i>
                y3r
              </td>
              <td >
                <span v-bind:class="[getReturnClass(scope.row.y3Return)]">{{ scope.row.y3Return | per }}</span>
              </td>
              <td class="cell-desc">y5r</td>
              <td >
                <span v-bind:class="[getReturnClass(scope.row.y5Return)]">{{ scope.row.y5Return | per }}</span>
              </td>
            </tr>

            <tr>
              <td colspan="2" class="return-rank">
                {{ scope.row.y3CatRank }} / {{ scope.row.y3CatSize }}
              </td>
              <td colspan="2" class="return-rank">
                {{ scope.row.y5CatRank }} / {{ scope.row.y5CatSize }}
              </td>
            </tr>
            <tr>
              <td colspan="4" class="date">
                {{scope.row.returnDate}}
              </td>
            </tr>
          </table>
        </template>
      </el-table-column>
      <el-table-column label="基本信息" align="center">
        <template v-slot="scope">

          <table class="fund-val-table">
            <tr>
              <td class="cell-desc">规模</td>
              <td>{{ scope.row.asset }}亿</td>
            </tr>
            <tr>
              <td class="cell-desc">费用</td>
              <td>{{ scope.row.fee }}%</td>
            </tr>
            <tr>
              <td class="cell-desc">免费期</td>
              <td>{{ scope.row.freeAt | free }}</td>
            </tr>
            <tr>
              <td colspan="2" class="manager" v-html="showManagers(scope.row.manager)">
              </td>
            </tr>
            <tr>
              <el-tooltip class="item" effect="dark" placement="left">
                <div slot="content" v-html="majorSectors(scope.row.industrySector)"></div>
              <td colspan="2" class="portfolio" v-html="showSectors(scope.row.industrySector)">
              </td>
              </el-tooltip>
            </tr>
          </table>
        </template>
      </el-table-column>
      <el-table-column label="资产分布" align="center" width="230px">
        <template v-slot="scope">
          <div :id="`pfBar${scope.row.id}`" :style="{width: '200px', height: '100px'}"></div>
          {{ getPortfolioBar(scope.row) }}
          <div v-html="showTopPortfolio(scope.row)" class="portfolio"></div>
        </template>
      </el-table-column>
      <!--  <el-table-column label="最低投资额" align="center" prop="minBuy" />
            <el-table-column label="基金经理" align="center" prop="manager" /> -->
      <el-table-column label="操作" align="center" width="80px" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-tooltip class="item" effect="dark" content="收藏" placement="left">
            <el-switch
              v-model="scope.row.favorite"
              @change="handleFav(scope.row)"
              v-hasPermi="['invest:fund:edit']"
            ></el-switch>
          </el-tooltip>
          <br>
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
import echarts from 'echarts'
import { listFund, getFund, delFund, addFund, updateFund, collectFund } from "@/api/invest/fund";

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
      },
    };
  },
  created() {
    this.getList();
  },
  mounted() {
  },
  methods: {
    getReturnClass(val) {
      if (val < 0) return 'cell-val-bad'
      else if (val > 10) return 'cell-val-good'
      else return ''
    },
    getCatReturnClass(val) {
      if (val < 0) return 'cell-val-bad'
      else if (val > 0) return 'cell-val-good'
      else return ''
    },
    getSharpClass(val) {
      if (val > 1.5 ) return 'cell-val-good'
      else if (val < 0) return 'cell-val-bad'
      else return ''
    },
    getRiskClass(val) {
      if (val < 5 ) return 'cell-val-good'
      else return ''
    },
    getStdClass(val) {
      if (val < 10) return 'cell-val-good'
      else return ''
    },
    getUpDownClass(v1, v2) {
      if(v1 === v2) return ''
      else if (v1 < v2) return 'el-icon-caret-bottom cell-val-bad'
      else return 'el-icon-caret-top cell-val-good'
    },
    getPortfolioLabelPos(val) {
      let pos = ''
      if(val >12) pos = ''
      else pos = 'right'
      return {position: pos}
    },
    getBarOption(data, formatter) {
      return {
        tooltip: {
          // trigger: 'axis',
          formatter(params) {
            // 返回值就是html代码可以使用标签
            return formatter(params)
          },
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          x: 0,//x 偏移量
            y: 0, // y 偏移量
            width: '100%', // 宽度
            height: 100
        },
        xAxis: {
          type: 'value',
            show: false
        },
        yAxis: {
          type: 'category',
            show: false
        },
        series: [
          {
            name: 'Direct',
            type: 'bar',
            stack: 'total',
            barMinHeight:1,
            itemStyle: {
              normal: {
                color: function(params) {
                  const colorList = ['#EAA720','#EFE42A', '#20EAEA','#AFF77D','#EFE42A','#EAA720'];
                  return colorList[params.dataIndex]
                }
              },
            },
            label: {
              show: true,
              color: '#000',
              formatter: "{b} {c}%",
            },
            emphasis: {
              focus: 'series'
            },
            data: data
          }
        ]
      }
    },
    getPortfolioBar(fund) {
      const data = [{
          value: fund.other,
          name: '其它',
        label: {position: 'insideLeft'}
        },{
        value: fund.cash,
        name: '现金',
        label: {position: 'insideLeft'}
      },{
          value: fund.bond,
          name: '债券',
        label: {position: 'insideLeft'}
        },{
          value: fund.stock,
          name: '股票',
        label: {position: 'insideLeft'}
        }]
      let option = this.getBarOption(data, (params) =>
        {return `${params.data.name}: ${params.data.value}%`}
      )
      //console.log(option)

      this.$nextTick(() => {
        let myCharts = echarts.init(document.getElementById('pfBar' + fund.id));   //初始化echarts实例
        myCharts.setOption(option)  //绘制图表, option绘制图表需要的数据
        myCharts.resize();
      })
    },
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
//        row.poster = JSON.stringify(row.poster)
//        row.magnets = JSON.stringify(row.magnets)
        collectFund(row).then(() => {
        this.getList();
          this.$modal.msgSuccess("已" + (row.favorite?"添加":"取消") + "收藏");
      })
    },
    showManagers (m) {
      if(m) {
        let currents = JSON.parse(m).filter(function (s) {
          return !s.Leave
        })
        let formatted = currents.map(function (m) {
          return `${m.ManagerName} (${m.ManagementTime})`
        })
        return formatted.join('<br>')
      }
      return ''
    },
    shortSector(s) {
      if (s.length <= 3) return s
      return s.replaceAll('、','').substring(0,2) + '业'
    },
    showSectors (s) {
      if(s) {
        let keptMajor = JSON.parse(s).filter(function (v) {
          return v.NetAssetWeight > 1
        })
        let sorted = keptMajor.sort(function (a, b) {
          return -(a.NetAssetWeight - b.NetAssetWeight)
        })
        let formatted = _.take(sorted, 3).map(m => {
          return `<li>` + this.shortSector(m.IndustryName) + ` ${m.NetAssetWeight}/${m.CatAvgWeight}</li>`
        })
        return formatted.join('')
      }
      return ''
    },
    majorSectors(s) {
      if(s) {
        let keptMajor = JSON.parse(s).filter(function (v) {
          return v.NetAssetWeight > 1
        })
        let sorted = keptMajor.sort(function (a, b) {
          return -(a.NetAssetWeight - b.NetAssetWeight)
        })
        let formatted = sorted.map(m => {
          return `<li>${m.IndustryName} ${m.NetAssetWeight}% / ${m.CatAvgWeight}%</li>`
        })
        return formatted.join('')
      }
      return ''
    },
    showTopPortfolio (fund) {
      let topStock = ''
      let topBond = ''

      if(fund.topStocks) {
        let sorted = JSON.parse(fund.topStocks).sort(function (a, b) {
          return -(a.Percent - b.Percent)
        })
        let formatted = _.take(sorted, 3).map(m => {
          return `${m.HoldingName} ${m.Percent}`
        })
        topStock = formatted.join('<br>')
      }
      if(fund.topBonds) {
        let sorted = JSON.parse(fund.topBonds).sort(function (a, b) {
          return -(a.Percent - b.Percent)
        })
        let formatted = _.take(sorted, 3).map(m => {
          return `${m.HoldingName}: ${m.Percent}`
        })
        topBond = formatted.join('<br>')
      }
      if (fund.stock > 10 && fund.bond > 10) {
        return `
<table>
<tr>
    <th>前十 ${fund.topStock}%</th>
    <th>前十 ${fund.topBond}%</th>
</tr>
<tr>
    <td>${topStock}</td>
    <td>${topBond}</td>
</tr>
<tr>
    <td colspan="2" class="date">${fund.portfolioDate}</td>
</tr>
</table>
      `
      } else if (fund.stock > 10) {
        return `
<table>
<tr>
    <th>前十 ${fund.topStock}%</th>
</tr>
<tr>
    <td>${topStock}</td>
</tr>
<tr>
    <td class="date">${fund.portfolioDate}</td>
</tr>
</table>
      `
      } else if (fund.bond > 10) {
        return `
<table class="top-portfolio">
<tr>
    <th>前十 ${fund.topBond}%</th>
</tr>
<tr>
    <td>${topBond}</td>
</tr>
<tr>
    <td class="date">${fund.portfolioDate}</td>
</tr>
</table>
      `
      }
    }
  },
  filters: {
    per (val) {
      return val.toFixed(2)
    },
    catPer (val) {
      return val < 0 ? '' : '+' + val.toFixed(2)
    },
    free (val) {
      if (val < 4) return val + '年'
      else return val + '天'
    }
  }
};
</script>

<style>
.fund-val-table td {
  border: 1px solid white;
  background: #f6f8fabd;
}
.cell-desc {
  font-weight: bold;
}
.cell-val-good {
  color: blue;
}
.cell-val-bad {
   color: red;
 }
.fund-val-table {
  margin: auto;
}
td.date {
  background: antiquewhite !important;
  text-align: center;
}
span.date {
  background: antiquewhite;
}
td.manager {
  max-width: 120px;
}
td.fund-name {
  max-width: 140px;
}
td.return-rank {
  text-align: right;
}
.portfolio {
  text-align: left;
}
.portfolio li {
  list-style: decimal;
}
.el-tooltip__popper li {
  list-style: decimal;
}
.portfolio {
  font-size: 1px;
}
.portfolio tr {
  vertical-align: baseline;
}
.portfolio td {
  border: 1px solid white;
  background: #f6f8fabd;
}
</style>
