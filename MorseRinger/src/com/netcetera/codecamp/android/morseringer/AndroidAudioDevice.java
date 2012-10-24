package com.netcetera.codecamp.android.morseringer;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class AndroidAudioDevice {
	AudioTrack track;
	short[] buffer = new short[1024];
	private int samplesWritten;

	public AndroidAudioDevice(int samplingrate) {
		int minSize = AudioTrack.getMinBufferSize(samplingrate,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT);
		track = new AudioTrack(AudioManager.STREAM_RING, samplingrate,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, minSize, AudioTrack.MODE_STREAM);
		track.play();
	}

	public void writeSamples(float[] samples) {
		fillBuffer(samples);
		track.write(buffer, 0, samples.length);
	}

	public void writeSamples(short[] samples) {
		samplesWritten = 0;
		while (samplesWritten < samples.length) {
			samplesWritten += track.write(samples, samplesWritten,
					Math.min(buffer.length, samples.length - samplesWritten));
		}
	}

	private void fillBuffer(float[] samples) {
		if (buffer.length < samples.length)
			buffer = new short[samples.length];

		for (int i = 0; i < samples.length; i++)
			buffer[i] = (short) (samples[i] * Short.MAX_VALUE);
		;
	}
}