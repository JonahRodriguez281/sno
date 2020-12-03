package edu.cnm.deepdive.sno.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import edu.cnm.deepdive.sno.service.SkiResortRepository;
import java.util.List;

/**
 * ViewModel class to handle the business logic between the {@link SkiResortRepository} and the
 * {@link edu.cnm.deepdive.sno.controller.ResortFragment}.
 */
public class ResortViewModel extends AndroidViewModel implements LifecycleObserver {

  private final SkiResortRepository skiResortRepository;

  /**
   * Constructs an instance of the ResortViewModel.
   * @param application Application context
   */
  public ResortViewModel(@NonNull Application application) {
    super(application);
    skiResortRepository = new SkiResortRepository(application);
  }

  /**
   * Returns a list of all ski resorts from the {@link SkiResortRepository}.
   */
  public LiveData<List<SkiResort>> getAllSkiResorts() {
    return skiResortRepository.getAllSkiResorts();
  }

}