package edu.cnm.deepdive.sno.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.sno.BuildConfig;
import edu.cnm.deepdive.sno.model.dto.WeatherResponse;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface to handle the weather API endpoints.
 */
public interface SnoWebService {

  @GET
  Single<WeatherResponse> getWeather(@Query("appid") String apiKey,
      @Query("lat") double latitude, @Query("lon") double longitude);

  // all the things we do in postman, we will implement in the interface
  // going to define all the requests we can send to the webservice

  /**
   * Returns a singleton instance of the SnoWebService
   */
  static SnoWebService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gson builder to handle endpoints
   */
  class InstanceHolder {

    private static final SnoWebService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          //.excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE); // based on the status of this build, build everything, or build nothing
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor) // any traffic uses this interceptor for logging
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson)) // add converter to retrofit object
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // specifies the connection to reactivex
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(SnoWebService.class);
    }

  }
}
