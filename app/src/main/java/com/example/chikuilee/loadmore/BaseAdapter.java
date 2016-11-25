package com.example.chikuilee.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chikuilee on 2016/11/22.
 */

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    List<T> dataSet = new ArrayList<>();
    private final int TYPE_LOAD_MORE = 100;
    private final int TYPE_NORMAL = 101;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private boolean canLoadMore = true;
    OnLoadingMore loadingMore;

    public BaseAdapter(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                Log.i("lastPosition --> ", lastPosition + "");
                Log.i("itemCount  --> ", itemCount + " ");

                if (canLoadMore && !isLoading && (lastPosition >= (itemCount - visibleThreshold))) {
                    if (loadingMore != null) {
                        isLoading = true;
                        loadingMore.onLoadMore();
                    }
                }
            }
        });
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == TYPE_LOAD_MORE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.pb_loading);
            progressBar.setInterpolator(new AccelerateInterpolator(2));
            progressBar.setIndeterminate(true);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal, parent, false);
        }
        return new BaseViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_LOAD_MORE) {
            View itemView = holder.itemView;
            if (canLoadMore && isLoading) {
                if (itemView.getVisibility() != View.VISIBLE) {
                    itemView.setVisibility(View.VISIBLE);
                }
            } else if (itemView.getVisibility() == View.VISIBLE) {
                itemView.setVisibility(View.GONE);
            }
        } else {
            TextView textView = (TextView) holder.itemView;
            textView.setText(String.valueOf(position));
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_LOAD_MORE;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void setLoadingMore(OnLoadingMore loadingMore) {
        this.loadingMore = loadingMore;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void addData(T t) {
        dataSet.add(t);
        notifyDataSetChanged();
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    interface OnLoadingMore {
        void onLoadMore();
    }

}
