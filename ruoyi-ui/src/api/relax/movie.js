import request from '@/utils/request'

// 查询最新电影列表
export function listMovie(query) {
  return request({
    url: '/relax/movie/list',
    method: 'get',
    params: query
  })
}

// 查询最新电影详细
export function getMovie(id) {
  return request({
    url: '/relax/movie/' + id,
    method: 'get'
  })
}

// 新增最新电影
export function addMovie(data) {
  return request({
    url: '/relax/movie',
    method: 'post',
    data: data
  })
}

// 修改最新电影
export function updateMovie(data) {
  return request({
    url: '/relax/movie',
    method: 'put',
    data: data
  })
}

// 删除最新电影
export function delMovie(id) {
  return request({
    url: '/relax/movie/' + id,
    method: 'delete'
  })
}

// 导出最新电影
export function exportMovie(query) {
  return request({
    url: '/relax/movie/export',
    method: 'get',
    params: query
  })
}


// 导出最新电影
export function sawMovie(data) {
  return request({
    url: '/relax/movie/saw',
    method: 'put',
    data: data
  })
}
