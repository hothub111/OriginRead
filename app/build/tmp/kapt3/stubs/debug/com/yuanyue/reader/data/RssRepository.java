package com.yuanyue.reader.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0019\u001a\u00020\u0014J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0016H\u0082@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010!\u001a\u0004\u0018\u00010\f2\u0006\u0010\"\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010#J\u0018\u0010$\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010#J \u0010%\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u00142\b\b\u0002\u0010&\u001a\u00020\'H\u0086@\u00a2\u0006\u0002\u0010(J\u0012\u0010)\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010*\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010+J\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00132\u0006\u0010\u001c\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/yuanyue/reader/data/RssRepository;", "", "feedDao", "Lcom/yuanyue/reader/core/db/RssFeedDao;", "articleDao", "Lcom/yuanyue/reader/core/db/RssArticleDao;", "rssClient", "Lcom/yuanyue/reader/data/RssClient;", "(Lcom/yuanyue/reader/core/db/RssFeedDao;Lcom/yuanyue/reader/core/db/RssArticleDao;Lcom/yuanyue/reader/data/RssClient;)V", "articles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/yuanyue/reader/model/RssArticleEntity;", "getArticles", "()Lkotlinx/coroutines/flow/Flow;", "feeds", "Lcom/yuanyue/reader/model/RssFeedEntity;", "getFeeds", "addFeed", "Lcom/yuanyue/reader/data/OperationResult;", "", "rawUrl", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "articlesForFeed", "feedId", "deleteFeed", "", "feed", "(Lcom/yuanyue/reader/model/RssFeedEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAndParse", "Lcom/yuanyue/reader/data/ParsedFeed;", "url", "getArticle", "articleId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFeed", "markRead", "read", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "normalizeUrl", "refreshAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshFeed", "toggleFavorite", "article", "(Lcom/yuanyue/reader/model/RssArticleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RssRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.core.db.RssFeedDao feedDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.core.db.RssArticleDao articleDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.RssClient rssClient = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.RssFeedEntity>> feeds = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.RssArticleEntity>> articles = null;
    
    public RssRepository(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.core.db.RssFeedDao feedDao, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.core.db.RssArticleDao articleDao, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.RssClient rssClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.RssFeedEntity>> getFeeds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.RssArticleEntity>> getArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.RssArticleEntity>> articlesForFeed(long feedId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getArticle(long articleId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.model.RssArticleEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFeed(long feedId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.model.RssFeedEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addFeed(@org.jetbrains.annotations.NotNull()
    java.lang.String rawUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<java.lang.Long>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshFeed(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.RssFeedEntity feed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteFeed(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.RssFeedEntity feed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markRead(long articleId, boolean read, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleFavorite(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.RssArticleEntity article, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object fetchAndParse(java.lang.String url, kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.ParsedFeed> $completion) {
        return null;
    }
    
    private final java.lang.String normalizeUrl(java.lang.String rawUrl) {
        return null;
    }
}