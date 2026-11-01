package com.yuanyue.reader.ui.online;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lcom/yuanyue/reader/ui/online/BookSourceManagerViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/yuanyue/reader/data/OnlineBookRepository;", "(Lcom/yuanyue/reader/data/OnlineBookRepository;)V", "sources", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/yuanyue/reader/model/BookSourceEntity;", "getSources", "()Lkotlinx/coroutines/flow/StateFlow;", "addSource", "Lcom/yuanyue/reader/data/BookSourceAddResult;", "json", "", "skipValidation", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSource", "", "source", "(Lcom/yuanyue/reader/model/BookSourceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class BookSourceManagerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.OnlineBookRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.BookSourceEntity>> sources = null;
    
    public BookSourceManagerViewModel(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.OnlineBookRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.BookSourceEntity>> getSources() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSource(@org.jetbrains.annotations.NotNull()
    java.lang.String json, boolean skipValidation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.BookSourceAddResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteSource(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}