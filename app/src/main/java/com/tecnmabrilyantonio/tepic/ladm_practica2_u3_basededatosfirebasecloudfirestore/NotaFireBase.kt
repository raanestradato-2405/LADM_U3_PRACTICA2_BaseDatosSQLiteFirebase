package com.tecnmabrilyantonio.tepic.ladm_practica2_u3_basededatosfirebasecloudfirestore

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class NotaFireBase(p: Context) {

    var id = 0
    var titulo = ""
    var contenido = ""
    var fecha = ""
    var hora = ""
    var pnt = p
    var baseRemota = FirebaseFirestore.getInstance()

    fun insertar(){

        var datosInsertar = hashMapOf(
            "id" to id,
            "titulo" to titulo,
            "contenido" to contenido,
            "fecha" to fecha,
            "hora" to hora
        )
        baseRemota.collection("notas")
            .add(datosInsertar as Any)
            .addOnSuccessListener {
                AlertDialog.Builder(pnt).setMessage("SINCRONIZACION CORRECTA !!")
                    .setPositiveButton("ACEPTAR "){d,i-> d.cancel()}
                    .show()
            }
            .addOnFailureListener{
                AlertDialog.Builder(pnt).setMessage("ERROR: ${it.message!!}")
                    .setPositiveButton("ACEPTAR "){d,i-> d.cancel()}
                    .show()
            }
    }
}