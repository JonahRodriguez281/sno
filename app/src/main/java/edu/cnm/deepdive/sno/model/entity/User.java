package edu.cnm.deepdive.sno.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entity class to store user credentials in the database.
 */
@Entity(
    indices = @Index(value = "oauth_Key", unique = true)
)
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long id;

  @ColumnInfo(name = "oauth_Key")
  private String oauthKey;

  @NonNull
  @ColumnInfo(name = "display_name")
  private String displayName;

  /**
   * Returns the id of a user.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id of a user.
   * @param id A user id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the OAuthKey of a user.
   */
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Sets the OAuthKey for a user.
   * @param oauthKey An OAuthKey
   */
  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  /**
   * Returns the display name of a user.
   */
  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Sets the display name of a user.
   * @param displayName The name of the user
   */
  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }
}
