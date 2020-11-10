package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.FavoriteSkiResort;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface FavoriteSkiResortDao {

  @Insert
  Single<Long> insert(FavoriteSkiResort favoriteSkiResort);

  @Insert
  Single<List<Long>> insert(FavoriteSkiResort... favoriteSkiResorts);

  @Insert
  Single<List<Long>> insert(Collection<FavoriteSkiResort> favoriteSkiResorts);

  @Update
  Single<Integer> update(FavoriteSkiResort favoriteSkiResort);

  @Update
  Single<Integer> update(FavoriteSkiResort... favoriteSkiResorts);

  @Update
  Single<Integer> update(Collection<FavoriteSkiResort> favoriteSkiResorts);

  @Delete
  Single<Integer> delete(FavoriteSkiResort favoriteSkiResort);

  @Delete
  Single<Integer> delete(FavoriteSkiResort... favoriteSkiResorts);

  @Delete
  Single<Integer> delete(Collection<FavoriteSkiResort> favoriteSkiResorts);

  @Query("SELECT m.* FROM SkiResort AS m INNER JOIN FavoriteSkiResort AS fm ON fm.ski_resort_id = m.ski_resort_id WHERE fm.user_id = :userId")
  LiveData<List<SkiResort>> getFavoriteMountains(long userId);

  @Query("SELECT u.* FROM User AS u INNER JOIN FavoriteSkiResort AS fm ON fm.user_id = u.user_id WHERE fm.ski_resort_id = :skiResortId")
  LiveData<List<User>> getUsersForFavoriteMountain(long skiResortId);

}
