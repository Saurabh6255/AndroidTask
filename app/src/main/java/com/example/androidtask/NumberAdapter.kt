package com.example.androidtask

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class NumberAdapter(private val allnumbers: List<Int>,private val context:Context,private var type:String)
    : RecyclerView.Adapter<NumberAdapter.ViewHolder>(){


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

        val number=itemview.findViewById<TextView>(R.id.tvNumbers)
        val cvMain=itemview.findViewById<MaterialCardView>(R.id.cvMain)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.card_design_home,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberAdapter.ViewHolder, position: Int) {


        val number = allnumbers[position]
        holder.number.text = number.toString()

        val shouldHighlight = when (type.uppercase()) {
            "ODD" -> number % 2 != 0
            "EVEN" -> number % 2 == 0
            "PRIME" -> isPrime(number)
            "FIBONACCI" -> isFibonacci(number)
            else -> false
        }

        val colorRes = if (shouldHighlight) R.color.yellow else R.color.white
        holder.cvMain.setCardBackgroundColor(ContextCompat.getColor(context, colorRes))


    }


    fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..n / 2) {
            if (n % i == 0) return false
        }
        return true
    }

    fun isFibonacci(n: Int): Boolean {
        if (n == 0 || n == 1) return true
        var a = 0
        var b = 1
        while (b < n) {
            val temp = a + b
            a = b
            b = temp
        }
        return b == n
    }

    override fun getItemCount(): Int {
       return allnumbers.size
    }
}