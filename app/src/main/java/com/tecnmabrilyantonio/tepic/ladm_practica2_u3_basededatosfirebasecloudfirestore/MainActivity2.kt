package com.tecnmabrilyantonio.tepic.ladm_practica2_u3_basededatosfirebasecloudfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    var idNota =ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        consultaNotas()

        agregar.setOnClickListener() {
            var agregar = Intent(this, MainActivity3::class.java)

            startActivity(agregar)
            DeseaActualizarLista()
        }

    }//onCreate

    fun consultaNotas(){
        val arregloNota = Nota(this).consulta()
            listaNota.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloNota)
            idNota.clear()
            idNota = Nota(this).obtenerIDs()
            activarEvento(listaNota)

    }


    private fun activarEvento(listaNotas: ListView){
        listaNotas.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->
            val idSeleccionado = idNota[indiceSeleccionado]
            val intento = Intent(this, Actualizar::class.java)
            intento.putExtra("idActualizar",idSeleccionado.toString())
            startActivity(intento)
            DeseaActualizarLista()
        }
    }

    fun DeseaActualizarLista(){
        AlertDialog.Builder(this).setMessage("DESEAS ACTUALIZAR LISTA?")
            .setPositiveButton("SI "){d,i-> consultaNotas()}
            .setNegativeButton("NO "){d,i-> d.cancel()}
            .show()
    }
}