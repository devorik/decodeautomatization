package com.example.myapplication.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Department
import kotlinx.android.synthetic.main.department_item.view.*
class DepartmentAdapter (
    private val items: List<Department> = listOf(),
    private val onItemClick: (Department) -> Unit
) : RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>(){
    inner class DepartmentViewHolder(private val view:View) :RecyclerView.ViewHolder(view) {
        fun bindItem(item : Department) {
            view.department_logo.setImageResource(item.department_logo)
            view.department_name.text = item.department_name
            view.setOnClickListener {
                 onItemClick(item)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder =
        DepartmentViewHolder( LayoutInflater.from(parent.context)
            .inflate(R.layout.department_item,parent,false));
    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
    override fun getItemCount() = items.size
}