package com.example.chikuilee.loadmore;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chikuilee on 2016/11/22.
 */

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    final String TAG = "BaseViewHolder";

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void image(@IdRes int id, String url) {
        //加载图片
    }

    public void text(@IdRes int id, CharSequence text) {
        TextView textView = (TextView) itemView.findViewById(id);
        textView.setText(text);
    }
    public void bindData(T t){}
}
