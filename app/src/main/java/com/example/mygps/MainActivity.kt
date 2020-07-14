package com.example.mygps

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleAdapter
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //選択されたメニューのIDのR値による処理の分岐。
        when(item.itemId) {
            //現在地が選択された
            R.id.menuPresent ->{
                //GPSActivity へ画面遷移
                //タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMutableMap型!
                val etSearch = findViewById<EditText>(R.id.etSearchWord)
                //インテントオブジェクトを生成。
                val intent = Intent(applicationContext, GPSActivity::class.java)
                //第2画面に送るデータを格納。
                //intent.getStringExtra( etSearch )
                intent.putExtra( "string", etSearch.toString() )
                //第2画面の起動。
                startActivity(intent)
            }

            //終了が選択された
            R.id.menuExit ->
                //アプリを終了させる
                finish()
        }
        //親クラスの同名メソッドを呼び出し、その戻り値を返却。
        return super.onOptionsItemSelected(item)
    }

    /**
     * 地図検索ボタンがタップされたときの処理メソッド。
     */
    fun onMapSearchButtonClick(view: View) {
        //入力欄に入力されたキーワード文字列を取得。
        val etSearchWord = findViewById<EditText>(R.id.etSearchWord)
        var searchWord = etSearchWord.text.toString()
        //入力されたキーワードをURLエンコード。
        searchWord = URLEncoder.encode(searchWord, "UTF-8")
        //マップアプリと連携するURI文字列を生成。
        val uriStr = "geo:0,0?q=${searchWord}"
        //URI文字列からURIオブジェクトを生成。
        val uri = Uri.parse(uriStr)
        //Intentオブジェクトを生成。
        val intent = Intent(Intent.ACTION_VIEW, uri)
        //アクティビティを起動。
        startActivity(intent)
    }

}