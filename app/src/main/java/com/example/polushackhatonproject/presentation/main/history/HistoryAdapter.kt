package com.example.polushackhatonproject.presentation.main.history

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.domain.main.model.TaskHistory
import com.google.android.material.chip.ChipGroup.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class HistoryAdapter(private val items: List<TaskHistory>):
    RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.name)
        val buttonArea: FrameLayout = item.findViewById(R.id.area_near_button)
        val button: Button = item.findViewById(R.id.button)
//        val description: LayoutInflater = item.findViewById(R.id.description)
        val description: LinearLayout = item.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_history_item, parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = items[position].name
//        holder.description.text = items[position].description

        holder.button.setOnClickListener(buttonClickListener(holder))
        holder.buttonArea.setOnClickListener(buttonClickListener(holder))
    }

    private fun buttonClickListener(holder: MyViewHolder) = OnClickListener {
        CoroutineScope(Dispatchers.Main).launch {
            if(holder.description.visibility == GONE) {
                it.animate()
                    .rotation(180f)
                    .start()
                println(180f)
                holder.description.visibility = VISIBLE
            }
            else {
                it.animate()
                    .rotation(0f)
                    .start()
                println(0f)
                holder.description.visibility = GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}