package com.ruoyi.web.mapper;

import java.util.List;
import com.ruoyi.web.domain.SpdFundBase;

/**
 * 基金基础数据Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
public interface SpdFundBaseMapper
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
     * 删除基金基础数据
     * 
     * @param id 基金基础数据主键
     * @return 结果
     */
    public int deleteSpdFundBaseById(Long id);

    /**
     * 批量删除基金基础数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpdFundBaseByIds(Long[] ids);
}
