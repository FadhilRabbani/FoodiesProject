package com.example.tugas7_1402021035

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tugas7_1402021035.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signup_activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.suClickHere.setOnClickListener {
            val intent = Intent(this, login_activity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {

            val email = binding.suEmail.text.toString()
            val pass = binding.suPass.text.toString()
            val repass = binding.suRepass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()){
                if(pass == repass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this, login_activity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty Field is not allowed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}