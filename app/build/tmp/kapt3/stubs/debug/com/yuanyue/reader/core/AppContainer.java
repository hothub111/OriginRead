package com.yuanyue.reader.core;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010)\u001a\u00020*\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020.\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100\u00a8\u00061"}, d2 = {"Lcom/yuanyue/reader/core/AppContainer;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "articleContentRepository", "Lcom/yuanyue/reader/data/ArticleContentRepository;", "getArticleContentRepository", "()Lcom/yuanyue/reader/data/ArticleContentRepository;", "bookSourceRuleEngine", "Lcom/yuanyue/reader/data/BookSourceRuleEngine;", "getBookSourceRuleEngine", "()Lcom/yuanyue/reader/data/BookSourceRuleEngine;", "comicRepository", "Lcom/yuanyue/reader/data/ComicRepository;", "getComicRepository", "()Lcom/yuanyue/reader/data/ComicRepository;", "comicSourceRuleEngine", "Lcom/yuanyue/reader/data/ComicSourceRuleEngine;", "getComicSourceRuleEngine", "()Lcom/yuanyue/reader/data/ComicSourceRuleEngine;", "database", "Lcom/yuanyue/reader/core/db/AppDatabase;", "hotSearchRepository", "Lcom/yuanyue/reader/data/HotSearchRepository;", "getHotSearchRepository", "()Lcom/yuanyue/reader/data/HotSearchRepository;", "libraryRepository", "Lcom/yuanyue/reader/data/LibraryRepository;", "getLibraryRepository", "()Lcom/yuanyue/reader/data/LibraryRepository;", "onlineBookRepository", "Lcom/yuanyue/reader/data/OnlineBookRepository;", "getOnlineBookRepository", "()Lcom/yuanyue/reader/data/OnlineBookRepository;", "rssClient", "Lcom/yuanyue/reader/data/RssClient;", "getRssClient", "()Lcom/yuanyue/reader/data/RssClient;", "rssRepository", "Lcom/yuanyue/reader/data/RssRepository;", "getRssRepository", "()Lcom/yuanyue/reader/data/RssRepository;", "settingsRepository", "Lcom/yuanyue/reader/data/SettingsRepository;", "getSettingsRepository", "()Lcom/yuanyue/reader/data/SettingsRepository;", "app_debug"})
public final class AppContainer {
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.core.db.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.RssClient rssClient = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.RssRepository rssRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.HotSearchRepository hotSearchRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.ArticleContentRepository articleContentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.BookSourceRuleEngine bookSourceRuleEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.ComicSourceRuleEngine comicSourceRuleEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.OnlineBookRepository onlineBookRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.LibraryRepository libraryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.ComicRepository comicRepository = null;
    
    public AppContainer(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.SettingsRepository getSettingsRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.RssClient getRssClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.RssRepository getRssRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.HotSearchRepository getHotSearchRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.ArticleContentRepository getArticleContentRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.BookSourceRuleEngine getBookSourceRuleEngine() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.ComicSourceRuleEngine getComicSourceRuleEngine() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.OnlineBookRepository getOnlineBookRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.LibraryRepository getLibraryRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.ComicRepository getComicRepository() {
        return null;
    }
}