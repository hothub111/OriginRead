package com.yuanyue.reader.ui.comic;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010%\u001a\u00020&H\u0002J\u0006\u0010\'\u001a\u00020&R7\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR/\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0007\u001a\u0004\u0018\u00010\u00118F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0007\u001a\u0004\u0018\u00010\u00188F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u0010\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR+\u0010 \u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00020\u001f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b$\u0010\u0010\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/yuanyue/reader/ui/comic/ComicDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "comicId", "", "repository", "Lcom/yuanyue/reader/data/ComicRepository;", "(JLcom/yuanyue/reader/data/ComicRepository;)V", "<set-?>", "", "Lcom/yuanyue/reader/model/ComicChapterEntity;", "chapters", "getChapters", "()Ljava/util/List;", "setChapters", "(Ljava/util/List;)V", "chapters$delegate", "Landroidx/compose/runtime/MutableState;", "Lcom/yuanyue/reader/model/OnlineComicEntity;", "comic", "getComic", "()Lcom/yuanyue/reader/model/OnlineComicEntity;", "setComic", "(Lcom/yuanyue/reader/model/OnlineComicEntity;)V", "comic$delegate", "", "error", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "error$delegate", "", "isLoading", "()Z", "setLoading", "(Z)V", "isLoading$delegate", "load", "", "retry", "app_debug"})
public final class ComicDetailViewModel extends androidx.lifecycle.ViewModel {
    private final long comicId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.ComicRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState comic$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState chapters$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isLoading$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState error$delegate = null;
    
    public ComicDetailViewModel(long comicId, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.ComicRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.yuanyue.reader.model.OnlineComicEntity getComic() {
        return null;
    }
    
    private final void setComic(com.yuanyue.reader.model.OnlineComicEntity p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.yuanyue.reader.model.ComicChapterEntity> getChapters() {
        return null;
    }
    
    private final void setChapters(java.util.List<com.yuanyue.reader.model.ComicChapterEntity> p0) {
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
    
    public final void retry() {
    }
    
    private final void load() {
    }
}