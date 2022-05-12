package com.ruoyi.web.domain;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONArray;
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

    /** 晨星ID */
    @Excel(name = "晨星ID")
    private String classId;

    /** 基金代码 */
    @Excel(name = "基金代码")
    private String code;

    /** 一个月回报 */
    @Excel(name = "一个月回报")
    private BigDecimal m1Return;

    /** 一个月同类平均 */
    @Excel(name = "一个月同类平均")
    private BigDecimal m1CatReturn;

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
    private JSONArray manager;

    /** 收藏基金 */
    @Excel(name = "收藏基金")
    private Boolean favorite;

    private BigDecimal freeAt;
    private Integer y3RiskRating;
    private Integer y5RiskRating;
    private Integer y3MsRating;
    private Integer y5MsRating;
    private String catName;
    private String banchmark;
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
    private Integer y1CatSize;
    private Integer y1CatRank;

    private BigDecimal y3Return;
    private Integer y3CatSize;
    private Integer y3CatRank;
    private BigDecimal y5Return;
    private Integer y5CatSize;
    private Integer y5CatRank;

    private String returnDate;
    private BigDecimal cash;
    private BigDecimal stock;
    private BigDecimal bond;
    private BigDecimal other;
    private BigDecimal topStock;
    private BigDecimal topBond;

    private String portfolioDate;
    private String asset;
    private JSONArray topStocks;
    private JSONArray topBonds;
    private String managers;
    private JSONArray industrySector;


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
    public void setM1CatReturn(BigDecimal m1CatReturn)
    {
        this.m1CatReturn = m1CatReturn;
    }

    public BigDecimal getM1CatReturn()
    {
        return m1CatReturn;
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
    public void setManager(JSONArray manager)
    {
        this.manager = manager;
    }

    public JSONArray getManager()
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
            .append("classId", getClassId())
            .append("code", getCode())
            .append("m1Return", getM1Return())
            .append("m1Cat", getM1CatReturn())
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

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getBond() {
        return bond;
    }

    public void setBond(BigDecimal bond) {
        this.bond = bond;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
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

    public String getPortfolioDate() {
        return portfolioDate;
    }

    public void setPortfolioDate(String portfolioDate) {
        this.portfolioDate = portfolioDate;
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

    public String getBanchmark() {
        return banchmark;
    }

    public void setBanchmark(String banchmark) {
        this.banchmark = banchmark;
    }

    public Integer getY3RiskRating() {
        return y3RiskRating;
    }

    public void setY3RiskRating(Integer y3RiskRating) {
        this.y3RiskRating = y3RiskRating;
    }

    public Integer getY5RiskRating() {
        return y5RiskRating;
    }

    public void setY5RiskRating(Integer y5RiskRating) {
        this.y5RiskRating = y5RiskRating;
    }

    public Integer getY3MsRating() {
        return y3MsRating;
    }

    public void setY3MsRating(Integer y3MsRating) {
        this.y3MsRating = y3MsRating;
    }

    public Integer getY5MsRating() {
        return y5MsRating;
    }

    public void setY5MsRating(Integer y5MsRating) {
        this.y5MsRating = y5MsRating;
    }

    public Integer getY1CatSize() {
        return y1CatSize;
    }

    public void setY1CatSize(Integer y1CatSize) {
        this.y1CatSize = y1CatSize;
    }

    public Integer getY1CatRank() {
        return y1CatRank;
    }

    public void setY1CatRank(Integer y1CatRank) {
        this.y1CatRank = y1CatRank;
    }

    public Integer getY3CatSize() {
        return y3CatSize;
    }

    public void setY3CatSize(Integer y3CatSize) {
        this.y3CatSize = y3CatSize;
    }

    public Integer getY3CatRank() {
        return y3CatRank;
    }

    public void setY3CatRank(Integer y3CatRank) {
        this.y3CatRank = y3CatRank;
    }

    public Integer getY5CatSize() {
        return y5CatSize;
    }

    public void setY5CatSize(Integer y5CatSize) {
        this.y5CatSize = y5CatSize;
    }

    public Integer getY5CatRank() {
        return y5CatRank;
    }

    public void setY5CatRank(Integer y5CatRank) {
        this.y5CatRank = y5CatRank;
    }

    public JSONArray getTopStocks() {
        return topStocks;
    }

    public void setTopStocks(JSONArray topStocks) {
        this.topStocks = topStocks;
    }

    public JSONArray getTopBonds() {
        return topBonds;
    }

    public void setTopBonds(JSONArray topBonds) {
        this.topBonds = topBonds;
    }

    public JSONArray getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(JSONArray industrySector) {
        this.industrySector = industrySector;
    }
}
