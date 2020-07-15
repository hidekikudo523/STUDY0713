package com.example.mygps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat

class GPSActivity : AppCompatActivity() {
    /**
     * 緯度フィールド。
     */
    private var _latitude = 0.0
    /**
     * 経度フィールド
     */
    private var _longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g_p_s)

        //アクションバーの［戻る］メニューを有効に設定。
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //LocationManagerオブジェクトを取得。
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //位置情報が更新された際のリスナオブジェクトを生成。
        val locationListener = GPSLocationListener()

        if(ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        //位置情報の追跡を開始。
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //選択されたメニューが［戻る］の場合、アクティビティを終了。
        if(item.itemId == android.R.id.home) {
            finish()
        }
        //親クラスの同名メソッドを呼び出し、その戻り値を返却。
        return super.onOptionsItemSelected(item)
    }

    /**
     * ロケーションリスナクラス。
     */
    private inner class GPSLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            //引数のLocationオブジェクトから緯度を取得。
            _latitude = location.latitude
            //引数のLocationオブジェクトから経度を取得。
            _longitude = location.longitude
            //取得した緯度をTextViewに表示。
            val tvLatitude = findViewById<TextView>(R.id.tvLatitude)
            tvLatitude.text = _latitude.toString()
            //取得した経度をTextViewに表示。
            val tvLongitude = findViewById<TextView>(R.id.tvLongitude)
            tvLongitude.text = _longitude.toString()
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    /**
     * 現在地の地図表示ボタンがタップされたときの処理メソッド。
     */
    fun onMapShowCurrentButtonClick(view: View) {
        //フィールドの緯度と経度の値をもとにマップアプリと連携するURI文字列を生成。
        val uriStr = "geo:${_latitude},${_longitude}"
        //URI文字列からURIオブジェクトを生成。
        val uri = Uri.parse(uriStr)
        //Intentオブジェクトを生成。
        val intent = Intent(Intent.ACTION_VIEW, uri)
        //アクティビティを起動。
        startActivity(intent)
    }
}