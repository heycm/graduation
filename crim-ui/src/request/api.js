import { sendGet, sendPost, sendDelete } from './http'
export const get = (url, data) => sendGet(url, data)
export const post = (url, data) => sendPost(url, data)
export const del = url => sendDelete(url)