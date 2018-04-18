package workholics.com.myin.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import workholics.com.myin.room.entity.User;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by fdc10 on 3/31/2018.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Query("SELECT * FROM User")
    List<User> getAllUser();

    @Query("SELECT * FROM User WHERE email=:email AND password=:password")
    User getUser(String email, String password);

    @Query("DELETE FROM User WHERE name=:name")
    int deleteUser(String name);

    @Query("DELETE FROM User")
    void deletAllUser();
}
