package com.yuanyue.reader.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a\u0016\u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\u001e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\u001e\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001aT\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a\u0016\u0010\u0018\u001a\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a%\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n\u00a2\u0006\u0002\b\u001dH\u0003\u001a\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0007H\u0002\u00a8\u0006 "}, d2 = {"DailyDigestCard", "", "articles", "", "Lcom/yuanyue/reader/model/RssArticleEntity;", "onOpenArticle", "Lkotlin/Function1;", "", "EmptyFeedHint", "onOpenFeedManager", "Lkotlin/Function0;", "FeedCard", "feed", "Lcom/yuanyue/reader/model/RssFeedEntity;", "onClick", "HomeHeader", "isRefreshing", "", "onRefresh", "HomeScreen", "viewModel", "Lcom/yuanyue/reader/ui/home/HomeViewModel;", "onOpenFeed", "onOpenHotBoard", "HotBoardCard", "SectionTitle", "title", "", "action", "Landroidx/compose/runtime/Composable;", "formatDigestTime", "epochMillis", "app_debug"})
public final class HomeScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.ui.home.HomeViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenFeedManager, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenFeed, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenArticle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenHotBoard) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HomeHeader(boolean isRefreshing, kotlin.jvm.functions.Function0<kotlin.Unit> onRefresh) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DailyDigestCard(java.util.List<com.yuanyue.reader.model.RssArticleEntity> articles, kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenArticle) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HotBoardCard(kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SectionTitle(java.lang.String title, kotlin.jvm.functions.Function0<kotlin.Unit> action) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FeedCard(com.yuanyue.reader.model.RssFeedEntity feed, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyFeedHint(kotlin.jvm.functions.Function0<kotlin.Unit> onOpenFeedManager) {
    }
    
    private static final java.lang.String formatDigestTime(long epochMillis) {
        return null;
    }
}