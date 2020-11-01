package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.TypeConverter;

@Entity(
    indices = {
        @Index(value = "type"),
        @Index(value = "description")
    })
public class Gear {

  @ColumnInfo(name = "gear_id")
  private long id;

  @ForeignKey(entity = User.class, parentColumns = "user_id",
      childColumns = "user_id", onDelete = ForeignKey.CASCADE)
  @ColumnInfo(name = "user_id")
  private long userId;

  @ColumnInfo(name = "description")
  private String description;

  @ColumnInfo(index = true)
  private GearType gearType;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public GearType getGearType() {
    return gearType;
  }

  public void setGearType(GearType gearType) {
    this.gearType = gearType;
  }

  public enum GearType {
    BOOTS, JACKET, PANTS, GOGGLES, HELMET, GLOVES, SNOWBOARD, SKIS, SOCKS, BASE_LAYER, MID_LAYER;

    @TypeConverter
    public static String typeToString(GearType type) {
      return (type != null ? type.toString() : null);
    }

    @TypeConverter
    public GearType stringToType(String name) {
      return (name != null ? GearType.valueOf(name) : null);
    }
  }


}
