package com.yuanyue.reader.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J(\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u001b2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\t2\u0006\u0010\u0014\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\b2\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\"\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001dH\u0002J6\u0010%\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020\'H\u0086@\u00a2\u0006\u0002\u0010+R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/yuanyue/reader/data/LibraryRepository;", "", "context", "Landroid/content/Context;", "bookDao", "Lcom/yuanyue/reader/core/db/BookDao;", "(Landroid/content/Context;Lcom/yuanyue/reader/core/db/BookDao;)V", "books", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/yuanyue/reader/model/BookEntity;", "getBooks", "()Lkotlinx/coroutines/flow/Flow;", "copyUriToFile", "", "uri", "Landroid/net/Uri;", "dest", "Ljava/io/File;", "deleteBook", "book", "(Lcom/yuanyue/reader/model/BookEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBook", "bookId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importBook", "Lcom/yuanyue/reader/data/OperationResult;", "suggestedName", "", "(Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadChapters", "Lcom/yuanyue/reader/model/BookChapter;", "observeBook", "queryDisplayName", "sanitizeFileName", "name", "updateProgress", "chapterIndex", "", "chapterCount", "pageIndex", "pageCount", "(JIIIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LibraryRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.core.db.BookDao bookDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.BookEntity>> books = null;
    
    public LibraryRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.core.db.BookDao bookDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.yuanyue.reader.model.BookEntity>> getBooks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.yuanyue.reader.model.BookEntity> observeBook(long bookId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBook(long bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.model.BookEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importBook(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    java.lang.String suggestedName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<java.lang.Long>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadChapters(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.yuanyue.reader.model.BookChapter>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateProgress(long bookId, int chapterIndex, int chapterCount, int pageIndex, int pageCount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBook(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.String queryDisplayName(android.net.Uri uri) {
        return null;
    }
    
    private final void copyUriToFile(android.net.Uri uri, java.io.File dest) {
    }
    
    private final java.lang.String sanitizeFileName(java.lang.String name) {
        return null;
    }
}