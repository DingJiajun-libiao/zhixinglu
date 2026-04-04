# 知行录

## 技术栈

### 前端

| 类别 | 技术 | 说明 |
| --- | --- | --- |
| 框架 | Nuxt 3 | Vue 3 元框架，支持 SSR/SSG |
| UI 组件库 | Nuxt UI + TailwindCSS | 内置 Tailwind，开箱即用的高质量组件 |
| 状态管理 | Pinia | Vue 官方推荐的状态管理库 |
| Markdown 编辑器 | TipTap (Vue 3) | 基于 ProseMirror，可高度定制的富文本/Markdown 编辑器 |
| Markdown 渲染 | @nuxtjs/mdc | 将 Markdown 渲染为 Vue 组件，支持 MDC 语法 |
| HTTP 客户端 | Axios | 与后端 API 交互 |
| 工具库 | @vueuse/core | 组合式工具函数集 |

### 后端

| 类别 | 技术 | 说明 |
| --- | --- | --- |
| 语言 | Java 21 | LTS 版本 |
| 框架 | Spring Boot 3.5.13 | 企业级微服务框架 |
| 数据库 | MySQL 8.0 | 关系型数据库，存储文档元数据、用户信息等 |
| ORM | MyBatis-Flex | 轻量级 ORM，支持 Lambda 查询 |
| 数据库迁移 | Flyway | 版本化 SQL 脚本管理 |
| 对象存储 | RustFS | 高性能对象存储，用于图片及静态资源 |
| 认证授权 | JWT (JSON Web Token) | 无状态用户认证 |
| 向量数据库 | Milvus | 开源向量数据库，用于 RAG 检索 |
| 本地模型服务 | Ollama | 运行本地 LLM（Embedding + Chat） |

## 构建

| 模块 | 工具 | 说明 |
| --- | --- | --- |
| 前端 | PNPM | 快速、节省磁盘空间的 Node.js 包管理器 |
| 后端 | Gradle | 基于 Groovy/Kotlin DSL 的自动化构建工具 |

## AI辅助Skills

开发前先手动创建`.claude`文件夹，在其中安装下列skills
- skills创造
    ```
    npx skills add https://github.com/anthropics/skills --skill skill-creator
    ```
- Vue最佳实践
    ```
    npx skills add https://github.com/hyf0/vue-skills --skill vue-best-practices
    ```
- 文档整合
    ```
    npx skills add https://github.com/anthropics/skills --skill doc-coauthoring
    ```
- Tailwindcss
    ```
    npx skills add https://github.com/wshobson/agents --skill tailwind-design-system
    ```
- 前端设计
    ```
    npx skills add https://github.com/anthropics/skills --skill frontend-design
    ```
- java-springboot
    ```
    npx skills add https://github.com/pluginagentmarketplace/custom-plugin-java --skill java-spring-boot
    ```
- java-springboot-copilot
    ```
    npx skills add https://github.com/github/awesome-copilot --skill java-springboot
    ```
