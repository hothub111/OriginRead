package com.yuanyue.reader.core.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&\u00a8\u0006\u0018"}, d2 = {"Lcom/yuanyue/reader/core/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "bookDao", "Lcom/yuanyue/reader/core/db/BookDao;", "bookSourceDao", "Lcom/yuanyue/reader/core/db/BookSourceDao;", "cachedContentDao", "Lcom/yuanyue/reader/core/db/CachedContentDao;", "comicChapterDao", "Lcom/yuanyue/reader/core/db/ComicChapterDao;", "comicSourceDao", "Lcom/yuanyue/reader/core/db/ComicSourceDao;", "onlineBookDao", "Lcom/yuanyue/reader/core/db/OnlineBookDao;", "onlineChapterDao", "Lcom/yuanyue/reader/core/db/OnlineChapterDao;", "onlineComicDao", "Lcom/yuanyue/reader/core/db/OnlineComicDao;", "rssArticleDao", "Lcom/yuanyue/reader/core/db/RssArticleDao;", "rssFeedDao", "Lcom/yuanyue/reader/core/db/RssFeedDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.yuanyue.reader.model.RssFeedEntity.class, com.yuanyue.reader.model.RssArticleEntity.class, com.yuanyue.reader.model.BookEntity.class, com.yuanyue.reader.model.BookSourceEntity.class, com.yuanyue.reader.model.OnlineBookEntity.class, com.yuanyue.reader.model.OnlineChapterEntity.class, com.yuanyue.reader.model.CachedChapterContentEntity.class, com.yuanyue.reader.model.ComicSourceEntity.class, com.yuanyue.reader.model.OnlineComicEntity.class, com.yuanyue.reader.model.ComicChapterEntity.class}, version = 3, exportSchema = false)
@androidx.room.TypeConverters(value = {com.yuanyue.reader.core.db.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.yuanyue.reader.core.db.AppDatabase instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.yuanyue.reader.core.db.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.RssFeedDao rssFeedDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.RssArticleDao rssArticleDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.BookDao bookDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.BookSourceDao bookSourceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.OnlineBookDao onlineBookDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.OnlineChapterDao onlineChapterDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.CachedContentDao cachedContentDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.ComicSourceDao comicSourceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.OnlineComicDao onlineComicDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.yuanyue.reader.core.db.ComicChapterDao comicChapterDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/yuanyue/reader/core/db/AppDatabase$Companion;", "", "()V", "instance", "Lcom/yuanyue/reader/core/db/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yuanyue.reader.core.db.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}