<script setup lang="ts">
definePageMeta({
  layout: false
})

import * as z from 'zod'
import type { FormSubmitEvent } from '@nuxt/ui'
import { type ApiError, AuthController } from '~/api'

const toast = useToast()
const router = useRouter()
const authStore = useAuthStore()

const state = reactive({ account: '', password: '' })

const schema = z.object({
  account: z
    .string()
    .min(1, '请输入账户')
    .min(2, '账号长度必须在2到16个字符之间')
    .max(16, '账号长度必须在2到16个字符之间')
    .regex(/^[a-zA-Z0-9\u4e00-\u9fa5\p{P}\p{S}]+$/u, '账号只能包含中英文、数字和常用标点符号'),
  password: z
    .string()
    .min(1, '请输入密码')
    .min(8, '密码长度必须在8到32个字符之间')
    .max(32, '密码长度必须在8到32个字符之间')
    .regex(/^[a-zA-Z0-9\u4e00-\u9fa5\p{P}\p{S}]+$/u, '密码只能包含中英文、数字和常用标点符号')
})

type Schema = z.output<typeof schema>

const onSubmit = async (payload: FormSubmitEvent<Schema>) => {
  try {
    const result = await AuthController.signIn(payload.data)
    authStore.setLoginResult(result)
    toast.add({ title: '登录成功', color: 'success' })
    await router.push('/home')
  } catch (err) {
    const e = err as ApiError
    toast.add({ title: '登录失败', description: e.msg, color: 'error' })
  }
}
</script>

<template>
  <div class="flex flex-col items-center justify-center min-h-screen gap-4 p-4">
    <UPageCard class="w-full max-w-md">
      <h2 class="text-xl font-semibold text-center mb-6">
        登录
      </h2>
      <UForm
        :schema="schema"
        :state="state"
        class="space-y-4"
        @submit="onSubmit"
      >
        <UFormField name="account">
          <div class="flex items-center gap-4">
            <label class="w-12 shrink-0 text-sm font-medium">账号</label>
            <UInput
              v-model="state.account"
              placeholder="请输入您的账户"
              class="flex-1"
            />
          </div>
        </UFormField>
        <UFormField name="password">
          <div class="flex items-center gap-4">
            <label class="w-12 shrink-0 text-sm font-medium">密码</label>
            <UInput
              v-model="state.password"
              type="password"
              placeholder="请输入您的密码"
              class="flex-1"
            />
          </div>
        </UFormField>
        <UButton
          type="submit"
          block
        >
          登录
        </UButton>
      </UForm>
    </UPageCard>
  </div>
</template>
