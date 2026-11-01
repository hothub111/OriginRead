package com.yuanyue.reader.core.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.yuanyue.reader.model.BookSourceEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BookSourceDao_Impl implements BookSourceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BookSourceEntity> __insertionAdapterOfBookSourceEntity;

  private final EntityDeletionOrUpdateAdapter<BookSourceEntity> __deletionAdapterOfBookSourceEntity;

  public BookSourceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookSourceEntity = new EntityInsertionAdapter<BookSourceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `book_sources` (`id`,`name`,`url`,`searchUrl`,`searchListRule`,`bookNameRule`,`bookAuthorRule`,`bookCoverRule`,`bookUrlRule`,`introRule`,`coverRule`,`tocUrlRule`,`chapterListRule`,`chapterNameRule`,`chapterUrlRule`,`contentRule`,`contentReplaceRules`,`enabled`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookSourceEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getUrl());
        }
        if (entity.getSearchUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSearchUrl());
        }
        if (entity.getSearchListRule() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getSearchListRule());
        }
        if (entity.getBookNameRule() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBookNameRule());
        }
        if (entity.getBookAuthorRule() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBookAuthorRule());
        }
        if (entity.getBookCoverRule() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getBookCoverRule());
        }
        if (entity.getBookUrlRule() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getBookUrlRule());
        }
        if (entity.getIntroRule() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getIntroRule());
        }
        if (entity.getCoverRule() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getCoverRule());
        }
        if (entity.getTocUrlRule() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getTocUrlRule());
        }
        if (entity.getChapterListRule() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getChapterListRule());
        }
        if (entity.getChapterNameRule() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getChapterNameRule());
        }
        if (entity.getChapterUrlRule() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getChapterUrlRule());
        }
        if (entity.getContentRule() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getContentRule());
        }
        if (entity.getContentReplaceRules() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getContentReplaceRules());
        }
        final int _tmp = entity.getEnabled() ? 1 : 0;
        statement.bindLong(18, _tmp);
        statement.bindLong(19, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfBookSourceEntity = new EntityDeletionOrUpdateAdapter<BookSourceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `book_sources` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookSourceEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final BookSourceEntity source,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfBookSourceEntity.insertAndReturnId(source);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final BookSourceEntity source,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBookSourceEntity.handle(source);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookSourceEntity>> observeAll() {
    final String _sql = "SELECT * FROM book_sources ORDER BY createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"book_sources"}, new Callable<List<BookSourceEntity>>() {
      @Override
      @NonNull
      public List<BookSourceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSearchUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "searchUrl");
          final int _cursorIndexOfSearchListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "searchListRule");
          final int _cursorIndexOfBookNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameRule");
          final int _cursorIndexOfBookAuthorRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookAuthorRule");
          final int _cursorIndexOfBookCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookCoverRule");
          final int _cursorIndexOfBookUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookUrlRule");
          final int _cursorIndexOfIntroRule = CursorUtil.getColumnIndexOrThrow(_cursor, "introRule");
          final int _cursorIndexOfCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "coverRule");
          final int _cursorIndexOfTocUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "tocUrlRule");
          final int _cursorIndexOfChapterListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterListRule");
          final int _cursorIndexOfChapterNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameRule");
          final int _cursorIndexOfChapterUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterUrlRule");
          final int _cursorIndexOfContentRule = CursorUtil.getColumnIndexOrThrow(_cursor, "contentRule");
          final int _cursorIndexOfContentReplaceRules = CursorUtil.getColumnIndexOrThrow(_cursor, "contentReplaceRules");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<BookSourceEntity> _result = new ArrayList<BookSourceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookSourceEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpSearchUrl;
            if (_cursor.isNull(_cursorIndexOfSearchUrl)) {
              _tmpSearchUrl = null;
            } else {
              _tmpSearchUrl = _cursor.getString(_cursorIndexOfSearchUrl);
            }
            final String _tmpSearchListRule;
            if (_cursor.isNull(_cursorIndexOfSearchListRule)) {
              _tmpSearchListRule = null;
            } else {
              _tmpSearchListRule = _cursor.getString(_cursorIndexOfSearchListRule);
            }
            final String _tmpBookNameRule;
            if (_cursor.isNull(_cursorIndexOfBookNameRule)) {
              _tmpBookNameRule = null;
            } else {
              _tmpBookNameRule = _cursor.getString(_cursorIndexOfBookNameRule);
            }
            final String _tmpBookAuthorRule;
            if (_cursor.isNull(_cursorIndexOfBookAuthorRule)) {
              _tmpBookAuthorRule = null;
            } else {
              _tmpBookAuthorRule = _cursor.getString(_cursorIndexOfBookAuthorRule);
            }
            final String _tmpBookCoverRule;
            if (_cursor.isNull(_cursorIndexOfBookCoverRule)) {
              _tmpBookCoverRule = null;
            } else {
              _tmpBookCoverRule = _cursor.getString(_cursorIndexOfBookCoverRule);
            }
            final String _tmpBookUrlRule;
            if (_cursor.isNull(_cursorIndexOfBookUrlRule)) {
              _tmpBookUrlRule = null;
            } else {
              _tmpBookUrlRule = _cursor.getString(_cursorIndexOfBookUrlRule);
            }
            final String _tmpIntroRule;
            if (_cursor.isNull(_cursorIndexOfIntroRule)) {
              _tmpIntroRule = null;
            } else {
              _tmpIntroRule = _cursor.getString(_cursorIndexOfIntroRule);
            }
            final String _tmpCoverRule;
            if (_cursor.isNull(_cursorIndexOfCoverRule)) {
              _tmpCoverRule = null;
            } else {
              _tmpCoverRule = _cursor.getString(_cursorIndexOfCoverRule);
            }
            final String _tmpTocUrlRule;
            if (_cursor.isNull(_cursorIndexOfTocUrlRule)) {
              _tmpTocUrlRule = null;
            } else {
              _tmpTocUrlRule = _cursor.getString(_cursorIndexOfTocUrlRule);
            }
            final String _tmpChapterListRule;
            if (_cursor.isNull(_cursorIndexOfChapterListRule)) {
              _tmpChapterListRule = null;
            } else {
              _tmpChapterListRule = _cursor.getString(_cursorIndexOfChapterListRule);
            }
            final String _tmpChapterNameRule;
            if (_cursor.isNull(_cursorIndexOfChapterNameRule)) {
              _tmpChapterNameRule = null;
            } else {
              _tmpChapterNameRule = _cursor.getString(_cursorIndexOfChapterNameRule);
            }
            final String _tmpChapterUrlRule;
            if (_cursor.isNull(_cursorIndexOfChapterUrlRule)) {
              _tmpChapterUrlRule = null;
            } else {
              _tmpChapterUrlRule = _cursor.getString(_cursorIndexOfChapterUrlRule);
            }
            final String _tmpContentRule;
            if (_cursor.isNull(_cursorIndexOfContentRule)) {
              _tmpContentRule = null;
            } else {
              _tmpContentRule = _cursor.getString(_cursorIndexOfContentRule);
            }
            final String _tmpContentReplaceRules;
            if (_cursor.isNull(_cursorIndexOfContentReplaceRules)) {
              _tmpContentReplaceRules = null;
            } else {
              _tmpContentReplaceRules = _cursor.getString(_cursorIndexOfContentReplaceRules);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new BookSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpSearchUrl,_tmpSearchListRule,_tmpBookNameRule,_tmpBookAuthorRule,_tmpBookCoverRule,_tmpBookUrlRule,_tmpIntroRule,_tmpCoverRule,_tmpTocUrlRule,_tmpChapterListRule,_tmpChapterNameRule,_tmpChapterUrlRule,_tmpContentRule,_tmpContentReplaceRules,_tmpEnabled,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final long id, final Continuation<? super BookSourceEntity> $completion) {
    final String _sql = "SELECT * FROM book_sources WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BookSourceEntity>() {
      @Override
      @Nullable
      public BookSourceEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSearchUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "searchUrl");
          final int _cursorIndexOfSearchListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "searchListRule");
          final int _cursorIndexOfBookNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameRule");
          final int _cursorIndexOfBookAuthorRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookAuthorRule");
          final int _cursorIndexOfBookCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookCoverRule");
          final int _cursorIndexOfBookUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookUrlRule");
          final int _cursorIndexOfIntroRule = CursorUtil.getColumnIndexOrThrow(_cursor, "introRule");
          final int _cursorIndexOfCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "coverRule");
          final int _cursorIndexOfTocUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "tocUrlRule");
          final int _cursorIndexOfChapterListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterListRule");
          final int _cursorIndexOfChapterNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameRule");
          final int _cursorIndexOfChapterUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterUrlRule");
          final int _cursorIndexOfContentRule = CursorUtil.getColumnIndexOrThrow(_cursor, "contentRule");
          final int _cursorIndexOfContentReplaceRules = CursorUtil.getColumnIndexOrThrow(_cursor, "contentReplaceRules");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final BookSourceEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpSearchUrl;
            if (_cursor.isNull(_cursorIndexOfSearchUrl)) {
              _tmpSearchUrl = null;
            } else {
              _tmpSearchUrl = _cursor.getString(_cursorIndexOfSearchUrl);
            }
            final String _tmpSearchListRule;
            if (_cursor.isNull(_cursorIndexOfSearchListRule)) {
              _tmpSearchListRule = null;
            } else {
              _tmpSearchListRule = _cursor.getString(_cursorIndexOfSearchListRule);
            }
            final String _tmpBookNameRule;
            if (_cursor.isNull(_cursorIndexOfBookNameRule)) {
              _tmpBookNameRule = null;
            } else {
              _tmpBookNameRule = _cursor.getString(_cursorIndexOfBookNameRule);
            }
            final String _tmpBookAuthorRule;
            if (_cursor.isNull(_cursorIndexOfBookAuthorRule)) {
              _tmpBookAuthorRule = null;
            } else {
              _tmpBookAuthorRule = _cursor.getString(_cursorIndexOfBookAuthorRule);
            }
            final String _tmpBookCoverRule;
            if (_cursor.isNull(_cursorIndexOfBookCoverRule)) {
              _tmpBookCoverRule = null;
            } else {
              _tmpBookCoverRule = _cursor.getString(_cursorIndexOfBookCoverRule);
            }
            final String _tmpBookUrlRule;
            if (_cursor.isNull(_cursorIndexOfBookUrlRule)) {
              _tmpBookUrlRule = null;
            } else {
              _tmpBookUrlRule = _cursor.getString(_cursorIndexOfBookUrlRule);
            }
            final String _tmpIntroRule;
            if (_cursor.isNull(_cursorIndexOfIntroRule)) {
              _tmpIntroRule = null;
            } else {
              _tmpIntroRule = _cursor.getString(_cursorIndexOfIntroRule);
            }
            final String _tmpCoverRule;
            if (_cursor.isNull(_cursorIndexOfCoverRule)) {
              _tmpCoverRule = null;
            } else {
              _tmpCoverRule = _cursor.getString(_cursorIndexOfCoverRule);
            }
            final String _tmpTocUrlRule;
            if (_cursor.isNull(_cursorIndexOfTocUrlRule)) {
              _tmpTocUrlRule = null;
            } else {
              _tmpTocUrlRule = _cursor.getString(_cursorIndexOfTocUrlRule);
            }
            final String _tmpChapterListRule;
            if (_cursor.isNull(_cursorIndexOfChapterListRule)) {
              _tmpChapterListRule = null;
            } else {
              _tmpChapterListRule = _cursor.getString(_cursorIndexOfChapterListRule);
            }
            final String _tmpChapterNameRule;
            if (_cursor.isNull(_cursorIndexOfChapterNameRule)) {
              _tmpChapterNameRule = null;
            } else {
              _tmpChapterNameRule = _cursor.getString(_cursorIndexOfChapterNameRule);
            }
            final String _tmpChapterUrlRule;
            if (_cursor.isNull(_cursorIndexOfChapterUrlRule)) {
              _tmpChapterUrlRule = null;
            } else {
              _tmpChapterUrlRule = _cursor.getString(_cursorIndexOfChapterUrlRule);
            }
            final String _tmpContentRule;
            if (_cursor.isNull(_cursorIndexOfContentRule)) {
              _tmpContentRule = null;
            } else {
              _tmpContentRule = _cursor.getString(_cursorIndexOfContentRule);
            }
            final String _tmpContentReplaceRules;
            if (_cursor.isNull(_cursorIndexOfContentReplaceRules)) {
              _tmpContentReplaceRules = null;
            } else {
              _tmpContentReplaceRules = _cursor.getString(_cursorIndexOfContentReplaceRules);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new BookSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpSearchUrl,_tmpSearchListRule,_tmpBookNameRule,_tmpBookAuthorRule,_tmpBookCoverRule,_tmpBookUrlRule,_tmpIntroRule,_tmpCoverRule,_tmpTocUrlRule,_tmpChapterListRule,_tmpChapterNameRule,_tmpChapterUrlRule,_tmpContentRule,_tmpContentReplaceRules,_tmpEnabled,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByUrl(final String url,
      final Continuation<? super BookSourceEntity> $completion) {
    final String _sql = "SELECT * FROM book_sources WHERE url = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BookSourceEntity>() {
      @Override
      @Nullable
      public BookSourceEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSearchUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "searchUrl");
          final int _cursorIndexOfSearchListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "searchListRule");
          final int _cursorIndexOfBookNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameRule");
          final int _cursorIndexOfBookAuthorRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookAuthorRule");
          final int _cursorIndexOfBookCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookCoverRule");
          final int _cursorIndexOfBookUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "bookUrlRule");
          final int _cursorIndexOfIntroRule = CursorUtil.getColumnIndexOrThrow(_cursor, "introRule");
          final int _cursorIndexOfCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "coverRule");
          final int _cursorIndexOfTocUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "tocUrlRule");
          final int _cursorIndexOfChapterListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterListRule");
          final int _cursorIndexOfChapterNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameRule");
          final int _cursorIndexOfChapterUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterUrlRule");
          final int _cursorIndexOfContentRule = CursorUtil.getColumnIndexOrThrow(_cursor, "contentRule");
          final int _cursorIndexOfContentReplaceRules = CursorUtil.getColumnIndexOrThrow(_cursor, "contentReplaceRules");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final BookSourceEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpSearchUrl;
            if (_cursor.isNull(_cursorIndexOfSearchUrl)) {
              _tmpSearchUrl = null;
            } else {
              _tmpSearchUrl = _cursor.getString(_cursorIndexOfSearchUrl);
            }
            final String _tmpSearchListRule;
            if (_cursor.isNull(_cursorIndexOfSearchListRule)) {
              _tmpSearchListRule = null;
            } else {
              _tmpSearchListRule = _cursor.getString(_cursorIndexOfSearchListRule);
            }
            final String _tmpBookNameRule;
            if (_cursor.isNull(_cursorIndexOfBookNameRule)) {
              _tmpBookNameRule = null;
            } else {
              _tmpBookNameRule = _cursor.getString(_cursorIndexOfBookNameRule);
            }
            final String _tmpBookAuthorRule;
            if (_cursor.isNull(_cursorIndexOfBookAuthorRule)) {
              _tmpBookAuthorRule = null;
            } else {
              _tmpBookAuthorRule = _cursor.getString(_cursorIndexOfBookAuthorRule);
            }
            final String _tmpBookCoverRule;
            if (_cursor.isNull(_cursorIndexOfBookCoverRule)) {
              _tmpBookCoverRule = null;
            } else {
              _tmpBookCoverRule = _cursor.getString(_cursorIndexOfBookCoverRule);
            }
            final String _tmpBookUrlRule;
            if (_cursor.isNull(_cursorIndexOfBookUrlRule)) {
              _tmpBookUrlRule = null;
            } else {
              _tmpBookUrlRule = _cursor.getString(_cursorIndexOfBookUrlRule);
            }
            final String _tmpIntroRule;
            if (_cursor.isNull(_cursorIndexOfIntroRule)) {
              _tmpIntroRule = null;
            } else {
              _tmpIntroRule = _cursor.getString(_cursorIndexOfIntroRule);
            }
            final String _tmpCoverRule;
            if (_cursor.isNull(_cursorIndexOfCoverRule)) {
              _tmpCoverRule = null;
            } else {
              _tmpCoverRule = _cursor.getString(_cursorIndexOfCoverRule);
            }
            final String _tmpTocUrlRule;
            if (_cursor.isNull(_cursorIndexOfTocUrlRule)) {
              _tmpTocUrlRule = null;
            } else {
              _tmpTocUrlRule = _cursor.getString(_cursorIndexOfTocUrlRule);
            }
            final String _tmpChapterListRule;
            if (_cursor.isNull(_cursorIndexOfChapterListRule)) {
              _tmpChapterListRule = null;
            } else {
              _tmpChapterListRule = _cursor.getString(_cursorIndexOfChapterListRule);
            }
            final String _tmpChapterNameRule;
            if (_cursor.isNull(_cursorIndexOfChapterNameRule)) {
              _tmpChapterNameRule = null;
            } else {
              _tmpChapterNameRule = _cursor.getString(_cursorIndexOfChapterNameRule);
            }
            final String _tmpChapterUrlRule;
            if (_cursor.isNull(_cursorIndexOfChapterUrlRule)) {
              _tmpChapterUrlRule = null;
            } else {
              _tmpChapterUrlRule = _cursor.getString(_cursorIndexOfChapterUrlRule);
            }
            final String _tmpContentRule;
            if (_cursor.isNull(_cursorIndexOfContentRule)) {
              _tmpContentRule = null;
            } else {
              _tmpContentRule = _cursor.getString(_cursorIndexOfContentRule);
            }
            final String _tmpContentReplaceRules;
            if (_cursor.isNull(_cursorIndexOfContentReplaceRules)) {
              _tmpContentReplaceRules = null;
            } else {
              _tmpContentReplaceRules = _cursor.getString(_cursorIndexOfContentReplaceRules);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new BookSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpSearchUrl,_tmpSearchListRule,_tmpBookNameRule,_tmpBookAuthorRule,_tmpBookCoverRule,_tmpBookUrlRule,_tmpIntroRule,_tmpCoverRule,_tmpTocUrlRule,_tmpChapterListRule,_tmpChapterNameRule,_tmpChapterUrlRule,_tmpContentRule,_tmpContentReplaceRules,_tmpEnabled,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
