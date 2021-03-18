package com.ruoyi.web.controller.relax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
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
import com.ruoyi.web.domain.SpdAdult;
import com.ruoyi.web.service.ISpdAdultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 成人电影Controller
 * 
 * @author common
 * @date 2021-03-09
 */
@RestController
@RequestMapping("/relax/blue")
public class SpdAdultController extends BaseController
{
    @Autowired
    private ISpdAdultService spdAdultService;

    @Value("${website.dataRoot}")
    private String dataRoot;

    /**
     * 查询成人电影列表
     */
    @PreAuthorize("@ss.hasPermi('relax:blue:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpdAdult spdAdult)
    {
        startPage();
        List<SpdAdult> list = spdAdultService.selectSpdAdultList(spdAdult);
        return getDataTable(list);
    }

    /**
     * 导出成人电影列表
     */
    @PreAuthorize("@ss.hasPermi('relax:blue:export')")
    @Log(title = "成人电影", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SpdAdult spdAdult)
    {
        List<SpdAdult> list = spdAdultService.selectSpdAdultList(spdAdult);
        ExcelUtil<SpdAdult> util = new ExcelUtil<SpdAdult>(SpdAdult.class);
        return util.exportExcel(list, "blue");
    }

    /**
     * 获取成人电影详细信息
     */
    @PreAuthorize("@ss.hasPermi('relax:blue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spdAdultService.selectSpdAdultById(id));
    }

    /**
     * 新增成人电影
     */
    @PreAuthorize("@ss.hasPermi('relax:blue:add')")
    @Log(title = "成人电影", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpdAdult spdAdult)
    {
        return toAjax(spdAdultService.insertSpdAdult(spdAdult));
    }

    /**
     * 修改成人电影
     */
    @PreAuthorize("@ss.hasPermi('relax:blue:edit')")
    @Log(title = "成人电影", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpdAdult spdAdult)
    {
        return toAjax(spdAdultService.updateSpdAdult(spdAdult));
    }

    /**
     * 删除成人电影
     */
    @PreAuthorize("@ss.hasPermi('relax:blue:remove')")
    @Log(title = "成人电影", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spdAdultService.deleteSpdAdultByIds(ids));
    }

    /**
     * 下载种子文件
     */
    @PreAuthorize("@ss.hasPermi('relax:blue:query')")
    @GetMapping("/torrent/{id}")
    public void torrent(HttpServletResponse response, @PathVariable("id") Long id)
    {
        Path torrentPath = Paths.get(dataRoot, "torrents", id + ".torrent");
        if (!Files.exists(torrentPath) || Files.isDirectory(torrentPath)) {
            throw new CustomException("文件不存在", HttpStatus.NOT_FOUND);
        }
        String ts = MessageFormat.format("attachment; filename=\"{0,number,#}.torrent\"", id);

        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", ts);
        response.setContentType("application/x-bittorrent; charset=UTF-8");
        try {
            Files.copy(torrentPath, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
