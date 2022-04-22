package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.web.domain.SpdFundBase;
import com.ruoyi.web.mapper.SpdFundBaseMapper;
import com.ruoyi.web.service.ISpdFundBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基金基础数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-22
 */
@Service
public class SpdFundBaseServiceImpl implements ISpdFundBaseService
{
    @Autowired
    private com.ruoyi.web.mapper.SpdFundBaseMapper SpdFundBaseMapper;

    /**
     * 查询基金基础数据
     * 
     * @param id 基金基础数据主键
     * @return 基金基础数据
     */
    @Override
    public SpdFundBase selectSpdFundBaseById(Long id)
    {
        return SpdFundBaseMapper.selectSpdFundBaseById(id);
    }

    /**
     * 查询基金基础数据列表
     * 
     * @param SpdFundBase 基金基础数据
     * @return 基金基础数据
     */
    @Override
    public List<SpdFundBase> selectSpdFundBaseList(SpdFundBase SpdFundBase)
    {
        return SpdFundBaseMapper.selectSpdFundBaseList(SpdFundBase);
    }

    /**
     * 新增基金基础数据
     * 
     * @param SpdFundBase 基金基础数据
     * @return 结果
     */
    @Override
    public int insertSpdFundBase(SpdFundBase SpdFundBase)
    {
        return SpdFundBaseMapper.insertSpdFundBase(SpdFundBase);
    }

    /**
     * 修改基金基础数据
     * 
     * @param SpdFundBase 基金基础数据
     * @return 结果
     */
    @Override
    public int updateSpdFundBase(SpdFundBase SpdFundBase)
    {
        return SpdFundBaseMapper.updateSpdFundBase(SpdFundBase);
    }

    /**
     * 批量删除基金基础数据
     * 
     * @param ids 需要删除的基金基础数据主键
     * @return 结果
     */
    @Override
    public int deleteSpdFundBaseByIds(Long[] ids)
    {
        return SpdFundBaseMapper.deleteSpdFundBaseByIds(ids);
    }

    /**
     * 删除基金基础数据信息
     * 
     * @param id 基金基础数据主键
     * @return 结果
     */
    @Override
    public int deleteSpdFundBaseById(Long id)
    {
        return SpdFundBaseMapper.deleteSpdFundBaseById(id);
    }
}
