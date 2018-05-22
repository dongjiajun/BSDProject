package com.example.bsdprojectone.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



	public class BootCompleteReceiver extends BroadcastReceiver {

		


		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "Boot receiver",Toast.LENGTH_LONG).show();
			}
	}
