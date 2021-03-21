package aimsmart.memead.aimad.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insert(User users);

    @Delete
    void delete(User user);

    @Delete
    void reset(List<User> users);

    @Query("UPDATE table_name SET Name = :sText , Capital = :sCap ,region = :sReg , Subregion = :sSub , Population = :sPop , border = :sBor , language = :sLang , stat = :sStat WHERE ID = :sID")
    void update(int sID , String  sText,String sCap, String sReg, String sSub , String sPop , String sBor , String sLang, Boolean sStat);

    @Query("SELECT * FROM table_name")
    List<User> getAll();
}
