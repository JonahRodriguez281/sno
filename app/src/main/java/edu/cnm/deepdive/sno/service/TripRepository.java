package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.TripDao;
import edu.cnm.deepdive.sno.model.entity.Mountain;
import edu.cnm.deepdive.sno.model.entity.Trip;
import io.reactivex.Completable;
import java.util.List;

public class TripRepository {

  private final Context context;
  private final TripDao tripDao;

  public TripRepository(Context context) {
    this.context = context;
    tripDao = SnoDatabase.getInstance().getTripDao();
  }

  private Completable save(Trip trip) {
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

  private Completable delete(Trip trip) {
    return (
        trip.getId() != 0
            ? tripDao.delete(trip).ignoreElement()
            : Completable.complete()
    );
  }

  private LiveData<Trip> getTrip(long tripId) {
    return tripDao.selectTrip(tripId);
  }

  private LiveData<List<Trip>> getAllTrips() {
    return tripDao.getAllTrips();
  }

  private LiveData<Integer> getDaysLogged() {
    return tripDao.getDaysLogged();
  }
}
