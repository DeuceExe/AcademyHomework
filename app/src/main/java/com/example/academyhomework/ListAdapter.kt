package com.example.academyhomework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.databinding.ItemListAdapterBinding

class ListAdapter(
    private var dataSet: MutableList<DataList>,
    private val clickAction: (Int) -> MutableList<DataList>,

    ) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var binding: ItemListAdapterBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        binding = ItemListAdapterBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])

        binding.btnDeleteItem.setOnClickListener {
            updateList(clickAction.invoke(position))
        }
    }

    private fun updateList(newList: MutableList<DataList>) {
        this.dataSet = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(
        private val binding: ItemListAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataList) {
            with(binding) {
                imagePerson.setImageResource(item.imageId)
                tvName.text = item.name
                tvSurname.text = item.surname
                tvPhone.text = "+${item.phone}"
                tvAge.text = "${item.age} years"
                tvBirthday.text = item.birthday

                /*fun createPalette(bitmap: Bitmap) = run {
                    fun createPalette(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()
                    GlideApp.with(Activity())
                        .asBitmap()
                        .load(imagePerson)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Bitmap?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                if (resource != null) {
                                    val palette: Palette = Palette.from(resource).generate()
                                    var color = palette.darkMutedSwatch?.rgb ?: R.color.white
                                }
                                return false
                            }
                        }).into(binding.imagePerson)
                }*/
            }
        }
    }
}