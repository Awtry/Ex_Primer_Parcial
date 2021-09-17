package com.awtry.ex_primer_parcial

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

class Generos(
    val Imagen_Libro: Foto = Foto.LIBRO_DESCONOCIDO,
    val Titulo: String = "",
    val Detalle: String = ""
): Parcelable {

    companion object{
        val generos = arrayListOf(
            Generos(Foto.MATEMATICA,"Matemática","Para el estudiante promedio"),
            Generos(Foto.SEGURIDAD,"Seguridad ","Todo lo básico"),
            Generos(Foto.COMIC,"Comics","Gran variedad"),
            Generos(Foto.INVESTIGACION,"Investigación Cientifica","Algo más profundo"),
            Generos(Foto.TECNOLOGIA,"Alta tecnlogía","Super tec"),
            Generos(Foto.GEOGRAFIA,"Geografía","Para los que se pierden rápido")
        )
    }

    fun conteoGeneros() = generos

}