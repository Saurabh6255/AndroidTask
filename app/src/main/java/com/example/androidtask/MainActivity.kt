package com.example.androidtask

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidtask.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var allNumbers:List<Int>
    private var type:String="All"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.pbMain.visibility= View.VISIBLE

        allNumbers=(1..100).toList()


        setupRecyclerView()
        setupButtonListeners()


    }


    private fun setupRecyclerView() {
        binding.rvNumbersList.layoutManager = GridLayoutManager(this, 4)
        binding.rvNumbersList.adapter = NumberAdapter(allNumbers, this, type)
        binding.pbMain.visibility = View.GONE
    }

    private fun setupButtonListeners() {
        binding.btnAll.setOnClickListener {
            onFilterSelected("All", "ALL Numbers in 1 to 100")
        }

        binding.btnODD.setOnClickListener {
            onFilterSelected("ODD", "ODD Numbers in 1 to 100")
        }

        binding.btnEVEN.setOnClickListener {
            onFilterSelected("EVEN", "EVEN Numbers in 1 to 100")
        }

        binding.btnPRIME.setOnClickListener {
            onFilterSelected("PRIME", "PRIME Numbers in 1 to 100")
        }

        binding.btnFIB.setOnClickListener {
            onFilterSelected("FIBONACCI", "FIBONACCI Numbers in 1 to 100")
        }
    }

    private fun onFilterSelected(filterType: String, title: String) {
        binding.pbMain.visibility = View.VISIBLE
        binding.tvSelected.text = title

        CoroutineScope(Dispatchers.Main).launch {
            delay(1500) // Optional: simulate loading
            type = filterType
            binding.rvNumbersList.adapter = NumberAdapter(allNumbers, this@MainActivity, type)
            binding.pbMain.visibility = View.GONE
        }
    }

}