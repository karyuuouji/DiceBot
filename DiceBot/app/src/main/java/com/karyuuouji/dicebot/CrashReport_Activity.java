package com.karyuuouji.dicebot;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import com.karyuuouji.dicebot.classes.*;

public class CrashReport_Activity extends Activity {

	TextView error;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ErrorHandler(this));
		setContentView(R.layout.crash);

		error = (TextView) findViewById(R.id.crashTextView);

		error.setText(getIntent().getStringExtra("error"));
	}
}
