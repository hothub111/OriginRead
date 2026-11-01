package com.yuanyue.reader.ui.reader;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0082@\u00a2\u0006\u0002\u0010\f\u001a0\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007\u00a8\u0006\u000e"}, d2 = {"paginate", "", "Landroidx/compose/ui/text/AnnotatedString;", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "pageWidth", "", "pageHeight", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;IILandroidx/compose/ui/text/TextMeasurer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rememberPagedText", "app_debug"})
public final class TextPaginatorKt {
    
    /**
     * 把章节正文排版成整页。
     *
     * 关键点：
     * - 排版在 Dispatchers.Default 上执行，主线程只负责渲染，不会再因为大段文本卡死；
     * - 每次测量最多取一页多一点的文本（而不是measure整章/整本书），避免 O(n²) 卡住；
     * - 字号/行距/屏幕尺寸变化时会先做 120ms 防抖，拖动滑块不会疯狂重排。
     */
    @androidx.compose.runtime.Composable()
    @org.jetbrains.annotations.Nullable()
    public static final java.util.List<androidx.compose.ui.text.AnnotatedString> rememberPagedText(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.text.TextStyle style, int pageWidth, int pageHeight) {
        return null;
    }
    
    private static final java.lang.Object paginate(java.lang.String text, androidx.compose.ui.text.TextStyle style, int pageWidth, int pageHeight, androidx.compose.ui.text.TextMeasurer textMeasurer, kotlin.coroutines.Continuation<? super java.util.List<androidx.compose.ui.text.AnnotatedString>> $completion) {
        return null;
    }
}