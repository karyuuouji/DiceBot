package com.karyuuouji.dicebot;

import android.app.*;
import android.os.*;
import com.karyuuouji.dicebot.api.*;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.content.res.*;
import android.view.*;
import android.text.*;
import javax.json.*;
import java.io.*;
import android.net.*;
import android.content.*;

public class MainActivity extends Activity 
{
	private static final String apiKey = "a82538c679c84bf4ab5f1ec088616e17";
	private SessionInfo session;
	private JsonObject dice;
	private BeginSessionResponse resp;
	private EditText user, pass;
	private TextView dsp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ErrorHandler(this));
        setContentView(R.layout.main);
		LinearLayout layout = (LinearLayout) findViewById(R.id.root);
		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.login_bg);
		BackgroundBitmapDrawable bg = new BackgroundBitmapDrawable(res, bitmap);
		layout.setBackgroundDrawable(bg);
		user = (EditText) findViewById(R.id.user);
		user.setText("karyuuouji");
		pass = (EditText) findViewById(R.id.pass);
		pass.setText("wgeojnhf5htjmg");
		dsp = (TextView) findViewById(R.id.txtView);
    }
	
	public void doLogin(View view){
		if (!isConnected()){
			Toast.makeText(getApplicationContext(),"No WiFi or Data Connection!!!",
				Toast.LENGTH_SHORT);
		}
		//DiceWebAPI.BeginSession(apiKey);
		//dsp.setText(user.getText().toString());
		new DiceConnect().execute();
	}
	
	private Boolean isConnected(){
		
		ConnectivityManager connMgr = (ConnectivityManager) 
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
		
	}
	
	private class DiceConnect extends AsyncTask<String, Void, DiceResponse > {
		
		SessionInfo si;
		BeginSessionResponse bs;
		
		@Override
		protected DiceResponse doInBackground(String... str)
		{
			// TODO: Implement this method
			//return null;
			bs = DiceWebAPI.BeginSession(apiKey, user.getText().toString(), pass.getText().toString());
			return bs;
		}

		@Override
		protected void onPostExecute(DiceResponse result)
		{
			// TODO: Implement this method
			//super.onPostExecute(result);
			if (result.isSuccess()){
				alert("Success!!!");
				dsp.setText("success");
			} else {
				alert("Failed!!!");
				dsp.setText("failed");
			}
			//dsp.setText(result.toString());
		}
	}
	
	private void alert(String msg){
		Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
	}
}
