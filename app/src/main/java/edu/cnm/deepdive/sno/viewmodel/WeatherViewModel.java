package edu.cnm.deepdive.sno.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.sno.model.dto.WeatherResponse;
import edu.cnm.deepdive.sno.service.WeatherRepository;

/**
 * ViewModel class to handle the business logic between the {@link WeatherRepository} and the
 * {@link edu.cnm.deepdive.sno.controller.WeatherFragment}
 */
public class WeatherViewModel extends AndroidViewModel {

  private final MutableLiveData<WeatherResponse> weather;
  private final WeatherRepository weatherRepository;

  /**
   * Constructs an instance of the WeatherViewModel.
   * @param application Application context
   */
  public WeatherViewModel(@NonNull Application application) {
    super(application);
    weatherRepository = new WeatherRepository(application);
    weather = new MutableLiveData<>();
  }

  /**
   * Returns the weather from {@link WeatherResponse}
   */
  public LiveData<WeatherResponse> getWeather() {
    return weather;
  }

  /**
   * Requests the weather forecast for the specified latitude and longitude.
   * @param latitude Latitude of location
   * @param longitude Longitude of location
   */
  public void requestWeather(float latitude, float longitude) {
    weatherRepository.get(latitude, longitude)
        .subscribe(
            weather::postValue,
            (throwable) -> {/* TODO Put throwable in livedata */}
        );
  }
}
