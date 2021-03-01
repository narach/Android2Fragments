package org.itacademy.sample.android2fragments.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import org.itacademy.sample.android2fragments.R
import org.itacademy.sample.android2fragments.data.NotebookItem

class NotebooksAdaptor(
    var notebooks: List<NotebookItem>
) : RecyclerView.Adapter<NotebooksAdaptor.NotebooksViewHolder>() {

    inner class NotebooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotebooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotebooksViewHolder, position: Int) {
        holder.itemView.apply {
            tvModel.text = notebooks[position].model
            tvScreen.text = notebooks[position].screen
            tvCharacterisctics.text = notebooks[position].hardware
            ivNote.setImageDrawable(notebooks[position].img)
        }
    }

    override fun getItemCount(): Int {
        return notebooks.size
    }
}