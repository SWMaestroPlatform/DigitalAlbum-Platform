package sympathyhome.iot.soma.smarthometabletapp;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
    }

    private Timer mTimer;
    public TimerTask mTimerTask;

    private final int mTimerDuration = 30000;
    private final int mTimerStartDuration = 3000;

    public void initActivity(){

        mTimer = new Timer(true);

//        mHandler = new Handler();

//        textViewTimer = (TextView) findViewById(R.id.text_time);
//        textViewTimer1 = (TextView) findViewById(R.id.text_time1);



        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                UpdateGUI();
            }
        }, mTimerStartDuration, mTimerDuration);
    }

    private void UpdateGUI() {
        //tv.setText(String.valueOf(i));
        startActivity(new Intent(MainActivity.this, AlbumPhotoActivity.class));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
