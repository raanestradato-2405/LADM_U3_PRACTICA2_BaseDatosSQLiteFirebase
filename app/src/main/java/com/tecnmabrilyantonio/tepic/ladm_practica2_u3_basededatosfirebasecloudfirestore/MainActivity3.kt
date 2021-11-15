package com.tecnmabrilyantonio.tepic.ladm_practica2_u3_basededatosfirebasecloudfirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        cancelar.setOnClickListener() {
            finish()
        }//cancelar

        aceptar.setOnClickListener() {
            insertar()
        }//aceptar

        sincronizar.setOnClickListener {
            Nota(this).insertarNube()
            //NotaFireBase(this).borrarDatosNube()

        }
    }//onCreate

    private fun insertar() {
        val nota = Nota(this)
        val sdffecha = SimpleDateFormat("yyyy-MM-dd")
        val fecha = sdffecha.format(Date())
        val sdfhora = SimpleDateFormat("HH-mm")
        val hora = sdfhora.format(Date())

        nota.titulo = titulo.text.toString()
        nota.contenido = contenido.text.toString()
        nota.fecha = fecha
        nota.hora = hora

        val resultado = nota.insertar()
        if(resultado){
            Toast.makeText(this,"NOTA AGREGADA CORRECTAMENTE", Toast.LENGTH_LONG).show()
            titulo.setText("")
            contenido.setText("")
        }else{
            Toast.makeText(this,"ERROR...NO SE CAPTURO LA NOTA", Toast.LENGTH_LONG).show()
        }
    }//insertar
}