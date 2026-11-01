package com.yuanyue.reader.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/yuanyue/reader/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "rssRepository", "Lcom/yuanyue/reader/data/RssRepository;", "(Lcom/yuanyue/reader/data/RssRepository;)V", "feeds", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/yuanyue/reader/model/RssFeedEntity;", "getFeeds", "()Lkotlinx/coroutines/flow/StateFlow;", "<set-?>", "", "isRefreshing", "()Z", "setRefreshing", "(Z)V", "isRefreshing$delegate", "Landroidx/compose/runtime/MutableState;", "latestArticles", "Lcom/yuanyue/reader/model/RssArticleEntity;", "getLatestArticles", "refreshAll", "", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.RssRepository rssRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.RssFeedEntity>> feeds = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.RssArticleEntity>> latestArticles = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isRefreshing$delegate = null;
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.RssRepository rssRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.RssFeedEntity>> getFeeds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.RssArticleEntity>> getLatestArticles() {
        return null;
    }
    
    public final boolean isRefreshing() {
        return false;
    }
    
    private final void setRefreshing(boolean p0) {
    }
    
    public final void refreshAll() {
    }
}