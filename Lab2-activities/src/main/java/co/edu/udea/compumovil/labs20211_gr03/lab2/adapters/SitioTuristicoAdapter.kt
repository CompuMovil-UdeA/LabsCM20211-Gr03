package co.edu.udea.compumovil.labs20211_gr03.lab2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.labs20211_gr03.lab2.databinding.CardviewListaSitiosBinding
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI

class SitioTuristicoAdapter(private val onclick: SitioAdapterOnClickListener)
    : ListAdapter<POI, SitioTuristicoAdapter.SitioTuristicoViewHolder>(SitioTuristicoDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitioTuristicoViewHolder {
        return SitioTuristicoViewHolder.from(this, parent)
    }

    override fun onBindViewHolder(holder: SitioTuristicoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SitioTuristicoViewHolder private constructor(val binding: CardviewListaSitiosBinding, private val listener: SitioAdapterOnClickListener)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: POI) {
            with(binding) {
               // cardView = item

                btnEdit.setOnClickListener {
                    listener.btnEditOnClick(item, it)
                }

                btnDelet.setOnClickListener {
                    listener.btnRemoveOnClick(item, it)
                }

                executePendingBindings()
            }
        }

        companion object {
            fun from(sitioTuristicoAdapter: SitioTuristicoAdapter, parent: ViewGroup): SitioTuristicoViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CardviewListaSitiosBinding.inflate(inflater, parent, false)
                return SitioTuristicoViewHolder(binding, sitioTuristicoAdapter.onclick)
            }
        }

    }

    interface SitioAdapterOnClickListener {
        fun btnEditOnClick(sitio: POI, view: View)
        fun btnRemoveOnClick(sitio: POI, view: View)
    }

}

//Calcula las diferencias entre dos listas
class SitioTuristicoDiffCallback : DiffUtil.ItemCallback<POI>(){

    override fun areItemsTheSame(oldItem: POI, newItem: POI): Boolean {
        return oldItem.sitioid == newItem.sitioid
    }

    override fun areContentsTheSame(oldItem: POI, newItem: POI): Boolean {
        return oldItem == newItem
    }

}