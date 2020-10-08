package edu.cnm.deepdive.sno;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class SnoApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
