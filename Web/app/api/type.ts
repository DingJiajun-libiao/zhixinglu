export interface BaseResponse<T = unknown> {
  code: number
  data: T
  message: string
}

export interface Page<T = unknown> {
  records: T[]
  pageNumber: number
  pageSize: number
  totalRow: number
  totalPage: number
}

export interface ApiError {
  code: number
  msg: string
  data?: unknown
}

/**
 * 数据格式类型
 * */
export const ContentType = {
  JSON: 'application/json',
  FORM_URLENCODED: 'application/x-www-form-urlencoded',
  FORM_DATA: 'multipart/form-data',
  TEXT: 'text/plain',
  XML: 'application/xml'
} as const
export type ContentType = (typeof ContentType)[keyof typeof ContentType]
