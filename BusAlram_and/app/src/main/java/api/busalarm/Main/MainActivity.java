package api.busalarm.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import api.busalarm.BusGps.BusGps;
import api.busalarm.BusSearch.BusSearch;
import api.busalarm.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.first).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent_act = new Intent(getApplicationContext(), BusSearch.class);
                        startActivity(intent_act);
                    }
                }
        );

        findViewById(R.id.second).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent_act = new Intent(getApplicationContext(), BusGps.class);
                        startActivity(intent_act);
                    }
                }
        );
    }
}