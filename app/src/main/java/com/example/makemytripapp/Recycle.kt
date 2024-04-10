import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.R

class RecyclerAdapter1(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter1.ViewHolder>() {
    private var items: List<String> = listOf()

    fun setData(data: List<String>) {
        items = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) {
            itemView.findViewById<TextView>(R.id.card_textview).text = item
        }
    }
}

