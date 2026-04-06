/** 注册请求参数 */
export interface UserSignUpParams {
  /** 账号 */
  account: string
  /** 密码 */
  password: string
}

/** 更新个人信息请求参数 */
export interface UserUpdateParams {
  /** 昵称 */
  nickname?: string
  /** 头像 URL */
  avatar?: string
  /** 性别（0:未知 1:男 2:女） */
  gender?: 0 | 1 | 2
}
