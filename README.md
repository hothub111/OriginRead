# 原阅 · Yuanyue

> 一个安静、克制的 Android 阅读器。

[![Version](https://img.shields.io/badge/version-1.2.0-5B5BD6?style=flat-square)](CHANGELOG.md)
[![Android CI](https://github.com/hothub111/Yuanyue/actions/workflows/android.yml/badge.svg)](https://github.com/hothub111/Yuanyue/actions/workflows/android.yml)
[![Android](https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/license-MIT-2EA44F?style=flat-square)](LICENSE)

原阅把 RSS、热榜、在线书源和本地书库收进同一个界面。它不追求信息密度，而是让阅读这件事少一点打扰。

所有数据保存在本地，没有账号，没有遥测，也没有服务器。

## 界面

| 书架 | 书籍详情 | 阅读 | 阅读设置 |
|:---:|:---:|:---:|:---:|
| <img src="docs/screenshots/shelf.png" width="220" alt="书架：阅读摘要、搜索、收藏与标签"> | <img src="docs/screenshots/detail.png" width="220" alt="书籍详情：封面、作者、阅读进度与手动编辑"> | <img src="docs/screenshots/reader.png" width="220" alt="阅读页：护眼主题、排版与进度"> | <img src="docs/screenshots/reading-settings.png" width="220" alt="阅读设置：字号、行距、段间距、页边距与主题"> |

<p align="center"><sub>书架 · 详情 · 阅读 · 阅读设置</sub></p>

## 特性

**阅读**

- 本地书库：TXT、EPUB、PDF、DOCX、RTF、HTML、FB2、MOBI、AZW3、Markdown。
- 图片型 EPUB 漫画自动识别，支持整页渲染、上下滚动和左右翻页。
- 横向自由翻阅、横向翻页、纵向滚动、自由滚动四种阅读方式。
- 字号、行距、段间距、页边距、首行缩进、衬线字体独立可调。
- 深色模式覆盖首页、书架、书源、设置、弹窗和阅读页。

**书库**

- 阅读摘要：藏书、在读、已读完、收藏和累计阅读时长。
- 按书名、作者、简介、标签搜索。
- 收藏夹与标签筛选。
- 书籍详情页：封面、作者、简介、阅读信息，支持手动编辑。

**外观**

- 浅色、深色、跟随系统。
- 浅色与深色主题分别自定义六个颜色角色：主色、强调色、背景、卡片、背景文字、卡片文字。
- HSV 调色盘与快捷色块，修改即时生效。

**内容源**

- 微博、百度、知乎、哔哩哔哩热榜聚合，支持失败提示与本地缓存。
- 自定义 RSS 订阅，首页生成每日资讯摘要。
- 在线小说与漫画书源；支持单/多书源 JSON、JSONL、Legado 分享链接、订阅页面和本地文件。
- 书源导入前可分析、检测可用性并批量添加。

**首次使用**

- 四页新手引导：书源、RSS、外观与阅读。
- 可随时跳过，完成后不再重复出现。

## 安装

从 [Releases](https://github.com/hothub111/Yuanyue/releases) 下载最新的 APK。

- 最低 Android 8.0（API 26）
- 目标 Android 15（API 35）
- 升级安装请保留同一签名；正式包使用 v2 + v3 签名

## 从源码构建

需要 JDK 17、Android SDK 35。

```bash
git clone https://github.com/hothub111/Yuanyue.git
cd Yuanyue
./gradlew assembleDebug
```

正式包：

```bash
./gradlew assembleRelease
```

正式签名依赖本地 `release/keystore.properties` 与 `release/yuanyue-release.jks`，二者不会提交到仓库。没有签名文件时请使用 debug 构建。

运行单元测试：

```bash
./gradlew testDebugUnitTest
```

## 技术栈

| 层 | 选型 |
|---|---|
| 语言 | Kotlin |
| UI | Jetpack Compose + Material 3 |
| 本地数据 | Room + DataStore |
| 网络 | OkHttp + Jsoup |
| 图片 | Coil |
| PDF | PDFBox-Android |

## 项目结构

```text
app/src/main/java/com/yuanyue/reader/
├── core/        # 依赖容器、Room、网络等基础设施
├── data/        # 仓库、书源规则、格式解析器
├── model/       # 数据模型
├── ui/          # 首页、书架、阅读、漫画、设置
└── MainActivity.kt
```

## 设计取向

- **Local-first**：书籍、书源、RSS、阅读进度和偏好都保存在设备上。
- **内容与界面分离**：书源规则、格式解析、阅读渲染各自独立，方便扩展。
- **克制的视觉**：低饱和配色、足够留白，让文字成为界面的主角。
- **可逆的定制**：主题、配色和排版都可以恢复默认，不把用户锁进某一种风格。

## 已知限制

- PDF 仅提取可复制文字，扫描版 PDF 暂不支持 OCR。
- MOBI/AZW3 仅支持无 DRM、非 HUFF/CDIC 压缩的文件。
- 旧版 `.doc` 需要先另存为 `.docx`。
- 在线漫画若依赖 JavaScript 动态解密图片地址，仍可能需要单独适配。
- 图片型 EPUB 采用启发式识别；旧版本已导入的漫画 EPUB 建议重新导入一次。

## 贡献

欢迎 issue、书源规则和格式解析方面的改进。提交前请阅读 [CONTRIBUTING.md](CONTRIBUTING.md)。

如果这个项目对你有帮助，给一个 Star 是最好的支持。

## 许可

[MIT](LICENSE) © 2026 Yuanyue Reader contributors

## 致谢

[Jetpack Compose](https://developer.android.com/jetpack/compose) · [Coil](https://coil-kt.github.io/coil/) · [OkHttp](https://square.github.io/okhttp/) · [Jsoup](https://jsoup.org/) · [PDFBox-Android](https://github.com/TomRoush/PdfBox-Android) · [Legado](https://github.com/gedoor/legado) 书源格式

完整版本记录见 [CHANGELOG.md](CHANGELOG.md)。
