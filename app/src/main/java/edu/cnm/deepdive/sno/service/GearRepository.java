package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.GearDao;
import edu.cnm.deepdive.sno.model.entity.Gear;
import edu.cnm.deepdive.sno.model.entity.Gear.GearType;
import edu.cnm.deepdive.sno.model.entity.Trip;
import io.reactivex.Completable;
import java.util.List;

public class GearRepository {

  private final Context context;
  private final GearDao gearDao;

  public GearRepository(Context context) {
    this.context = context;
    gearDao = SnoDatabase.getInstance().getGearDao();
  }

  private Completable save(Gear gear) {
    return (
        (gear.getId() == 0)
            ? gearDao.insert(gear)
            .map((id) -> {
              gear.setId(id);
              return gear;
            })
            : gearDao.update(gear)
                .map((num) -> gear)
    )
        .ignoreElement();
  }

  private Completable delete(Gear gear) {
    return (
        gear.getId() != 0
            ? gearDao.delete(gear).ignoreElement()
            : Completable.complete()
    );
  }

  private LiveData<Gear> getGear(long gearId) {
    return gearDao.getGear(gearId);
  }

  private LiveData<List<Gear>> getAllGear() {
    return gearDao.getAllGear();
  }

  private LiveData<List<Gear>> getGearByType(GearType gearType) {
    return gearDao.getGearByType(gearType);
  }
}
