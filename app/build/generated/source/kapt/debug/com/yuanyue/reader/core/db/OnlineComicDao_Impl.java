package com.yuanyue.reader.core.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.yuanyue.reader.model.OnlineComicEntity;
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
public final class OnlineComicDao_Impl implements OnlineComicDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OnlineComicEntity> __insertionAdapterOfOnlineComicEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBySource;

  public OnlineComicDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOnlineComicEntity = new EntityInsertionAdapter<OnlineComicEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `online_comics` (`id`,`sourceId`,`title`,`author`,`coverUrl`,`comicUrl`,`intro`,`cachedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OnlineComicEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getSourceId());
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getAuthor() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAuthor());
        }
        if (entity.getCoverUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCoverUrl());
        }
        if (entity.getComicUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getComicUrl());
        }
        if (entity.getIntro() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getIntro());
        }
        statement.bindLong(8, entity.getCachedAt());
      }
    };
    this.__preparedStmtOfDeleteBySource = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM online_comics WHERE sourceId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<OnlineComicEntity> comics,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfOnlineComicEntity.insertAndReturnIdsList(comics);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBySource(final long sourceId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBySource.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, sourceId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteBySource.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<OnlineComicEntity>> observeBySource(final long sourceId) {
    final String _sql = "SELECT * FROM online_comics WHERE sourceId = ? ORDER BY cachedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sourceId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"online_comics"}, new Callable<List<OnlineComicEntity>>() {
      @Override
      @NonNull
      public List<OnlineComicEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfComicUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "comicUrl");
          final int _cursorIndexOfIntro = CursorUtil.getColumnIndexOrThrow(_cursor, "intro");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<OnlineComicEntity> _result = new ArrayList<OnlineComicEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OnlineComicEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpSourceId;
            _tmpSourceId = _cursor.getLong(_cursorIndexOfSourceId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final String _tmpComicUrl;
            if (_cursor.isNull(_cursorIndexOfComicUrl)) {
              _tmpComicUrl = null;
            } else {
              _tmpComicUrl = _cursor.getString(_cursorIndexOfComicUrl);
            }
            final String _tmpIntro;
            if (_cursor.isNull(_cursorIndexOfIntro)) {
              _tmpIntro = null;
            } else {
              _tmpIntro = _cursor.getString(_cursorIndexOfIntro);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new OnlineComicEntity(_tmpId,_tmpSourceId,_tmpTitle,_tmpAuthor,_tmpCoverUrl,_tmpComicUrl,_tmpIntro,_tmpCachedAt);
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
  public Object getById(final long id, final Continuation<? super OnlineComicEntity> $completion) {
    final String _sql = "SELECT * FROM online_comics WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<OnlineComicEntity>() {
      @Override
      @Nullable
      public OnlineComicEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfComicUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "comicUrl");
          final int _cursorIndexOfIntro = CursorUtil.getColumnIndexOrThrow(_cursor, "intro");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final OnlineComicEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpSourceId;
            _tmpSourceId = _cursor.getLong(_cursorIndexOfSourceId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final String _tmpComicUrl;
            if (_cursor.isNull(_cursorIndexOfComicUrl)) {
              _tmpComicUrl = null;
            } else {
              _tmpComicUrl = _cursor.getString(_cursorIndexOfComicUrl);
            }
            final String _tmpIntro;
            if (_cursor.isNull(_cursorIndexOfIntro)) {
              _tmpIntro = null;
            } else {
              _tmpIntro = _cursor.getString(_cursorIndexOfIntro);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _result = new OnlineComicEntity(_tmpId,_tmpSourceId,_tmpTitle,_tmpAuthor,_tmpCoverUrl,_tmpComicUrl,_tmpIntro,_tmpCachedAt);
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
