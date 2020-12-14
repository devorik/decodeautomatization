package com.example.myapplication.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.bottomsheet.BottomSheetDialog


class StatsFillFragment : Fragment() {

    //private lateinit var bottomSheetDialog:BottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_fill, container, false)
    }

    override fun onResume() {
        super.onResume()
        val visitors = listOf(
                BarEntry(0f,10f),
                BarEntry(1f,24f),
                BarEntry(2f,56f),
                BarEntry(3f,18f),
                BarEntry(4f,45f),
                BarEntry(5f,23f)
        )
        var totalLeads = 0
        for (i in visitors){
            totalLeads+=i.y.toInt()
        }
        //val numberOfLeads: TextView? = requireActivity().findViewById(R.id.numberOfLead)
        //numberOfLeads?.text =totalLeads.toString()

        val barChart: BarChart? = requireActivity().findViewById(R.id.barChartFill)
        //val drawBg = ContextCompat.getDrawable(requireContext(),R.drawable.fade_blue)

        val xAxisLabels = arrayListOf(
                "Дс",
                "Cc",
                "Cc",
                "Бс",
                "Жм",
                "Сб"
        )

        barChart?.xAxis?.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart?.xAxis?.position = XAxis.XAxisPosition.BOTTOM

        // barChart?.xAxis?.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart?.setDrawGridBackground(false)
        //barChart?.axisLeft?.isEnabled = false
        barChart?.axisRight?.isEnabled = false
        barChart?.description?.isEnabled = false

        val barDataSet = BarDataSet(visitors,"Visitors")

        barDataSet.color = Color.CYAN
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f


        val barData = BarData(barDataSet)
        barChart?.setFitBars(true)
        barChart?.legend?.isEnabled = false
        barChart?.data = barData
        barChart?.animateY(2000)


        barChart?.setOnChartValueSelectedListener(object:OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
//                Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_LONG).show()
                val bottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
                val bottomSheetView = LayoutInflater.from(requireContext())
                        .inflate(
                                R.layout.layout_bottom_sheet,requireActivity().findViewById(R.id.bottomSheetContainer)
                                )
                bottomSheetView.findViewById<Button>(R.id.button_dialog_sheet).setOnClickListener{
                    Toast.makeText(requireContext(),"Dialog sheet button was pressed",Toast.LENGTH_LONG).show()
                    bottomSheetDialog.dismiss()
                }
                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
            }

            override fun onNothingSelected() {

            }

        })

    }

}