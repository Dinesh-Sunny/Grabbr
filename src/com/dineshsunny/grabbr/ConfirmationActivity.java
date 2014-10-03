package com.dineshsunny.grabbr;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ConfirmationActivity extends Activity {

	public ImageView resendImage;
	public ImageView doneImage;

	// ////////////////////////////////////////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation);

		// hiding the action bar
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		// hiding the action bar done

		// hiding Keyboard method
		setupUI(findViewById(R.id.confirmationparent));
		// hiding Keyboard method done

		resendButton();
		doneButton();

	}

	// ///////////////////////////////////////////////////////////

	private void doneButton() {
		doneImage = (ImageView) findViewById(R.id.confirmation_done_circle);
		doneImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(),
				// R.string.signup_done_button_toast, Toast.LENGTH_SHORT)
				// .show();

				startActivity(new Intent(ConfirmationActivity.this,
						ConfirmationDoneActivity.class));

			}
		});

	}

	private void resendButton() {
		resendImage = (ImageView) findViewById(R.id.confirmation_resend_circle);
		resendImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						R.string.confirmation_soon_you_will_get_message,
						Toast.LENGTH_SHORT).show();

			}
		});
	}

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

	@SuppressLint("ClickableViewAccessibility")
	public void setupUI(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(ConfirmationActivity.this);
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirmation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
