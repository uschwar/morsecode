package com.netcetera.codecamp.android.morseringer;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MorseService extends IntentService {

  private static final String LOG_ID = "com.netcetera.codecamp.android.morseringer.MorseService";

  public MorseService() {
    super("noname");
    Log.d(LOG_ID, "MorseService()");
    // TODO Auto-generated constructor stub
  }
  
  public MorseService(String name) {
    super(name);
    Log.d(LOG_ID, "MorseService(String name)");
  }

  @Override
  public void onCreate() {
    // TODO Auto-generated method stub
    super.onCreate();
    Log.d(LOG_ID, "onCreate()");
  }

  @Override
  public void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();
    Log.d(LOG_ID, "onDestroy()");
  }

  @Override
  public void onStart(Intent intent, int startId) {
    // TODO Auto-generated method stub
    super.onStart(intent, startId);
    Log.d(LOG_ID, "onStart()");
  }

  @Override
  public IBinder onBind(Intent intent) {
    // TODO Auto-generated method stub
    Log.d(LOG_ID, "onBind");
    return null;
  }

  @Override
  protected void onHandleIntent(Intent arg0) {
    // TODO Auto-generated method stub
    Log.d(LOG_ID, "onHandleIntent");

  }
}
