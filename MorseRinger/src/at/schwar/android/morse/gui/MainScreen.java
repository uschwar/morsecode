package at.schwar.android.morse.gui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class MainScreen extends Activity implements OnGestureListener {

  protected Bundle savedInstanceState;
  private Context context;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = this;
    this.savedInstanceState = savedInstanceState;
    setContentView(R.layout.activity_main);

    addListenerOnCheckBoxEnable();
    addListenerOnClickShowMorseCode();

  }


  private void addListenerOnClickShowMorseCode() {
    Button showMorseCode = (Button) findViewById(R.id.button_show_morsecode);
    showMorseCode.setOnClickListener(new OnClickListener() {

      public void onClick(View v) {
        startActivity(new Intent(context,ListViewActivity.class)); 
      }
    });
  }


  private void addListenerOnCheckBoxEnable() {
    CheckBox checkBoxEnable = (CheckBox) findViewById(R.id.checkbox_enable);
    checkBoxEnable.setOnClickListener(new OnClickListener() {

      public void onClick(View v) {
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_enable);
        if (!checkBox.isChecked()) {
          checkBox.setChecked(true);
        }
      }
    });
  }


  // ----------lifecycle callbacks----------------
  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onStop() {
    super.onStop();
  }

  // ------------gesture callbacks--------------------
  @Override
  public boolean onTouchEvent(MotionEvent e) {
    return true;
  }

  public boolean onDown(MotionEvent e) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    // TODO Auto-generated method stub
    return false;
  }

  public void onLongPress(MotionEvent e) {
    // TODO Auto-generated method stub

  }

  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    // TODO Auto-generated method stub
    return false;
  }

  public void onShowPress(MotionEvent e) {
    // TODO Auto-generated method stub

  }

  public boolean onSingleTapUp(MotionEvent e) {
    // TODO Auto-generated method stub
    return false;
  }


}
