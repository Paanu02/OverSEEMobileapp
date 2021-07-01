package com.panu.overseemobileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val deviceList : ArrayList<DataDevice>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener :  onItemClickListener

    interface onItemClickListener{

        fun  onItemClick(position: Int)

    }
    fun  setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.device_item,
                parent,false)
            return MyViewHolder(itemView,mListener)

        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val currentitem = deviceList[position]

            holder.Devicename.text = currentitem.Devicename
            holder.Type.text = currentitem.Type



        }

        override fun getItemCount(): Int {

            return deviceList.size
        }

        class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

            val Devicename : TextView = itemView.findViewById(R.id.DeviceName)
            val Type : TextView = itemView.findViewById(R.id.TypeName)


            init {

                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }

            }




        }
    }
