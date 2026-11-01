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
import com.yuanyue.reader.model.ComicSourceEntity;
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
public final class ComicSourceDao_Impl implements ComicSourceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ComicSourceEntity> __insertionAdapterOfComicSourceEntity;

  private final EntityDeletionOrUpdateAdapter<ComicSourceEntity> __deletionAdapterOfComicSourceEntity;

  public ComicSourceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfComicSourceEntity = new EntityInsertionAdapter<ComicSourceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `comic_sources` (`id`,`name`,`url`,`searchUrl`,`searchListRule`,`comicTitleRule`,`comicUrlRule`,`comicCoverRule`,`chapterListRule`,`chapterNameRule`,`chapterUrlRule`,`imageListRule`,`imageUrlRule`,`enabled`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ComicSourceEntity entity) {
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
        if (entity.getComicTitleRule() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getComicTitleRule());
        }
        if (entity.getComicUrlRule() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getComicUrlRule());
        }
        if (entity.getComicCoverRule() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getComicCoverRule());
        }
        if (entity.getChapterListRule() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getChapterListRule());
        }
        if (entity.getChapterNameRule() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getChapterNameRule());
        }
        if (entity.getChapterUrlRule() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getChapterUrlRule());
        }
        if (entity.getImageListRule() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getImageListRule());
        }
        if (entity.getImageUrlRule() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getImageUrlRule());
        }
        final int _tmp = entity.getEnabled() ? 1 : 0;
        statement.bindLong(14, _tmp);
        statement.bindLong(15, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfComicSourceEntity = new EntityDeletionOrUpdateAdapter<ComicSourceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `comic_sources` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ComicSourceEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final ComicSourceEntity source,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfComicSourceEntity.insertAndReturnId(source);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final ComicSourceEntity source,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfComicSourceEntity.handle(source);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ComicSourceEntity>> observeAll() {
    final String _sql = "SELECT * FROM comic_sources ORDER BY createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"comic_sources"}, new Callable<List<ComicSourceEntity>>() {
      @Override
      @NonNull
      public List<ComicSourceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSearchUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "searchUrl");
          final int _cursorIndexOfSearchListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "searchListRule");
          final int _cursorIndexOfComicTitleRule = CursorUtil.getColumnIndexOrThrow(_cursor, "comicTitleRule");
          final int _cursorIndexOfComicUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "comicUrlRule");
          final int _cursorIndexOfComicCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "comicCoverRule");
          final int _cursorIndexOfChapterListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterListRule");
          final int _cursorIndexOfChapterNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameRule");
          final int _cursorIndexOfChapterUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterUrlRule");
          final int _cursorIndexOfImageListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "imageListRule");
          final int _cursorIndexOfImageUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrlRule");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ComicSourceEntity> _result = new ArrayList<ComicSourceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ComicSourceEntity _item;
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
            final String _tmpComicTitleRule;
            if (_cursor.isNull(_cursorIndexOfComicTitleRule)) {
              _tmpComicTitleRule = null;
            } else {
              _tmpComicTitleRule = _cursor.getString(_cursorIndexOfComicTitleRule);
            }
            final String _tmpComicUrlRule;
            if (_cursor.isNull(_cursorIndexOfComicUrlRule)) {
              _tmpComicUrlRule = null;
            } else {
              _tmpComicUrlRule = _cursor.getString(_cursorIndexOfComicUrlRule);
            }
            final String _tmpComicCoverRule;
            if (_cursor.isNull(_cursorIndexOfComicCoverRule)) {
              _tmpComicCoverRule = null;
            } else {
              _tmpComicCoverRule = _cursor.getString(_cursorIndexOfComicCoverRule);
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
            final String _tmpImageListRule;
            if (_cursor.isNull(_cursorIndexOfImageListRule)) {
              _tmpImageListRule = null;
            } else {
              _tmpImageListRule = _cursor.getString(_cursorIndexOfImageListRule);
            }
            final String _tmpImageUrlRule;
            if (_cursor.isNull(_cursorIndexOfImageUrlRule)) {
              _tmpImageUrlRule = null;
            } else {
              _tmpImageUrlRule = _cursor.getString(_cursorIndexOfImageUrlRule);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ComicSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpSearchUrl,_tmpSearchListRule,_tmpComicTitleRule,_tmpComicUrlRule,_tmpComicCoverRule,_tmpChapterListRule,_tmpChapterNameRule,_tmpChapterUrlRule,_tmpImageListRule,_tmpImageUrlRule,_tmpEnabled,_tmpCreatedAt);
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
  public Object getById(final long id, final Continuation<? super ComicSourceEntity> $completion) {
    final String _sql = "SELECT * FROM comic_sources WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ComicSourceEntity>() {
      @Override
      @Nullable
      public ComicSourceEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSearchUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "searchUrl");
          final int _cursorIndexOfSearchListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "searchListRule");
          final int _cursorIndexOfComicTitleRule = CursorUtil.getColumnIndexOrThrow(_cursor, "comicTitleRule");
          final int _cursorIndexOfComicUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "comicUrlRule");
          final int _cursorIndexOfComicCoverRule = CursorUtil.getColumnIndexOrThrow(_cursor, "comicCoverRule");
          final int _cursorIndexOfChapterListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterListRule");
          final int _cursorIndexOfChapterNameRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameRule");
          final int _cursorIndexOfChapterUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterUrlRule");
          final int _cursorIndexOfImageListRule = CursorUtil.getColumnIndexOrThrow(_cursor, "imageListRule");
          final int _cursorIndexOfImageUrlRule = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrlRule");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ComicSourceEntity _result;
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
            final String _tmpComicTitleRule;
            if (_cursor.isNull(_cursorIndexOfComicTitleRule)) {
              _tmpComicTitleRule = null;
            } else {
              _tmpComicTitleRule = _cursor.getString(_cursorIndexOfComicTitleRule);
            }
            final String _tmpComicUrlRule;
            if (_cursor.isNull(_cursorIndexOfComicUrlRule)) {
              _tmpComicUrlRule = null;
            } else {
              _tmpComicUrlRule = _cursor.getString(_cursorIndexOfComicUrlRule);
            }
            final String _tmpComicCoverRule;
            if (_cursor.isNull(_cursorIndexOfComicCoverRule)) {
              _tmpComicCoverRule = null;
            } else {
              _tmpComicCoverRule = _cursor.getString(_cursorIndexOfComicCoverRule);
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
            final String _tmpImageListRule;
            if (_cursor.isNull(_cursorIndexOfImageListRule)) {
              _tmpImageListRule = null;
            } else {
              _tmpImageListRule = _cursor.getString(_cursorIndexOfImageListRule);
            }
            final String _tmpImageUrlRule;
            if (_cursor.isNull(_cursorIndexOfImageUrlRule)) {
              _tmpImageUrlRule = null;
            } else {
              _tmpImageUrlRule = _cursor.getString(_cursorIndexOfImageUrlRule);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ComicSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpSearchUrl,_tmpSearchListRule,_tmpComicTitleRule,_tmpComicUrlRule,_tmpComicCoverRule,_tmpChapterListRule,_tmpChapterNameRule,_tmpChapterUrlRule,_tmpImageListRule,_tmpImageUrlRule,_tmpEnabled,_tmpCreatedAt);
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
