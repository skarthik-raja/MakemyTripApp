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

    private var currentVideoPosition = RecyclerView.NO_POSITION
    private var currentVideoView: VideoView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_items, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoUri = videoUris[position]
        holder.bindVideo(context, videoUri, position == currentVideoPosition)
    }

    override fun getItemCount(): Int {
        return videoUris.size
    }

    fun playVideo(position: Int) {
        if (position != currentVideoPosition) {
            currentVideoPosition = position
            notifyDataSetChanged()
        }
    }

    fun stopPlayback() {
        currentVideoPosition = RecyclerView.NO_POSITION
        currentVideoView?.stopPlayback()
        currentVideoView = null
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoView: VideoView = itemView.findViewById(R.id.video_view)

        fun bindVideo(context: Context, videoUri: String, playNow: Boolean) {
            val uri = Uri.parse(videoUri)
            videoView.setVideoURI(uri)

            if (playNow) {
                startVideo()
            } else {
                stopVideo()
            }

            videoView.setOnPreparedListener {
                currentVideoView = videoView
            }
        }

        private fun startVideo() {
            videoView.start()
            currentVideoView = videoView
        }

        private fun stopVideo() {
            if (videoView.isPlaying) {
                videoView.stopPlayback()
            }
        }
    }
}

