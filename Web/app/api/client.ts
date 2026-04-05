import axios from 'axios'
import type { AxiosRequestConfig, AxiosResponse, InternalAxiosRequestConfig } from 'axios'
import { ContentType } from './type'
import type { BaseResponse, ApiError } from './type'
// 从 Nuxt runtimeConfig 获取 baseURL
const instance = axios.create({
  baseURL: '/api',
  timeout: 30000,
  withCredentials: true
})

// ==================== 请求拦截器 ====================
instance.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    if (config.headers?.['Content-Type'] === ContentType.FORM_URLENCODED) {
      if (config.data && typeof config.data === 'object') {
        config.data = new URLSearchParams(config.data as Record<string, string>).toString()
      }
    }
    if (config.headers?.['Content-Type'] === ContentType.FORM_DATA) {
      if (config.data && typeof config.data === 'object' && !(config.data instanceof FormData)) {
        const formData = new FormData()
        Object.keys(config.data).forEach((key) => {
          const value = config.data[key]
          if (Array.isArray(value)) {
            value.forEach(item => formData.append(key, item))
          } else if (value !== undefined && value !== null) {
            formData.append(key, value)
          }
        })
        config.data = formData
      }
    }
    return config
  },
  (error: unknown) => {
    return Promise.reject(error)
  }
)
// ==================== 响应拦截器 ====================
instance.interceptors.response.use(
  (response: AxiosResponse<BaseResponse>) => {
    const res = response.data
    if (res && typeof res === 'object' && 'code' in res) {
      if (res.code === 0) {
        return { data: res.data } as AxiosResponse
      }
      return Promise.reject({
        code: res.code,
        msg: res.message || '请求失败',
        data: res.data
      })
    }
    return response
  },
  (error: unknown) => {
    const axiosError = error as { response?: AxiosResponse, message?: string }
    const resData = axiosError?.response?.data as BaseResponse | undefined
    const backendMessage = resData?.message
    return Promise.reject({
      code: axiosError?.response?.status,
      msg: backendMessage || axiosError?.message || '网络异常',
      data: resData
    })
  }
)
// ==================== 导出请求方法 ====================
export function get<T = unknown>(
  url: string,
  params?: Record<string, unknown>,
  config?: AxiosRequestConfig
) {
  return instance.get<T>(url, { params, ...config })
}
export function post<T = unknown>(url: string, data?: unknown, config?: AxiosRequestConfig) {
  return instance.post<T>(url, data, config)
}
export function put<T = unknown>(url: string, data?: unknown, config?: AxiosRequestConfig) {
  return instance.put<T>(url, data, config)
}
export function del<T = unknown>(url: string, config?: AxiosRequestConfig) {
  return instance.delete<T>(url, config)
}
export function patch<T = unknown>(url: string, data?: unknown, config?: AxiosRequestConfig) {
  return instance.patch<T>(url, data, config)
}
export function isAuthError(error: unknown): boolean {
  const err = error as ApiError | undefined
  return err?.code === 401 || err?.code === 40100
}
export default instance
