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

  @Query("SELECT m.* FROM Mountain AS m INNER JOIN FavoriteMountain AS fm ON fm.mountain_id = m.mountain_id WHERE fm.user_id = :userId")
  LiveData<List<Mountain>> getFavoriteMountains(long userId);

  @Query("SELECT u.* FROM User AS u INNER JOIN FavoriteMountain AS fm ON fm.user_id = u.user_id WHERE fm.mountain_id = :mountainId")
  LiveData<List<User>> getUsersForFavoriteMountain(long mountainId);

}
