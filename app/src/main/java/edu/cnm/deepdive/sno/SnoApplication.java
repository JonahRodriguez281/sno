package edu.cnm.deepdive.sno;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.sno.service.SnoDatabase;
import io.reactivex.schedulers.Schedulers;

public class SnoApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    SnoDatabase.setContext(this);
    SnoDatabase.getInstance().getMountainDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }
}
