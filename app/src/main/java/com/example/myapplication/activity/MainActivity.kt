package com.example.myapplication.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.friend.FriendActivity
import com.example.myapplication.activity.stock.StockActivity
import com.example.myapplication.model.MenuSingleton
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var mData: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initData()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.my_recycler_view)

        val gridLayoutManager = GridLayoutManager(this, 3)
//        // 設置RecyclerView為列表型態
//        gridLayoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int) =
//                when (position % 3) {
//                0 -> 1 // 50dp item
//                1 -> 2 // 100dp item
//                else ->3
//            }
//        }
        recyclerView.layoutManager = gridLayoutManager
    }

    private fun initData() {
        val menuList = MenuSingleton.getMenuList()
        mData = ArrayList()
        for(i in 0..menuList.size) {
            mData.add("列表:"+i)
        }
        myAdapter = MyAdapter(mData, this)
        recyclerView.adapter = myAdapter
    }

    class MyAdapter(private val mData: ArrayList<String>, private val context: Context) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout)

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {
            // create a new view
            val constraintLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false) as ConstraintLayout
            // set the view's size, margins, paddings and layout parameters
            return MyViewHolder(
                constraintLayout
            )
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.constraintLayout.tv_item_title.text = mData[position]
            holder.constraintLayout.btn_item_action.setOnClickListener {
                jumpToNext(position)
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = mData.size

        fun jumpToNext(position: Int) {
            var intent = Intent()
            when (mData[position]) {
                "列表:1" -> intent.setClass(context, StockActivity::class.java)
                "列表:2" -> intent.setClass(context, FriendActivity::class.java)
                else -> return
            }
            context.startActivity(intent)
        }
    }
}
