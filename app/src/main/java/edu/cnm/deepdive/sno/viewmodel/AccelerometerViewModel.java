package edu.cnm.deepdive.sno.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.sno.model.entity.Trip;
import edu.cnm.deepdive.sno.service.TripRepository;

/**
 * ViewModel class to handle the business logic between the {@link TripRepository} and the
 * {@link edu.cnm.deepdive.sno.controller.AccelerometerFragment}.
 */
public class AccelerometerViewModel extends AndroidViewModel implements LifecycleObserver {

  private TripRepository tripRepository;
  private MutableLiveData<Trip> trip;

  /**
   * Constructs an instance of the AccelerometerViewModel.
   * @param application Application context
   */
  public AccelerometerViewModel(@NonNull Application application) {
    super(application);
    tripRepository = new TripRepository(application);
  }

  /**
   * Returns the max speed in the database from the {@link TripRepository}.
   */
  public LiveData<Integer> getMaxSpeed() {
    return tripRepository.getMaxSpeed();
  }
}