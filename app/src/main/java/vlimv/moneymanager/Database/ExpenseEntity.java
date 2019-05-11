package vlimv.moneymanager.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import vlimv.moneymanager.Models.Expense;

import static android.arch.persistence.room.ForeignKey.NO_ACTION;

@Entity(tableName = "expense_table", foreignKeys = @ForeignKey(entity = CategoryEntity.class,
        parentColumns = "id",
        childColumns = "category_id",
        onDelete = NO_ACTION))
public class ExpenseEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull @ColumnInfo(name = "category_id")
    private int categoryId;

    @NonNull
    private String date;
    @NonNull
    private float sum;

    //constructor
    public ExpenseEntity(int categoryId, String date, float sum) {
        this.categoryId = categoryId;
        this.date = date;
        this.sum = sum;
    }

    private ExpenseEntity(Parcel in){
        this.id = in.readInt();
        this.categoryId = in.readInt();
        this.date = in.readString();
        this.sum = in.readFloat();
    }

    //getters and setters
    public int getId(){return this.id;}
    public void setId(int id) {this.id = id;}

    public int getCategoryId(){return this.categoryId;}
    public void setCategoryId(int categoryId) {this.categoryId = categoryId;}

    public String getDate(){return this.date;}
    public void setDate(String date) {this.date = date;}

    public float getSum(){return this.sum;}
    public void setSum (float sum) {this.sum = sum;}

    public Expense toExpense(){
        String[] array = this.date.split("[.]");
        System.out.println("date " + date);
        int day = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]) + 1;
        int year = Integer.parseInt(array[2]);


        Date dateObject = new GregorianCalendar(year, month, day).getTime();

        return new Expense(this.categoryId, dateObject, this.sum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(categoryId);
        parcel.writeString(date);
        parcel.writeFloat(sum);
    }

    public static final Parcelable.Creator<ExpenseEntity> CREATOR = new Parcelable.Creator<ExpenseEntity>() {

        @Override
        public ExpenseEntity createFromParcel(Parcel source) {
            return new ExpenseEntity(source);
        }

        @Override
        public ExpenseEntity[] newArray(int size) {
            return new ExpenseEntity[size];
        }
    };
}
