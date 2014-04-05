tinygyro
=======

Just small openfl java extension with callback

usage
=====

To compile ndll use:

<pre>
cd project
haxelib run hxcpp Build.xml
haxelib run hxcpp Build.xml -Dandroid
</pre>

Add the extension to haxelib:

<pre>
haxelib dev TinyGyro PATH_TO_THE_EXTENSION_ROOT
</pre>

Usage in project:

<pre>
Add 
    haxelib name="TinyGyro" to project.xml
In class you should have claaback function 
    public function traceGyro():Void
After main class creation use
    TinyGyro.init(); // to init JNI functions
    TinyGyro.assignCallbackObject(this); // to assign callback with traceGyro():Void function
    TinyGyro.startMeasure(); // to start measuring gyro
    TinyGyro.stopMeasure(); // to stop measuring gyro
</pre>

