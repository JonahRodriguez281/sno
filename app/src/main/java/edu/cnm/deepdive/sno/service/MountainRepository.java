package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.FavoriteMountainDao;
import edu.cnm.deepdive.sno.model.dao.MountainDao;
import edu.cnm.deepdive.sno.model.entity.Mountain;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

public class MountainRepository {

  private final Context context;
  private final MountainDao mountainDao;
  private final FavoriteMountainDao favoriteMountainDao;

  public MountainRepository(Context context) {
    this.context = context;
    mountainDao = SnoDatabase.getInstance().getMountainDao();
    favoriteMountainDao = SnoDatabase.getInstance().getFavoriteMountainDao();
  }

  private Completable save(Mountain mountain) {
    return (
        (mountain.getMountainId() == 0)
            ? mountainDao.insert(mountain) // Single<Long>
            .map((id) -> {
              mountain.setMountainId(id);
              return mountain;
            }) // Single<Mountain>
            : mountainDao.update(mountain) // Single<Integer>
                .map((numRecords) -> mountain) // Single<Mountain>
    )
        .ignoreElement(); // Changes Single to Completable
  }

  private Completable delete(Mountain mountain) {
    return (
        (mountain.getMountainId() != 0)
            ? mountainDao.delete(mountain).ignoreElement()
            : Completable.complete() // single task that returns a value 0 to the consumer
        );
  }

  private LiveData<List<Mountain>> getFavorites(User user) {
    return favoriteMountainDao.getFavoriteMountains(user.getId());
  }

  private LiveData<Mountain> getMountain(long mountainId) {
    return mountainDao.selectMountain(mountainId);
  }

  private LiveData<List<Mountain>> getAllMountains() {
    return mountainDao.selectAllMountains();
  }

}
