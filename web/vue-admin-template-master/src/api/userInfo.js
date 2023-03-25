import request from '@/utils/request'

const api_name = '/user'

export default {
  // 用户列表
  getPageList(searchObj) {
    return request({
      url: `${api_name}/list`,
      method: 'post',
      data: searchObj
    })
  },
  getApplyList(searchObj) {
    return request({
      url: `${api_name}/applying`,
      method: 'post',
      data: searchObj
    })
  },
  // 用户锁定
  lock(id, status) {
    return request({
      url: `${api_name}/change/${id}/${status}`,
      method: 'get'
    })
  },
  // 用户详情
  show(id) {
    return request({
      url: `${api_name}/query/${id}`,
      method: 'get'
    })
  },
  // 用户修改
  update(userInfo) {
    return request({
      url: `${api_name}/update`,
      method: 'post',
      data: userInfo
    })
  },
  // 认证审批
  approval(id, authStatus) {
    return request({
      url: `${api_name}/change/${id}/${authStatus}`,
      method: 'get'
    })
  },
  // 获取用户id和姓名
  getUserIdAndName() {
    return request({
      url: `${api_name}/listvo`,
      method: 'get'
    })
  }
}

