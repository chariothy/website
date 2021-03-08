package com.ruoyi.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.SpdMovieMapper;
import com.ruoyi.web.domain.SpdMovie;
import com.ruoyi.web.service.ISpdMovieService;

/**
 * 最新电影Service业务层处理
 * 
 * @author henry
 * @date 2021-03-08
 */
@Service
public class SpdMovieServiceImpl implements ISpdMovieService 
{
    @Autowired
    private SpdMovieMapper spdMovieMapper;

    /**
     * 查询最新电影
     * 
     * @param id 最新电影ID
     * @return 最新电影
     */
    @Override
    public SpdMovie selectSpdMovieById(Long id)
    {
        return spdMovieMapper.selectSpdMovieById(id);
    }

    /**
     * 查询最新电影列表
     * 
     * @param spdMovie 最新电影
     * @return 最新电影
     */
    @Override
    public List<SpdMovie> selectSpdMovieList(SpdMovie spdMovie)
    {
        return spdMovieMapper.selectSpdMovieList(spdMovie);
    }

    /**
     * 新增最新电影
     * 
     * @param spdMovie 最新电影
     * @return 结果
     */
    @Override
    public int insertSpdMovie(SpdMovie spdMovie)
    {
        return spdMovieMapper.insertSpdMovie(spdMovie);
    }

    /**
     * 修改最新电影
     * 
     * @param spdMovie 最新电影
     * @return 结果
     */
    @Override
    public int updateSpdMovie(SpdMovie spdMovie)
    {
        return spdMovieMapper.updateSpdMovie(spdMovie);
    }

    /**
     * 批量删除最新电影
     * 
     * @param ids 需要删除的最新电影ID
     * @return 结果
     */
    @Override
    public int deleteSpdMovieByIds(Long[] ids)
    {
        return spdMovieMapper.deleteSpdMovieByIds(ids);
    }

    /**
     * 删除最新电影信息
     * 
     * @param id 最新电影ID
     * @return 结果
     */
    @Override
    public int deleteSpdMovieById(Long id)
    {
        return spdMovieMapper.deleteSpdMovieById(id);
    }
}
