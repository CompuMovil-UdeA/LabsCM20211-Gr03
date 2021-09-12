package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.labs20211_gr03.lab2.R
import co.edu.udea.compumovil.labs20211_gr03.lab2.adapters.SitioTuristicoAdapter
import co.edu.udea.compumovil.labs20211_gr03.lab2.database.SitiosTuristicosDatabase
import co.edu.udea.compumovil.labs20211_gr03.lab2.databinding.FragmentListaSitiosBinding
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI

class ListaSitiosFragment : Fragment(), SitioTuristicoAdapter.SitioAdapterOnClickListener {

    private lateinit var binding: FragmentListaSitiosBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ListaSitiosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate( inflater, R.layout.fragment_lista_sitios, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        //boton flotante navegar hacia crear sitio turistico
        binding.fabCrearSitio.setOnClickListener {
            view?.findNavController()?.navigate(
                ListaSitiosFragmentDirections.actionListaSitiosFragmentToCrearSitioFragment(null)
            )
        }

        viewManager = LinearLayoutManager(context)
        val viewAdapter = SitioTuristicoAdapter(this)
        recyclerView = binding.recyclerViewSitiosTuristicos.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        //instanciar BD, factory y el viewModel
        val database = SitiosTuristicosDatabase.getDatabase(requireContext())
        val factory =
            ListaSitiosViewModelFactory(
                database.poiDao()
            )
        viewModel = ViewModelProvider(this, factory).get(ListaSitiosViewModel::class.java)

        //patrón observer
        viewModel.sitios.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewAdapter.submitList(it)
            }
        })

        return binding.root

    }

    override fun btnEditOnClick(sitio: POI, view: View) {
        view.findNavController().navigate(
            ListaSitiosFragmentDirections.actionListaSitiosFragmentToCrearSitioFragment(sitio)
        )
    }

    override fun btnRemoveOnClick(sitio: POI, view: View) {
        viewModel.delete(sitio)
    }

    override fun btnViewOnClick(sitio: POI, view: View) {
        view.findNavController().navigate(
            ListaSitiosFragmentDirections.actionListaSitiosFragmentToVistaDetalleSitioFragment(sitio)
        )
    }


}