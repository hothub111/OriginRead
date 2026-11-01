package com.yuanyue.reader.ui.library;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0016J(\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/yuanyue/reader/ui/library/LibraryViewModel;", "Landroidx/lifecycle/ViewModel;", "libraryRepository", "Lcom/yuanyue/reader/data/LibraryRepository;", "(Lcom/yuanyue/reader/data/LibraryRepository;)V", "books", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/yuanyue/reader/model/BookEntity;", "getBooks", "()Lkotlinx/coroutines/flow/StateFlow;", "<set-?>", "", "isImporting", "()Z", "setImporting", "(Z)V", "isImporting$delegate", "Landroidx/compose/runtime/MutableState;", "deleteBook", "", "book", "(Lcom/yuanyue/reader/model/BookEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importBook", "Lcom/yuanyue/reader/data/OperationResult;", "", "uri", "Landroid/net/Uri;", "name", "", "(Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LibraryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.LibraryRepository libraryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isImporting$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.BookEntity>> books = null;
    
    public LibraryViewModel(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.LibraryRepository libraryRepository) {
        super();
    }
    
    public final boolean isImporting() {
        return false;
    }
    
    private final void setImporting(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.BookEntity>> getBooks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importBook(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.OperationResult<java.lang.Long>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBook(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}