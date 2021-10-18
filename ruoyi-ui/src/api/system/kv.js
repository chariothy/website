import request from '@/utils/request'

// 查询键值对列表
export function listKv(query) {
  return request({
    url: '/system/kv/list',
    method: 'get',
    params: query
  })
}

// 查询键值对详细
export function getKv(id) {
  return request({
    url: '/system/kv/' + id,
    method: 'get'
  })
}

// 查询键值对详细
export function getKvByKey(key) {
  return request({
    url: '/system/kv/key/' + key,
    method: 'get'
  })
}

// 新增键值对
export function addKv(data) {
  return request({
    url: '/system/kv',
    method: 'post',
    data: data
  })
}

// 修改键值对
export function updateKv(data) {
  return request({
    url: '/system/kv',
    method: 'put',
    data: data
  })
}

// 删除键值对
export function delKv(id) {
  return request({
    url: '/system/kv/' + id,
    method: 'delete'
  })
}

// 导出键值对
export function exportKv(query) {
  return request({
    url: '/system/kv/export',
    method: 'get',
    params: query
  })
}
