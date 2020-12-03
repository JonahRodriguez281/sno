package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.TripDao;
import edu.cnm.deepdive.sno.model.entity.Trip;
import io.reactivex.Completable;
import java.util.List;

/**
 * Repository class to communicate with the {@link TripDao}.
 */
public class TripRepository {

  private final Context context;
  private final TripDao tripDao;

  /**
   * Creates an instance of the trip repository.
   * @param context Application context
   */
  public TripRepository(Context context) {
    this.context = context;
    tripDao = SnoDatabase.getInstance().getTripDao();
  }

  /**
   * Saves a trip in the database if one doesn't exist, else, updates a trip.
   * @param trip A trip
   * @return A saved trip or an updated trip.
   */
  public Completable save(Trip trip) {
    return (
        (trip.getId() == 0)
            ? tripDao.insert(trip)
            .map((id) -> {
              trip.setId(id);
              return trip;
            })
            : tripDao.update(trip)
                .map((num) -> trip)
    )
        .ignoreElement();
  }

  /**
   * Deletes a trip from the database.
   * @param trip A trip
   * @return A deleted trip from the database.
   */
  public Completable delete(Trip trip) {
    return (
        trip.getId() != 0
            ? tripDao.delete(trip).ignoreElement()
            : Completable.complete()
    );
  }

  /**
   * Returns a trip according to it's id.
   * @param tripId A trip id.
   * @return A trip.
   */
  public LiveData<Trip> getTrip(long tripId) {
    return tripDao.selectTrip(tripId);
  }

  /**
   * Returns all trips in the database.
   * @return A list of all trips.
   */
  public LiveData<List<Trip>> getAllTrips() {
    return tripDao.getAllTrips();
  }

  /**
   * Returns the number of days logged in a trip.
   * @return The number of days logged.
   */
  public LiveData<Integer> getDaysLogged() {
    return tripDao.getDaysLogged();
  }

  /**
   * Returns the highest speed recorded in the database.
   * @return The max speed recorded in {@link Trip}.
   */
  public LiveData<Integer> getMaxSpeed() {
    return tripDao.getMaxSpeed();
  }

  public LiveData<Float> getDistance() {
    return tripDao.getDistance();
  }
}
