package com.example.mju_eclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.mju_eclass.databinding.ActivityMainBinding
import com.example.mju_eclass.databinding.ActivityMyclassBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginbutton.setOnClickListener {


            val retrofit = Retrofit.Builder()
                .baseUrl("http://14.39.43.250:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //retrofit 객체를 통해 인터페이스 생성
            val service = retrofit.create(EclassLogin::class.java)
            val id: String = binding.enterIDtext.text.toString()
            val pwd: String = binding.enterPWtext.text.toString()
            Log.d("id/pwd", id + " + " + pwd)
            service.addFriend(id, pwd)?.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("CometChatAPI::", "Failed API call with call: " + call +
                            " + exception: " + t)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("Response:: ", response.body().toString())
                    if (response.body().toString().equals("true")){
                        val intent: Intent = Intent(this@MainActivity, MyclassActivity::class.java)
                        intent.putExtra("id", id)
                        intent.putExtra("pwd", pwd)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@MainActivity, "로그인 정보가 유효하지 않습니다", Toast.LENGTH_SHORT).show()

                    }




                }

            })



            if (binding.enterIDtext.text.isNotBlank() && binding.enterPWtext.text.isNotBlank()) {
                val intent = Intent(this, MyclassActivity::class.java).apply {
                    putExtra("ID", binding.enterIDtext.text.toString())
                    putExtra("Password", binding.enterPWtext.text.toString())
                }




            }

        }
    }



}






