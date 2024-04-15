import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.makemytripapp.Places
import com.example.makemytripapp.R

class PlaceAdapter(private val context: Context, private val placeList: List<Places>) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.places_layout, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placeList[position]
        holder.bind(place)
    }

    override fun getItemCount(): Int = placeList.size

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val cardView: CardView = itemView.findViewById(R.id.cardView_go)
       val placeImageView: ImageView = itemView.findViewById(R.id.placeImageView)
      val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(place: Places) {
            textView.text = place.name
            Glide.with(context).load(place.imageResource).centerCrop().into(placeImageView)

            cardView.setOnClickListener {
            }
        }
    }
}
