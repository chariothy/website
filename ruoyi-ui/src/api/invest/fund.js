import request from '@/utils/request'

// 查询基金基础数据列表
export function listFund(query) {
  return request({
    url: '/invest/fund/list',
    method: 'get',
    params: query
  })
}

// 查询基金基础数据详细
export function getFund(id) {
  return request({
    url: '/invest/fund/' + id,
    method: 'get'
  })
}

// 新增基金基础数据
export function addFund(data) {
  return request({
    url: '/invest/fund',
    method: 'post',
    data: data
  })
}

// 修改基金基础数据
export function updateFund(data) {
  return request({
    url: '/invest/fund',
    method: 'put',
    data: data
  })
}

// 删除基金基础数据
export function delFund(id) {
  return request({
    url: '/invest/fund/' + id,
    method: 'delete'
  })
}

// 收藏基金基础数据
export function collectFund(data) {
  return request({
    url: '/invest/fund/fav',
    method: 'put',
    data: data
  })
}
