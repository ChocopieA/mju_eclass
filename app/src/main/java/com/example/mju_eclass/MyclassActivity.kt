package com.example.mju_eclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mju_eclass.data.MyClass
import com.example.mju_eclass.databinding.ActivityMainBinding
import com.example.mju_eclass.databinding.ActivityMyclassBinding
import com.example.mju_eclass.databinding.ListBarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyclassActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMyclassBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myclass)
        setContentView(binding.root)

        val id = intent.getStringExtra("id").toString()
        val pwd = intent.getStringExtra("pwd").toString()

        val retrofit1 = Retrofit.Builder()
            .baseUrl("http://14.39.43.250:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var cnt = 0
        val service1 = retrofit1.create(EclassSchedule::class.java)
        Log.d("id/pwd", "asdf")
        val datas = mutableListOf<MyClass>()
        service1.addFriend(id, pwd)?.enqueue(object : Callback<List<ClassData>> {

            override fun onFailure(call: Call<List<ClassData>>, t: Throwable) {
                Log.d(
                    "CometChatAPI::", "Failed API call with call: " + call +
                            " + exception: " + t
                )
            }

            override fun onResponse(call: Call<List<ClassData>>, response: Response<List<ClassData>>) {
                Log.d("Response:: ", response.body().toString())

                for (i in 0 until((response.body()?.size)  ?: 1)) {
                    datas.add(
                        MyClass(
                            response.body()?.get(i)?.letureName.toString(),
                            response.body()?.get(i)?.lectureSchedule.toString(),
                            response.body()?.get(i)?.noticeCount.toString(),
                            response.body()?.get(i)?.documentCount.toString()
                        )
                    )
                }
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MyclassActivity)
                binding.recyclerView.adapter = MyAdapter(datas)
                binding.recyclerView.addItemDecoration(DividerItemDecoration(this@MyclassActivity, LinearLayoutManager.VERTICAL))


            }
        })








    }
}



class MyViewHolder(val binding: ListBarBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<MyClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun getItemCount(): Int = datas.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = MyViewHolder(
        ListBarBinding.inflate(LayoutInflater.from(parent.context),parent,false))



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("kkang","onBindViewHolder : $position")
        val binding = (holder as MyViewHolder).binding

        binding.lecturetext.text = datas[position].lectureName
        binding.lecturetimetext.text = datas[position].lectureSchedule
        binding.noticetext.text=datas[position].noticeCount
        binding.referencetext.text=datas[position].documentCount


    }
}
