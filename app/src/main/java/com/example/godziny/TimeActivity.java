package com.example.godziny;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeActivity extends AppCompatActivity {

    private TextView time;
    private TextView newYorkTimeTextView;
    private TextView londonTimeTextView;
    private TextView tokyoTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                time = findViewById(R.id.time);
                                newYorkTimeTextView = findViewById(R.id.newYorkTimeTextView);
                                londonTimeTextView = findViewById(R.id.londonTimeTextView);
                                tokyoTimeTextView = findViewById(R.id.tokyoTimeTextView);

                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                                String currentTime = sdf.format(new Date());
                                time.setText(currentTime);

                                TimeZone newYorkTimeZone = TimeZone.getTimeZone("America/New_York");
                                TimeZone londonTimeZone = TimeZone.getTimeZone("Europe/London");
                                TimeZone tokyoTimeZone = TimeZone.getTimeZone("Asia/Tokyo");

                                sdf.setTimeZone(newYorkTimeZone);
                                String newYorkTime = sdf.format(new Date());
                                newYorkTimeTextView.setText(newYorkTime);

                                sdf.setTimeZone(londonTimeZone);
                                String londonTime = sdf.format(new Date());
                                londonTimeTextView.setText(londonTime);

                                sdf.setTimeZone(tokyoTimeZone);
                                String tokyoTime = sdf.format(new Date());
                                tokyoTimeTextView.setText(tokyoTime);


                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }
}