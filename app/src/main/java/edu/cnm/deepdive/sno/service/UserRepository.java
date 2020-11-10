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

public class UserRepository {

  private final Context context;
  private final UserDao userDao;
  private final FavoriteSkiResortDao favoriteSkiResortDao;
  private final GoogleSignInService signInService;
  private final SnoWebService webService;

  public UserRepository(Context context) {
    this.context = context;
    userDao = SnoDatabase.getInstance().getUserDao();
    favoriteSkiResortDao = SnoDatabase.getInstance().getFavoriteSkiResortDao();
    signInService = GoogleSignInService.getInstance();
    webService = SnoWebService.getInstance();
  }

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

  public Single<User> getServerUserProfile() {
    return signInService.refresh()
        .observeOn(Schedulers.io())
        .flatMap((account) -> webService.getProfile(getBearerToken(account.getIdToken()))
            .flatMap((user) -> userDao.getUserOauthKey(account.getId())
                .flatMap((localUser) -> {
                  localUser.setDisplayName(user.getDisplayName());
                  return userDao.update(localUser) // take the user and update it to the database
                      .map((count) -> localUser);
                })
            )
        );
  }

  private String getBearerToken(String idToken) {
    return String.format("Bearer %s", idToken);
  }

  private LiveData<List<User>> getAllUsersFavoriteSkiResorts(SkiResort skiResort) {
    return favoriteSkiResortDao.getUsersForFavoriteSkiResorts(skiResort.getSkiResortId());
  }
}
