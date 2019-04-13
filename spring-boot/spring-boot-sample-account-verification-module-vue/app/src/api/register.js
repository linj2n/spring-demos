import request from '@/utils/request'

export function register(data) {
  return request({
    url: '/api/v1/account',
    method: 'post',
    data
  })
}

export function checkUsernameOrEmailExistence(login, email) {
  console.log('login: ' + login + ',email: ' + email)
  return request({
    url: '/api/v1/account/existence',
    method: 'get',
    params: {
      login: login,
      email: email || ''
    }
  })
}
