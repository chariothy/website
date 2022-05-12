package com.ruoyi.web.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 最新电影对象 spd_movie
 * 
 * @author henry
 * @date 2021-03-08
 */
public class SpdMovie extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Long id;

    /** 中文标题 */
    @Excel(name = "中文标题")
    private String titleCn;

    /** 英文标题 */
    @Excel(name = "英文标题")
    private String titleEn;

    /** 内容简介 */
    @Excel(name = "内容简介")
    private String desc;

    /** IMDB分数 */
    @Excel(name = "IMDB分数")
    private BigDecimal imdb;

    /** 豆瓣分数 */
    @Excel(name = "豆瓣分数")
    private BigDecimal douban;

    /** 电影链接 */
    @Excel(name = "电影链接")
    private String href;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 类别 */
    @Excel(name = "类别")
    private String category;

    /** 上映日期 */
    @Excel(name = "上映日期")
    private String showDate;

    /** 电影海报 */
    @Excel(name = "电影海报")
    private JSONArray poster;

    /** 磁力列表 */
    @Excel(name = "磁力列表")
    private JSONArray magnets;

    /** 逻辑删除 */
    private Boolean seen;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitleCn(String titleCn) 
    {
        this.titleCn = titleCn;
    }

    public String getTitleCn() 
    {
        return titleCn;
    }
    public void setTitleEn(String titleEn) 
    {
        this.titleEn = titleEn;
    }

    public String getTitleEn() 
    {
        return titleEn;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
    }
    public void setImdb(BigDecimal imdb) 
    {
        this.imdb = imdb;
    }

    public BigDecimal getImdb() 
    {
        return imdb;
    }
    public void setDouban(BigDecimal douban) 
    {
        this.douban = douban;
    }

    public BigDecimal getDouban() 
    {
        return douban;
    }
    public void setHref(String href) 
    {
        this.href = href;
    }

    public String getHref() 
    {
        return href;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setShowDate(String showDate) 
    {
        this.showDate = showDate;
    }

    public String getShowDate() 
    {
        return showDate;
    }
    public void setPoster(JSONArray poster)
    {
        this.poster = poster;
    }

    public JSONArray getPoster()
    {
        return poster;
    }
    public void setMagnets(JSONArray magnets)
    {
        this.magnets = magnets;
    }

    public JSONArray getMagnets()
    {
        return magnets;
    }
    public void setSeen(Boolean seen)
    {
        this.seen = seen;
    }

    public Boolean getSeen()
    {
        return seen;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("titleCn", getTitleCn())
            .append("titleEn", getTitleEn())
            .append("desc", getDesc())
            .append("imdb", getImdb())
            .append("douban", getDouban())
            .append("href", getHref())
            .append("country", getCountry())
            .append("category", getCategory())
            .append("showDate", getShowDate())
            .append("poster", getPoster())
            .append("magnets", getMagnets())
            .append("seen", getSeen())
            .toString();
    }
}
