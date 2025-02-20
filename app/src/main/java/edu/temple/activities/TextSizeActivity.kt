package edu.temple.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TextSizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an array of text sizes (multiples of 5)
        val textSizes = Array(20) { (it + 1) * 5 }
        Log.d("Array values", textSizes.contentToString())

        val recyclerView = findViewById<RecyclerView>(R.id.textSizeSelectorRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TextSizeAdapter(textSizes) { selectedSize ->
            // TODO Step 2: Pass selected value back to activity that launched TextSizeActivity

            val resultIntent = Intent().apply {
                putExtra(DisplayActivity.SIZE_KEY, selectedSize.toFloat())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}


class TextSizeAdapter(private val textSizes: Array<Int>, private val callback: (Int) -> Unit) :
    RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
        init {
            textView.setOnClickListener {
                callback(textSizes[adapterPosition])
            }
        }
    }
