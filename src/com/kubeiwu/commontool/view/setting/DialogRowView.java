package com.kubeiwu.commontool.view.setting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public abstract class DialogRowView extends RowView implements OnDismissListener, android.content.DialogInterface.OnClickListener {

	public DialogRowView(Context context) {
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
	private CharSequence mPositiveButtonText = "确定";
	private CharSequence mNegativeButtonText = "取消";
	private int mWhichButtonClicked;
	/** The dialog, if it is showing. */
	private Dialog mDialog;
	private CharSequence mDialogMessage;
	private int mDialogLayoutResId;

	public void showDialog() {
		Context context = getContext();
		mBuilder = new AlertDialog.Builder(context)//
				.setTitle(getTitle())//
				.setPositiveButton(mPositiveButtonText, this)//
				.setNegativeButton(mNegativeButtonText, this);

		View contentView = onCreateDialogView();
		if (contentView != null) {
			onBindDialogView(contentView);
			mBuilder.setView(contentView);
		} else {
			mBuilder.setMessage(mDialogMessage);
		}
		onPrepareDialogBuilder(mBuilder);
		final Dialog dialog = mDialog = mBuilder.create();

		dialog.setOnDismissListener(this);
		dialog.show();
	}

	public void onClick(DialogInterface dialog, int which) {
		mWhichButtonClicked = which;
	}

	public void setDialogLayoutResource(int dialogLayoutResId) {
		mDialogLayoutResId = dialogLayoutResId;
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

	public CharSequence getDialogMessage() {
		return mDialogMessage;
	}

	protected View onCreateDialogView() {
		if (mDialogLayoutResId == 0) {
			return null;
		}

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return inflater.inflate(mDialogLayoutResId, null);
	}

	protected void onBindDialogView(View view) {
		View dialogMessageView = view.findViewById(android.R.id.message);

		if (dialogMessageView != null) {
			final CharSequence message = getDialogMessage();
			int newVisibility = View.GONE;

			if (!TextUtils.isEmpty(message)) {
				if (dialogMessageView instanceof TextView) {
					((TextView) dialogMessageView).setText(message);
				}

				newVisibility = View.VISIBLE;
			}

			if (dialogMessageView.getVisibility() != newVisibility) {
				dialogMessageView.setVisibility(newVisibility);
			}
		}
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
