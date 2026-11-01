package com.yuanyue.reader.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fH\u0002J$\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000f2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u001fH\u0002J\u0018\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0002J.\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/yuanyue/reader/data/ComicSourceRuleEngine;", "", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "chapters", "", "Lcom/yuanyue/reader/model/ComicChapterEntity;", "source", "Lcom/yuanyue/reader/model/ComicSourceEntity;", "comic", "Lcom/yuanyue/reader/model/OnlineComicEntity;", "(Lcom/yuanyue/reader/model/ComicSourceEntity;Lcom/yuanyue/reader/model/OnlineComicEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detail", "extract", "", "element", "Lorg/jsoup/nodes/Element;", "rule", "fetch", "url", "images", "Lcom/yuanyue/reader/model/ComicImageItem;", "chapterUrl", "(Lcom/yuanyue/reader/model/ComicSourceEntity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isHttpUrl", "", "value", "renderUrl", "template", "replacements", "", "resolveUrl", "base", "search", "keyword", "page", "", "(Lcom/yuanyue/reader/model/ComicSourceEntity;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ComicSourceRuleEngine {
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    
    public ComicSourceRuleEngine(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient client) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object search(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.ComicSourceEntity source, @org.jetbrains.annotations.NotNull()
    java.lang.String keyword, int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.yuanyue.reader.model.OnlineComicEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object detail(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.ComicSourceEntity source, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.OnlineComicEntity comic, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.model.OnlineComicEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object chapters(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.ComicSourceEntity source, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.OnlineComicEntity comic, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.yuanyue.reader.model.ComicChapterEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object images(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.ComicSourceEntity source, @org.jetbrains.annotations.NotNull()
    java.lang.String chapterUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.yuanyue.reader.model.ComicImageItem>> $completion) {
        return null;
    }
    
    private final java.lang.String renderUrl(java.lang.String template, java.util.Map<java.lang.String, java.lang.String> replacements) {
        return null;
    }
    
    private final java.lang.String fetch(java.lang.String url) {
        return null;
    }
    
    private final java.lang.String extract(org.jsoup.nodes.Element element, java.lang.String rule) {
        return null;
    }
    
    private final java.lang.String resolveUrl(java.lang.String base, java.lang.String value) {
        return null;
    }
    
    private final boolean isHttpUrl(java.lang.String value) {
        return false;
    }
}