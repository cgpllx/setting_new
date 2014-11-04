package com.kubeiwu.commontool.view.setting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

public abstract class DialogRowView extends RowView implements OnDismissListener, android.content.DialogInterface.OnClickListener {

	protected DialogRowView(Context context) {
		super(context);
	}

	/**
	 * Prepares the dialog builder to be shown when the preference is clicked. Use this to set custom properties on the dialog.
	 * <p>
	 * Do not {@link AlertDialog.Builder#create()} or {@link AlertDialog.Builder#show()}.
	 */
	protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
	}

	AlertDialog.Builder mBuilder;
	private CharSequence mPositiveButtonText = "11";
	private CharSequence mNegativeButtonText = "22";
	private int mWhichButtonClicked;
	/** The dialog, if it is showing. */
	private Dialog mDialog;

	public void showDialog() {
		Context context = getContext();
		mBuilder = new AlertDialog.Builder(context)//
				.setTitle(getTitle())//
				.setPositiveButton(mPositiveButtonText, this)//
				.setNegativeButton(mNegativeButtonText, this);
		onPrepareDialogBuilder(mBuilder);
		final Dialog dialog = mDialog = mBuilder.create();

		dialog.setOnDismissListener(this);
		dialog.show();
	}

	public void onClick(DialogInterface dialog, int which) {
		mWhichButtonClicked = which;
	}

	/**
	 * Gets the dialog that is shown by this preference.
	 * 
	 * @return The dialog, or null if a dialog is not being shown.
	 */
	public Dialog getDialog() {
		return mDialog;
	}

	public void onDismiss(DialogInterface dialog) {
		mDialog = null;
		onDialogClosed(mWhichButtonClicked == DialogInterface.BUTTON_POSITIVE);
	}

	protected void onDialogClosed(boolean positiveResult) {
	}

	public void setPositiveButtonText(CharSequence positiveButtonText) {
		mPositiveButtonText = positiveButtonText;
	}

	public void setPositiveButtonText(int positiveButtonTextResId) {
		setPositiveButtonText(getContext().getString(positiveButtonTextResId));
	}

	public CharSequence getPositiveButtonText() {
		return mPositiveButtonText;
	}

	public void setNegativeButtonText(CharSequence negativeButtonText) {
		mNegativeButtonText = negativeButtonText;
	}

}
