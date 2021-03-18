package com.ruoyi.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.SpdAdultMapper;
import com.ruoyi.web.domain.SpdAdult;
import com.ruoyi.web.service.ISpdAdultService;

/**
 * 成人电影Service业务层处理
 * 
 * @author common
 * @date 2021-03-09
 */
@Service
public class SpdAdultServiceImpl implements ISpdAdultService 
{
    @Autowired
    private SpdAdultMapper spdAdultMapper;

    /**
     * 查询成人电影
     * 
     * @param id 成人电影ID
     * @return 成人电影
     */
    @Override
    public SpdAdult selectSpdAdultById(Long id)
    {
        return spdAdultMapper.selectSpdAdultById(id);
    }

    /**
     * 查询成人电影列表
     * 
     * @param spdAdult 成人电影
     * @return 成人电影
     */
    @Override
    public List<SpdAdult> selectSpdAdultList(SpdAdult spdAdult)
    {
        return spdAdultMapper.selectSpdAdultList(spdAdult);
    }

    /**
     * 新增成人电影
     * 
     * @param spdAdult 成人电影
     * @return 结果
     */
    @Override
    public int insertSpdAdult(SpdAdult spdAdult)
    {
        return spdAdultMapper.insertSpdAdult(spdAdult);
    }

    /**
     * 修改成人电影
     * 
     * @param spdAdult 成人电影
     * @return 结果
     */
    @Override
    public int updateSpdAdult(SpdAdult spdAdult)
    {
        return spdAdultMapper.updateSpdAdult(spdAdult);
    }

    /**
     * 批量删除成人电影
     * 
     * @param ids 需要删除的成人电影ID
     * @return 结果
     */
    @Override
    public int deleteSpdAdultByIds(Long[] ids)
    {
        return spdAdultMapper.deleteSpdAdultByIds(ids);
    }

    /**
     * 删除成人电影信息
     * 
     * @param id 成人电影ID
     * @return 结果
     */
    @Override
    public int deleteSpdAdultById(Long id)
    {
        return spdAdultMapper.deleteSpdAdultById(id);
    }
}
