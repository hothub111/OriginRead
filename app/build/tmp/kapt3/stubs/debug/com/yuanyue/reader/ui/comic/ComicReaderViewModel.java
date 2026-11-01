package com.yuanyue.reader.ui.comic;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020\nH\u0002J\u000e\u00100\u001a\u00020-2\u0006\u0010/\u001a\u00020\nJ\u0006\u00101\u001a\u00020-R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R7\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0013\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00128F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R/\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\b\u001a\u0004\u0018\u00010\u001a8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b \u0010\u0011\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR7\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020!0\t8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b%\u0010\u0011\u001a\u0004\b#\u0010\r\"\u0004\b$\u0010\u000fR+\u0010\'\u001a\u00020&2\u0006\u0010\b\u001a\u00020&8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b+\u0010\u0011\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/yuanyue/reader/ui/comic/ComicReaderViewModel;", "Landroidx/lifecycle/ViewModel;", "comicId", "", "chapterId", "repository", "Lcom/yuanyue/reader/data/ComicRepository;", "(JJLcom/yuanyue/reader/data/ComicRepository;)V", "<set-?>", "", "Lcom/yuanyue/reader/model/ComicChapterEntity;", "chapters", "getChapters", "()Ljava/util/List;", "setChapters", "(Ljava/util/List;)V", "chapters$delegate", "Landroidx/compose/runtime/MutableState;", "", "currentIndex", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "currentIndex$delegate", "Landroidx/compose/runtime/MutableIntState;", "", "error", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "error$delegate", "Lcom/yuanyue/reader/model/ComicImageItem;", "images", "getImages", "setImages", "images$delegate", "", "isLoading", "()Z", "setLoading", "(Z)V", "isLoading$delegate", "load", "", "loadImages", "chapter", "openChapter", "retry", "app_debug"})
public final class ComicReaderViewModel extends androidx.lifecycle.ViewModel {
    private final long comicId = 0L;
    private final long chapterId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.ComicRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState chapters$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableIntState currentIndex$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState images$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isLoading$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState error$delegate = null;
    
    public ComicReaderViewModel(long comicId, long chapterId, @org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.ComicRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.yuanyue.reader.model.ComicChapterEntity> getChapters() {
        return null;
    }
    
    private final void setChapters(java.util.List<com.yuanyue.reader.model.ComicChapterEntity> p0) {
    }
    
    public final int getCurrentIndex() {
        return 0;
    }
    
    private final void setCurrentIndex(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.yuanyue.reader.model.ComicImageItem> getImages() {
        return null;
    }
    
    private final void setImages(java.util.List<com.yuanyue.reader.model.ComicImageItem> p0) {
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
    com.yuanyue.reader.model.ComicChapterEntity chapter) {
    }
    
    public final void retry() {
    }
    
    private final void load() {
    }
    
    private final void loadImages(com.yuanyue.reader.model.ComicChapterEntity chapter) {
    }
}