package com.yuanyue.reader.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0013R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/yuanyue/reader/ui/home/ArticleListViewModel;", "Landroidx/lifecycle/ViewModel;", "rssRepository", "Lcom/yuanyue/reader/data/RssRepository;", "feedId", "", "(Lcom/yuanyue/reader/data/RssRepository;J)V", "articles", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/yuanyue/reader/model/RssArticleEntity;", "getArticles", "()Lkotlinx/coroutines/flow/StateFlow;", "feed", "Lcom/yuanyue/reader/model/RssFeedEntity;", "getFeed", "refresh", "Lcom/yuanyue/reader/data/OperationResult;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ArticleListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.RssRepository rssRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.yuanyue.reader.model.RssFeedEntity> feed = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.RssArticleEntity>> articles = null;
    
    public ArticleListViewModel(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.RssRepository rssRepository, long feedId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.yuanyue.reader.model.RssFeedEntity> getFeed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.RssArticleEntity>> getArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refresh(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<kotlin.Unit>> $completion) {
        return null;
    }
}