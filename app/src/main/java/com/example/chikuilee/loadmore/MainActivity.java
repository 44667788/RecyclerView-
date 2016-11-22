package com.example.chikuilee.loadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final BaseAdapter adapter = new BaseAdapter(recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.setLoadingMore(new BaseAdapter.OnLoadingMore() {
            @Override
            public void onLoadMore() {
                adapter.setLoading(true);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int count = (int) (Math.random() * 10);
                                for (int i = 0; i < count; ++i) {
                                    adapter.addData(2);
                                }
                                adapter.setLoading(false);
                            }
                        });
                    }
                }.start();
            }
        });
        adapter.addData(1);
        adapter.addData(1);
        adapter.addData(1);
        adapter.addData(1);
        adapter.addData(1);
        adapter.addData(1);
        adapter.addData(1);
        adapter.addData(1);
    }
}
