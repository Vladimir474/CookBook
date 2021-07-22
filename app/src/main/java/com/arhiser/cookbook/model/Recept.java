package com.arhiser.cookbook.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Recept implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name_dish")
    public String name_dish;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "ingredients")
    public String ingredients;

    @ColumnInfo(name = "preparation")
    public String preparation;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "favorite")
    public boolean favorite;

    public Recept() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recept)) return false;
        Recept recept = (Recept) o;
        return uid == recept.uid &&
                favorite == recept.favorite &&
                name_dish.equals(recept.name_dish) &&
                description.equals(recept.description) &&
                ingredients.equals(recept.ingredients) &&
                preparation.equals(recept.preparation) &&
                time.equals(recept.time);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(uid, name_dish, description, ingredients, preparation, time, favorite);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(name_dish);
        dest.writeString(description);
        dest.writeString(ingredients);
        dest.writeString(preparation);
        dest.writeString(time);
        dest.writeByte((byte) (favorite ? 1 : 0));

    }

    protected Recept(Parcel in) {
        uid = in.readInt();
        name_dish = in.readString();
        description = in.readString();
        ingredients = in.readString();
        preparation = in.readString();
        time = in.readString();
        favorite = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recept> CREATOR = new Creator<Recept>() {
        @Override
        public Recept createFromParcel(Parcel in) {
            return new Recept(in);
        }

        @Override
        public Recept[] newArray(int size) {
            return new Recept[size];
        }
    };
}
