package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.Trip;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface TripDao {

  @Insert
  Single<Long> insert(Trip trip);

  @Insert
  Single<List<Long>> insert(Trip... trips);

  @Insert
  Single<List<Long>> insert(Collection<Trip> trips);

  @Update
  Single<Integer> update(Trip trip);

  @Update
  Single<Integer> update(Trip... trips);

  @Update
  Single<Integer> update(Collection<Trip> trips);

  @Delete
  Single<Integer> delete(Trip trip);

  @Delete
  Single<Integer> delete(Trip... trips);

  @Delete
  Single<Integer> delete(Collection<Trip> trips);

  @Query("SELECT * FROM `Trip` WHERE trip_id = :tripId")
  LiveData<Trip> selectTrip(long tripId);

  @Query("SELECT * FROM Trip ORDER BY distance")
  LiveData<List<Trip>> getAllTrips();

  @Query("SELECT SUM(ROUND((end_time - start_time) / 86400000 + 0.499999999)) AS grand_total FROM `Trip` ")
  LiveData<Integer> getDaysLogged();

  @Query("SELECT * FROM Trip ORDER BY max_speed")
  LiveData<Trip> getMaxSpeed();
}
