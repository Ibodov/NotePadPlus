package com.smile.notepad.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smile.notepad.R
import com.smile.notepad.models.AppNote

class MainAdapter:RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private var mListNotes = emptyList<AppNote>()

    class MainHolder(view: View):RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.findViewById(R.id.item_note_name)
        val textNote: TextView = view.findViewById(R.id.item_note_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainHolder(view)
    }
    //нажатие на элемент в RV
    override fun onViewAttachedToWindow(holder: MainHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.click(mListNotes[holder.adapterPosition]) //получения позиции holder a в адаптер
//            MainFragment.clicl2(mListNotes[holder.adapterPosition])
        }
    }
    //отключения слушателя клика holder a (после ухода с экрана)
    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.textNote.text = mListNotes[position].text
        holder.nameNote.text = mListNotes[position].name
    }
    override fun getItemCount(): Int {
        return mListNotes.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<AppNote>) {
        mListNotes = list
        notifyDataSetChanged()
    }
}