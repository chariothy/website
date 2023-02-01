package com.ruoyi.web.domain;

import java.util.Date;

import com.alibaba.fastjson2.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成人电影对象 spd_adult
 * 
 * @author common
 * @date 2021-03-09
 */
public class SpdAdult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 链接 */
    @Excel(name = "链接")
    private String href;

    /** 图片列表 */
    @Excel(name = "图片列表")
    private JSONArray pics;

    /** 种子地址 */
    @Excel(name = "种子地址")
    private String torrent;

    /** 是否有码 */
    @Excel(name = "是否有码")
    private Long hasMosaic;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 逻辑删除 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "逻辑删除", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deletedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setHref(String href) 
    {
        this.href = href;
    }

    public String getHref() 
    {
        return href;
    }
    public void setPics(JSONArray pics)
    {
        this.pics = pics;
    }

    public JSONArray getPics()
    {
        return pics;
    }
    public void setTorrent(String torrent) 
    {
        this.torrent = torrent;
    }

    public String getTorrent() 
    {
        return torrent;
    }
    public void setHasMosaic(Long hasMosaic) 
    {
        this.hasMosaic = hasMosaic;
    }

    public Long getHasMosaic() 
    {
        return hasMosaic;
    }
    public void setDeletedAt(Date deletedAt) 
    {
        this.deletedAt = deletedAt;
    }

    public String getSource()
    {
        return source;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public Date getDeletedAt() 
    {
        return deletedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("href", getHref())
            .append("pics", getPics())
            .append("torrent", getTorrent())
            .append("hasMosaic", getHasMosaic())
            .append("source", getSource())
            .append("deletedAt", getDeletedAt())
            .toString();
    }
}
