package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.FavoriteSkiResortDao;
import edu.cnm.deepdive.sno.model.dao.SkiResortDao;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Completable;
import java.util.List;

public class SkiResortRepository {

  private final Context context;
  private final SkiResortDao skiResortDao;
  private final FavoriteSkiResortDao favoriteSkiResortDao;

  public SkiResortRepository(Context context) {
    this.context = context;
    skiResortDao = SnoDatabase.getInstance().getMountainDao();
    favoriteSkiResortDao = SnoDatabase.getInstance().getFavoriteMountainDao();
  }

  private Completable save(SkiResort skiResort) {
    return (
        (skiResort.getSkiResortId() == 0)
            ? skiResortDao.insert(skiResort) // Single<Long>
            .map((id) -> {
              skiResort.setSkiResortId(id);
              return skiResort;
            }) // Single<Mountain>
            : skiResortDao.update(skiResort) // Single<Integer>
                .map((numRecords) -> skiResort) // Single<Mountain>
    )
        .ignoreElement(); // Changes Single to Completable
  }

  private Completable delete(SkiResort skiResort) {
    return (
        (skiResort.getSkiResortId() != 0)
            ? skiResortDao.delete(skiResort).ignoreElement()
            : Completable.complete() // single task that returns a value 0 to the consumer
        );
  }

  private LiveData<List<SkiResort>> getFavorites(User user) {
    return favoriteSkiResortDao.getFavoriteMountains(user.getId());
  }

  private LiveData<SkiResort> getMountain(long mountainId) {
    return skiResortDao.selectSkiResort(mountainId);
  }

  private LiveData<List<SkiResort>> getAllMountains() {
    return skiResortDao.selectAllSkiResorts();
  }

}
