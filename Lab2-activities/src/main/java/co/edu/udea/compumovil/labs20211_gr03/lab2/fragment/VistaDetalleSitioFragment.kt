package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import co.edu.udea.compumovil.labs20211_gr03.lab2.R
import co.edu.udea.compumovil.labs20211_gr03.lab2.databinding.FragmentVistaDetalleSitioBinding
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI
import co.edu.udea.compumovil.labs20211_gr03.lab2.util.ImagenUtil


class VistaDetalleSitioFragment : Fragment() {
    private lateinit var binding: FragmentVistaDetalleSitioBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //Cambiar titulo al Action Bar
        (activity as AppCompatActivity).supportActionBar?.title = "Detalle sitio"

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_vista_detalle_sitio, container, false)
        val args = arguments?.let {
            VistaDetalleSitioFragmentArgs.fromBundle(
                it
            )
        }
        val sitio: POI? = args?.sitio
        binding.sitioTuristico = sitio
        sitio?.let {
            ImagenUtil.assignUrlImageToView(sitio.imagenUrl, binding.thumbmail)
        }
        return binding.root
    }

}