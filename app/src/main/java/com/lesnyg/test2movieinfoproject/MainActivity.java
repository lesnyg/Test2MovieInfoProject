package com.lesnyg.test2movieinfoproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, MovieGridFragment.newInstance())
                .commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu item) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, FavoritesListFragment.newInstance())
                        .commit();
                return true;
            case R.id.action_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, MovieGridFragment.newInstance())
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
