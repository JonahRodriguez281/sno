package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(User user);

  @Update
  Single<Integer> update(User user);

  @Delete
  Single<Integer> delete(User user);

  @Query("SELECT * FROM User WHERE user_id = :userId")
  LiveData<User> getUser(long userId);

  @Query("SELECT * FROM User WHERE oauth_Key = :oauthKey")
  Single<User> getUserOauthKey(String oauthKey);

}
