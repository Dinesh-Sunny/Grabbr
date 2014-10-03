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
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity {

	protected Button mCancelButton;
	protected Button mDoneButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		// hiding the action bar
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		// hiding the action bar done

		// hiding Keyboard method
		setupUI(findViewById(R.id.signupparent));
		// hiding Keyboard method done

		cancelButton();
		DoneButton();

	}

	private void DoneButton() {
		mDoneButton = (Button) findViewById(R.id.signup_done_button);
		mDoneButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(),
				// R.string.signup_done_button_toast, Toast.LENGTH_LONG).show();
				Intent doneIntent = new Intent(getApplicationContext(),
						ConfirmationActivity.class);
				startActivity(doneIntent);
			}
		});

	}

	private void cancelButton() {
		mCancelButton = (Button) findViewById(R.id.signup_cancel_button);
		mCancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

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
					hideSoftKeyboard(SignupActivity.this);
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
		getMenuInflater().inflate(R.menu.signup, menu);
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
