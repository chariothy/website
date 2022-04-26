package com.ruoyi.web.controller.invest;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.domain.SpdFundBase;
import com.ruoyi.web.service.ISpdFundBaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基金基础数据Controller
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
@RestController
@RequestMapping("/invest/fund")
public class SpdFundController extends BaseController
{
    @Autowired
    private ISpdFundBaseService SpdFundBaseService;

    /**
     * 查询基金基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('invest:fund:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpdFundBase SpdFundBase)
    {
        startPage();
        List<SpdFundBase> list = SpdFundBaseService.selectSpdFundBaseList(SpdFundBase);
        return getDataTable(list);
    }

    /**
     * 导出基金基础数据列表
     */
    @PreAuthorize("@ss.hasPermi('invest:fund:export')")
    @Log(title = "基金基础数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpdFundBase SpdFundBase)
    {
        List<SpdFundBase> list = SpdFundBaseService.selectSpdFundBaseList(SpdFundBase);
        ExcelUtil<SpdFundBase> util = new ExcelUtil<SpdFundBase>(SpdFundBase.class);
        util.exportExcel(response, list, "基金基础数据数据");
    }

    /**
     * 获取基金基础数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('invest:fund:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(SpdFundBaseService.selectSpdFundBaseById(id));
    }

    /**
     * 新增基金基础数据
     */
    @PreAuthorize("@ss.hasPermi('invest:fund:add')")
    @Log(title = "基金基础数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpdFundBase SpdFundBase)
    {
        return toAjax(SpdFundBaseService.insertSpdFundBase(SpdFundBase));
    }

    /**
     * 修改基金基础数据
     */
    @PreAuthorize("@ss.hasPermi('invest:fund:edit')")
    @Log(title = "基金基础数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpdFundBase SpdFundBase)
    {
        return toAjax(SpdFundBaseService.updateSpdFundBase(SpdFundBase));
    }

    /**
     * 收藏基金基础数据
     */
    @PreAuthorize("@ss.hasPermi('invest:fund:edit')")
    @Log(title = "基金基础数据", businessType = BusinessType.UPDATE)
    @PutMapping("fav")
    public AjaxResult collect(@RequestBody SpdFundBase SpdFundBase)
    {
        return toAjax(SpdFundBaseService.updateSpdFundBaseFav(SpdFundBase));
    }

    /**
     * 删除基金基础数据
     */
    @PreAuthorize("@ss.hasPermi('invest:fund:remove')")
    @Log(title = "基金基础数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(SpdFundBaseService.deleteSpdFundBaseByIds(ids));
    }
}
