package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

/**
 * Entity class to store information in the database regarding various types of gear a {@link User}
 * may possess.
 */
@Entity(
    indices = {
        @Index(value = "gear_type"),
        @Index(value = "description")
    })
public class Gear {

  @PrimaryKey
  @ColumnInfo(name = "gear_id")
  private long id;

  @ForeignKey(entity = User.class, parentColumns = "user_id",
      childColumns = "user_id", onDelete = ForeignKey.CASCADE)
  @ColumnInfo(name = "user_id")
  private Long userId;

  private String description;

  @ColumnInfo(name = "gear_type")
  private GearType gearType;

  /**
   * Returns the id of a gear piece.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id of a gear piece.
   * @param id A gear piece id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the id of a {@link User}.
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Sets the id of a {@link User}.
   * @param userId A user id
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * Returns the description of a gear piece.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of a gear piece
   * @param description The description of a gear piece.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the specific type of a gear i.e. Goggles, Helmet, Skis, etc.
   */
  public GearType getGearType() {
    return gearType;
  }

  /**
   * Sets the specific type of a gear i.e. Goggles, Helmet, Skis, etc.
   * @param gearType Gear type to be set
   */
  public void setGearType(GearType gearType) {
    this.gearType = gearType;
  }

  /**
   * Enum class to hold the various types of gear that exist for skiing and snowboarding.
   */
  public enum GearType {
    BOOTS, JACKET, PANTS, GOGGLES, HELMET, SNOWBOARD, GLOVES, SKIS, SOCKS, BASE_LAYER, MID_LAYER;

    /**
     * Converts the gear type to an integer so it can be stored in the database.
     * @param type Type of gear to be stored
     * @return An integer
     */
    @TypeConverter
    public static Integer typeToInt(GearType type) {
      return (type != null ? type.ordinal() : null);
    }

    /**
     * Converts from the integer value of a {@link GearType} to a specific type of gear.
     * @param value Integer value of a gear type.
     * @return A gear type from the database.
     */
    @TypeConverter
    public static GearType intToType(Integer value) {
      return (value != null ? GearType.values()[value] : null);
    }
  }


}
