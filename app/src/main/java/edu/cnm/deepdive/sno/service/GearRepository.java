package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.GearDao;
import edu.cnm.deepdive.sno.model.entity.Gear;
import edu.cnm.deepdive.sno.model.entity.Gear.GearType;
import io.reactivex.Completable;
import java.util.List;

/**
 * Repository class to communicate with the {@link GearDao}.
 */
public class GearRepository {

  private final Context context;
  private final GearDao gearDao;

  /**
   * Constructs an instance of the gear repository.
   * @param context Application context
   */
  public GearRepository(Context context) {
    this.context = context;
    gearDao = SnoDatabase.getInstance().getGearDao();
  }

  /**
   * Saves a piece of gear in the database.
   * @param gear A piece of gear
   * @return A saved piece of gear.
   */
  public Completable save(Gear gear) {
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

  /**
   * Deletes a piece of gear in the database.
   * @param gear A piece of gear
   * @return A deleted piece of gear.
   */
  public Completable delete(Gear gear) {
    return (
        gear.getId() != 0
            ? gearDao.delete(gear).ignoreElement()
            : Completable.complete()
    );
  }

  /**
   * Gets a single piece of gear from the database.
   * @param gearId The id of the gear
   * @return A single piece of gear.
   */
  public LiveData<Gear> getGear(long gearId) {
    return gearDao.getGear(gearId);
  }

  /**
   * Returns all gear stored in the database
   */
  public LiveData<List<Gear>> getAllGear() {
    return gearDao.getAllGear();
  }

  /**
   * Returns a list of gear according to it's specific type according to {@link GearType}.
   * @param gearType The specific type of gear.
   * @return A list of gear by type.
   */
  public LiveData<List<Gear>> getGearByType(GearType gearType) {
    return gearDao.getGearByType(gearType);
  }
}
