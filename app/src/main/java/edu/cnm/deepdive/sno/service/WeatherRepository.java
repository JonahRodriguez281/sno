package edu.cnm.deepdive.sno.service;

import android.content.Context;
import edu.cnm.deepdive.sno.R;
import edu.cnm.deepdive.sno.model.dto.WeatherResponse;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository class to communicate with the {@link SnoWebService} for the weather API.
 */
public class WeatherRepository {

  private final Context context;
  private final SnoWebService webService;

  /**
   * Constructs an instance of the weather repository.
   * @param context Context of the application
   */
  public WeatherRepository(Context context) {
    this.context = context;
    webService = SnoWebService.getInstance();
  }

  /**
   * Gets the latitude and longitude from the weather API to display the weather for the specified
   * location.
   * @param latitude latitude of location
   * @param longitude longitude of location
   * @return A {@link WeatherResponse}
   */
  public Single<WeatherResponse> get(double latitude, double longitude) {
    return webService.getWeather(context.getString(R.string.api_key), latitude, longitude)
        .subscribeOn(Schedulers.io());
  }
}
