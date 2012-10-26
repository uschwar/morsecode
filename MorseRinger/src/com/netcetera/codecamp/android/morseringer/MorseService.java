package com.netcetera.codecamp.android.morseringer;

import com.netcetera.codecamp.android.morseringer.ext.CallerIDResult;
import com.netcetera.codecamp.android.morseringer.ext.ContactsHelper;
import com.netcetera.codecamp.android.morseringer.ext.NewContactsHelper;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MorseService extends IntentService {

  private static final String LOG_ID = "com.netcetera.codecamp.android.morseringer.MorseService";
  private MorseSoundGenerator morseSoundGenerator = null;
  private ContactsHelper contactsHelper = new NewContactsHelper();
  private static final int SAMPLINGRATE = 44100;

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
    handleCommand(intent, startId);
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
  
  
  protected void handleCommand(Intent intent, int startId){
    final String phoneState = intent
                    .getStringExtra(TelephonyManager.EXTRA_STATE);
    final String phoneNumber = intent
                    .getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

    CallerIDResult result = contactsHelper .getContact(phoneNumber, this.getContentResolver());
    Log.d(LOG_ID, "Lookup result is " + result.getName());
    
    morseSoundGenerator  = new MorseSoundGenerator(SAMPLINGRATE, 800.0, 50);
    morseSoundGenerator.morse(result.getName());
    
//    // since we're about to start a new lookup or the phone stopped ringing,
//    // we want to cancel any lookups in progress
//    if (currentLookupAsyncTask != null)
//            currentLookupAsyncTask.cancel(true);
//
//    if (TelephonyManager.EXTRA_STATE_RINGING.equals(phoneState)) {
//            if(TextUtils.isEmpty(phoneNumber) || SpecialPhoneNumbers.UNKNOWN_NUMBER.equals(phoneNumber)){
//                    toastLayout.setVisibility(View.GONE);
//                    textToSpeechHelper.speak(getString(R.string.incoming_call_tts_unknown), TextToSpeech.QUEUE_FLUSH, ttsParametersMap);
//            }else if(SpecialPhoneNumbers.PRIVATE_NUMBER.equals(phoneNumber)){
//                    toastLayout.setVisibility(View.GONE);
//                    textToSpeechHelper.speak(getString(R.string.incoming_call_tts_private), TextToSpeech.QUEUE_FLUSH, ttsParametersMap);
//            }else if(SpecialPhoneNumbers.PAYPHONE_NUMBER.equals(phoneNumber)){
//                    toastLayout.setVisibility(View.GONE);
//                    textToSpeechHelper.speak(getString(R.string.incoming_call_tts_payphone), TextToSpeech.QUEUE_FLUSH, ttsParametersMap);
//            }else{
//                    try{
//                            CallerIDResult result = contactsHelper.getContact(phoneNumber);
//                            toastLayout.setVisibility(View.GONE);
//                            //speak the contact's name even when we don't need to use the CallerID service to get information
//                            
//                            if(ttsEnabled && result.getName()!=null && result.getName()!=""){
//                                    textToSpeechHelper.speak(getString(R.string.incoming_call_tts, result.getName()), TextToSpeech.QUEUE_FLUSH, ttsParametersMap);
//                            }
//                    }catch(NoResultException e){
//                            currentLookupAsyncTask = new ToastLookupAsyncTask(this, phoneNumber);
//                            currentLookupAsyncTask.execute();
//                    }
//            }
//    } else {
//            toastLayout.setVisibility(View.GONE);
//            stopSelf(startId);
//    }
//    
//    if (TelephonyManager.EXTRA_STATE_IDLE.equals(phoneState)
//                    && TelephonyManager.EXTRA_STATE_RINGING.equals(previousPhoneState)
//                    && previousPhoneNumber!=null
//                    && !contactsHelper.haveContactWithPhoneNumber(previousPhoneNumber)){
//            //add missed call notification
//            final Notification notification = new Notification(
//                            android.R.drawable.sym_call_missed,
//                            previousCallerID==null?getString(R.string.missed_call_from_unknown):MessageFormat.format(getString(R.string.missed_call_from_known),previousCallerID),
//                            System.currentTimeMillis());
//            final Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
//            notificationIntent.putExtra("phoneNumber", previousPhoneNumber);
//            final PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//            notification.setLatestEventInfo(getApplicationContext(), getString(R.string.missed_call), previousCallerID==null?getString(R.string.perform_lookup_label):previousCallerID, contentIntent);
//            notification.flags |= Notification.FLAG_AUTO_CANCEL;
//            notificationManager.notify(new Random().nextInt(), notification);
//            
//            if(ttsEnabled){
//                    textToSpeechHelper.stop();
//            }
//    }
//    previousPhoneNumber = phoneNumber;
//    previousPhoneState = phoneState;
}
}
