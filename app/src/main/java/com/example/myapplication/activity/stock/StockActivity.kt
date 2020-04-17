package com.example.myapplication.activity.stock

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.StockData
import com.example.myapplication.model.StockSingleton
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.stock_list_item.view.*

class StockActivity: AppCompatActivity(), OnChartValueSelectedListener {

    private lateinit var clRecyclerViewTitle: ConstraintLayout
    private lateinit var pieChartView: PieChart
    private val jsonStock = StockSingleton.getStockData()
    private lateinit var jsonArray: ArrayList<StockData>
    private lateinit var stockRecyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        initView()
        initJSONData()
        initPieChartView()
        initRecyclerView()
    }

    private fun initView() {
        clRecyclerViewTitle = findViewById(R.id.cl_stock_recyclerview_title)
        pieChartView = findViewById(R.id.pc_piechart)
        stockRecyclerView = findViewById(R.id.rv_stock)

        setTitleRight()
    }

    private fun setTitleRight() {
        val tvTitleRight = findViewById<TextView>(R.id.tv_title_bar_right)
        tvTitleRight.text = "編輯"
        tvTitleRight.setOnClickListener { println("13513513533") }
    }

    private fun initJSONData() {
        val listType = object : TypeToken<ArrayList<StockData>>(){}.type
        jsonArray = Gson().fromJson<ArrayList<StockData>>(jsonStock, listType)
    }

    private fun initPieChartView() {
        var pieList: ArrayList<PieEntry> = ArrayList()
        for (obj in jsonArray) {
            println(obj.name + ":" + (obj.price*obj.quantity))
            pieList.add(PieEntry((obj.price*obj.quantity), obj.name))
        }

        val dataSet = PieDataSet(pieList, "Label")

        val colorList = StockSingleton.getColorList(this, pieList.size)
        dataSet.setColors(colorList)

        var pieData = PieData(dataSet)
        pieData.setDrawValues(true)
        pieData.setValueTextSize(12f)    //special
        pieData.setValueFormatter(PercentFormatter(pieChartView))     // important

        pieChartView.setUsePercentValues(true)
        pieChartView.isDrawHoleEnabled = false
        pieChartView.description.isEnabled = false

        pieChartView.setEntryLabelTextSize(10f)

        pieChartView.data = pieData
        pieChartView.invalidate()
        pieChartView.animateXY(1000, 1000)

        pieChartView.setOnChartValueSelectedListener(this)
    }

    private fun initRecyclerView() {
        stockRecyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter =  MyAdapter(jsonArray, this)
        stockRecyclerView.adapter = myAdapter
    }

    class MyAdapter(private val mData: ArrayList<StockData>, private val context: Context) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout)

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {
            // create a new view
            val constraintLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.stock_list_item, parent, false) as ConstraintLayout
            // set the view's size, margins, paddings and layout parameters
            return MyViewHolder(
                constraintLayout
            )
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.constraintLayout.tv_stock_name.text = mData[position].name
            holder.constraintLayout.tv_stock_code.text = mData[position].code
            holder.constraintLayout.tv_stock_quantity.text = "" + mData[position].quantity
            holder.constraintLayout.tv_stock_price.text = "" + mData[position].price
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = mData.size
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null) return

        h?.let {
            Log.i("VAL SELECTED","Value: " + e.getY() + ", index: " + it.x
                    + ", DataSet index: " + it.dataSetIndex)
        }
    }

    override fun onNothingSelected() {
        Log.i("PieChart", "nothing selected")
    }
}