package com.yuanyue.reader.core.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile RssFeedDao _rssFeedDao;

  private volatile RssArticleDao _rssArticleDao;

  private volatile BookDao _bookDao;

  private volatile BookSourceDao _bookSourceDao;

  private volatile OnlineBookDao _onlineBookDao;

  private volatile OnlineChapterDao _onlineChapterDao;

  private volatile CachedContentDao _cachedContentDao;

  private volatile ComicSourceDao _comicSourceDao;

  private volatile OnlineComicDao _onlineComicDao;

  private volatile ComicChapterDao _comicChapterDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `rss_feeds` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `url` TEXT NOT NULL, `description` TEXT, `siteUrl` TEXT, `faviconUrl` TEXT, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `rss_articles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `feedId` INTEGER NOT NULL, `guid` TEXT NOT NULL, `title` TEXT NOT NULL, `link` TEXT, `description` TEXT, `contentHtml` TEXT, `author` TEXT, `publishedAt` INTEGER, `isRead` INTEGER NOT NULL, `isFavorite` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_rss_articles_feedId_guid` ON `rss_articles` (`feedId`, `guid`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `books` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `author` TEXT, `localPath` TEXT NOT NULL, `fileName` TEXT NOT NULL, `format` TEXT NOT NULL, `coverPath` TEXT, `lastReadPosition` INTEGER NOT NULL, `totalPages` INTEGER NOT NULL, `progressPercent` REAL NOT NULL, `addedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `book_sources` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `searchUrl` TEXT, `searchListRule` TEXT, `bookNameRule` TEXT, `bookAuthorRule` TEXT, `bookCoverRule` TEXT, `bookUrlRule` TEXT, `introRule` TEXT, `coverRule` TEXT, `tocUrlRule` TEXT, `chapterListRule` TEXT, `chapterNameRule` TEXT, `chapterUrlRule` TEXT, `contentRule` TEXT, `contentReplaceRules` TEXT, `enabled` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `online_books` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `sourceId` INTEGER NOT NULL, `title` TEXT NOT NULL, `author` TEXT, `coverUrl` TEXT, `bookUrl` TEXT NOT NULL, `intro` TEXT, `latestChapter` TEXT, `cachedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_online_books_sourceId_bookUrl` ON `online_books` (`sourceId`, `bookUrl`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `online_chapters` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `bookId` INTEGER NOT NULL, `sourceId` INTEGER NOT NULL, `title` TEXT NOT NULL, `url` TEXT NOT NULL, `chapterIndex` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_online_chapters_bookId_url` ON `online_chapters` (`bookId`, `url`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `cached_chapter_content` (`chapterId` INTEGER NOT NULL, `content` TEXT NOT NULL, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`chapterId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `comic_sources` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `searchUrl` TEXT, `searchListRule` TEXT, `comicTitleRule` TEXT, `comicUrlRule` TEXT, `comicCoverRule` TEXT, `chapterListRule` TEXT, `chapterNameRule` TEXT, `chapterUrlRule` TEXT, `imageListRule` TEXT, `imageUrlRule` TEXT, `enabled` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `online_comics` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `sourceId` INTEGER NOT NULL, `title` TEXT NOT NULL, `author` TEXT, `coverUrl` TEXT, `comicUrl` TEXT NOT NULL, `intro` TEXT, `cachedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_online_comics_sourceId_comicUrl` ON `online_comics` (`sourceId`, `comicUrl`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `comic_chapters` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `comicId` INTEGER NOT NULL, `sourceId` INTEGER NOT NULL, `title` TEXT NOT NULL, `url` TEXT NOT NULL, `chapterIndex` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_comic_chapters_comicId_url` ON `comic_chapters` (`comicId`, `url`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5f71323698092d80d710d71da1527b7b')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `rss_feeds`");
        db.execSQL("DROP TABLE IF EXISTS `rss_articles`");
        db.execSQL("DROP TABLE IF EXISTS `books`");
        db.execSQL("DROP TABLE IF EXISTS `book_sources`");
        db.execSQL("DROP TABLE IF EXISTS `online_books`");
        db.execSQL("DROP TABLE IF EXISTS `online_chapters`");
        db.execSQL("DROP TABLE IF EXISTS `cached_chapter_content`");
        db.execSQL("DROP TABLE IF EXISTS `comic_sources`");
        db.execSQL("DROP TABLE IF EXISTS `online_comics`");
        db.execSQL("DROP TABLE IF EXISTS `comic_chapters`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRssFeeds = new HashMap<String, TableInfo.Column>(7);
        _columnsRssFeeds.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssFeeds.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssFeeds.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssFeeds.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssFeeds.put("siteUrl", new TableInfo.Column("siteUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssFeeds.put("faviconUrl", new TableInfo.Column("faviconUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssFeeds.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRssFeeds = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRssFeeds = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRssFeeds = new TableInfo("rss_feeds", _columnsRssFeeds, _foreignKeysRssFeeds, _indicesRssFeeds);
        final TableInfo _existingRssFeeds = TableInfo.read(db, "rss_feeds");
        if (!_infoRssFeeds.equals(_existingRssFeeds)) {
          return new RoomOpenHelper.ValidationResult(false, "rss_feeds(com.yuanyue.reader.model.RssFeedEntity).\n"
                  + " Expected:\n" + _infoRssFeeds + "\n"
                  + " Found:\n" + _existingRssFeeds);
        }
        final HashMap<String, TableInfo.Column> _columnsRssArticles = new HashMap<String, TableInfo.Column>(11);
        _columnsRssArticles.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("feedId", new TableInfo.Column("feedId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("guid", new TableInfo.Column("guid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("link", new TableInfo.Column("link", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("contentHtml", new TableInfo.Column("contentHtml", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("author", new TableInfo.Column("author", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("publishedAt", new TableInfo.Column("publishedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("isRead", new TableInfo.Column("isRead", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRssArticles.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRssArticles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRssArticles = new HashSet<TableInfo.Index>(1);
        _indicesRssArticles.add(new TableInfo.Index("index_rss_articles_feedId_guid", true, Arrays.asList("feedId", "guid"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoRssArticles = new TableInfo("rss_articles", _columnsRssArticles, _foreignKeysRssArticles, _indicesRssArticles);
        final TableInfo _existingRssArticles = TableInfo.read(db, "rss_articles");
        if (!_infoRssArticles.equals(_existingRssArticles)) {
          return new RoomOpenHelper.ValidationResult(false, "rss_articles(com.yuanyue.reader.model.RssArticleEntity).\n"
                  + " Expected:\n" + _infoRssArticles + "\n"
                  + " Found:\n" + _existingRssArticles);
        }
        final HashMap<String, TableInfo.Column> _columnsBooks = new HashMap<String, TableInfo.Column>(11);
        _columnsBooks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("author", new TableInfo.Column("author", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("localPath", new TableInfo.Column("localPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("fileName", new TableInfo.Column("fileName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("format", new TableInfo.Column("format", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("coverPath", new TableInfo.Column("coverPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("lastReadPosition", new TableInfo.Column("lastReadPosition", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("totalPages", new TableInfo.Column("totalPages", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("progressPercent", new TableInfo.Column("progressPercent", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBooks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBooks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBooks = new TableInfo("books", _columnsBooks, _foreignKeysBooks, _indicesBooks);
        final TableInfo _existingBooks = TableInfo.read(db, "books");
        if (!_infoBooks.equals(_existingBooks)) {
          return new RoomOpenHelper.ValidationResult(false, "books(com.yuanyue.reader.model.BookEntity).\n"
                  + " Expected:\n" + _infoBooks + "\n"
                  + " Found:\n" + _existingBooks);
        }
        final HashMap<String, TableInfo.Column> _columnsBookSources = new HashMap<String, TableInfo.Column>(19);
        _columnsBookSources.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("searchUrl", new TableInfo.Column("searchUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("searchListRule", new TableInfo.Column("searchListRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("bookNameRule", new TableInfo.Column("bookNameRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("bookAuthorRule", new TableInfo.Column("bookAuthorRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("bookCoverRule", new TableInfo.Column("bookCoverRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("bookUrlRule", new TableInfo.Column("bookUrlRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("introRule", new TableInfo.Column("introRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("coverRule", new TableInfo.Column("coverRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("tocUrlRule", new TableInfo.Column("tocUrlRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("chapterListRule", new TableInfo.Column("chapterListRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("chapterNameRule", new TableInfo.Column("chapterNameRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("chapterUrlRule", new TableInfo.Column("chapterUrlRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("contentRule", new TableInfo.Column("contentRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("contentReplaceRules", new TableInfo.Column("contentReplaceRules", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookSources.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBookSources = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBookSources = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBookSources = new TableInfo("book_sources", _columnsBookSources, _foreignKeysBookSources, _indicesBookSources);
        final TableInfo _existingBookSources = TableInfo.read(db, "book_sources");
        if (!_infoBookSources.equals(_existingBookSources)) {
          return new RoomOpenHelper.ValidationResult(false, "book_sources(com.yuanyue.reader.model.BookSourceEntity).\n"
                  + " Expected:\n" + _infoBookSources + "\n"
                  + " Found:\n" + _existingBookSources);
        }
        final HashMap<String, TableInfo.Column> _columnsOnlineBooks = new HashMap<String, TableInfo.Column>(9);
        _columnsOnlineBooks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("sourceId", new TableInfo.Column("sourceId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("author", new TableInfo.Column("author", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("coverUrl", new TableInfo.Column("coverUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("bookUrl", new TableInfo.Column("bookUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("intro", new TableInfo.Column("intro", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("latestChapter", new TableInfo.Column("latestChapter", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineBooks.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOnlineBooks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOnlineBooks = new HashSet<TableInfo.Index>(1);
        _indicesOnlineBooks.add(new TableInfo.Index("index_online_books_sourceId_bookUrl", true, Arrays.asList("sourceId", "bookUrl"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoOnlineBooks = new TableInfo("online_books", _columnsOnlineBooks, _foreignKeysOnlineBooks, _indicesOnlineBooks);
        final TableInfo _existingOnlineBooks = TableInfo.read(db, "online_books");
        if (!_infoOnlineBooks.equals(_existingOnlineBooks)) {
          return new RoomOpenHelper.ValidationResult(false, "online_books(com.yuanyue.reader.model.OnlineBookEntity).\n"
                  + " Expected:\n" + _infoOnlineBooks + "\n"
                  + " Found:\n" + _existingOnlineBooks);
        }
        final HashMap<String, TableInfo.Column> _columnsOnlineChapters = new HashMap<String, TableInfo.Column>(6);
        _columnsOnlineChapters.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineChapters.put("bookId", new TableInfo.Column("bookId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineChapters.put("sourceId", new TableInfo.Column("sourceId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineChapters.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineChapters.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineChapters.put("chapterIndex", new TableInfo.Column("chapterIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOnlineChapters = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOnlineChapters = new HashSet<TableInfo.Index>(1);
        _indicesOnlineChapters.add(new TableInfo.Index("index_online_chapters_bookId_url", true, Arrays.asList("bookId", "url"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoOnlineChapters = new TableInfo("online_chapters", _columnsOnlineChapters, _foreignKeysOnlineChapters, _indicesOnlineChapters);
        final TableInfo _existingOnlineChapters = TableInfo.read(db, "online_chapters");
        if (!_infoOnlineChapters.equals(_existingOnlineChapters)) {
          return new RoomOpenHelper.ValidationResult(false, "online_chapters(com.yuanyue.reader.model.OnlineChapterEntity).\n"
                  + " Expected:\n" + _infoOnlineChapters + "\n"
                  + " Found:\n" + _existingOnlineChapters);
        }
        final HashMap<String, TableInfo.Column> _columnsCachedChapterContent = new HashMap<String, TableInfo.Column>(3);
        _columnsCachedChapterContent.put("chapterId", new TableInfo.Column("chapterId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedChapterContent.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedChapterContent.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCachedChapterContent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCachedChapterContent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCachedChapterContent = new TableInfo("cached_chapter_content", _columnsCachedChapterContent, _foreignKeysCachedChapterContent, _indicesCachedChapterContent);
        final TableInfo _existingCachedChapterContent = TableInfo.read(db, "cached_chapter_content");
        if (!_infoCachedChapterContent.equals(_existingCachedChapterContent)) {
          return new RoomOpenHelper.ValidationResult(false, "cached_chapter_content(com.yuanyue.reader.model.CachedChapterContentEntity).\n"
                  + " Expected:\n" + _infoCachedChapterContent + "\n"
                  + " Found:\n" + _existingCachedChapterContent);
        }
        final HashMap<String, TableInfo.Column> _columnsComicSources = new HashMap<String, TableInfo.Column>(15);
        _columnsComicSources.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("searchUrl", new TableInfo.Column("searchUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("searchListRule", new TableInfo.Column("searchListRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("comicTitleRule", new TableInfo.Column("comicTitleRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("comicUrlRule", new TableInfo.Column("comicUrlRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("comicCoverRule", new TableInfo.Column("comicCoverRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("chapterListRule", new TableInfo.Column("chapterListRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("chapterNameRule", new TableInfo.Column("chapterNameRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("chapterUrlRule", new TableInfo.Column("chapterUrlRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("imageListRule", new TableInfo.Column("imageListRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("imageUrlRule", new TableInfo.Column("imageUrlRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicSources.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComicSources = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComicSources = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComicSources = new TableInfo("comic_sources", _columnsComicSources, _foreignKeysComicSources, _indicesComicSources);
        final TableInfo _existingComicSources = TableInfo.read(db, "comic_sources");
        if (!_infoComicSources.equals(_existingComicSources)) {
          return new RoomOpenHelper.ValidationResult(false, "comic_sources(com.yuanyue.reader.model.ComicSourceEntity).\n"
                  + " Expected:\n" + _infoComicSources + "\n"
                  + " Found:\n" + _existingComicSources);
        }
        final HashMap<String, TableInfo.Column> _columnsOnlineComics = new HashMap<String, TableInfo.Column>(8);
        _columnsOnlineComics.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineComics.put("sourceId", new TableInfo.Column("sourceId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineComics.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineComics.put("author", new TableInfo.Column("author", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineComics.put("coverUrl", new TableInfo.Column("coverUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineComics.put("comicUrl", new TableInfo.Column("comicUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineComics.put("intro", new TableInfo.Column("intro", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOnlineComics.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOnlineComics = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOnlineComics = new HashSet<TableInfo.Index>(1);
        _indicesOnlineComics.add(new TableInfo.Index("index_online_comics_sourceId_comicUrl", true, Arrays.asList("sourceId", "comicUrl"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoOnlineComics = new TableInfo("online_comics", _columnsOnlineComics, _foreignKeysOnlineComics, _indicesOnlineComics);
        final TableInfo _existingOnlineComics = TableInfo.read(db, "online_comics");
        if (!_infoOnlineComics.equals(_existingOnlineComics)) {
          return new RoomOpenHelper.ValidationResult(false, "online_comics(com.yuanyue.reader.model.OnlineComicEntity).\n"
                  + " Expected:\n" + _infoOnlineComics + "\n"
                  + " Found:\n" + _existingOnlineComics);
        }
        final HashMap<String, TableInfo.Column> _columnsComicChapters = new HashMap<String, TableInfo.Column>(6);
        _columnsComicChapters.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicChapters.put("comicId", new TableInfo.Column("comicId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicChapters.put("sourceId", new TableInfo.Column("sourceId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicChapters.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicChapters.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComicChapters.put("chapterIndex", new TableInfo.Column("chapterIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComicChapters = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComicChapters = new HashSet<TableInfo.Index>(1);
        _indicesComicChapters.add(new TableInfo.Index("index_comic_chapters_comicId_url", true, Arrays.asList("comicId", "url"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoComicChapters = new TableInfo("comic_chapters", _columnsComicChapters, _foreignKeysComicChapters, _indicesComicChapters);
        final TableInfo _existingComicChapters = TableInfo.read(db, "comic_chapters");
        if (!_infoComicChapters.equals(_existingComicChapters)) {
          return new RoomOpenHelper.ValidationResult(false, "comic_chapters(com.yuanyue.reader.model.ComicChapterEntity).\n"
                  + " Expected:\n" + _infoComicChapters + "\n"
                  + " Found:\n" + _existingComicChapters);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "5f71323698092d80d710d71da1527b7b", "a71eb102e14454e73b707279d3738d02");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "rss_feeds","rss_articles","books","book_sources","online_books","online_chapters","cached_chapter_content","comic_sources","online_comics","comic_chapters");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `rss_feeds`");
      _db.execSQL("DELETE FROM `rss_articles`");
      _db.execSQL("DELETE FROM `books`");
      _db.execSQL("DELETE FROM `book_sources`");
      _db.execSQL("DELETE FROM `online_books`");
      _db.execSQL("DELETE FROM `online_chapters`");
      _db.execSQL("DELETE FROM `cached_chapter_content`");
      _db.execSQL("DELETE FROM `comic_sources`");
      _db.execSQL("DELETE FROM `online_comics`");
      _db.execSQL("DELETE FROM `comic_chapters`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RssFeedDao.class, RssFeedDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RssArticleDao.class, RssArticleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BookDao.class, BookDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BookSourceDao.class, BookSourceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OnlineBookDao.class, OnlineBookDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OnlineChapterDao.class, OnlineChapterDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CachedContentDao.class, CachedContentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ComicSourceDao.class, ComicSourceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OnlineComicDao.class, OnlineComicDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ComicChapterDao.class, ComicChapterDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RssFeedDao rssFeedDao() {
    if (_rssFeedDao != null) {
      return _rssFeedDao;
    } else {
      synchronized(this) {
        if(_rssFeedDao == null) {
          _rssFeedDao = new RssFeedDao_Impl(this);
        }
        return _rssFeedDao;
      }
    }
  }

  @Override
  public RssArticleDao rssArticleDao() {
    if (_rssArticleDao != null) {
      return _rssArticleDao;
    } else {
      synchronized(this) {
        if(_rssArticleDao == null) {
          _rssArticleDao = new RssArticleDao_Impl(this);
        }
        return _rssArticleDao;
      }
    }
  }

  @Override
  public BookDao bookDao() {
    if (_bookDao != null) {
      return _bookDao;
    } else {
      synchronized(this) {
        if(_bookDao == null) {
          _bookDao = new BookDao_Impl(this);
        }
        return _bookDao;
      }
    }
  }

  @Override
  public BookSourceDao bookSourceDao() {
    if (_bookSourceDao != null) {
      return _bookSourceDao;
    } else {
      synchronized(this) {
        if(_bookSourceDao == null) {
          _bookSourceDao = new BookSourceDao_Impl(this);
        }
        return _bookSourceDao;
      }
    }
  }

  @Override
  public OnlineBookDao onlineBookDao() {
    if (_onlineBookDao != null) {
      return _onlineBookDao;
    } else {
      synchronized(this) {
        if(_onlineBookDao == null) {
          _onlineBookDao = new OnlineBookDao_Impl(this);
        }
        return _onlineBookDao;
      }
    }
  }

  @Override
  public OnlineChapterDao onlineChapterDao() {
    if (_onlineChapterDao != null) {
      return _onlineChapterDao;
    } else {
      synchronized(this) {
        if(_onlineChapterDao == null) {
          _onlineChapterDao = new OnlineChapterDao_Impl(this);
        }
        return _onlineChapterDao;
      }
    }
  }

  @Override
  public CachedContentDao cachedContentDao() {
    if (_cachedContentDao != null) {
      return _cachedContentDao;
    } else {
      synchronized(this) {
        if(_cachedContentDao == null) {
          _cachedContentDao = new CachedContentDao_Impl(this);
        }
        return _cachedContentDao;
      }
    }
  }

  @Override
  public ComicSourceDao comicSourceDao() {
    if (_comicSourceDao != null) {
      return _comicSourceDao;
    } else {
      synchronized(this) {
        if(_comicSourceDao == null) {
          _comicSourceDao = new ComicSourceDao_Impl(this);
        }
        return _comicSourceDao;
      }
    }
  }

  @Override
  public OnlineComicDao onlineComicDao() {
    if (_onlineComicDao != null) {
      return _onlineComicDao;
    } else {
      synchronized(this) {
        if(_onlineComicDao == null) {
          _onlineComicDao = new OnlineComicDao_Impl(this);
        }
        return _onlineComicDao;
      }
    }
  }

  @Override
  public ComicChapterDao comicChapterDao() {
    if (_comicChapterDao != null) {
      return _comicChapterDao;
    } else {
      synchronized(this) {
        if(_comicChapterDao == null) {
          _comicChapterDao = new ComicChapterDao_Impl(this);
        }
        return _comicChapterDao;
      }
    }
  }
}
