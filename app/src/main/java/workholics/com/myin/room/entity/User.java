package workholics.com.myin.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by fdc10 on 3/31/2018.
 */


@Entity
public class User {

    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String email;
    public String password;
}
