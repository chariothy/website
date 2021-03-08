package com.ruoyi.web.controller.relax;

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
import com.ruoyi.web.domain.SpdMovie;
import com.ruoyi.web.service.ISpdMovieService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 最新电影Controller
 * 
 * @author henry
 * @date 2021-03-08
 */
@RestController
@RequestMapping("/relax/movie")
public class SpdMovieController extends BaseController
{
    @Autowired
    private ISpdMovieService spdMovieService;

    /**
     * 查询最新电影列表
     */
    @PreAuthorize("@ss.hasPermi('relax:movie:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpdMovie spdMovie)
    {
        startPage();
        List<SpdMovie> list = spdMovieService.selectSpdMovieList(spdMovie);
        return getDataTable(list);
    }

    /**
     * 导出最新电影列表
     */
    @PreAuthorize("@ss.hasPermi('relax:movie:export')")
    @Log(title = "最新电影", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SpdMovie spdMovie)
    {
        List<SpdMovie> list = spdMovieService.selectSpdMovieList(spdMovie);
        ExcelUtil<SpdMovie> util = new ExcelUtil<SpdMovie>(SpdMovie.class);
        return util.exportExcel(list, "movie");
    }

    /**
     * 获取最新电影详细信息
     */
    @PreAuthorize("@ss.hasPermi('relax:movie:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spdMovieService.selectSpdMovieById(id));
    }

    /**
     * 新增最新电影
     */
    @PreAuthorize("@ss.hasPermi('relax:movie:add')")
    @Log(title = "最新电影", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpdMovie spdMovie)
    {
        return toAjax(spdMovieService.insertSpdMovie(spdMovie));
    }

    /**
     * 修改最新电影
     */
    @PreAuthorize("@ss.hasPermi('relax:movie:edit')")
    @Log(title = "最新电影", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpdMovie spdMovie)
    {
        return toAjax(spdMovieService.updateSpdMovie(spdMovie));
    }

    /**
     * 删除最新电影
     */
    @PreAuthorize("@ss.hasPermi('relax:movie:remove')")
    @Log(title = "最新电影", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spdMovieService.deleteSpdMovieByIds(ids));
    }
}
