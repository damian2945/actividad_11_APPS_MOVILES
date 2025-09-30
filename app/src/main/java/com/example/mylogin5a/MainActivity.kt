package com.example.mylogin5a

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylogin5a.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelperUsuario(this)

        //Cuando hacemos click en Login
        binding.btnLogin.setOnClickListener {
            val loginInput = binding.txtUsuario.text.toString()
            val passInput = binding.txtPassword.text.toString()

            //Abrimos la BD para sólo lectura
            val db = dbHelper.readableDatabase

            //Creamos los argumentos para la consulta como array
            val selectionArgs = arrayOf(loginInput, passInput)

            //Creamos el cursor para ejecutar la consulta
            val cursor = db.rawQuery(
                "SELECT * FROM usuarios WHERE userLogin = ? AND userPass = ?",
                selectionArgs
            )

            //Verificamos si se encontró ocurrencia en la consulta, moviendo el cursor al inicio
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "El usuario es correcto :-) ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Usuario invalido :-( ", Toast.LENGTH_SHORT).show()
            }

            //Cerramos el cursor
            cursor.close()

            //Cerramos la BD
            db.close()
        }

        //Botón para ir a la pantalla de registro
        binding.tvRegistrarse.setOnClickListener {
            val intent = Intent(this, MainActivityRegistrar::class.java)
            startActivity(intent)
        }
    }
}