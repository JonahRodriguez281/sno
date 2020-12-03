package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * Joint entity class for {@link User} and {@link SkiResort} to record a list of favorite ski
 * resorts for a user, storing the favorite ski resort in the database.
 */
@Entity(
    primaryKeys = {"user_id", "ski_resort_id"},
    foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "user_id",
            childColumns = "user_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = SkiResort.class, parentColumns = "ski_resort_id",
            childColumns = "ski_resort_id", onDelete = ForeignKey.CASCADE)
    }
)
public class FavoriteSkiResort {

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @ColumnInfo(name = "ski_resort_id", index = true)
  private long skiResortId;

  /**
   * Returns the id of a {@link User}.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets the id of a {@link User}.
   * @param userId A user id
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Returns the id of a {@link SkiResort}.
   */
  public long getSkiResortId() {
    return skiResortId;
  }

  /**
   * Sets the id of a {@link SkiResort}.
   * @param skiResortId A ski resort id
   */
  public void setSkiResortId(long skiResortId) {
    this.skiResortId = skiResortId;
  }
}
