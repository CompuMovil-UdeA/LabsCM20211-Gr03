package co.edu.udea.compumovil.labs20211_gr03.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.UserDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.database.SitiosTuristicosDatabase
import co.edu.udea.compumovil.labs20211_gr03.lab2.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cambiamos la forma de inflar la vista utilizando el binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //instanciamos la BD y obtenemos el Dao de usuario
        val database = SitiosTuristicosDatabase.getDatabase(this)
        val factory =
            AutenticacionViewModelFactory(
                database.userDao()
            )

        //instanciamos el viewModel
        val viewModel = ViewModelProvider(this, factory).get(AutenticacionViewModel::class.java)

        //Lo envia al formulario de registro de usuario
       binding.registerBtn.setOnClickListener {
            val intent = Intent(this, registro_usuario::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val usuario : String = binding.LoginUser.text.toString()
            val contraseña: String = binding.Password.text.toString()
            viewModel.authenticate(usuario, contraseña)
        }

        viewModel.isAuthenticated.observe(this, Observer { isAuth ->
            if(isAuth){
                val intent = Intent(this, SitiosTuristicosActivity::class.java).apply {
                    putExtra("uid", viewModel.user.value?.uid)
                }
                startActivity(intent)
            }
        })

        viewModel.showAuthError.observe(this, Observer {
            if(it  == true){
                Toast.makeText(this, "El usuario y/o contraseña no es válido", Toast.LENGTH_SHORT)
                    .show()
                viewModel.showAuthErrorIsDone()
            }
        })
    }
}