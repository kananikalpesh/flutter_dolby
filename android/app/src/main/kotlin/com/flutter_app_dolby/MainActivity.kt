package com.flutter_app_dolby

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.test/test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)

        MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "callSendStringFun") {
                showHelloFromFlutter(call.argument("arg"))
                val temp = sendString()
                result.success(temp)
            } else {
                result.notImplemented()
            }
        }
    }

    private fun sendString(): String {
        val stringToSend: String = "Hello from Kotlin"
        return stringToSend
    }

    private fun showHelloFromFlutter(argFromFlutter : String?){
        Toast.makeText(this, argFromFlutter, Toast.LENGTH_SHORT).show()
    }
}

/*
class MainActivity : FlutterActivity() {
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)
        MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(
                object : MethodCallHandler() {
                    fun onMethodCall(methodCall: MethodCall, result: Result) {
                        if (methodCall.method.equals("changeLife")) {
                            val message = "Life Changed"
                            result.success(message)
                        }
                        if (methodCall.method.equals("vibrateDevice")) {
                            val message = "Vibrated device for 2500ms"
                            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    vibrator.vibrate(VibrationEffect.createOneShot(2500, VibrationEffect.DEFAULT_AMPLITUDE))
                                }
                            } else {
                                vibrator.vibrate(2500)
                            }
                            result.success(message)
                        }
                    }
                }
        )
    }

    companion object {
        private const val CHANNEL = "com.test/test"
    }
}*/
