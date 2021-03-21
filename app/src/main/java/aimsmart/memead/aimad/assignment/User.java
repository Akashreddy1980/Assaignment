package aimsmart.memead.aimad.assignment;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo( name = "stat")
    public Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @ColumnInfo(name = "Name")
    public String Name;

    @ColumnInfo(name = "capital")
    public String Capital;

    @ColumnInfo(name = "region")
    public String Reg;

    @ColumnInfo(name = "Subregion")
    public String Subreg;

    @ColumnInfo(name = "Population")
    public String Population;

    @ColumnInfo(name = "border")
    public String border;

    @ColumnInfo(name = "language")
    public String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getReg() {
        return Reg;
    }

    public void setReg(String reg) {
        Reg = reg;
    }

    public String getSubreg() {
        return Subreg;
    }

    public void setSubreg(String subreg) {
        Subreg = subreg;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
