package;

#if cpp
import cpp.Lib;
#elseif neko
import neko.Lib;
#end


class TinyGyro {
	private static var inited:Bool = false;

	public static function init():Void
	{
		init_media();
	}

	public static function startMeasure()
	{
		tinygyro_start();
	}

	public static function stopMeasure()
	{
		tinygyro_stop();
	}

	public static function assignCallbackObject(haxeObject:Dynamic):Void
	{
		tinygyro_assign_callback_object(haxeObject);
	}

	public static function getAmplitude():Float
	{
		return tinygyro_get_amplitude();
	}

	private static function init_media():Void {
		if(inited)
			return;
		tinygyro_start = openfl.utils.JNI.createStaticMethod(
				"tinygyro.TinyGyro",
				"start",
				"()V"
				);
		tinygyro_stop = openfl.utils.JNI.createStaticMethod(
				"tinygyro.TinyGyro",
				"stop",
				"()V"
				);
		tinygyro_assign_callback_object = openfl.utils.JNI.createStaticMethod(
				"tinygyro.TinyGyro",
				"assignCallbackObject",
				"(Lorg/haxe/lime/HaxeObject;)V"
				);
		tinygyro_get_amplitude = openfl.utils.JNI.createStaticMethod(
				"tinygyro.TinyGyro",
				"getAmplitude",
				"()D"
				);
		inited = true;
	}

	private static var tinygyro_start : Dynamic;
	private static var tinygyro_stop : Dynamic;
	private static var tinygyro_assign_callback_object : Dynamic;
	private static var tinygyro_get_amplitude : Dynamic;
}
