package workholics.com.myin.room;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import workholics.com.myin.R;
import workholics.com.myin.room.DBHelper.AppDatabase;
import workholics.com.myin.room.adapter.UserAdapter;
import workholics.com.myin.room.entity.User;

public class RoomMainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_record;
    EditText et_name, et_lname, et_email, et_lemail, et_demail, et_password, et_lpassword;
    Button btn_register, btn_login, btn_delete;
    AppDatabase mDb;
    RecyclerView rcy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);
        init();

        getAllREcord();
    }


    private void init() {
        mDb = AppDatabase.getAppDatabase(getApplicationContext());

        rcy = (RecyclerView) findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_delete = (Button) findViewById(R.id.btn_delete);


        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_lemail = (EditText) findViewById(R.id.et_lemail);
        et_demail = (EditText) findViewById(R.id.et_demail);
        et_password = (EditText) findViewById(R.id.et_password);
        et_lpassword = (EditText) findViewById(R.id.et_lpassword);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_delete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_register:
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String pwd = et_password.getText().toString();

                if(validRegister(name, email, pwd)) {
                    insertRecord(name, email, pwd);

                    et_name.setText("");
                    et_email.setText("");
                    et_password.setText("");
                }
                getAllREcord();

                break;

            case R.id.btn_login:

                String email1 = et_lemail.getText().toString();
                String pwd1 = et_lpassword.getText().toString();


                if(validLogin(email1,pwd1)) {
                    getUser(email1, pwd1);

                }

                break;

            case R.id.btn_delete:

                if(validDelete(et_demail.getText().toString())) {
                    deleteUser(et_demail.getText().toString());
                }



                getAllREcord();
                break;


        }

    }

    User insertRecord(String name, String email, String pwd) {
        User user = new User();
        long time = System.currentTimeMillis();
        user.id = "" + time;
        user.email = email;
        user.name = name;
        user.password = pwd;
        mDb.userDao().insertUser(user);
        Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show();

        Log.e("Insert Record", "");

        return user;
    }


    User getUser(String email, String pwd) {


        User user = mDb.userDao().getUser(email, pwd);

        if(user==null) {

            Toast.makeText(this, "Invalid email and password", Toast.LENGTH_SHORT).show();


        }else {

            if (email.equalsIgnoreCase(user.email)) {
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show();

                et_lemail.setText("");
                et_lpassword.setText("");
            }
        }

        return user;
    }


    int deleteUser(String name) {

        int user = mDb.userDao().deleteUser(name);

        Log.e("Delete  Record", "" + user);

        Toast.makeText(this, "Delete Record :-" + user, Toast.LENGTH_SHORT).show();
        et_demail.setText("");

        return user;
    }

    void getAllREcord() {


        List<User> user = mDb.userDao().getAllUser();
        if (user.size() > 0) {

            UserAdapter userAdapter = new UserAdapter(this, user);
            rcy.setAdapter(userAdapter);
        } else {
            Toast.makeText(this, "No record", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean validRegister(String name, String email,String  pwd) {

        if (name.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return false;
        } else if (email.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return false;
        } else if (pwd.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private Boolean validLogin(String email,String pwd) {

        if (email.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return false;
        } else if (pwd.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private Boolean validDelete(String name) {

        if (name.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
