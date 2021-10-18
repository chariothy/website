package com.ruoyi.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.KVMapper;
import com.ruoyi.web.domain.KV;
import com.ruoyi.web.service.IKVService;

/**
 * 键值对Service业务层处理
 * 
 * @author henry
 * @date 2021-10-17
 */
@Service
public class KVServiceImpl implements IKVService 
{
    @Autowired
    private KVMapper kVMapper;

    /**
     * 查询键值对
     * 
     * @param id 键值对主键
     * @return 键值对
     */
    @Override
    public KV selectKVById(Long id)
    {
        return kVMapper.selectKVById(id);
    }

    /**
     * 查询键值对
     *
     * @param key 键值对key
     * @return 键值对
     */
    @Override
    public KV selectKVByKey(String key)
    {
        return kVMapper.selectKVByKey(key);
    }
    /**
     * 查询键值对列表
     * 
     * @param kV 键值对
     * @return 键值对
     */
    @Override
    public List<KV> selectKVList(KV kV)
    {
        return kVMapper.selectKVList(kV);
    }

    /**
     * 新增键值对
     * 
     * @param kV 键值对
     * @return 结果
     */
    @Override
    public int insertKV(KV kV)
    {
        return kVMapper.insertKV(kV);
    }

    /**
     * 修改键值对
     * 
     * @param kV 键值对
     * @return 结果
     */
    @Override
    public int updateKV(KV kV)
    {
        return kVMapper.updateKV(kV);
    }

    /**
     * 批量删除键值对
     * 
     * @param ids 需要删除的键值对主键
     * @return 结果
     */
    @Override
    public int deleteKVByIds(Long[] ids)
    {
        return kVMapper.deleteKVByIds(ids);
    }

    /**
     * 删除键值对信息
     * 
     * @param id 键值对主键
     * @return 结果
     */
    @Override
    public int deleteKVById(Long id)
    {
        return kVMapper.deleteKVById(id);
    }
}
