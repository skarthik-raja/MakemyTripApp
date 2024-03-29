import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makemytripapp.R

class RecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private val dataList = mutableListOf<RecyclerData>()

    fun updateDataset(updatedList: List<RecyclerData>){
        dataList.clear()
        dataList.addAll(updatedList)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_office: ImageView = itemView.findViewById(R.id.iv_image1)
        var tv_office: TextView = itemView.findViewById(R.id.tv_text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        if(currentItem.isVisible){
            holder.iv_office.visibility = View.VISIBLE
            holder.tv_office.visibility = View.VISIBLE
        }else{
            holder.iv_office.visibility = View.GONE
            holder.tv_office.visibility = View.GONE
        }
        holder.iv_office.setImageResource(currentItem.img)
        holder.tv_office.text = currentItem.office
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
