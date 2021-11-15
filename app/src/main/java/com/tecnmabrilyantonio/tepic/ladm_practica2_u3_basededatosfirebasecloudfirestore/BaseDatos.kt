package com.tecnmabrilyantonio.tepic.ladm_practica2_u3_basededatosfirebasecloudfirestore

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(p: SQLiteDatabase) {

        p.execSQL("CREATE TABLE Notas(IDNOTA INTEGER PRIMARY KEY AUTOINCREMENT,TITULO VARCHAR(100),CONTENIDO VARCHAR(2000), FECHA DATE, HORA DATE )")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //se ejecuta cuando hay un cambio de version
    }
}
