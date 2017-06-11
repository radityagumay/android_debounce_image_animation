package net.radityalabs.debounceimage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Debounce> items;

    private RecyclerView images;
    private DebounceAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        images = (RecyclerView) findViewById(R.id.rv_image);

        items = new ArrayList<>();
        imageAdapter = new DebounceAdapter(items);

        images.setItemAnimator(new DefaultItemAnimator());
        images.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        images.setAdapter(imageAdapter);

        items.addAll(builder());
        imageAdapter.notifyDataSetChanged();
    }

    private List<Debounce> builder() {
        List<Debounce> items = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            Debounce item = new Debounce();
            item.background = R.drawable.bg_no1;
            item.foreground = R.drawable.advisor_sample;
            items.add(item);
        }
        return items;
    }
}
