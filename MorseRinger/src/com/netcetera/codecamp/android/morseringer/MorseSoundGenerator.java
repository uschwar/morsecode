package com.netcetera.codecamp.android.morseringer;


public class MorseSoundGenerator {
	private int samprate;
	private short[] ditBuffer = null;
	private short[] dahBuffer = null;
	private short[] ditPauseBuffer = null;
	private short[] charPauseBuffer = null;
	private short[] wordPauseBuffer = null;
	private int DITLENGTH = 60;
	private int DAHLENGTH = DITLENGTH * 3;
	private int DITPAUSELENGTH = DITLENGTH;
	private int CHARPAUSELENGTH = DAHLENGTH;
	private int WORDPAUSELENGTH = DITLENGTH * 4;
	private int samplesPerMillisecond;
	private AndroidAudioDevice audioDevice;
	private MorseCode code = new MorseCode();
	private char[] ds;
	private double frequency;

	public MorseSoundGenerator(int samplingRate, double frequency, int ditLength) {
		this.samprate = samplingRate;
		this.frequency = frequency;
		this.samplesPerMillisecond = samprate / 1000;
		this.DITLENGTH = ditLength;
		this.DAHLENGTH = DITLENGTH * 3;
		this.DITPAUSELENGTH = DITLENGTH;
		this.CHARPAUSELENGTH = DITLENGTH * 4;
		this.WORDPAUSELENGTH = DITLENGTH * 7;
		ditBuffer = new short[getBufferSize(DITLENGTH)];
		dahBuffer = new short[getBufferSize(DAHLENGTH)];
		ditPauseBuffer = new short[getBufferSize(DITPAUSELENGTH)];
		charPauseBuffer = new short[getBufferSize(CHARPAUSELENGTH)];
		wordPauseBuffer = new short[getBufferSize(WORDPAUSELENGTH)];
		fillBuffer(ditBuffer, false);
		fillBuffer(dahBuffer, false);
		fillBuffer(ditPauseBuffer, true);
		fillBuffer(charPauseBuffer, true);
		fillBuffer(wordPauseBuffer, true);
		deGlitchBuffer(ditBuffer);
		deGlitchBuffer(dahBuffer);
	}

	private void deGlitchBuffer(short[] buffer) {
		int fadeLengthMs = 2;//MUST be > 0 (Div / 0)
		int fadeLengthSamples = fadeLengthMs * samplesPerMillisecond;
		if (buffer.length > fadeLengthSamples * 3) {
			//Only fade in/out if buffer is long enough
			short toValIn = buffer[fadeLengthSamples];
			float stepIn = (float) toValIn / (float) fadeLengthSamples;
			short fromValOut = buffer[buffer.length - fadeLengthSamples];
			float stepOut = (float) fromValOut / (float) fadeLengthSamples;
			//Fade-IN
			for (int i = 0; i < fadeLengthSamples; i++) {
				buffer[i] = (short) (stepIn * i);
			}
			//Fade-OUT
			for (int i = 1; i <= fadeLengthSamples; i++) {
				buffer[buffer.length - i] =
				    (short) (stepOut * (i - 1));
			}
		}
	}

	private void writeCharacterToAudioBuffer(char c) {
		ds = code.getMorseCode(c);
		if (null == ds) {
			audioDevice.writeSamples(getWordPauseBuffer());
			return;
		}
		for (int i = 0; i < ds.length; i++) {
			if (ds[i] == '.') {
				audioDevice.writeSamples(getDitBuffer());
			} else if (ds[i] == '-') {
				audioDevice.writeSamples(getDahBuffer());
			} else {
				audioDevice.writeSamples(getWordPauseBuffer());
			}
			if (i == ds.length - 1) {//add character pause
				audioDevice.writeSamples(getCharPauseBuffer());
			} else {//add dit-pause
				audioDevice.writeSamples(getDitPauseBuffer());
			}
		}
	}

	private int getBufferSize(int length) {
		return samplesPerMillisecond * length;
	}

	void fillBuffer(short[] buffer, boolean pause) {
		double omega, t;
		double dt = 1.0 / samprate; // sec per samp

		if (pause) {
			for (int i = 0; i < buffer.length; i++) {
				buffer[i] = (short) 0;
			}
		} else {
			t = 0.0;
			omega = (float) (2.0 * Math.PI * frequency);
			for (int i = 0; i < buffer.length; i++) {
				buffer[i] = (short) (Short.MAX_VALUE * Math.sin(omega * t));
				t += dt;
			}
		}
	}

	public short[] getBuffer(int lengthMs, boolean mute) {
		short [] buffer = new short[samplesPerMillisecond * lengthMs];
		fillBuffer(buffer, mute);
		return buffer;
	}

	public short[] getDitBuffer() {
		return ditBuffer;
	}

	public short[] getDahBuffer() {
		return dahBuffer;
	}

	public short[] getDitPauseBuffer() {
		return ditPauseBuffer;
	}

	public short[] getCharPauseBuffer() {
		return charPauseBuffer;
	}

	public short[] getWordPauseBuffer() {
		return wordPauseBuffer;
	}

	public void morse(final String string) {
		audioDevice = new AndroidAudioDevice(samprate);
		char[] charArray = string.toUpperCase().toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			writeCharacterToAudioBuffer(charArray[i]);
		}
	}

	public void stop() {
		audioDevice.track.stop();
	}

	public void release() {
		audioDevice.track.release();
	}


}
