package com.awtry.ex_primer_parcial

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

class Detalle_Usuario(
    var lector: Persona = Persona.LECTOR_DILUC,
    var articulo: String = Detalle_Escritor().titulo,
    var gusta: Boolean = false
) : Parcelable {
    companion object{
        var gustos = mutableListOf(
            Detalle_Usuario(Persona.LECTOR_SUCROSE, Detalle_Escritor().titulo,false),
            Detalle_Usuario(Persona.LECTOR_XINGQIU, Detalle_Escritor().titulo,false),
            Detalle_Usuario(Persona.LECTOR_DILUC, Detalle_Escritor().titulo,false)
        )
    }
}