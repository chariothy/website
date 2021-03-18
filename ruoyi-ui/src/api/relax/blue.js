import request from '@/utils/request'

// 查询成人电影列表
export function listBlue(query) {
  return request({
    url: '/relax/blue/list',
    method: 'get',
    params: query
  })
}

// 查询成人电影详细
export function getBlue(id) {
  return request({
    url: '/relax/blue/' + id,
    method: 'get'
  })
}

// 新增成人电影
export function addBlue(data) {
  return request({
    url: '/relax/blue',
    method: 'post',
    data: data
  })
}

// 修改成人电影
export function updateBlue(data) {
  return request({
    url: '/relax/blue',
    method: 'put',
    data: data
  })
}

// 删除成人电影
export function delBlue(id) {
  return request({
    url: '/relax/blue/' + id,
    method: 'delete'
  })
}

// 导出成人电影
export function exportBlue(query) {
  return request({
    url: '/relax/blue/export',
    method: 'get',
    params: query
  })
}
