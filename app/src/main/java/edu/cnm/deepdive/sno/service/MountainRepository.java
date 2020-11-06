package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.FavoriteMountainDao;
import edu.cnm.deepdive.sno.model.dao.MountainDao;
import edu.cnm.deepdive.sno.model.entity.Mountain;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Completable;
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
        (mountain.getMountainId() == 0)
            ? mountainDao.delete(mountain)
            .map((id) -> {
              mountain.setMountainId(id);
              return mountain;
            })
            : mountainDao.update(mountain)
                .map((numRecords) -> mountain)
        )
        .ignoreElement();
  }

  private LiveData<List<Mountain>> getFavorites(User user) {
    return favoriteMountainDao.getFavoriteMountains(user.getId());
  }

  //delete method, similar to save
  //things youd expect UI to do for a user with a mountain
}
