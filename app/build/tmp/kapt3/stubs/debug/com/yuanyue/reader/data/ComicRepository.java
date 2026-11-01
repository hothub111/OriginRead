package com.yuanyue.reader.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\"\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000f0\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000f0\u000e2\u0006\u0010\u001f\u001a\u00020\u0015J\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010#J\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0017H\u0002J\u001c\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000f2\u0006\u0010(\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010)J\u0018\u0010*\u001a\u0004\u0018\u00010\u001c2\u0006\u0010(\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010)J*\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u000f0\u00142\u0006\u0010(\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010.J*\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000f0\u00142\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u00101R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u00062"}, d2 = {"Lcom/yuanyue/reader/data/ComicRepository;", "", "sourceDao", "Lcom/yuanyue/reader/core/db/ComicSourceDao;", "comicDao", "Lcom/yuanyue/reader/core/db/OnlineComicDao;", "chapterDao", "Lcom/yuanyue/reader/core/db/ComicChapterDao;", "ruleEngine", "Lcom/yuanyue/reader/data/ComicSourceRuleEngine;", "client", "Lokhttp3/OkHttpClient;", "(Lcom/yuanyue/reader/core/db/ComicSourceDao;Lcom/yuanyue/reader/core/db/OnlineComicDao;Lcom/yuanyue/reader/core/db/ComicChapterDao;Lcom/yuanyue/reader/data/ComicSourceRuleEngine;Lokhttp3/OkHttpClient;)V", "sources", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/yuanyue/reader/model/ComicSourceEntity;", "getSources", "()Lkotlinx/coroutines/flow/Flow;", "addSource", "Lcom/yuanyue/reader/data/OperationResult;", "", "input", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "chapters", "Lcom/yuanyue/reader/model/ComicChapterEntity;", "comic", "Lcom/yuanyue/reader/model/OnlineComicEntity;", "(Lcom/yuanyue/reader/model/OnlineComicEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "comics", "sourceId", "deleteSource", "", "source", "(Lcom/yuanyue/reader/model/ComicSourceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detail", "fetchJson", "url", "getChapters", "comicId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getComic", "images", "Lcom/yuanyue/reader/model/ComicImageItem;", "chapterId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "keyword", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ComicRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.core.db.ComicSourceDao sourceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.core.db.OnlineComicDao comicDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.core.db.ComicChapterDao chapterDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.ComicSourceRuleEngine ruleEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.ComicSourceEntity>> sources = null;
    
    public ComicRepository(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.core.db.ComicSourceDao sourceDao, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.core.db.OnlineComicDao comicDao, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.core.db.ComicChapterDao chapterDao, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.ComicSourceRuleEngine ruleEngine, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient client) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.ComicSourceEntity>> getSources() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.OnlineComicEntity>> comics(long sourceId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSource(@org.jetbrains.annotations.NotNull()
    java.lang.String input, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<java.lang.Long>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteSource(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.ComicSourceEntity source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object search(long sourceId, @org.jetbrains.annotations.NotNull()
    java.lang.String keyword, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<? extends java.util.List<com.yuanyue.reader.model.OnlineComicEntity>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getComic(long comicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.model.OnlineComicEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object detail(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.OnlineComicEntity comic, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<com.yuanyue.reader.model.OnlineComicEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object chapters(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.OnlineComicEntity comic, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<? extends java.util.List<com.yuanyue.reader.model.ComicChapterEntity>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getChapters(long comicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.yuanyue.reader.model.ComicChapterEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object images(long comicId, long chapterId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<? extends java.util.List<com.yuanyue.reader.model.ComicImageItem>>> $completion) {
        return null;
    }
    
    private final java.lang.String fetchJson(java.lang.String url) {
        return null;
    }
}