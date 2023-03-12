package com.example.academyhomework

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.databinding.ItemListAdapterBinding

class ListAdapter(
    private var dataSet: MutableList<DataList>,
    private val clickAction: (Int, Int) -> MutableList<DataList>,

    ) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var binding: ItemListAdapterBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        binding = ItemListAdapterBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding, viewGroup.context, clickAction)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position], position)
    }

    fun updateList(newList: MutableList<DataList>) {
        this.dataSet = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(
        private val binding: ItemListAdapterBinding, private val context: Context,
        private val clickAction: (Int, Int) -> MutableList<DataList>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataList, position: Int) {
            with(binding) {
                when (item.imageId) {
                    0 -> {
                        imagePerson.setImageResource(R.drawable.ic_baby)
                        val bitmap1: Bitmap =
                            BitmapFactory.decodeResource(context.resources, R.drawable.ic_baby)
                        createPalette(bitmap1, binding)
                    }
                    1 -> {
                        imagePerson.setImageResource(R.drawable.ic_schoolboy)
                        val bitmap1: Bitmap =
                            BitmapFactory.decodeResource(context.resources, R.drawable.ic_schoolboy)
                        createPalette(bitmap1, binding)
                    }
                    2 -> {
                        imagePerson.setImageResource(R.drawable.ic_man)
                        val bitmap1: Bitmap =
                            BitmapFactory.decodeResource(context.resources, R.drawable.ic_man)
                        createPalette(bitmap1, binding)
                    }
                    3 -> {
                        imagePerson.setImageResource(R.drawable.ic_elderly)
                        val bitmap1: Bitmap =
                            BitmapFactory.decodeResource(context.resources, R.drawable.ic_elderly)
                        createPalette(bitmap1, binding)
                    }
                }

                imagePerson.setImageResource(item.imageId)
                tvName.text = item.name
                tvSurname.text = item.surname
                tvPhone.text = "+${item.phone}"
                tvAge.text = "${item.age} years"
                tvBirthday.text = item.birthday
            }

            binding.btnDeleteItem.setOnClickListener {
                updateList(
                    (clickAction.invoke(
                        item.itemId, position
                    ))
                )
            }
        }

        private fun createPalette(bitmap: Bitmap, binding: ItemListAdapterBinding) {
            Palette.from(bitmap).generate { palette ->
                val defaultValue = 0x000000
                binding.root.setBackgroundColor(palette!!.getDominantColor(defaultValue))
            }
        }
    }
}