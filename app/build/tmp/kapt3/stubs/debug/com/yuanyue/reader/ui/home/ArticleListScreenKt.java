package com.yuanyue.reader.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a2\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0002\u001a\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002\u00a8\u0006\u0012"}, d2 = {"ArticleCard", "", "article", "Lcom/yuanyue/reader/model/RssArticleEntity;", "onClick", "Lkotlin/Function0;", "ArticleListScreen", "viewModel", "Lcom/yuanyue/reader/ui/home/ArticleListViewModel;", "onBack", "onOpenArticle", "Lkotlin/Function1;", "", "formatTime", "", "epochMillis", "stripHtml", "text", "app_debug"})
public final class ArticleListScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ArticleListScreen(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.ui.home.ArticleListViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenArticle) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ArticleCard(com.yuanyue.reader.model.RssArticleEntity article, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    private static final java.lang.String stripHtml(java.lang.String text) {
        return null;
    }
    
    private static final java.lang.String formatTime(long epochMillis) {
        return null;
    }
}