package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.udea.compumovil.labs20211_gr03.lab2.R
import co.edu.udea.compumovil.labs20211_gr03.lab2.database.SitiosTuristicosDatabase
import co.edu.udea.compumovil.labs20211_gr03.lab2.databinding.FragmentCrearSitioBinding
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI
import co.edu.udea.compumovil.labs20211_gr03.lab2.populator.SitioTuristicoPopulator


class CrearSitioFragment : Fragment() {

    private lateinit var binding: FragmentCrearSitioBinding
    private lateinit var viewModel: CrearSitioViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //inflamos la vista con data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crear_sitio, container, false)

        //Reunir lo ingresado por el usuario usando safeArgs
        val args = arguments?.let {
            CrearSitioFragmentArgs.fromBundle(
                it
            )
        }
        val sitio: POI? = args?.sitio

        binding.lifecycleOwner = viewLifecycleOwner

        populateActivity(sitio)

        val database = SitiosTuristicosDatabase.getDatabase(requireContext())
        val factory =
            CrearSitioViewModelFactory(
                database.poiDao()
            )
        viewModel = ViewModelProvider(this, factory).get(CrearSitioViewModel::class.java)
        binding.viewmodel = viewModel

        binding.imageBtnRegistrar.setOnClickListener {
            if(sitio != null){
                SitioTuristicoPopulator.populate(sitio, binding)
                viewModel.update(sitio)
            } else {
                val nuevoSitio = POI()
                SitioTuristicoPopulator.populate(nuevoSitio, binding)
                viewModel.insert(nuevoSitio)
            }
        }

        viewModel.navigateToRequestsFragment.observe(viewLifecycleOwner, Observer {
            if(it == true){
                this.findNavController().navigate(R.id.action_crearSitioFragment_to_listaSitiosFragment)
                viewModel.doneNavigation()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubirArchivo.setOnClickListener {
            loadImage()
        }
    }

    private fun populateActivity(sitio: POI?) {
        binding.apply {
            inputNombreSitio.setText(sitio?.nombreSitio)
            infoGeneral.setText(sitio?.informacion)
            inputUbicacion.setText(sitio?.ubicacion)
            inputTemperatura.setText(sitio?.temperatura)
            inputSitiosRecomendados.setText(sitio?.actividades)
            inputPuntuacion.setText(sitio?.puntuacion)
            //TODO: falta mapear la imagen
            }
        }

    private fun loadImage() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        intent.setType("image/")
        startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), 10)
    }

}