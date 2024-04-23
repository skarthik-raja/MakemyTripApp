import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.R

class VideoAdapter(private val context: Context, private val videoUris: List<String>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_items, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoUri = videoUris[position]
        holder.bindVideo(context, videoUri)
    }

    override fun getItemCount(): Int {
        return videoUris.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoView: VideoView = itemView.findViewById(R.id.videoView)

        fun bindVideo(context: Context, videoUri: String) {
            val uri = Uri.parse(videoUri)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()
        }
    }
}
