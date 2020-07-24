package sg.edu.rp.c346.p10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    int reqCode = 12345;

    Button btnLater;
    ArrayList <Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLater = findViewById(R.id.btnLater);
        vPager = findViewById(R.id.viewPager);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Fragment1());
        al.add(new Fragment2());
        al.add(new Fragment3());

        adapter = new MyFragmentPagerAdapter(fm, al);

        vPager.setAdapter(adapter);
        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 300);

                Intent intent = new Intent(MainActivity.this,
                        Notification.class);
                intent.putExtra("text", "yes");


                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);


                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.previous:
                if (vPager.getCurrentItem() > 0){
                    int previousPage = vPager.getCurrentItem() - 1;
                    vPager.setCurrentItem(previousPage, true);
                    return true;

                }
                return true;


            case R.id.next:

                int max = vPager.getChildCount();
                if (vPager.getCurrentItem() < max-1){
                    int nextPage = vPager.getCurrentItem() + 1;
                    vPager.setCurrentItem(nextPage, true);
                    return true;
                }
                return true;

            case R.id.random:
                Random rnd = new Random();
                int index = rnd.nextInt(vPager.getChildCount());
                vPager.setCurrentItem(index, true);


        }
        return super.onOptionsItemSelected(item);
    }
}
