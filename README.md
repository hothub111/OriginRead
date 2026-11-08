一个免费好用的RSS+在线书源 Android 阅读器项目
📖 项目简介
 
原阅 (OriginRead) 是一个以白色为主色调的 Android 阅读器 App，集成了 RSS 订阅、本地电子书阅读和全网热榜聚合功能。项目完全本地化运行，无内置账号系统，所有数据均保存在本地，致力于为用户提供纯净、私密的阅读体验。‌￼ 
 
✨ 功能特性
 
🏠 首页
• 每日资讯摘要：聚合您关心的 RSS 内容，打造个性化信息流
• 热搜聚合：汇总微博、百度、知乎、哔哩哔哩等平台热榜，支持失败提示与本地缓存
• RSS 管理：支持自定义 RSS 源订阅与添加、编辑、删除 
📚 书架
• 多维度分类：内置「本地」、「在线」、「漫画」三大分类（顶部 Tab 切换）
• 本地导入：支持导入 TXT 和 EPUB 格式的电子书文件
• 在线书源：支持在线小说和漫画书源订阅，导入前自动检测书源是否可用 
🔥 热榜
• 多平台覆盖：一键切换微博、百度、知乎、哔哩哔哩四大主流平台热榜
• 实时数据：展示各话题实时热度数值 
📖 阅读体验
• Kindle 风格阅读页：支持横向翻页与纵向滚动两种模式
• 个性化设置：支持纸张主题、字号与行距调节 
 
🛠️ 技术栈
类别 技术
语言 Kotlin
UI 框架 Jetpack Compose + Material 3
本地存储 Room、DataStore
网络请求 OkHttp
HTML 解析 Jsoup

‌
 
 
💻 环境要求
工具 版本
Android Studio 最新稳定版
JDK 17
Android SDK 35
最低 Android 版本 8.0 (API 26)

‌以上环境要求来自项目仓库的 README。‌￼ 
 
🚀 快速开始
# 1. 克隆仓库
git clone https://github.com/hothub111/OriginRead.git

# 2. 用 Android Studio 打开项目目录
#    等待 Gradle 同步完成

# 3. 选择模拟器或真机，运行 app 模块
 
详细步骤：
1. 使用 Android Studio 打开本项目根目录
2. 等待 Gradle 同步完成
3. 选择模拟器或连接真机，运行 app 模块‌￼ 
 
📦 构建与发布
 
正式打包（v1.1.0）
项目 详情
版本号 versionName = "1.1.0"，versionCode = 110
签名配置 release/keystore.properties 指向 release/yuanyue-release.jks
签名信息 口令与证书指纹见 release/signing-info.txt（不要提交到版本库）
构建命令 gradlew assembleRelease
产物路径 app/build/outputs/apk/release/app-release.apk
正式安装包 release/YuanyueReader-1.1.0-release.apk（v2 + v3 签名）
旧版本安装包 release/YuanyueReader-1.0.0-release.apk（仅作回滚参考）

 
单元测试
gradlew testDebugUnitTest
 
测试覆盖：章节解析、书源 JSON 兼容性。以上构建与测试信息均来自项目仓库的 README。‌￼ 
 
📂 项目结构（顶层）
OriginRead/
├── app/                     # 应用主模块
├── gradle/wrapper/          # Gradle Wrapper 配置
├── build.gradle.kts         # 项目级构建脚本
├── settings.gradle.kts      # 项目设置
├── gradle.properties        # Gradle 属性配置
├── gradlew                  # Unix Gradle Wrapper 脚本
├── gradlew.bat              # Windows Gradle Wrapper 脚本
├── README.md
└── .gitignore
‌以上结构信息来自仓库文件列表。‌￼ 
 
📋 版本历史
版本 说明
v1.1.0 当前正式版本，包含完整的 RSS 订阅、热榜聚合、本地书源导入等功能
v1.0.0 初始版本，仅作回滚参考

 
 
🤝 贡献指南
 
欢迎任何形式的贡献！如果您有好的想法或发现了 Bug：
1. Fork 本仓库
2. 新建分支 (git checkout -b feature/YourFeature)
3. 提交修改 (git commit -m 'Add some YourFeature')
4. 推送分支 (git push origin feature/YourFeature)
5. 提交 Pull Request 
 
⚠️ 免责声明
• 本项目仅供学习与交流使用，禁止用于任何商业用途。
• 热榜数据及 RSS 内容均来源于公开网络接口，版权归原网站/作者所有。如涉及侵权，请联系删除。
• 本项目无内置账号系统，所有数据均保存在本地，请自行做好数据备份。‌￼ 
 
📄 开源协议
 
本项目基于 MIT License 协议开源。 