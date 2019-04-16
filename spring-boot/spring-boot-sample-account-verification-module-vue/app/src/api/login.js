import qs from 'qs'
import request from '@/utils/request'

export function login(username, password) {
  const data = {
    'username': username,
    'password': password
  }
  return request({
    url: '/api/v1/account/authentication',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
    },
    data: qs.stringify(data)
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
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

export function getMessage() {
  return request({
    url: '/api/v1/message',
    method: 'get'
  })
}