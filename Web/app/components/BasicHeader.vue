<script setup lang="ts">
import type { NavigationMenuItem, DropdownMenuItem } from '@nuxt/ui'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

const items = computed<NavigationMenuItem[]>(() => [
  {
    label: '主页',
    to: '/home',
    active: route.path.startsWith('/home')
  },
  {
    label: '文档',
    to: '/docs',
    active: route.path.startsWith('/docs')
  }
])

const dropdownItems: DropdownMenuItem[][] = [
  [
    {
      label: '个人中心',
      icon: 'i-lucide-user'
    }
  ],
  [
    {
      label: '退出登录',
      icon: 'i-lucide-log-out',
      onSelect: async () => {
        authStore.logout()
        await router.push('/')
      }
    }
  ]
]
</script>

<template>
  <UHeader>
    <template #title>
      <h1>知行录</h1>
    </template>

    <UNavigationMenu :items="items" />

    <template #right>
      <UColorModeButton />

      <UDropdownMenu :items="dropdownItems">
        <UAvatar
          :src="authStore.user?.avatar || undefined"
          :alt="authStore.user?.nickname || '用户'"
          icon="i-lucide-user"
          size="sm"
          class="cursor-pointer"
        />
      </UDropdownMenu>
    </template>
  </UHeader>
</template>
