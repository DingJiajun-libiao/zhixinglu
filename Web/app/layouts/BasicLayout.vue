<script setup lang="ts">
import BasicHeader from '~/components/BasicHeader.vue'
import BasicFooter from '~/components/BasicFooter.vue'
</script>

<template>
  <div class="flex flex-col h-screen overflow-hidden">
    <BasicHeader class="shrink-0" />
    <UMain class="flex-1 min-h-0 overflow-y-auto">
      <slot />
    </UMain>
    <BasicFooter class="shrink-0" />
  </div>
</template>
