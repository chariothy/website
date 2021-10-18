package com.ruoyi.web.controller.common;

import java.util.List;
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
import com.ruoyi.web.domain.KV;
import com.ruoyi.web.service.IKVService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 键值对Controller
 * 
 * @author henry
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/system/kv")
public class KVController extends BaseController
{
    @Autowired
    private IKVService kVService;

    /**
     * 查询键值对列表
     */
    @PreAuthorize("@ss.hasPermi('system:kv:list')")
    @GetMapping("/list")
    public TableDataInfo list(KV kV)
    {
        startPage();
        List<KV> list = kVService.selectKVList(kV);
        return getDataTable(list);
    }

    /**
     * 导出键值对列表
     */
    @PreAuthorize("@ss.hasPermi('system:kv:export')")
    @Log(title = "键值对", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(KV kV)
    {
        List<KV> list = kVService.selectKVList(kV);
        ExcelUtil<KV> util = new ExcelUtil<KV>(KV.class);
        return util.exportExcel(list, "键值对数据");
    }

    /**
     * 获取键值对详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:kv:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(kVService.selectKVById(id));
    }

    /**
     * 获取键值对详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:kv:query')")
    @GetMapping(value = "/key/{key}")
    public AjaxResult getInfo(@PathVariable("key") String key)
    {
        return AjaxResult.success(kVService.selectKVByKey(key));
    }

    /**
     * 新增键值对
     */
    @PreAuthorize("@ss.hasPermi('system:kv:add')")
    @Log(title = "键值对", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KV kV)
    {
        return toAjax(kVService.insertKV(kV));
    }

    /**
     * 修改键值对
     */
    @PreAuthorize("@ss.hasPermi('system:kv:edit')")
    @Log(title = "键值对", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KV kV)
    {
        return toAjax(kVService.updateKV(kV));
    }

    /**
     * 删除键值对
     */
    @PreAuthorize("@ss.hasPermi('system:kv:remove')")
    @Log(title = "键值对", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(kVService.deleteKVByIds(ids));
    }
}
