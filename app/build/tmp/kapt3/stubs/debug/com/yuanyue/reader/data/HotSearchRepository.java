package com.yuanyue.reader.data;

/**
 * 热榜抓取。
 *
 * 各平台接口在风控时经常返回 200 + 空数据 / HTML 登录页，之前会直接得到一个空列表，
 * 界面因此出现“白屏”。这里统一做三件事：
 * 1. 每个平台准备备用接口，主接口失败时自动切换；
 * 2. 空数据视为失败，不再当成“没有热搜”；
 * 3. 失败时回退到上次成功缓存，并附上提示，保证界面始终有内容或明确错误。
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 ;2\u00020\u0001:\u0002:;B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\u001fH\u0002J\u0018\u0010\"\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010#\u001a\u00020$H\u0002J\u001c\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010&\u001a\u00020\bH\u0002J*\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010+\u001a\u00020,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00180.H\u0002J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u00100\u001a\u00020\bH\u0002J\n\u00101\u001a\u0004\u0018\u000102H\u0002J\u0012\u00103\u001a\u0004\u0018\u0001042\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u00105\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u001e\u00106\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u0018\u00108\u001a\u00020\b2\u0006\u00109\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/yuanyue/reader/data/HotSearchRepository;", "", "client", "Lokhttp3/OkHttpClient;", "context", "Landroid/content/Context;", "(Lokhttp3/OkHttpClient;Landroid/content/Context;)V", "baiduSearchUrl", "", "title", "cacheKey", "platform", "Lcom/yuanyue/reader/data/HotSearchPlatform;", "collectWordObjects", "", "node", "out", "", "Lorg/json/JSONObject;", "fetch", "Lcom/yuanyue/reader/data/HotSearchResult;", "(Lcom/yuanyue/reader/data/HotSearchPlatform;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchBaidu", "", "Lcom/yuanyue/reader/data/HotSearchItem;", "fetchBilibili", "fetchWeibo", "fetchWeiboMobile", "fetchZhihu", "formatNumber", "value", "", "formatTime", "time", "friendlyError", "error", "", "get", "url", "referer", "isHttpUrl", "", "parseArray", "array", "Lorg/json/JSONArray;", "transform", "Lkotlin/Function1;", "parseBaidu", "body", "prefs", "Landroid/content/SharedPreferences;", "readCache", "Lcom/yuanyue/reader/data/HotSearchRepository$CachedHotSearch;", "weiboSearchUrl", "writeCache", "items", "zhihuFallbackUrl", "id", "CachedHotSearch", "Companion", "app_debug"})
public final class HotSearchRepository {
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.Nullable()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String PREFS_NAME = "hot_search_cache";
    @org.jetbrains.annotations.NotNull()
    private static final com.yuanyue.reader.data.HotSearchRepository.Companion Companion = null;
    
    public HotSearchRepository(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient client, @org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetch(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.data.HotSearchPlatform platform, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yuanyue.reader.data.HotSearchResult> $completion) {
        return null;
    }
    
    private final java.util.List<com.yuanyue.reader.data.HotSearchItem> fetchWeibo() {
        return null;
    }
    
    private final java.util.List<com.yuanyue.reader.data.HotSearchItem> fetchWeiboMobile() {
        return null;
    }
    
    private final java.util.List<com.yuanyue.reader.data.HotSearchItem> fetchBaidu() {
        return null;
    }
    
    private final java.util.List<com.yuanyue.reader.data.HotSearchItem> parseBaidu(java.lang.String body) {
        return null;
    }
    
    /**
     * 百度接口把榜单放在 cards[].content[].content[] 这类多层数组里，这里递归取出带 word 的对象。
     */
    private final void collectWordObjects(java.lang.Object node, java.util.List<org.json.JSONObject> out) {
    }
    
    private final java.util.List<com.yuanyue.reader.data.HotSearchItem> fetchZhihu() {
        return null;
    }
    
    private final java.util.List<com.yuanyue.reader.data.HotSearchItem> fetchBilibili() {
        return null;
    }
    
    private final java.util.List<com.yuanyue.reader.data.HotSearchItem> parseArray(org.json.JSONArray array, kotlin.jvm.functions.Function1<? super org.json.JSONObject, com.yuanyue.reader.data.HotSearchItem> transform) {
        return null;
    }
    
    private final java.lang.String get(java.lang.String url, java.lang.String referer) {
        return null;
    }
    
    private final java.lang.String friendlyError(com.yuanyue.reader.data.HotSearchPlatform platform, java.lang.Throwable error) {
        return null;
    }
    
    private final java.lang.String formatNumber(long value) {
        return null;
    }
    
    private final boolean isHttpUrl(java.lang.String url) {
        return false;
    }
    
    private final java.lang.String weiboSearchUrl(java.lang.String title) {
        return null;
    }
    
    private final java.lang.String baiduSearchUrl(java.lang.String title) {
        return null;
    }
    
    private final java.lang.String zhihuFallbackUrl(java.lang.String id, java.lang.String title) {
        return null;
    }
    
    private final android.content.SharedPreferences prefs() {
        return null;
    }
    
    private final com.yuanyue.reader.data.HotSearchRepository.CachedHotSearch readCache(com.yuanyue.reader.data.HotSearchPlatform platform) {
        return null;
    }
    
    private final void writeCache(com.yuanyue.reader.data.HotSearchPlatform platform, java.util.List<com.yuanyue.reader.data.HotSearchItem> items) {
    }
    
    private final java.lang.String cacheKey(com.yuanyue.reader.data.HotSearchPlatform platform) {
        return null;
    }
    
    private final java.lang.String formatTime(long time) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/yuanyue/reader/data/HotSearchRepository$CachedHotSearch;", "", "items", "", "Lcom/yuanyue/reader/data/HotSearchItem;", "time", "", "(Ljava/util/List;J)V", "getItems", "()Ljava/util/List;", "getTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    static final class CachedHotSearch {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.yuanyue.reader.data.HotSearchItem> items = null;
        private final long time = 0L;
        
        public CachedHotSearch(@org.jetbrains.annotations.NotNull()
        java.util.List<com.yuanyue.reader.data.HotSearchItem> items, long time) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.yuanyue.reader.data.HotSearchItem> getItems() {
            return null;
        }
        
        public final long getTime() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.yuanyue.reader.data.HotSearchItem> component1() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yuanyue.reader.data.HotSearchRepository.CachedHotSearch copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.yuanyue.reader.data.HotSearchItem> items, long time) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/yuanyue/reader/data/HotSearchRepository$Companion;", "", "()V", "PREFS_NAME", "", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}