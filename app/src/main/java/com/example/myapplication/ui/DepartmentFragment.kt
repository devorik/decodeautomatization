package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.DepartmentAdapter
import com.example.myapplication.model.Department
import kotlinx.android.synthetic.main.fragment_department.*

class DepartmentFragment : Fragment() {

    private val departmentList = listOf(
        Department(R.drawable.digitalmarketing,"Маркетинг"),
        Department(R.drawable.analytics,"Аналитика"),
        Department(R.drawable.hr,"Кадр"),
        Department(R.drawable.accounting,"Есеп"),
        Department(R.drawable.money,"Қаржы"),
            Department(R.drawable.digitalmarketing,"Маркетинг"),
            Department(R.drawable.analytics,"Аналитика"),
            Department(R.drawable.hr,"Кадр"),
            Department(R.drawable.accounting,"Есеп"),
            Department(R.drawable.money,"Қаржы")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_department, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        department_list.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        department_list.adapter = DepartmentAdapter(departmentList,onItemClick = {
            val frag = EmployeeStatsFragment()
            val bundle = Bundle()
            bundle.putString("DEPNAME",it.department_name)
            frag.arguments = bundle
            //handleFrame(EmployeeStatsFragment())
            requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame1,EmployeeStatsFragment())
                    .commit()
        })
    }

    override fun onResume() {
        super.onResume()
    }

    private fun handleFrame(fragment:Fragment): Boolean {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransaction.replace(R.id.frame1,fragment).addToBackStack(null).commit()
        return true
    }

    companion object {
        fun newInstance(): DepartmentFragment = DepartmentFragment()
    }

}