package com.yuanyue.reader.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0017\u001a\u00020\u0018R/\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00108F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/yuanyue/reader/ui/home/ArticleDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "rssRepository", "Lcom/yuanyue/reader/data/RssRepository;", "articleId", "", "(Lcom/yuanyue/reader/data/RssRepository;J)V", "<set-?>", "Lcom/yuanyue/reader/model/RssArticleEntity;", "article", "getArticle", "()Lcom/yuanyue/reader/model/RssArticleEntity;", "setArticle", "(Lcom/yuanyue/reader/model/RssArticleEntity;)V", "article$delegate", "Landroidx/compose/runtime/MutableState;", "", "feedTitle", "getFeedTitle", "()Ljava/lang/String;", "setFeedTitle", "(Ljava/lang/String;)V", "feedTitle$delegate", "toggleFavorite", "", "app_debug"})
public final class ArticleDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.RssRepository rssRepository = null;
    private final long articleId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState article$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState feedTitle$delegate = null;
    
    public ArticleDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.RssRepository rssRepository, long articleId) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.yuanyue.reader.model.RssArticleEntity getArticle() {
        return null;
    }
    
    private final void setArticle(com.yuanyue.reader.model.RssArticleEntity p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFeedTitle() {
        return null;
    }
    
    private final void setFeedTitle(java.lang.String p0) {
    }
    
    public final void toggleFavorite() {
    }
}