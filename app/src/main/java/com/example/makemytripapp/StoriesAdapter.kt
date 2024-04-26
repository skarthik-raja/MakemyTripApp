// MediaAdapter.kt
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.MediaModel
import com.example.makemytripapp.R

class MediaAdapter(private val mediaList: List<MediaModel>) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    private var onVideoClickListener: OnVideoClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.stories_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val media = mediaList[position]

        if (media.mediaType == 0) {
            // Set image resource
            holder.imageView.setImageResource(media.resourceId)
            holder.imageView.visibility = View.VISIBLE
            holder.videoView.visibility = View.GONE
        } else if (media.mediaType == 1) {
            holder.videoView.setVideoURI(Uri.parse("android.resource://" + holder.itemView.context.packageName + "/" + media.videoResourceId))
            holder.videoView.start()
            holder.imageView.visibility = View.GONE
            holder.videoView.visibility = View.VISIBLE
        }

        holder.textView.text = media.text
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    fun setOnVideoClickListener(listener: OnVideoClickListener) {
        this.onVideoClickListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.story_image)
        val videoView: VideoView = itemView.findViewById(R.id.videoreels)
        val textView: TextView = itemView.findViewById(R.id.text_story_image)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onVideoClickListener?.onVideoClick(adapterPosition)
        }
    }

    interface OnVideoClickListener {
        fun onVideoClick(position: Int)
    }
}