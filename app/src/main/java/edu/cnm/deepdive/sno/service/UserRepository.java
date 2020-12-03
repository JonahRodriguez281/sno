package edu.cnm.deepdive.sno.service;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.sno.model.dao.FavoriteSkiResortDao;
import edu.cnm.deepdive.sno.model.dao.UserDao;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import edu.cnm.deepdive.sno.model.entity.User;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Repository class to communicate with the {@link UserDao} and {@link FavoriteSkiResortDao} as well
 * as handle the Google Sign In Service.
 */
public class UserRepository {

  private final Context context;
  private final UserDao userDao;
  private final FavoriteSkiResortDao favoriteSkiResortDao;
  private final GoogleSignInService signInService;
  private final SnoWebService webService;

  /**
   * Constructs an instance of the {@link UserRepository}.
   * @param context Application context
   */
  public UserRepository(Context context) {
    this.context = context;
    userDao = SnoDatabase.getInstance().getUserDao();
    favoriteSkiResortDao = SnoDatabase.getInstance().getFavoriteSkiResortDao();
    signInService = GoogleSignInService.getInstance();
    webService = SnoWebService.getInstance();
  }

  /**
   * Creates a user to store in the database.
   * @param account Google account to store in the database.
   * @return A saved {@link User}
   */
  @SuppressWarnings("ConstantConditions")
  public Single<User> createUser(@NonNull GoogleSignInAccount account) {
    return Single.fromCallable(() -> {
      User user = new User();
      user.setDisplayName(account.getDisplayName());
      user.setOauthKey(account.getId());
      return user;
    })
        .flatMap((user) ->
            userDao.insert(user)
                .map((id) -> {
                  if (id > 0) {
                    user.setId(id);
                  }
                  return user;
                })
        )
        .subscribeOn(Schedulers.io());
  }

  /**
   * Returns a list of users that marked a specific ski resort as a favorite.
   * @param skiResort Id of {@link SkiResort}
   * @return A list of {@link User}
   */
  public LiveData<List<User>> getAllUsersFavoriteSkiResorts(SkiResort skiResort) {
    return favoriteSkiResortDao.getUsersForFavoriteSkiResorts(skiResort.getSkiResortId());
  }

  private String getBearerToken(String idToken) {
    return String.format("Bearer %s", idToken);
  }
}
