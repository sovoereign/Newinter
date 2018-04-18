package workholics.com.myin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import workholics.com.myin.map.MapsActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_map=(Button)findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goMap(v);

            }
        });
    }


    private void goMap(View view){

        startActivity(new Intent(this,MapsActivity.class));

    }
}
