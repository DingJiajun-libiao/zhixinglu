/** 登录用户信息 */
export interface LoginUserVO {
  /** 账号 */
  account: string
  /** 昵称 */
  nickname: string
  /** 头像URL */
  avatar: string
  /** 角色名称 */
  roleName: string
  /** 访问令牌 */
  accessToken: string
  /** 刷新令牌 */
  refreshToken: string
  /** 访问令牌有效期（毫秒） */
  accessTokenTtl: number
  /** 刷新令牌有效期（毫秒） */
  refreshTokenTtl: number
}
