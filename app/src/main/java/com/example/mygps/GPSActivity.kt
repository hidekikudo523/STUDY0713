package com.example.mygps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class GPSActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g_p_s)

        //アクションバーの［戻る］メニューを有効に設定。
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //選択されたメニューが［戻る］の場合、アクティビティを終了。
        if(item.itemId == android.R.id.home) {
            finish()
        }
        //親クラスの同名メソッドを呼び出し、その戻り値を返却。
        return super.onOptionsItemSelected(item)
    }

}