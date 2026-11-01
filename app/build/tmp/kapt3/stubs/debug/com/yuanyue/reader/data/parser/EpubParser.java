package com.yuanyue.reader.data.parser;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bJ\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u00a8\u0006\u001a"}, d2 = {"Lcom/yuanyue/reader/data/parser/EpubParser;", "", "()V", "extractCover", "", "zip", "Ljava/util/zip/ZipFile;", "baseDir", "candidates", "", "outputDir", "Ljava/io/File;", "extractText", "element", "Lorg/jsoup/nodes/Element;", "findOpfPath", "firstText", "root", "Lorg/w3c/dom/Element;", "localName", "parse", "Lcom/yuanyue/reader/data/parser/ParsedEpub;", "file", "coverOutputDir", "resolveHref", "href", "app_debug"})
public final class EpubParser {
    
    public EpubParser() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.yuanyue.reader.data.parser.ParsedEpub parse(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    java.io.File coverOutputDir) {
        return null;
    }
    
    private final java.lang.String findOpfPath(java.util.zip.ZipFile zip) {
        return null;
    }
    
    private final java.lang.String extractCover(java.util.zip.ZipFile zip, java.lang.String baseDir, java.util.Map<java.lang.String, java.lang.String> candidates, java.io.File outputDir) {
        return null;
    }
    
    private final java.lang.String firstText(org.w3c.dom.Element root, java.lang.String localName) {
        return null;
    }
    
    private final java.lang.String extractText(org.jsoup.nodes.Element element) {
        return null;
    }
    
    private final java.lang.String resolveHref(java.lang.String baseDir, java.lang.String href) {
        return null;
    }
}