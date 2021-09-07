package co.edu.udea.compumovil.labs20211_gr03.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.UserDao

class registro_usuario : AppCompatActivity() {
    private lateinit var binding: registro_usuario
    private lateinit var userDao : UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuario)
    }
}