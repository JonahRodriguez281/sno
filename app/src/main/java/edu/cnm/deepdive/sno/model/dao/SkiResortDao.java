package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface SkiResortDao {

  @Insert
  Single<Long> insert(SkiResort skiResort);

  @Insert
  Single<List<Long>> insert(SkiResort... skiResorts);

  @Insert
  Single<List<Long>> insert(Collection<SkiResort> skiResorts);

  @Update
  Single<Integer> update(SkiResort skiResort);

  @Update
  Single<Integer> update(SkiResort... skiResorts);

  @Update
  Single<Integer> update(Collection<SkiResort> skiResorts);

  @Delete
  Single<Integer> delete(SkiResort skiResort);

  @Delete
  Single<Integer> delete(SkiResort... skiResorts);

  @Delete
  Single<Integer> delete(Collection<SkiResort> skiResorts);

  @Query("SELECT * FROM SkiResort WHERE ski_resort_id = :id")
  LiveData<SkiResort> selectSkiResort(long id);

  @Query("SELECT * FROM SkiResort ORDER BY name")
  LiveData<List<SkiResort>> selectAllSkiResorts();
}
