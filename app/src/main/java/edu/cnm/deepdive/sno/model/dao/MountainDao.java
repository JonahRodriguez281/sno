package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.Mountain;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

@Dao
public interface MountainDao {

  @Insert
  Single<Long> insert(Mountain mountain);

  @Insert
  Single<List<Long>> insert(Mountain... mountains);

  @Insert
  Single<List<Long>> insert(Collection<Mountain> mountains);

  @Update
  Single<Integer> update(Mountain mountain);

  @Update
  Single<Integer> update(Mountain... mountains);

  @Update
  Single<Integer> update(Collection<Mountain> mountains);

  @Delete
  Single<Integer> delete(Mountain mountain);

  @Delete
  Single<Integer> delete(Mountain... mountains);

  @Delete
  Single<Integer> delete(Collection<Mountain> mountains);

  @Query("SELECT * FROM mountain WHERE mountain_id = :id ORDER BY name DESC")
  LiveData<List<Mountain>> select(long id);
}
