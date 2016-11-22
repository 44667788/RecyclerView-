package com.example.chikuilee.loadmore;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by chikuilee on 2016/11/22.
 */

public class ProgressHolder extends BaseViewHolder {
    public ProgressHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object o) {
        ProgressBar progressBar= (ProgressBar) itemView;
        progressBar.setIndeterminate(true);
    }
}
