import { post } from '~/api/client'
import type { UserSignInParams, RefreshTokenParams } from '~/api/AuthController/params'
import type { LoginUserVO } from '~/api/AuthController/vo'

const PREFIX = '/auth'

/** 认证相关接口 */
export const AuthController = {
  /** 登录 */
  signIn: async (params: UserSignInParams): Promise<LoginUserVO> => {
    const { data } = await post<LoginUserVO>(`${PREFIX}/sign-in`, params)
    return data
  },

  /** 刷新令牌 */
  refreshToken: async (params: RefreshTokenParams): Promise<LoginUserVO> => {
    const { data } = await post<LoginUserVO>(`${PREFIX}/refresh-token`, params)
    return data
  }
}
