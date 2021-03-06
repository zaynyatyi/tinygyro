package tinygyro;

import org.haxe.lime.GameActivity;
import org.haxe.lime.HaxeObject;
import org.haxe.extension.Extension;
import android.util.Log;
import android.os.Environment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.content.Context;
import java.util.List;

class TinyGyro extends Extension implements SensorEventListener
{
	static private TinyGyro instance;
	static private boolean isRegistered;

	private static SensorManager sm = null;
	private static double[] currentValue = {0.0, 0.0, 0.0};


	static private HaxeObject eventHaxeHandler = null;

	public static void start() 
	{
		registerExtension();
		Log.e("tinygyro", "registered extension in start()");
		if (sm == null) {
			sm = (SensorManager) mainContext.getSystemService(Context.SENSOR_SERVICE);
		}
		List<Sensor> typedSensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if (typedSensors == null || typedSensors.size() <= 0) {
			Log.e("tinygyro", "there are no ACCELEROMETER sensor");
		}
		sm.registerListener(TinyGyro.getInstance(), typedSensors.get(0), SensorManager.SENSOR_DELAY_GAME);
	}

	public static void stop() 
	{
		sm.unregisterListener(TinyGyro.getInstance());
	}

	public static double[] getAmplitude() 
	{
		return currentValue;
	}

	public static void assignCallbackObject(HaxeObject eventHaxeHandler)
	{
		TinyGyro.eventHaxeHandler = eventHaxeHandler;
	}

	public static void registerExtension()
	{
		if (isRegistered) return;
		GameActivity.getInstance().registerExtension(TinyGyro.getInstance());
		isRegistered = true;
	}

	public void onSensorChanged(SensorEvent event) {
		currentValue[0] = event.values[0];
		currentValue[1] = event.values[1];
		currentValue[2] = event.values[2];
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	private static TinyGyro getInstance()
	{
		if (instance == null)
			instance = new TinyGyro();
		return instance;
	}
}
