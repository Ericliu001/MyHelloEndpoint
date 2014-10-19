package com.ericliu.myhelloendpoint;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mymodule.backend.myApi.model.MyCard;

import de.greenrobot.event.EventBus;


public class HomeActivity extends Activity {
    EventBus bus = EventBus.getDefault();

    TextView tvId;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvId = (TextView)findViewById(R.id.tvId);
        tvName = (TextView)findViewById(R.id.tvName);

    }


    public void onButtonClicked(View view){
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
    }

    public void onEventMainThread(MyCard card){
        tvId.setText( String.valueOf(card.getId()));
        tvName.setText(card.getName());

    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
