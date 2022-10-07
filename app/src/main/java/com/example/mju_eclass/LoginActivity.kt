package com.example.mju_eclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mju_eclass.data.MyclassActivity
import com.example.mju_eclass.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.LoginButton.setonClickListener{
            val intent = Intent(this, MyclassActivity::class.java)
            startActivity(intent)

        }
    }
}