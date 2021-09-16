package com.awtry.ex_primer_parcial

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

class Libros(
    val Imagen_Libro: Foto = Foto.LIBRO_DESCONOCIDO,
    val Titulo: String = "",
    val Detalle: String = ""
): Parcelable {

    companion object{
        val ejemplares = arrayListOf(
            Libros(Foto.MATEMATICA,"Matemática","Para el estudiante promedio"),
            Libros(Foto.SEGURIDAD,"Seguridad ","Todo lo básico"),
            Libros(Foto.COMIC,"Comics","Gran variedad"),
            Libros(Foto.INVESTIGACION,"Investigación Cientifica","Algo más profundo"),
            Libros(Foto.TECNOLOGIA,"Alta tecnlogía","Super tec"),
            Libros(Foto.GEOGRAFIA,"Geografía","Para los que se pierden rápido")
        )
    }

    fun conteoLibros() = ejemplares

}