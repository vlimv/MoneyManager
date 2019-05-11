package vlimv.moneymanager.Models;

public class Category {
    private String name;
    private int icon;
    private String type;
    private int color;

    public Category(String name, String type, int icon, int color) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.color = color;
    }

    public String getName(){return this.name;}
    public void setName(String name) {this.name = name;}

    public int getIcon(){return this.icon;}
    public void setIcon (int icon) {this.icon = icon;}

    public String getType(){return this.type;}
    public void setType (String type) {this.type = type;}

    public int getColor(){return this.color;}
    public void setColor (int color) {this.color = color;}
}
