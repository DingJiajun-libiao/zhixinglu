import { get, post } from '~/api/client'
import type { UserSignUpParams, UserUpdateParams } from '~/api/UserController/params'
import type { UserVO } from '~/api/UserController/vo'

const PREFIX = '/user'

/** 用户相关接口 */
export const UserController = {
  /** 注册 */
  signUp: async (params: UserSignUpParams): Promise<boolean> => {
    const { data } = await post<boolean>(`${PREFIX}/sign-up`, params)
    return data
  },

  /** 更新个人信息 */
  update: async (params: UserUpdateParams): Promise<boolean> => {
    const { data } = await post<boolean>(`${PREFIX}/update`, params)
    return data
  },

  /** 获取当前登录用户信息 */
  get: async (): Promise<UserVO> => {
    const { data } = await get<UserVO>(`${PREFIX}/get`)
    return data
  }

}
