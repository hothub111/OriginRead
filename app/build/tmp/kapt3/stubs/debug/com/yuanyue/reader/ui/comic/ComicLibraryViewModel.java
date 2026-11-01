package com.yuanyue.reader.ui.comic;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u000fJ\u000e\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020 R7\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR/\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u000f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R+\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00168F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R7\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u000e\u001a\u0004\b\u001d\u0010\n\"\u0004\b\u001e\u0010\fR/\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u0005\u001a\u0004\u0018\u00010 8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b&\u0010\u000e\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00060(\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+\u00a8\u00061"}, d2 = {"Lcom/yuanyue/reader/ui/comic/ComicLibraryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/yuanyue/reader/data/ComicRepository;", "(Lcom/yuanyue/reader/data/ComicRepository;)V", "<set-?>", "", "Lcom/yuanyue/reader/model/OnlineComicEntity;", "comics", "getComics", "()Ljava/util/List;", "setComics", "(Ljava/util/List;)V", "comics$delegate", "Landroidx/compose/runtime/MutableState;", "", "error", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "error$delegate", "", "isSearching", "()Z", "setSearching", "(Z)V", "isSearching$delegate", "searchResults", "getSearchResults", "setSearchResults", "searchResults$delegate", "", "selectedSourceId", "getSelectedSourceId", "()Ljava/lang/Long;", "setSelectedSourceId", "(Ljava/lang/Long;)V", "selectedSourceId$delegate", "sources", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/yuanyue/reader/model/ComicSourceEntity;", "getSources", "()Lkotlinx/coroutines/flow/StateFlow;", "search", "", "keyword", "selectSource", "sourceId", "app_debug"})
public final class ComicLibraryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.ComicRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.ComicSourceEntity>> sources = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState selectedSourceId$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState comics$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState searchResults$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isSearching$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState error$delegate = null;
    
    public ComicLibraryViewModel(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.ComicRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.yuanyue.reader.model.ComicSourceEntity>> getSources() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getSelectedSourceId() {
        return null;
    }
    
    private final void setSelectedSourceId(java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.yuanyue.reader.model.OnlineComicEntity> getComics() {
        return null;
    }
    
    private final void setComics(java.util.List<com.yuanyue.reader.model.OnlineComicEntity> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.yuanyue.reader.model.OnlineComicEntity> getSearchResults() {
        return null;
    }
    
    private final void setSearchResults(java.util.List<com.yuanyue.reader.model.OnlineComicEntity> p0) {
    }
    
    public final boolean isSearching() {
        return false;
    }
    
    private final void setSearching(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    private final void setError(java.lang.String p0) {
    }
    
    public final void selectSource(long sourceId) {
    }
    
    public final void search(@org.jetbrains.annotations.NotNull()
    java.lang.String keyword) {
    }
}