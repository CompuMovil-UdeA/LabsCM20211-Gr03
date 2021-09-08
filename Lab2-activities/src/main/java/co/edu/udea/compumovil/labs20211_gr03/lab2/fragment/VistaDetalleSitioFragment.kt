package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.udea.compumovil.labs20211_gr03.lab2.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VistaDetalleSitioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this co.edu.udea.compumovil.labs20211_gr03.lab2.fragment
        return inflater.inflate(R.layout.fragment_vista_detalle_sitio, container, false)
    }

}