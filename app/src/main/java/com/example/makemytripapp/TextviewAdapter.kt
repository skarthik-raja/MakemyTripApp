import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.R

class TextAdapter(private val dataList: List<String>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    private var selectedItemIndex: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.textview_items, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val text = dataList[position]
        holder.bind(text, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.text_view_recycler)

        fun bind(text: String, position: Int) {
            textView.text = text
            if (position == selectedItemIndex) {
                // Highlight the selected item with outline box color
                textView.background = ContextCompat.getDrawable(itemView.context, R.drawable.selceted_text_background)
                textView.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
            } else {
                // Reset color for other items
                textView.background = null
                textView.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            }

            itemView.setOnClickListener {
                selectedItemIndex = adapterPosition
                notifyDataSetChanged()

                onItemClick(text)
            }
        }
    }
}
