package com.ruoyi.web.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基金基础数据对象 spd_ms_fund_base
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
public class SpdFundBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 基金名称 */
    @Excel(name = "基金名称")
    private String name;

    /** 分类ID */
    @Excel(name = "分类ID")
    private String categoryId;

    /** 晨星ID */
    @Excel(name = "晨星ID")
    private String classId;

    /** 基金代码 */
    @Excel(name = "基金代码")
    private String code;

    /** 一个月回报 */
    @Excel(name = "一个月回报")
    private BigDecimal m1Return;

    /** 一个月基准指数 */
    @Excel(name = "一个月基准指数")
    private BigDecimal m1Index;

    /** 一个月同类平均 */
    @Excel(name = "一个月同类平均")
    private BigDecimal m1Cat;

    /** 成立日期 */
    @Excel(name = "成立日期")
    private String regDate;

    /** 开放日期 */
    @Excel(name = "开放日期")
    private String openDate;

    /** 上市日期 */
    @Excel(name = "上市日期")
    private String saleDate;

    /** 可以申购 */
    @Excel(name = "可以申购")
    private Boolean canBuy;

    /** 可以赎回 */
    @Excel(name = "可以赎回")
    private Boolean canSale;

    /** 最低投资额 */
    @Excel(name = "最低投资额")
    private Long minBuy;

    /** 费用明细 */
    @Excel(name = "费用明细")
    private String fee;

    /** 基金经理 */
    @Excel(name = "基金经理")
    private String manager;

    /** 收藏基金 */
    @Excel(name = "收藏基金")
    private Boolean favorite;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCategoryId(String categoryId) 
    {
        this.categoryId = categoryId;
    }

    public String getCategoryId() 
    {
        return categoryId;
    }
    public void setClassId(String classId) 
    {
        this.classId = classId;
    }

    public String getClassId() 
    {
        return classId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setM1Return(BigDecimal m1Return) 
    {
        this.m1Return = m1Return;
    }

    public BigDecimal getM1Return() 
    {
        return m1Return;
    }
    public void setM1Index(BigDecimal m1Index) 
    {
        this.m1Index = m1Index;
    }

    public BigDecimal getM1Index() 
    {
        return m1Index;
    }
    public void setM1Cat(BigDecimal m1Cat) 
    {
        this.m1Cat = m1Cat;
    }

    public BigDecimal getM1Cat() 
    {
        return m1Cat;
    }
    public void setRegDate(String regDate) 
    {
        this.regDate = regDate;
    }

    public String getRegDate() 
    {
        return regDate;
    }
    public void setOpenDate(String openDate) 
    {
        this.openDate = openDate;
    }

    public String getOpenDate() 
    {
        return openDate;
    }
    public void setSaleDate(String saleDate) 
    {
        this.saleDate = saleDate;
    }

    public String getSaleDate() 
    {
        return saleDate;
    }
    public void setCanBuy(Boolean canBuy) 
    {
        this.canBuy = canBuy;
    }

    public Boolean getCanBuy() 
    {
        return canBuy;
    }
    public void setCanSale(Boolean canSale) 
    {
        this.canSale = canSale;
    }

    public Boolean getCanSale() 
    {
        return canSale;
    }
    public void setMinBuy(Long minBuy) 
    {
        this.minBuy = minBuy;
    }

    public Long getMinBuy() 
    {
        return minBuy;
    }
    public void setFee(String fee) 
    {
        this.fee = fee;
    }

    public String getFee() 
    {
        return fee;
    }
    public void setManager(String manager) 
    {
        this.manager = manager;
    }

    public String getManager() 
    {
        return manager;
    }
    public void setFavorite(Boolean favorite) 
    {
        this.favorite = favorite;
    }

    public Boolean getFavorite() 
    {
        return favorite;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("categoryId", getCategoryId())
            .append("classId", getClassId())
            .append("code", getCode())
            .append("m1Return", getM1Return())
            .append("m1Index", getM1Index())
            .append("m1Cat", getM1Cat())
            .append("regDate", getRegDate())
            .append("openDate", getOpenDate())
            .append("saleDate", getSaleDate())
            .append("canBuy", getCanBuy())
            .append("canSale", getCanSale())
            .append("minBuy", getMinBuy())
            .append("fee", getFee())
            .append("manager", getManager())
            .append("favorite", getFavorite())
            .toString();
    }
}
