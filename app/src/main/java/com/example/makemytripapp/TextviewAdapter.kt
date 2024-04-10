import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.R

class TextAdapter(private val dataList: List<String>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.textview_items, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val text = dataList[position]
        holder.bind(text)
        holder.itemView.setOnClickListener { onItemClick(text) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.text_view_recycler)

        fun bind(text: String) {
            textView.text = text
            itemView.setOnClickListener { onItemClick(text) }
        }
    }
}

