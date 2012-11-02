/*
 * Copyright (C) 2012 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.netcetera.codecamp.android.morseringer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import at.schwar.android.morse.service.MorseService;


public class PhoneStateBroadcastReceiver extends BroadcastReceiver {

  /** {@inheritDoc} */
  @Override
  public void onReceive(Context context, Intent intent) {
    final Intent myIntent=new Intent(context,MorseService.class);
    myIntent.putExtras(intent);
    context.startService(myIntent);
  }

}
