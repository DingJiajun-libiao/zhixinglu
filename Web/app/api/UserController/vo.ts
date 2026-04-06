/** 用户信息 */
export interface UserVO {
  /** 用户ID（字符串形式的长整型） */
  id: string
  /** 账号 */
  account: string
  /** 昵称 */
  nickname: string
  /** 头像URL */
  avatar: string
  /** 性别（0:未知 1:男 2:女） */
  gender: 0 | 1 | 2
  /** 角色名称 */
  roleName: string
}
