package edu.cnm.deepdive.sno.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@TypeConverters({TypeConverter.class})
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

  private enum Type {
    BOOTS, JACKET, PANTS, GOGGLES, HELMET, GLOVES, SNOWBOARD, SKIS, SOCKS, BASE_LAYER, MID_LAYER
  }

  public static class TypeConverter {

    public static String typeToString(Type type) {
      return type.toString();
    }
  }

}
