package com.refreshactionprovider.lib.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.actionbarsherlock.view.ActionProvider;
import com.refreshactionprovider.R;

/**
 * Refresh action provider is presenting a button to let the user refresh.
 * To be able to listen to 
 * 
 * @author Gherschon Alexandre
 * 
 */
public class RefreshActionProvider extends ActionProvider {

	public interface OnRefreshListener {

		public void onRefreshListener();
	}

	private static final int BUTTON_VIEW = 0;
	private static final int PROGRESS_VIEW = 1;

	private OnRefreshListener onRefreshClickListener;
	private ViewSwitcher viewSwitcher;

	public RefreshActionProvider(final Context context) {

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

		refreshButton.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View view) {

				showCheatsheet(context, view);
				return true;
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

	/** 
	 * Slightly modified code from Jake Wharton's ABS
	 */
	private void showCheatsheet(Context context, View view) {

		final int[] screenPos = new int[2];
		final Rect displayFrame = new Rect();
		view.getLocationOnScreen(screenPos);
		view.getWindowVisibleDisplayFrame(displayFrame);

		final int width = view.getWidth();
		final int height = view.getHeight();
		final int midy = screenPos[1] + height / 2;
		final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;

		Toast cheatSheet = Toast.makeText(context, context.getString(R.string.refresh), Toast.LENGTH_SHORT);
		if (midy < displayFrame.height()) {
			// Show along the top; follow action buttons
			cheatSheet.setGravity(Gravity.TOP | Gravity.RIGHT, screenWidth - screenPos[0] - width / 2, height);
		} else {
			// Show along the bottom center
			cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
		}
		cheatSheet.show();

	}

}