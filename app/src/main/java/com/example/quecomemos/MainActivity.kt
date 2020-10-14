package com.example.quecomemos

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.quecomemos.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 1024

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        session()
        val Img = img
        Glide.with(this).load("http://dulcegloriaaccesorios.com.ar/QueComemos.png")
            .apply(RequestOptions.circleCropTransform())
            .into(Img)

    }//***********fin OnCreate********************

    private fun session() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            program()
        } else {
            login()
        }
    }

    private fun program() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = "Bienvenidx " + user.displayName + "."
            val photoUrl = user.photoUrl
            val imgUser = img_user
            Glide.with(this).load(photoUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(imgUser)
            tv_1.text = name
            img.setImageURI(photoUrl)
        }
        //**************boton al azar***************
        btn_azar.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al saludable***************
        btn_saludable.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }//**************boton al pastas***************
        btn_pastas.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al carnes***************
        btn_carnes.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al rapidas***************
        btn_rapidas.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al veganas***************
        btn_vegana.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al pescados***************
        btn_pescados.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al economicas***************
        btn_economicas.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al gourmet***************
        btn_gourmet.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //**************boton al postres***************
        btn_postres.setOnClickListener {
            val intent = Intent(this, Receta::class.java)
            startActivity(intent)
        }
        //****************acercade********************
        img_derechos.setOnClickListener {
            val intent = Intent(this, AcercaDe::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(
                    this,
                    "Bienvenido/a " + user?.displayName.toString() + " a Qué Comemos?",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                showAlert()
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta de Error")
        builder.setMessage(R.string.show_alert)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun login() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build()
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo)
                .setTheme(R.style.AppTheme)
                .build(),
            RC_SIGN_IN
        )
    }
}