package com.netcetera.codecamp.android.morseringer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.widget.TextView;

public class HelloAndroid extends Activity implements OnGestureListener {
	private static final int SAMPLINGRATE = 44100;
	// private AudioTrack at;
	private TextView tv;
	private MorseSoundGenerator morseSoundGenerator;
	private Thread playThread;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		morseSoundGenerator = new MorseSoundGenerator(SAMPLINGRATE, 800.0, 50);
		tv = new TextView(this);
		setContentView(tv);
		tv.setTextColor(Color.WHITE);
	}

	void playit() {
		if (null == playThread || !playThread.isAlive()) {
			playThread = new Thread() {

				@Override
				public void run() {
					if (null != morseSoundGenerator) {
						morseSoundGenerator.morse("Hallo Hallo");
					}
					// morseSoundGenerator.morse(
					// "a a a b b b c c c d d d e e e f f f g g g h h h i i i j j j k k k l l l m m m n n n o o o p p p q q q r r r s s s t t t u u u v v v w w w x x x y y y z z z 1 1 1 2 2 2 3 3 3 4 4 4 5 5 5 6 6 6 7 7 7 8 8 8 9 9 9 0 0 0");
				}

			};
			playThread.start();
		}
	}

	// ----------lifecycle callbacks----------------
	@Override
	public void onPause() {
		super.onPause();
		if (null != morseSoundGenerator) {
			morseSoundGenerator.stop();
		}
		tv.append("onpause!\n"); // gets to here
	}

	@Override
	public void onResume() {
		super.onResume();
		tv.append("onresume!\n");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (null != morseSoundGenerator) {
			morseSoundGenerator.stop();
			morseSoundGenerator.release();
		}
		tv.append("onstop!\n"); // gets to here
	}

	// ------------gesture callbacks--------------------
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			tv.append("in ontouch!\n"); // gets to here
			playit();
		}
		return true;
	}

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
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

}// activity 