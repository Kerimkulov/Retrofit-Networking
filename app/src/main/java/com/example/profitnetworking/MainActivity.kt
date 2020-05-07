package com.example.profitnetworking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profitnetworking.networking.DataLoader
import com.example.profitnetworking.networking.FactResponse
import com.example.profitnetworking.networking.Hint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    companion object{
        const val KEY = "3588873-8bb0e70fdfcef7f31eee25461"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image_list.layoutManager = LinearLayoutManager(this)


        DataLoader(
            onSuccess = { response ->
                displayImages(response)
                find(response)

            },
            onError = {
                Log.d("taaag", it.message)
            },
            key = KEY
        ).loadData()
    }

        private fun find(response: FactResponse){
                val search = findViewById<SearchView>(R.id.search_view)
                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {

                    return false
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()){
                        displayImages(response)
                    }
                    else{
                        searchByTag(response,newText)
                    }
                    return false
                }
            })
        }

    private fun searchByTag(response: FactResponse, tag: String){
        var byTag = tag.toLowerCase(Locale.getDefault())
        val list = ArrayList<Hint>()
        for (hint in response.hits){
            if (hint.tags.contains(byTag,false)){
                list.add(hint)
            }
        }
        image_list.adapter = list.let {
            MyAdapter(hintList = it, onHintClick = {
                val intent = Intent(this, DisplayImage::class.java)
                intent.putExtra("uri", it.largeImageURL)
                startActivity(intent)
            })
        }

    }
    private fun displayImages(response: FactResponse){
        image_list.adapter = response.hits.let {
            MyAdapter(hintList = it, onHintClick = {
                val intent = Intent(this, DisplayImage::class.java)
                intent.putExtra("uri", it.largeImageURL)
                startActivity(intent)
            })
        }
    }
}
