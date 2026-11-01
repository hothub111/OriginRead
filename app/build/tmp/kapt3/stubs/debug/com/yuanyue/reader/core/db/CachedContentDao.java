package com.yuanyue.reader.core.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/yuanyue/reader/core/db/CachedContentDao;", "", "deleteByChapters", "", "chapterIds", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "", "chapterId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "content", "Lcom/yuanyue/reader/model/CachedChapterContentEntity;", "(Lcom/yuanyue/reader/model/CachedChapterContentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface CachedContentDao {
    
    @androidx.room.Query(value = "SELECT content FROM cached_chapter_content WHERE chapterId = :chapterId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object get(long chapterId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.CachedChapterContentEntity content, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM cached_chapter_content WHERE chapterId IN (:chapterIds)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteByChapters(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> chapterIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}