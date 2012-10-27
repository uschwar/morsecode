package com.netcetera.codecamp.android.morseringer;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public class AndroidAudioDevice {

  private static final String LOG_ID = "com.netcetera.codecamp.android.morseringer.AndroidAudioDevice";
  private AudioTrack track;
  short[] buffer = new short[1024];
  private int samplesWritten;
  private Boolean active = false;

  public AndroidAudioDevice(int samplingrate) {
    int minSize = AudioTrack.getMinBufferSize(samplingrate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
        AudioFormat.ENCODING_PCM_16BIT);
    track = new AudioTrack(AudioManager.STREAM_RING, samplingrate,
        AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, minSize,
        AudioTrack.MODE_STREAM);
    track.play();
    this.active = true;
  }

  public Boolean writeSamples(float[] samples) {
    Log.d(LOG_ID, "writeSamples");
    Log.d(LOG_ID, "TRACK STATE: " + track.getState());
    Log.d(LOG_ID, "TRACK STATE: " + track.getPlayState());
    if (active) {
      fillBuffer(samples);
      track.write(buffer, 0, samples.length);
    }
    return active;
  }

  public Boolean writeSamples(short[] samples) {
    Log.d(LOG_ID, "writeSamples");
    Log.d(LOG_ID, "TRACK STATE: " + track.getState());
    Log.d(LOG_ID, "TRACK STATE: " + track.getPlayState());
    if (active) {
      samplesWritten = 0;
      while (samplesWritten < samples.length) {
        samplesWritten += track.write(samples, samplesWritten,
            Math.min(buffer.length, samples.length - samplesWritten));
      }
    }
    return active;
  }

  private void fillBuffer(float[] samples) {
    Log.d(LOG_ID, "fillBuffer");
    if (buffer.length < samples.length)
      buffer = new short[samples.length];

    for (int i = 0; i < samples.length; i++)
      buffer[i] = (short) (samples[i] * Short.MAX_VALUE);;
  }

  public void stop() {
    Log.d(LOG_ID, "stop()");
    tryToStopTrack(track);
    active = false;
  }

  private void tryToStopTrack(AudioTrack track2) {
	  if (null != track && track.getState() != AudioTrack.STATE_UNINITIALIZED) {
		track.stop();
	}
	
}

public void release() {
    Log.d(LOG_ID, "release()");
    tryToRelease(track);
    active = false;
  }

  private void tryToRelease(AudioTrack track2) {
	  if (null != track && track.getState() != AudioTrack.STATE_UNINITIALIZED) {
		track.release();
	}
	  track.release();
  }

public Boolean isActive() {
    return active;
  }
}
