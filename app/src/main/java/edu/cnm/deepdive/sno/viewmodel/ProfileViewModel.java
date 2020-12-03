package edu.cnm.deepdive.sno.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.sno.model.entity.Gear;
import edu.cnm.deepdive.sno.service.GearRepository;
import edu.cnm.deepdive.sno.service.TripRepository;
import io.reactivex.disposables.CompositeDisposable;

/**
 * ViewModel class to handle the business logic between the {@link TripRepository} and the
 * {@link GearRepository}, in accordance with the {@link edu.cnm.deepdive.sno.controller.ProfileFragment}.
 */
public class ProfileViewModel extends AndroidViewModel implements LifecycleObserver {

  private final TripRepository tripRepository;
  private final GearRepository gearRepository;
  private final MutableLiveData<Long> gearId;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final LiveData<Gear> gear;

  /**
   * Constructs an instance of the ProfileViewModel
   * @param application Application context
   */
  public ProfileViewModel(@NonNull Application application) {
    super(application);
    tripRepository = new TripRepository(application);
    gearRepository = new GearRepository(application);
    gearId = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    gear = Transformations.switchMap(gearId, gearRepository::getGear);
  }

  /**
   * Saves a piece of gear into the database.
   * @param gear Gear object to be saved.
   */
  public void update(Gear gear) {
    throwable.setValue(null);
    pending.add(
        gearRepository.save(gear)
            .subscribe(
                () -> {},
                throwable::postValue
            )
    );
  }

  /**
   * Deletes a piece of gear from the database.
   * @param gear Gear object to be deleted.
   */
  public void delete(Gear gear) {
    throwable.setValue(null);
    pending.add(
        gearRepository.delete(gear)
            .subscribe(
                () -> {},
                throwable::postValue
            )
    );
  }

  /**
   * Returns the distance of a {@link edu.cnm.deepdive.sno.model.entity.Trip} from the
   * {@link TripRepository}.
   */
  public LiveData<Float> getDistance() {
    return tripRepository.getDistance();
  }

  /**
   * Returns the days logged of a {@link edu.cnm.deepdive.sno.model.entity.Trip} from the
   * {@link TripRepository}.
   */
  public LiveData<Integer> getDaysLogged() {
    return tripRepository.getDaysLogged();
  }

  /**
   * Returns a {@link Gear} object;
   */
  public LiveData<Gear> getGear() {
    return gear;
  }

  /**
   * Sets the id of a gear object.
   */
  public void setGearId(long id) {
    gearId.setValue(id);
  }

}