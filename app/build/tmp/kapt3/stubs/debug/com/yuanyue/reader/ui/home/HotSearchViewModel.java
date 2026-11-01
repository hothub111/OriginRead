package com.yuanyue.reader.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\"H\u0002J\u0006\u00100\u001a\u00020.J\u000e\u00101\u001a\u00020.2\u0006\u0010/\u001a\u00020\"R/\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R+\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R7\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\r\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010#\u001a\u00020\"2\u0006\u0010\u0005\u001a\u00020\"8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b(\u0010\r\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R/\u0010)\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b,\u0010\r\u001a\u0004\b*\u0010\t\"\u0004\b+\u0010\u000b\u00a8\u00062"}, d2 = {"Lcom/yuanyue/reader/ui/home/HotSearchViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/yuanyue/reader/data/HotSearchRepository;", "(Lcom/yuanyue/reader/data/HotSearchRepository;)V", "<set-?>", "", "error", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "error$delegate", "Landroidx/compose/runtime/MutableState;", "", "fromCache", "getFromCache", "()Z", "setFromCache", "(Z)V", "fromCache$delegate", "isLoading", "setLoading", "isLoading$delegate", "", "Lcom/yuanyue/reader/data/HotSearchItem;", "items", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "items$delegate", "loadJob", "Lkotlinx/coroutines/Job;", "Lcom/yuanyue/reader/data/HotSearchPlatform;", "selectedPlatform", "getSelectedPlatform", "()Lcom/yuanyue/reader/data/HotSearchPlatform;", "setSelectedPlatform", "(Lcom/yuanyue/reader/data/HotSearchPlatform;)V", "selectedPlatform$delegate", "warning", "getWarning", "setWarning", "warning$delegate", "load", "", "platform", "retry", "selectPlatform", "app_debug"})
public final class HotSearchViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.yuanyue.reader.data.HotSearchRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState selectedPlatform$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState items$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isLoading$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState error$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState warning$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState fromCache$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job loadJob;
    
    public HotSearchViewModel(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.HotSearchRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.HotSearchPlatform getSelectedPlatform() {
        return null;
    }
    
    private final void setSelectedPlatform(com.yuanyue.reader.data.HotSearchPlatform p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.yuanyue.reader.data.HotSearchItem> getItems() {
        return null;
    }
    
    private final void setItems(java.util.List<com.yuanyue.reader.data.HotSearchItem> p0) {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getWarning() {
        return null;
    }
    
    private final void setWarning(java.lang.String p0) {
    }
    
    public final boolean getFromCache() {
        return false;
    }
    
    private final void setFromCache(boolean p0) {
    }
    
    public final void selectPlatform(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.HotSearchPlatform platform) {
    }
    
    public final void retry() {
    }
    
    private final void load(com.yuanyue.reader.data.HotSearchPlatform platform) {
    }
}