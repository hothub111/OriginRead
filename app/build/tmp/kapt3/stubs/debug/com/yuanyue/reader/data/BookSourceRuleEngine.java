package com.yuanyue.reader.data;

/**
 * 书源规则引擎。
 *
 * 规则兼容：
 * - 普通 CSS 选择器 / `a@href` 形式；
 * - Legado 的 `class.xxx@tag.a@href`、`tag.a.0@href`、`text`、`@text` 形式；
 * - `||` 备用规则。
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0002J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0002J\u001c\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J\u001e\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060!2\u0006\u0010\"\u001a\u00020\u0006H\u0002J$\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060&H\u0002J\u0018\u0010\'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J.\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u00062\b\b\u0002\u0010+\u001a\u00020,H\u0086@\u00a2\u0006\u0002\u0010-J\u001c\u0010.\u001a\u0004\u0018\u00010\u00172\u0006\u0010/\u001a\u0002002\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0002J \u00101\u001a\u0002022\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010*\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/yuanyue/reader/data/BookSourceRuleEngine;", "", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "applyReplaceRules", "", "text", "json", "chapters", "", "Lcom/yuanyue/reader/model/OnlineChapterEntity;", "source", "Lcom/yuanyue/reader/model/BookSourceEntity;", "book", "Lcom/yuanyue/reader/model/OnlineBookEntity;", "(Lcom/yuanyue/reader/model/BookSourceEntity;Lcom/yuanyue/reader/model/OnlineBookEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "content", "chapterUrl", "(Lcom/yuanyue/reader/model/BookSourceEntity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detail", "extract", "element", "Lorg/jsoup/nodes/Element;", "rule", "fetch", "url", "referer", "isAttribute", "", "value", "isHttpUrl", "parseRule", "Lkotlin/Pair;", "rawRule", "renderUrl", "template", "replacements", "", "resolveUrl", "base", "search", "keyword", "page", "", "(Lcom/yuanyue/reader/model/BookSourceEntity;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectContentElement", "document", "Lorg/jsoup/nodes/Document;", "testSource", "Lcom/yuanyue/reader/data/BookSourceTestResult;", "translateSelector", "segment", "app_debug"})
public final class BookSourceRuleEngine {
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    
    public BookSourceRuleEngine(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient client) {
        super();
    }
    
    /**
     * 添加书源前先跑一遍：结构检查 + 实际搜索 + 目录 + 正文抽样。
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object testSource(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source, @org.jetbrains.annotations.NotNull()
    java.lang.String keyword, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.BookSourceTestResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object search(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source, @org.jetbrains.annotations.NotNull()
    java.lang.String keyword, int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.yuanyue.reader.model.OnlineBookEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object detail(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.OnlineBookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.model.OnlineBookEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object chapters(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.OnlineBookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.yuanyue.reader.model.OnlineChapterEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object content(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source, @org.jetbrains.annotations.NotNull()
    java.lang.String chapterUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final org.jsoup.nodes.Element selectContentElement(org.jsoup.nodes.Document document, java.lang.String rule) {
        return null;
    }
    
    private final java.lang.String renderUrl(java.lang.String template, java.util.Map<java.lang.String, java.lang.String> replacements) {
        return null;
    }
    
    private final java.lang.String fetch(java.lang.String url, java.lang.String referer) {
        return null;
    }
    
    private final java.lang.String extract(org.jsoup.nodes.Element element, java.lang.String rule) {
        return null;
    }
    
    /**
     * 把 Legado 规则翻译成 CSS 选择器 + 属性名。
     * 例：`class.book-item@tag.a@href` -> (".book-item a", "href")
     *    `@css:.content p@text` -> (".content p", null)
     */
    private final kotlin.Pair<java.lang.String, java.lang.String> parseRule(java.lang.String rawRule) {
        return null;
    }
    
    private final java.lang.String translateSelector(java.lang.String segment) {
        return null;
    }
    
    private final boolean isAttribute(java.lang.String value) {
        return false;
    }
    
    private final java.lang.String resolveUrl(java.lang.String base, java.lang.String value) {
        return null;
    }
    
    private final boolean isHttpUrl(java.lang.String value) {
        return false;
    }
    
    private final java.lang.String applyReplaceRules(java.lang.String text, java.lang.String json) {
        return null;
    }
}