package com.example.employeeinfo;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button click;
    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click=(Button) findViewById(R.id.bt1);
        data=(TextView) findViewById(R.id.data);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data process= new data();
                process.execute();
                Log.d("myTag", "This is my message");


            }
        });


    }
}
