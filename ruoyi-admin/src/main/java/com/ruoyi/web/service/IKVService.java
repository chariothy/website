package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.KV;

/**
 * 键值对Service接口
 * 
 * @author henry
 * @date 2021-10-17
 */
public interface IKVService 
{
    /**
     * 查询键值对
     * 
     * @param id 键值对主键
     * @return 键值对
     */
    public KV selectKVById(Long id);

    /**
     * 查询键值对
     *
     * @param key 键值对key
     * @return 键值对
     */
    public KV selectKVByKey(String key);

    /**
     * 查询键值对列表
     * 
     * @param kV 键值对
     * @return 键值对集合
     */
    public List<KV> selectKVList(KV kV);

    /**
     * 新增键值对
     * 
     * @param kV 键值对
     * @return 结果
     */
    public int insertKV(KV kV);

    /**
     * 修改键值对
     * 
     * @param kV 键值对
     * @return 结果
     */
    public int updateKV(KV kV);

    /**
     * 批量删除键值对
     * 
     * @param ids 需要删除的键值对主键集合
     * @return 结果
     */
    public int deleteKVByIds(Long[] ids);

    /**
     * 删除键值对信息
     * 
     * @param id 键值对主键
     * @return 结果
     */
    public int deleteKVById(Long id);
}
