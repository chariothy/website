package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.SpdAdult;

/**
 * 成人电影Service接口
 * 
 * @author common
 * @date 2021-03-09
 */
public interface ISpdAdultService 
{
    /**
     * 查询成人电影
     * 
     * @param id 成人电影ID
     * @return 成人电影
     */
    public SpdAdult selectSpdAdultById(Long id);

    /**
     * 查询成人电影列表
     * 
     * @param spdAdult 成人电影
     * @return 成人电影集合
     */
    public List<SpdAdult> selectSpdAdultList(SpdAdult spdAdult);

    /**
     * 新增成人电影
     * 
     * @param spdAdult 成人电影
     * @return 结果
     */
    public int insertSpdAdult(SpdAdult spdAdult);

    /**
     * 修改成人电影
     * 
     * @param spdAdult 成人电影
     * @return 结果
     */
    public int updateSpdAdult(SpdAdult spdAdult);

    /**
     * 批量删除成人电影
     * 
     * @param ids 需要删除的成人电影ID
     * @return 结果
     */
    public int deleteSpdAdultByIds(Long[] ids);

    /**
     * 删除成人电影信息
     * 
     * @param id 成人电影ID
     * @return 结果
     */
    public int deleteSpdAdultById(Long id);
}
