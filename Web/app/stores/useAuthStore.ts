// stores/useAuthStore.ts
import { defineStore } from 'pinia'
import { computed } from 'vue'
import { useLocalStorage } from '@vueuse/core'
import type { LoginUserVO } from '~/api'

const jsonSerializer = {
  read: <T>(v: string | null): T | null => (v ? JSON.parse(v) : null),
  write: <T>(v: T): string => JSON.stringify(v)
}

export const useAuthStore = defineStore('auth', () => {
  const accessToken = useLocalStorage<string | null>('accessToken', null)
  const refreshToken = useLocalStorage<string | null>('refreshToken', null)
  const user = useLocalStorage<LoginUserVO | null>('auth-user', null, { serializer: jsonSerializer })

  const isLoggedIn = computed(() => !!accessToken.value)

  const setLoginResult = (result: LoginUserVO) => {
    accessToken.value = result.accessToken
    refreshToken.value = result.refreshToken
    user.value = result
  }

  const logout = () => {
    accessToken.value = null
    refreshToken.value = null
    user.value = null
  }

  return { accessToken, refreshToken, user, isLoggedIn, setLoginResult, logout }
})
