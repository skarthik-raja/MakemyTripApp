import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.R

class ImageAdapter(private var dataList: List<Int>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_horizontal, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageResource = dataList[position]
        holder.bind(imageResource)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(images: List<Int>) {
        dataList = images
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view_icon)

        fun bind(imageResource: Int) {
            imageView.setImageResource(imageResource)
        }
    }
}
