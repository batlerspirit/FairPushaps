package ua.in.kroozo.fairpushups.support;

import ua.in.kroozo.fairpushups.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sounds {

	public static final int soundBeep = R.raw.single_beep;
	private static SoundPool soundPool;
	private static int soundId;

	public static void initSounds(Context context) {
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
		soundId = soundPool.load(context, R.raw.single_beep, 1);
	}

	public static void playSound(Context context) {
		if (soundPool == null) {
			initSounds(context);
		}
		soundPool.play(soundId, 1, 1, 0, 0, 1);
	}

}
