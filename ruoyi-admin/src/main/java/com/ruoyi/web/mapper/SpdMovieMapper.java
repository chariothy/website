package com.ruoyi.web.mapper;

import java.util.List;
import com.ruoyi.web.domain.SpdMovie;

/**
 * 最新电影Mapper接口
 * 
 * @author henry
 * @date 2021-03-08
 */
public interface SpdMovieMapper 
{
    /**
     * 查询最新电影
     * 
     * @param id 最新电影ID
     * @return 最新电影
     */
    public SpdMovie selectSpdMovieById(Long id);

    /**
     * 查询最新电影列表
     * 
     * @param spdMovie 最新电影
     * @return 最新电影集合
     */
    public List<SpdMovie> selectSpdMovieList(SpdMovie spdMovie);

    /**
     * 新增最新电影
     * 
     * @param spdMovie 最新电影
     * @return 结果
     */
    public int insertSpdMovie(SpdMovie spdMovie);

    /**
     * 修改最新电影
     * 
     * @param spdMovie 最新电影
     * @return 结果
     */
    public int updateSpdMovie(SpdMovie spdMovie);

    /**
     * 删除最新电影
     * 
     * @param id 最新电影ID
     * @return 结果
     */
    public int deleteSpdMovieById(Long id);

    /**
     * 批量删除最新电影
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpdMovieByIds(Long[] ids);

    public int handleMovieSeen(SpdMovie spdMovie);
}
