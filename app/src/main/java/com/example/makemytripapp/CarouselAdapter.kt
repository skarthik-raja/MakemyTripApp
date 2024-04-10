import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.R

class CarouselAdapter(private val context: Context, private val images: List<Int>, private val imageDescriptions: List<String>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_carousel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResId = images[position]
        val imageDescription = imageDescriptions[position]
        holder.imageView.setImageResource(imageResId)
        holder.textViewImageDescription.text = imageDescription
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewCarousel)
        val textViewImageDescription: TextView = itemView.findViewById(R.id.textcarousel)
    }
}
