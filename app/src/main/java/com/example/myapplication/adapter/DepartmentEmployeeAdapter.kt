package com.example.myapplication.adapter
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.department_emp_list_item.view.*

class DepartmentEmployeeAdapter (
        private val items: List<User> = listOf(),
        private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<DepartmentEmployeeAdapter.DepartmentEmployeeViewHolder>(){
    inner class DepartmentEmployeeViewHolder(private val view:View) :RecyclerView.ViewHolder(view) {
        fun bindItem(item : User) {
            val yellow: Drawable? =  ResourcesCompat.getDrawable(this.view.resources,R.drawable.pb_background_yellow,null)
            val red: Drawable? =  ResourcesCompat.getDrawable(this.view.resources,R.drawable.pb_backgorund_red,null)
            val green: Drawable? =  ResourcesCompat.getDrawable(this.view.resources,R.drawable.pb_background_green,null)
            Picasso.with(view.context).load(item.profilePhoto).into(view.profile_image_dep_emp_list)
            view.user_name_dep_emp_list_item.text = item.firstName+" "+item.lastName
            view.user_work_percent_dep_emp.text = item.workPercent.toString()+"%"
            view.progressBar.progress = item.workPercent
            if(item.workPercent in 1..32){
                view.progressBar.progressDrawable = red
            }else if(item.workPercent in 34..65){
                view.progressBar.progressDrawable = yellow
            }else {
                view.progressBar.progressDrawable = green
            }
            view.setOnClickListener {
                onItemClick(item)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentEmployeeViewHolder =
            DepartmentEmployeeViewHolder( LayoutInflater.from(parent.context)
                    .inflate(R.layout.department_emp_list_item,parent,false));
    override fun onBindViewHolder(holder: DepartmentEmployeeViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
    override fun getItemCount() = items.size
}