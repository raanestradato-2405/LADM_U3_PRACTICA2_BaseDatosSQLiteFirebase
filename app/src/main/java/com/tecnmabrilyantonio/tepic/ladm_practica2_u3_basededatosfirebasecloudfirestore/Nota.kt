package com.tecnmabrilyantonio.tepic.ladm_practica2_u3_basededatosfirebasecloudfirestore

import android.content.ContentValues
import android.content.Context

class Nota(p: Context) {

    var titulo = ""
    var contenido = ""
    var fecha = ""
    var hora = ""
    var pnt = p

    fun insertar():Boolean{
        val tablaNotas = BaseDatos(pnt,"Notas",null,1).writableDatabase
        val dato = ContentValues()
        dato.put("titulo",titulo)
        dato.put("contenido",contenido)
        dato.put("fecha",fecha)
        dato.put("hora",hora)
        val resultado = tablaNotas.insert("Notas",null,dato)
        tablaNotas.close()
        if(resultado == -1L){
            return false
        }
        return true
    }//insertar

    fun consulta(): ArrayList<String>{
        val tablaNotas = BaseDatos(pnt,"Notas",null,1).readableDatabase
        val resultadoConsulta = ArrayList<String>()
        val cursor = tablaNotas.query("Notas",arrayOf("*"),null,null,null,null,null )
        if(cursor.moveToFirst()){

            do{
                var dato = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+"Fecha: "+cursor.getString(3)+"\n"+"Hora: "+cursor.getString(4)+"\n"+"ID: "+cursor.getInt(0)
                resultadoConsulta.add(dato)
            }while(cursor.moveToNext())
        }else{
            resultadoConsulta.add("Cero Notas Existentes")
        }

        return resultadoConsulta
    }

    fun consultaActualizar(NID:String): ArrayList<String>{
        val tablaNotas = BaseDatos(pnt,"Notas",null,1).readableDatabase
        val resultadoConsulta = ArrayList<String>()
        val cursor = tablaNotas.query("Notas",arrayOf("*"),"IDNOTA=?", arrayOf(NID),null,null,null )
        if(cursor.moveToFirst()){
            var dato = ""
            do{
                dato = cursor.getString(1)+"#$"+cursor.getString(2)
                resultadoConsulta.add(dato)
            }while(cursor.moveToNext())
        }
        tablaNotas.close()
        return resultadoConsulta
    }

    fun obtenerIDs(): ArrayList<Int>{
        val tablaNotas = BaseDatos(pnt,"Notas",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaNotas.query("Notas",arrayOf("*"),null,null,null,null,null )
        if(cursor.moveToFirst()){
            do{
                resultado.add(cursor.getInt(0))
            }while(cursor.moveToNext())
        }//if
        tablaNotas.close()
        return resultado
    }

 //---------------------------------------------------Funcion Actualizar-------------------------------------------
    fun actualizar(idActualizar:String):Boolean{
        val tablaNota = BaseDatos(pnt,"Notas",null,1).writableDatabase
        val datos = ContentValues()
        datos.put("titulo",titulo)
        datos.put("contenido",contenido)
        datos.put("fecha",fecha)
        datos.put("hora",hora)
        val resultado = tablaNota.update("Notas",datos,"IDNOTA=?", arrayOf(idActualizar))
        if(resultado==0)return false
        return true
    }
//-----------------------------------------------------Funcion Eliminar----------------------------------------------
    fun eliminar(idEliminar:Int):Boolean{
        val tablaNota= BaseDatos(pnt,"Notas",null,1).writableDatabase
        val resultado = tablaNota.delete("Notas","IDNOTA=?",arrayOf(idEliminar.toString()))
        if(resultado==0)return false
        return true
    }
//---------------------------------------------------------FireBase---------------------------------------------------------
    fun insertarNube(){
        val tablaNotas = BaseDatos(pnt,"Notas",null,1).readableDatabase
        val insertarNotaNube = NotaFireBase(pnt)
        val cursor = tablaNotas.query("Notas",arrayOf("*"),null,null,null,null,null )
        if(cursor.moveToFirst()){
            do{
                insertarNotaNube.id = cursor.getString(0).toInt()
                insertarNotaNube.titulo = cursor.getString(1)
                insertarNotaNube.contenido = cursor.getString(2)
                insertarNotaNube.fecha = cursor.getString(3)
                insertarNotaNube.hora = cursor.getString(4)

                insertarNotaNube.insertar()
            }while(cursor.moveToNext())
        }
        tablaNotas.close()

    }
}