package workholics.com.myin.room.DBHelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import workholics.com.myin.room.dao.UserDao;
import workholics.com.myin.room.entity.User;

/**
 * Created by fdc10 on 3/27/2018.
 */


@Database(entities = {User.class},version = 1)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE=null;

    public static AppDatabase getINSTANCE(Context context){

        if(INSTANCE==null){

            INSTANCE= Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();

            return INSTANCE;

        }

        return INSTANCE;
    }

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyAppData() {
        INSTANCE = null;
    }

    public abstract UserDao userDao();



}
