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
    @Excel(name = "费用")
    private BigDecimal fee;

    /** 基金经理 */
    @Excel(name = "基金经理")
    private String manager;

    /** 收藏基金 */
    @Excel(name = "收藏基金")
    private Boolean favorite;

    private BigDecimal freeAt;
    private String catName;
    private String managers;
    private BigDecimal y5Risk;
    private BigDecimal y5Std;
    private BigDecimal y5Sharp;

    private BigDecimal y3Risk;
    private BigDecimal y3Std;
    private BigDecimal y3Sharp;
    private String ratingDate;
    private BigDecimal m6Return;
    private BigDecimal m3Return;

    private BigDecimal ytdReturn;
    private BigDecimal y1Return;

    private BigDecimal y3Return;
    private BigDecimal y5Return;

    private String returnDate;
    private BigDecimal cashP;
    private BigDecimal stockP;
    private BigDecimal bondP;
    private BigDecimal otherP;
    private BigDecimal topStock;
    private BigDecimal topBond;

    private String pfDate;
    private String asset;


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
    public void setFee(BigDecimal fee)
    {
        this.fee = fee;
    }

    public BigDecimal getFee()
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

    public BigDecimal getFreeAt() {
        return freeAt;
    }

    public void setFreeAt(BigDecimal freeAt) {
        this.freeAt = freeAt;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getManagers() {
        return managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public BigDecimal getY5Risk() {
        return y5Risk;
    }

    public void setY5Risk(BigDecimal y5Risk) {
        this.y5Risk = y5Risk;
    }

    public BigDecimal getY5Std() {
        return y5Std;
    }

    public void setY5Std(BigDecimal y5Std) {
        this.y5Std = y5Std;
    }

    public BigDecimal getY5Sharp() {
        return y5Sharp;
    }

    public void setY5Sharp(BigDecimal y5Sharp) {
        this.y5Sharp = y5Sharp;
    }

    public BigDecimal getM6Return() {
        return m6Return;
    }

    public void setM6Return(BigDecimal m6Return) {
        this.m6Return = m6Return;
    }

    public BigDecimal getY5Return() {
        return y5Return;
    }

    public void setY5Return(BigDecimal y5Return) {
        this.y5Return = y5Return;
    }

    public BigDecimal getCashP() {
        return cashP;
    }

    public void setCashP(BigDecimal cashP) {
        this.cashP = cashP;
    }

    public BigDecimal getStockP() {
        return stockP;
    }

    public void setStockP(BigDecimal stockP) {
        this.stockP = stockP;
    }

    public BigDecimal getBondP() {
        return bondP;
    }

    public void setBondP(BigDecimal bondP) {
        this.bondP = bondP;
    }

    public BigDecimal getotherP() {
        return otherP;
    }

    public void setotherP(BigDecimal otherP) {
        this.otherP = otherP;
    }

    public BigDecimal getTopStock() {
        return topStock;
    }

    public void setTopStock(BigDecimal topStock) {
        this.topStock = topStock;
    }

    public BigDecimal getTopBond() {
        return topBond;
    }

    public void setTopBond(BigDecimal topBond) {
        this.topBond = topBond;
    }

    public String getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(String ratingDate) {
        this.ratingDate = ratingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPfDate() {
        return pfDate;
    }

    public void setPfDate(String pfDate) {
        this.pfDate = pfDate;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getY3Risk() {
        return y3Risk;
    }

    public void setY3Risk(BigDecimal y3Risk) {
        this.y3Risk = y3Risk;
    }

    public BigDecimal getY3Std() {
        return y3Std;
    }

    public void setY3Std(BigDecimal y3Std) {
        this.y3Std = y3Std;
    }

    public BigDecimal getY3Sharp() {
        return y3Sharp;
    }

    public void setY3Sharp(BigDecimal y3Sharp) {
        this.y3Sharp = y3Sharp;
    }

    public BigDecimal getM3Return() {
        return m3Return;
    }

    public void setM3Return(BigDecimal m3Return) {
        this.m3Return = m3Return;
    }

    public BigDecimal getYtdReturn() {
        return ytdReturn;
    }

    public void setYtdReturn(BigDecimal ytdReturn) {
        this.ytdReturn = ytdReturn;
    }

    public BigDecimal getY1Return() {
        return y1Return;
    }

    public void setY1Return(BigDecimal y1Return) {
        this.y1Return = y1Return;
    }

    public BigDecimal getY3Return() {
        return y3Return;
    }

    public void setY3Return(BigDecimal y3Return) {
        this.y3Return = y3Return;
    }
}
