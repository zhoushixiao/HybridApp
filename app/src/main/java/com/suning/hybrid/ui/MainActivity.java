package com.suning.hybrid.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.suning.hybrid.R;

public class MainActivity extends AppCompatActivity {

    private Button h5Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h5Btn= (Button) findViewById(R.id.button);
        h5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });
    }

}
