package co.edu.udea.compumovil.labs20211_gr03.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Lo envia al formulario de registro de usuario
        btn = findViewById(R.id.registerBtn)
        btn.setOnClickListener {
            val intent = Intent(this, registro_usuario::class.java)
            startActivity(intent)

        }
    }
}