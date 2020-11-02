package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.FavoriteMountain;
import edu.cnm.deepdive.sno.model.entity.Gear;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface GearDao {

  @Insert
  Single<Long> insert(Gear gear);

  @Insert
  Single<List<Long>> insert(Gear... gear);

  @Insert
  Single<List<Long>> insert(Collection<Gear> gear);

  @Update
  Single<Integer> update(Gear gear);

  @Update
  Single<Integer> update(Gear... gear);

  @Update
  Single<Integer> update(Collection<Gear> gear);

  @Delete
  Single<Integer> delete(Gear gear);

  @Delete
  Single<Integer> delete(Gear... gear);

  @Delete
  Single<Integer> delete(Collection<Gear> gear);

  @Query("SELECT * FROM Gear WHERE gear_id = :id ORDER BY gear_type DESC")
  LiveData<List<Gear>> getGear(long id);
}
