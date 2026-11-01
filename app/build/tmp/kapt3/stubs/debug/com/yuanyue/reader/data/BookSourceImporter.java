package com.yuanyue.reader.data;

/**
 * 书源 JSON 解析。
 *
 * 同时兼容两种常见格式：
 * - 本应用早期的扁平字段（bookSourceName / bookList / chapterList / content ...）；
 * - Legado「阅读 3.0」的嵌套字段（ruleSearch / ruleBookInfo / ruleToc / ruleContent）。
 * 之前只认扁平字段，粘贴标准书源 JSON 时会报「缺少书源名称」，看起来像 App 出错。
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\b\"\u00020\u0004H\u0002\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u000f\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/yuanyue/reader/data/BookSourceImporter;", "", "()V", "firstString", "", "root", "Lorg/json/JSONObject;", "keys", "", "(Lorg/json/JSONObject;[Ljava/lang/String;)Ljava/lang/String;", "fromJson", "Lcom/yuanyue/reader/model/BookSourceEntity;", "json", "missingFields", "", "source", "toJson", "app_debug"})
public final class BookSourceImporter {
    @org.jetbrains.annotations.NotNull()
    public static final com.yuanyue.reader.data.BookSourceImporter INSTANCE = null;
    
    private BookSourceImporter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.model.BookSourceEntity fromJson(@org.jetbrains.annotations.NotNull()
    java.lang.String json) {
        return null;
    }
    
    /**
     * 返回书源缺少的必要字段（用于添加前的结构检查）。
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> missingFields(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toJson(@org.jetbrains.annotations.NotNull()
    com.yuanyue.reader.model.BookSourceEntity source) {
        return null;
    }
    
    private final java.lang.String firstString(org.json.JSONObject root, java.lang.String... keys) {
        return null;
    }
}