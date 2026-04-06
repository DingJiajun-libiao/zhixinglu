/** 登录请求参数 */
export interface UserSignInParams {
  /** 账号 */
  account: string
  /** 密码 */
  password: string
}

/** 刷新令牌请求参数 */
export interface RefreshTokenParams {
  /** 刷新令牌 */
  refreshToken: string
}
