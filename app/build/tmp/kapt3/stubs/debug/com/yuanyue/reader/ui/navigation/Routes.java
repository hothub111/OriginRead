package com.yuanyue.reader.ui.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0015J\u0016\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015J\u001e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004J\u000e\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0015J\u0016\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015J\u000e\u0010#\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/yuanyue/reader/ui/navigation/Routes;", "", "()V", "ARTICLE_DETAIL", "", "ARTICLE_LIST", "BOOKSHELF", "BOOK_SOURCE_MANAGER", "COMIC_DETAIL", "COMIC_READER", "COMIC_SOURCE_MANAGER", "FEED_MANAGER", "HOME", "HOT", "HOT_ARTICLE", "ONLINE_BOOK", "ONLINE_READER", "READER", "SETTINGS", "articleDetail", "articleId", "", "articleList", "feedId", "comicDetail", "comicId", "comicReader", "chapterId", "hotArticle", "title", "url", "subtitle", "onlineBook", "bookId", "onlineReader", "reader", "app_debug"})
public final class Routes {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOME = "home";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BOOKSHELF = "bookshelf";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOT = "hot";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SETTINGS = "settings";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BOOK_SOURCE_MANAGER = "book_source_manager";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMIC_SOURCE_MANAGER = "comic_source_manager";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FEED_MANAGER = "feed_manager";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARTICLE_LIST = "article_list/{feedId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARTICLE_DETAIL = "article_detail/{articleId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String READER = "reader/{bookId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOT_ARTICLE = "hot_article?title={title}&url={url}&subtitle={subtitle}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ONLINE_BOOK = "online_book/{bookId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ONLINE_READER = "online_reader/{bookId}/{chapterId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMIC_DETAIL = "comic_detail/{comicId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMIC_READER = "comic_reader/{comicId}/{chapterId}";
    @org.jetbrains.annotations.NotNull()
    public static final com.yuanyue.reader.ui.navigation.Routes INSTANCE = null;
    
    private Routes() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String articleList(long feedId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String articleDetail(long articleId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String reader(long bookId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String hotArticle(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String onlineBook(long bookId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String onlineReader(long bookId, long chapterId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String comicDetail(long comicId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String comicReader(long comicId, long chapterId) {
        return null;
    }
}