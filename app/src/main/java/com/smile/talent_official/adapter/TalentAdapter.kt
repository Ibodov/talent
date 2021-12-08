package com.smile.talent_official.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.smile.talent_official.R
import com.smile.talent_official.RecyclerViewCallback
import com.smile.talent_official.data.models.TalentModel
import com.chauthai.swipereveallayout.ViewBinderHelper


//2. Создаем адаптер, наследуем от RecyclerView.Adapter
class TalentAdapter(val recyclerViewCallback: RecyclerViewCallback) :
    RecyclerView.Adapter<TalentAdapter.TalentViewHolder>() {
    private val viewBinderHelper = ViewBinderHelper()

    init {
        viewBinderHelper.setOpenOnlyOne(true)
    }

    var listTalent = emptyList<TalentModel>()

//    class TalentViewHolder(view: View): RecyclerView.ViewHolder(view){
//        val tvSpecialistName = view.findViewById<TextView>(R.id.tv_specialist_name)
//        val tvSpecialization = view.findViewById<TextView>(R.id.tv_specialization_rc)
//        val ivProfile = view.findViewById<ImageView>(R.id.iv_profile_rc)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalentViewHolder {
        val view1 = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return TalentViewHolder(view1)
    }

//    override fun onBindViewHolder(holder: TalentViewHolder, position: Int) {
//        holder.tvSpecialistName.text = listTalent[position].name
//        holder.tvSpecialization.text = listTalent[position].specialization
//    }


    override fun onBindViewHolder(holder: TalentViewHolder, position: Int) {
        viewBinderHelper.bind(holder.swipeRevealLayout, listTalent.get(position).id.toString())
        holder.tvDelete?.setOnClickListener {
            recyclerViewCallback.onDeleteItem(listTalent.get(position))
        }
        listTalent.let {
            holder.initSpecialist(it[position])
        }
    }


    override fun getItemCount(): Int {
        return listTalent.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<TalentModel>) {
        //передаем наш лист
        listTalent = list
        notifyDataSetChanged()// этот метод при изменение уведомляет
    }


//    override fun onViewAttachedToWindow(holder: TalentViewHolder) {
//        super.onViewAttachedToWindow(holder)
//        holder.itemView.setOnClickListener {
//            recyclerViewCallback.onItemClick(listTalent[holder.bindingAdapterPosition])
//        }
//    }

    override fun onViewDetachedFromWindow(holder: TalentViewHolder) {
        holder.itemView.setOnClickListener(null)
    }


    /*--------*/

    inner class TalentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var specialistImage: ImageView? = null
        var name: TextView? = null
        var specialization: TextView? = null
        var phoneNumber: TextView? = null
        var swipeRevealLayout = itemView?.findViewById<SwipeRevealLayout>(R.id.swipe_reveal_layout)
        val tvDelete = itemView?.findViewById<TextView>(R.id.tv_delete)

        init {
            name = itemView?.findViewById(R.id.tv_specialist_name)
            specialization = itemView?.findViewById(R.id.tv_specialization_rc)
            specialistImage = itemView?.findViewById(R.id.iv_profile_rc)
            phoneNumber = itemView?.findViewById(R.id.tv_phone_number)

            itemView?.findViewById<View>(R.id.cv_talent)?.setOnClickListener {
                    recyclerViewCallback.onItemClick(listTalent[bindingAdapterPosition])

            }
        }

        fun initSpecialist(item: TalentModel) {
            name?.text = item.name
            specialization?.text = item.specialization
            phoneNumber?.text = item.phoneNumber

            if (item.uri.isNotBlank()) {
                specialistImage?.setImageURI(item.uri.toUri())
            } else {
                specialistImage?.setImageResource(item.picture)

            }

        }

    }


}