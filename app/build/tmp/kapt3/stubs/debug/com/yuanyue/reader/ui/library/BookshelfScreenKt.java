package com.yuanyue.reader.ui.library;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ax\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00040\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00040\f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011H\u0007\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"bookshelfTabs", "", "Lcom/yuanyue/reader/ui/library/BookshelfTab;", "BookshelfScreen", "", "localViewModel", "Lcom/yuanyue/reader/ui/library/LibraryViewModel;", "onlineViewModel", "Lcom/yuanyue/reader/ui/online/OnlineLibraryViewModel;", "comicViewModel", "Lcom/yuanyue/reader/ui/comic/ComicLibraryViewModel;", "onOpenLocalBook", "Lkotlin/Function1;", "", "onOpenOnlineBook", "onOpenComic", "onOpenBookSourceManager", "Lkotlin/Function0;", "onOpenComicSourceManager", "app_debug"})
public final class BookshelfScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.yuanyue.reader.ui.library.BookshelfTab> bookshelfTabs = null;
    
    /**
     * 书架：把原来的「书库 / 小说 / 漫画」三个底部 Tab 合并为一个，
     * 用顶部 Tab 区分本地、在线与漫画，底部导航只保留 4 个入口。
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void BookshelfScreen(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.ui.library.LibraryViewModel localViewModel, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.ui.online.OnlineLibraryViewModel onlineViewModel, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.ui.comic.ComicLibraryViewModel comicViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenLocalBook, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenOnlineBook, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenComic, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenBookSourceManager, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenComicSourceManager) {
    }
}