package at.schwar.android.morse.gui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import at.schwar.android.morse.service.MorseService;

public class PhoneStateBroadcastReceiver extends BroadcastReceiver {

	/** {@inheritDoc} */
	@Override
	public void onReceive(Context context, Intent intent) {
		final Intent myIntent = new Intent(context, MorseService.class);
		myIntent.putExtras(intent);
		context.startService(myIntent);
	}

}
