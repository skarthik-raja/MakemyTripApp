import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.makemytripapp.R
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.DataSource

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dataset: List<RecyclerData> = ArrayList()
    private var isItemsVisible = true

    fun updateDataset(newDataset: List<RecyclerData>) {
        dataset = newDataset
        notifyDataSetChanged()
    }

    fun toggleItems() {
        isItemsVisible = !isItemsVisible
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataset[position]
        if (isItemsVisible) {
            holder.itemView.visibility = View.VISIBLE
            holder.bind(currentItem)
        } else {
            holder.itemView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.tv_text1)
        private val imageViewIcon: ImageView = itemView.findViewById(R.id.iv_image1)

        fun bind(item: RecyclerData) {
            textViewName.text = item.name
            // Load image using Glide from the URL stored in RecyclerData
            Glide.with(context)
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                .error(R.drawable.error_image) // Error image if loading fails
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Log the error
                        Log.e("Glide", "Error loading image: ${e?.message}")
                        // Return false to allow Glide to handle the error image
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Image loaded successfully
                        Log.d("Glide", "Image loaded successfully")
                        return false
                    }
                })
                .into(imageViewIcon)
        }
    }
}
