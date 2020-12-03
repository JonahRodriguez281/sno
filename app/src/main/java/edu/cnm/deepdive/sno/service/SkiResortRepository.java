package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.sno.model.dao.FavoriteSkiResortDao;
import edu.cnm.deepdive.sno.model.dao.SkiResortDao;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Completable;
import java.util.List;

/**
 * Repository class to communicate with the {@link SkiResortDao}.
 */
public class SkiResortRepository {

  private final Context context;
  private final SkiResortDao skiResortDao;
  private final FavoriteSkiResortDao favoriteSkiResortDao;

  /**
   * Constructs an instance of the ski resort repository.
   * @param context Application context
   */
  public SkiResortRepository(Context context) {
    this.context = context;
    skiResortDao = SnoDatabase.getInstance().getSkiResortDao();
    favoriteSkiResortDao = SnoDatabase.getInstance().getFavoriteSkiResortDao();
  }

  /**
   * Saves/updates a ski resort to the database
   * @param skiResort A {@link SkiResort}
   * @return A saved ski resort in the database.
   */
  public Completable save(SkiResort skiResort) {
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

  /**
   * Deletes a {@link SkiResort} from the database.
   * @param skiResort A ski resort
   * @return A deleted ski resort from the database.
   */
  public Completable delete(SkiResort skiResort) {
    return (
        (skiResort.getSkiResortId() != 0)
            ? skiResortDao.delete(skiResort).ignoreElement()
            : Completable.complete() // single task that returns a value 0 to the consumer
        );
  }

  /**
   * Gets a list of favorite ski resorts for a {@link User}
   * @param user A {@link User}
   * @return A list of ski resorts.
   */
  public LiveData<List<SkiResort>> getFavorites(User user) {
    return favoriteSkiResortDao.getFavoriteSkiResorts(user.getId());
  }

  /**
   * Returns a single ski resort according to its id.
   * @param skiResortId A ski resort id
   * @return A ski resort
   */
  public LiveData<SkiResort> getSkiResort(long skiResortId) {
    return skiResortDao.selectSkiResort(skiResortId);
  }

  /**
   * Returns a list of all ski resorts
   */
  public LiveData<List<SkiResort>> getAllSkiResorts() {
    return skiResortDao.selectAllSkiResorts();
  }

}
