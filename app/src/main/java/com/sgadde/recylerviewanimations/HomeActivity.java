package com.sgadde.recylerviewanimations;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


public class HomeActivity extends Activity {

    RecyclerView recyclerView;
    AnimationAdapter animationAdapter;
    int numColumns = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numColumns));

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        animationAdapter = new AnimationAdapter(recyclerAdapter);
        animationAdapter.setNumberOfColumns(numColumns);
        animationAdapter.setAnimationType(AnimationAdapter.FADE_IN);
        recyclerView.setAdapter(animationAdapter);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_fade) {
            setAnimationTypeAndRefresh(AnimationAdapter.FADE_IN);
            return true;
        } else if (id == R.id.action_swing_bottom) {
            setAnimationTypeAndRefresh(AnimationAdapter.SWING_IN_BOTTOM);
            return true;
        } else if (id == R.id.action_swing_right) {
            setAnimationTypeAndRefresh(AnimationAdapter.SWING_IN_RIGHT);
            return true;
        } else if (id == R.id.action_scale) {
            setAnimationTypeAndRefresh(AnimationAdapter.SCALE_IN);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setAnimationTypeAndRefresh(int animationType) {
        animationAdapter.setAnimationType(animationType);
        animationAdapter.notifyDataSetChanged();
    }
}
