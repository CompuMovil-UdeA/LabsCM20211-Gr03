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
import co.edu.udea.compumovil.labs20211_gr03.lab2.databinding.RegistroUsuarioBinding
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.User

class registro_usuario : AppCompatActivity() {
    private lateinit var binding: RegistroUsuarioBinding
    private lateinit var userDao : UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cambiamos la forma de inflar la vista utilizando el binding
        binding = DataBindingUtil.setContentView(this, R.layout.registro_usuario)

        //instanciamos la BD y obtenemos el Dao de usuario
        val database = SitiosTuristicosDatabase.getDatabase(this)
        userDao = database.userDao()

        binding.lifecycleOwner = this
        val factory =
                RegistroUsuarioViewModelFactory(
                        userDao
                )

        //instanciamos el viewModel
        val viewModel = ViewModelProvider(this, factory).get(RegistroUsuarioViewModel::class.java)

        //Se controla el evento navegacion para que solo suceda una vez
        viewModel.navigateToLogin.observe(this, Observer { isDone ->
            if(isDone){
                viewModel.onNavigateToLoginSuccess()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })

        //Registramos exitosamente un usuario
        binding.imageBtnRegistrar.setOnClickListener {
            Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
            viewModel.insert(convertirFormularioAusuario(binding))
        }

    }
}

//datos ingresados por el usuario
private fun convertirFormularioAusuario(binding : RegistroUsuarioBinding): User {
    val infoUsuario = User()
    infoUsuario.usuario = binding.inputUser.text.toString()
    infoUsuario.correo = binding.inputCorreo.text.toString()
    infoUsuario.contraseña = binding.inputPassRegistro.text.toString()
    return infoUsuario

}
