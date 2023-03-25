import request from '@/utils/request'

export default {
  findPlanList(searchObj) {
    return request({
      url: `/plan/list`,
      method: 'post',
      data: searchObj
    })
  },
  add(planUpdateInfo) {
    return request({
      url: `/plan/add`,
      method: 'post',
      data: planUpdateInfo
    })
  },
  delete(id) {
    return request({
      url: `/plan/delete/${id}`,
      method: 'get'
    })
  },
  query(id) {
    return request({
      url: `/plan/query/${id}`,
      method: 'get'
    })
  },
  update(planUpdateInfo) {
    return request({
      url: `/plan/update`,
      method: 'post',
      data: planUpdateInfo
    })
  },
  findPlanByState(state) {
    return request({
      url: `/plan/list/${state}`,
      method: 'get'
    })
  }
}
