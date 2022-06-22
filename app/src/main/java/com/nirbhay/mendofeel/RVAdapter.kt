package com.nirbhay.mendofeel

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


const val DOUBLE_CLICK_TIME : Long = 300

class RVAdapter(private val mList: List<ItemViewModel>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = mList[position]
        holder.textView.text = itemViewModel.text
        holder.textView1.text = itemViewModel.text2
        holder.textView2.text = itemViewModel.text3

        holder.textView2.setOnClickListener(object : DoubleClickListener(){
            override fun onDoubleClick(v: View?) {
                holder.like.setImageResource(R.drawable.heart)
                holder.likeCount.text = (Integer.valueOf(itemViewModel.likeCount)+1).toString()
            }
        })

        holder.postPic.setOnClickListener(object : DoubleClickListener(){
            override fun onDoubleClick(v: View?) {
                holder.like.setImageResource(R.drawable.heart)
                holder.likeCount.text = (Integer.valueOf(itemViewModel.likeCount)+1).toString()
            }
        })

        holder.like.setOnClickListener {
            holder.like.setImageResource(R.drawable.heart_outline)
            holder.likeCount.text = itemViewModel.likeCount
        }


        if(itemViewModel.img == "null"){
            holder.imageView.setImageResource(R.drawable.img)
        }else {
            Glide.with(holder.imageView)
                .load(itemViewModel.img)
                .into(holder.imageView)
        }

        if(itemViewModel.postPic == "null"){
            holder.postPic.isVisible = false
        }else {
            Glide.with(holder.postPic)
                .load(itemViewModel.postPic)
                .into(holder.postPic)
        }

        holder.likeCount.text = itemViewModel.likeCount
        holder.commentCount.text = itemViewModel.commentCount

        if (itemViewModel.isLiked == "true"){
            holder.like.setImageResource(R.drawable.heart)
        }

        holder.relativePoll.isVisible = itemViewModel.type == "Polls"

        if (itemViewModel.type == "Polls"){
            holder.relativeLike.isVisible = false
            val choiceItem = itemViewModel.choiceList?.get(0)
            val choiceItem2 = itemViewModel.choiceList?.get(1)
            holder.progressText1.text = choiceItem?.choiceText
            holder.progressText2.text = choiceItem2?.choiceText
            holder.bar1.progress = choiceItem?.percentage!!
            holder.bar2.progress = choiceItem2?.percentage!!
            holder.progressDone1.text = choiceItem.percentage.toString()+"%"
            holder.progressDone2.text = choiceItem2.percentage.toString()+"%"

            val totalVotes = choiceItem.votes?.plus(choiceItem2.votes!!)
            Log.e("cc", totalVotes.toString())

            holder.bar1.setOnClickListener {
                if (totalVotes != 0){
                    if (totalVotes != null) {
                        holder.progressDone1.text = (((choiceItem.votes.plus(1).toDouble()).div((totalVotes.plus(1).toDouble()))).times(100.0)).toString().format("%.1f")+"%"
                        holder.progressDone2.text = (((choiceItem2.votes?.toDouble())?.div((totalVotes.plus(1).toDouble())))?.times(100.0)).toString().format("%.1f")+"%"
                        holder.bar1.progress = (((choiceItem.votes.plus(1).toDouble()).div(
                            (totalVotes.plus(1).toDouble())
                        )).times(100.0)).toInt()
                        holder.bar2.progress = (((choiceItem2.votes?.toDouble())?.div((totalVotes.plus(1).toDouble())))?.times(100.0))?.toInt()!!
                        holder.bar2.isClickable = false

                        Toast.makeText(holder.bar1.context,"Thanks For Your Vote", Toast.LENGTH_SHORT).show()

                    }
                }else if (totalVotes == 0){
                    holder.progressDone1.text = "100%"
                    holder.bar1.progress = 100
                    holder.bar2.isClickable = false
                    Toast.makeText(holder.bar1.context,"Thanks For Your Vote", Toast.LENGTH_SHORT).show()
                }
            }

            holder.bar2.setOnClickListener {
                if (totalVotes != 0){
                    if (totalVotes != null) {
                        holder.progressDone1.text = (((choiceItem.votes.toDouble()).div((totalVotes.plus(1).toDouble()))).times(100.0)).toString().format("%.1f")+"%"
                        holder.progressDone2.text = (((choiceItem2.votes?.plus(1)?.toDouble())?.div((totalVotes.plus(1).toDouble())))?.times(100.0)).toString().format("%.1f")+"%"
                        holder.bar1.progress = (((choiceItem.votes.toDouble()).div(
                            (totalVotes.plus(1).toDouble())
                        )).times(100.0)).toInt()
                        holder.bar2.progress = (((choiceItem2.votes?.plus(1)?.toDouble())?.div((totalVotes.plus(1).toDouble())))?.times(100.0))?.toInt()!!
                        holder.bar1.isClickable = false
                        Toast.makeText(holder.bar2.context,"Thanks For Your Vote", Toast.LENGTH_SHORT).show()
                    }
                }else if (totalVotes == 0){
                    holder.progressDone2.text = "100%"
                    holder.bar2.progress = 100
                    holder.bar1.isClickable = false
                    Toast.makeText(holder.bar2.context,"Thanks For Your Vote", Toast.LENGTH_SHORT).show()

                }
            }

        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val textView: TextView = itemView.findViewById(R.id.userName)
        val textView1: TextView = itemView.findViewById(R.id.publish)
        val textView2: TextView = itemView.findViewById(R.id.text)
        val imageView: ImageView = itemView.findViewById(R.id.profilePic)
        val postPic: ImageView = itemView.findViewById(R.id.postPic)

        val likeCount: TextView = itemView.findViewById(R.id.likeCount)
        val commentCount: TextView = itemView.findViewById(R.id.commentCount)

        val like: ImageView = itemView.findViewById(R.id.like)

        val relativePoll : RelativeLayout = itemView.findViewById(R.id.relativePoll)

        val relativeLike : RelativeLayout = itemView.findViewById(R.id.relativeLike)

        val bar1 : ProgressBar = itemView.findViewById(R.id.bar1)
        val bar2 : ProgressBar = itemView.findViewById(R.id.bar2)

        val progressText1 : TextView = itemView.findViewById(R.id.progressText1)
        val progressText2 : TextView = itemView.findViewById(R.id.progressText2)

        val progressDone1: TextView = itemView.findViewById(R.id.progressDone1)
        val progressDone2: TextView = itemView.findViewById(R.id.progressDone2)


    }

}

abstract class DoubleClickListener: View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(v: View?) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME){
            onDoubleClick(v)
        }
        lastClickTime = clickTime
    }

    abstract fun onDoubleClick(v: View?)

}
