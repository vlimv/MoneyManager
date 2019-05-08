package vlimv.moneymanager.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by HP on 25-Oct-18.
 */
@Entity (tableName = "category_table")
public class CategoryEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    private int icon;
    private String type;
    @NonNull
    private int color;

    //constructor
    public CategoryEntity(String name, String type, int icon, int color) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.color = color;
    }

    //getters and setters
    public int getId(){return this.id;}
    public void setId(int id) {this.id = id;}

    public String getName(){return this.name;}
    public void setName(String name) {this.name = name;}

    public int getIcon(){return this.icon;}
    public void setIcon (int icon) {this.icon = icon;}

    public String getType(){return this.type;}
    public void setType (String type) {this.type = type;}

    public int getColor(){return this.color;}
    public void setColor (int color) {this.color = color;}

}
