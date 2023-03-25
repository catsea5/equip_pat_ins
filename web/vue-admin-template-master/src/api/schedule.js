import request from '@/utils/request'

export default {
  findScheduleList(id) {
    return request({
      url: `/schedule/list/${id}`,
      method: 'get'
    })
  },
  deleteSchedule(aid, pid) {
    return request({
      url: `/schedule/delete/${aid}/${pid}`,
      method: 'get'
    })
  },
  addInfo(addObj) {
    return request({
      url: `/schedule/add`,
      method: 'post',
      data: addObj
    })
  }
}
