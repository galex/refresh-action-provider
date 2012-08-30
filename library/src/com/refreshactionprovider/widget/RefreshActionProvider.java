package com.refreshactionprovider.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

import com.actionbarsherlock.view.ActionProvider;
import com.refreshactionprovider.R;

public class RefreshActionProvider extends ActionProvider {

    public interface OnRefreshListener {

        public void onRefreshListener();
    }

    private static final int BUTTON_VIEW = 0;
    private static final int PROGRESS_VIEW = 1;

    private OnRefreshListener onRefreshClickListener;
    private ViewSwitcher viewSwitcher;

    public RefreshActionProvider(Context context) {
    	
        super(context);
        
        viewSwitcher = (ViewSwitcher) LayoutInflater.from(context).inflate(R.layout.refresh_action_item, null);

        ImageButton refreshButton = (ImageButton) viewSwitcher.findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                viewSwitcher.setDisplayedChild(PROGRESS_VIEW);

                if (onRefreshClickListener != null)
                    onRefreshClickListener.onRefreshListener();
            }
        });

    }

    @Override
    public View onCreateActionView() {
        return viewSwitcher;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
    	
        this.onRefreshClickListener = onRefreshListener;
    }

    public void showButton() {

        viewSwitcher.setDisplayedChild(BUTTON_VIEW);
    }

    public void showProgressBar() {

        viewSwitcher.setDisplayedChild(PROGRESS_VIEW);
    }

}