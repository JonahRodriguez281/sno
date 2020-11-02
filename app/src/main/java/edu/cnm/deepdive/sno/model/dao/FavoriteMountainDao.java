package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.FavoriteMountain;
import edu.cnm.deepdive.sno.model.entity.Mountain;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface FavoriteMountainDao {

  @Insert
  Single<Long> insert(FavoriteMountain favoriteMountain);

  @Insert
  Single<List<Long>> insert(FavoriteMountain... favoriteMountains);

  @Insert
  Single<List<Long>> insert(Collection<FavoriteMountain> favoriteMountains);

  @Update
  Single<Integer> update(FavoriteMountain favoriteMountain);

  @Update
  Single<Integer> update(FavoriteMountain... favoriteMountains);

  @Update
  Single<Integer> update(Collection<FavoriteMountain> favoriteMountains);

  @Delete
  Single<Integer> delete(FavoriteMountain favoriteMountain);

  @Delete
  Single<Integer> delete(FavoriteMountain... favoriteMountains);

  @Delete
  Single<Integer> delete(Collection<FavoriteMountain> favoriteMountains);

  @Query("SELECT * FROM User WHERE user_id = :id")
  LiveData<User> getUser(long id);

  @Query("SELECT * FROM `Mountain` WHERE mountain_id = :id ORDER BY name DESC")
  LiveData<List<Mountain>> getMountain(long id);
}
