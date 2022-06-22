package com.nirbhay.mendofeel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.nirbhay.mendofeel.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var data = ArrayList<ItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.shimmer.apply {
            startShimmer()
            isVisible = true
        }

        loadData()


    }

    private fun loadData(){
        val userD : Call<Response> = Service.serviceInstance.getUserData()
        userD.enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                try {
                    if(response.code()==200) {
                        val list = response.body()!!.results!!

                        for (element in list) {

                            val name = if (element?.fullname != null) {
                                element.fullname
                            } else {
                                "Anonymous User"
                            }

                            val time = if (element?.publish != null) {
                                element.publish.toString()
                            } else {
                                element?.publishtime.toString()
                            }
                            val text = if (element?.question != null) {
                                element.question
                            } else {
                                element?.questionText.toString()
                            }

                            val profilePic = if (element?.profilePhoto != null) {
                                element.profilePhoto
                            } else {
                                "null"
                            }

                            val postPic = if (element?.postPhoto != null) {
                                element.postPhoto
                            } else {
                                "null"
                            }

                            val likeCount = element?.likescount.toString()
                            val commentCount = element?.commentscount.toString()

                            val isLiked = element?.isLiked.toString()

                            val type = element?.type.toString()

                            var choiceList: List<ChoicesItem?>? = null

                            if (type == "Polls") {
                                choiceList = element?.choices
                            }
                            data.add(ItemViewModel(
                                    name,
                                    time,
                                    text,
                                    profilePic,
                                    postPic,
                                    likeCount,
                                    commentCount,
                                    isLiked,
                                    type,
                                    choiceList
                                )
                            )


                        }
                        binding.shimmer.apply {
                            startShimmer()
                            isVisible = false
                        }
                        binding.progressBar.isVisible = false
                        val adapter = RVAdapter(data)
                        binding.recyclerView.adapter = adapter

                    }else{
                        Toast.makeText(this@MainActivity,"Network Error",Toast.LENGTH_LONG).show()
                    }

                }catch (e: IOException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("tag", "Failure")
                t.printStackTrace()
            }

        })
    }
}