package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.SpdFundBase;

/**
 * 基金基础数据Service接口
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
public interface ISpdFundBaseService 
{
    /**
     * 查询基金基础数据
     * 
     * @param id 基金基础数据主键
     * @return 基金基础数据
     */
    public SpdFundBase selectSpdFundBaseById(Long id);

    /**
     * 查询基金基础数据列表
     * 
     * @param SpdFundBase 基金基础数据
     * @return 基金基础数据集合
     */
    public List<SpdFundBase> selectSpdFundBaseList(SpdFundBase SpdFundBase);

    /**
     * 新增基金基础数据
     * 
     * @param SpdFundBase 基金基础数据
     * @return 结果
     */
    public int insertSpdFundBase(SpdFundBase SpdFundBase);

    /**
     * 修改基金基础数据
     * 
     * @param SpdFundBase 基金基础数据
     * @return 结果
     */
    public int updateSpdFundBase(SpdFundBase SpdFundBase);

    /**
     * 批量删除基金基础数据
     * 
     * @param ids 需要删除的基金基础数据主键集合
     * @return 结果
     */
    public int deleteSpdFundBaseByIds(Long[] ids);

    /**
     * 删除基金基础数据信息
     * 
     * @param id 基金基础数据主键
     * @return 结果
     */
    public int deleteSpdFundBaseById(Long id);
}
