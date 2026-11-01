package com.yuanyue.reader.ui.online;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u0002032\u0006\u00105\u001a\u00020\u0012H\u0002J\u000e\u00106\u001a\u0002032\u0006\u00105\u001a\u00020\u0012J\u0006\u00107\u001a\u000203R/\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R7\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R+\u0010\u001a\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00198F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR+\u0010!\u001a\u00020 2\u0006\u0010\b\u001a\u00020 8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b&\u0010\'\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R/\u0010(\u001a\u0004\u0018\u00010\u00192\b\u0010\b\u001a\u0004\u0018\u00010\u00198F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b+\u0010\u0010\u001a\u0004\b)\u0010\u001c\"\u0004\b*\u0010\u001eR+\u0010-\u001a\u00020,2\u0006\u0010\b\u001a\u00020,8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b1\u0010\u0010\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/yuanyue/reader/ui/online/OnlineReaderViewModel;", "Landroidx/lifecycle/ViewModel;", "bookId", "", "chapterId", "repository", "Lcom/yuanyue/reader/data/OnlineBookRepository;", "(JJLcom/yuanyue/reader/data/OnlineBookRepository;)V", "<set-?>", "Lcom/yuanyue/reader/model/OnlineBookEntity;", "book", "getBook", "()Lcom/yuanyue/reader/model/OnlineBookEntity;", "setBook", "(Lcom/yuanyue/reader/model/OnlineBookEntity;)V", "book$delegate", "Landroidx/compose/runtime/MutableState;", "", "Lcom/yuanyue/reader/model/OnlineChapterEntity;", "chapters", "getChapters", "()Ljava/util/List;", "setChapters", "(Ljava/util/List;)V", "chapters$delegate", "", "content", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "content$delegate", "", "currentIndex", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "currentIndex$delegate", "Landroidx/compose/runtime/MutableIntState;", "error", "getError", "setError", "error$delegate", "", "isLoading", "()Z", "setLoading", "(Z)V", "isLoading$delegate", "load", "", "loadContent", "chapter", "openChapter", "retry", "app_debug"})
public final class OnlineReaderViewModel extends androidx.lifecycle.ViewModel {
    private final long bookId = 0L;
    private final long chapterId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.OnlineBookRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState book$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState chapters$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableIntState currentIndex$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState content$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isLoading$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState error$delegate = null;
    
    public OnlineReaderViewModel(long bookId, long chapterId, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.OnlineBookRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.yuanyue.reader.model.OnlineBookEntity getBook() {
        return null;
    }
    
    private final void setBook(com.yuanyue.reader.model.OnlineBookEntity p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.yuanyue.reader.model.OnlineChapterEntity> getChapters() {
        return null;
    }
    
    private final void setChapters(java.util.List<com.yuanyue.reader.model.OnlineChapterEntity> p0) {
    }
    
    public final int getCurrentIndex() {
        return 0;
    }
    
    private final void setCurrentIndex(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContent() {
        return null;
    }
    
    private final void setContent(java.lang.String p0) {
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    private final void setLoading(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    private final void setError(java.lang.String p0) {
    }
    
    public final void openChapter(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.OnlineChapterEntity chapter) {
    }
    
    public final void retry() {
    }
    
    private final void load() {
    }
    
    private final void loadContent(com.yuanyue.reader.model.OnlineChapterEntity chapter) {
    }
}