package com.refreshactionprovider.fragment;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.refreshactionprovider.R;
import com.refreshactionprovider.widget.RefreshActionProvider;
import com.refreshactionprovider.widget.RefreshActionProvider.OnRefreshListener;

/**
 * Refresh Fragment is a extendable class to use the RefreshActionProvider coupled with a clean loader management
 * @author Alexandre Gherschon
 *
 */
public abstract class RefreshFragment extends SherlockFragment {

	private RefreshActionProvider refreshActionProvider;
	private boolean isLoading;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		inflater.inflate(R.menu.refresh_menu, menu);

		refreshActionProvider = (RefreshActionProvider) menu.findItem(R.id.refresh_action_item).getActionProvider();
		refreshActionProvider.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefreshListener() {
				forceLoad();
			}
		});

		if (isLoading)
			refreshActionProvider.showProgressBar();

		super.onCreateOptionsMenu(menu, inflater);
	}

	public void manageRefreshOnLoaderCreated() {

		isLoading = true;
		if (refreshActionProvider != null)
			refreshActionProvider.showProgressBar();

	}

	public void manageRefreshOnLoaderFinished() {

		isLoading = false;

		if (refreshActionProvider != null)
			refreshActionProvider.showButton();
	}
	

	abstract void forceLoad();
	abstract void load();
}
