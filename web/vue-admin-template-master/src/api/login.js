import request from '@/utils/request'

export function login(email, password) {
  return request({
    url: '/admin/login',
    method: 'post',
    data: {
      email,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
