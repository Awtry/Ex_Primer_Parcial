package com.awtry.ex_primer_parcial

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

class Usuario(
    var Nombre_Usuario: String = "",
    var Contra: String = "",
    var Nivel: Nivel_Usuario = Nivel_Usuario.LECTOR
): Parcelable {

    companion object{
        val usuarios = arrayOf(
            Usuario("Prueba_lector","123",Nivel_Usuario.LECTOR),
            Usuario("Prueba_escritor","456",Nivel_Usuario.ESCRITOR)
        )
    }

    fun validateUser() = usuarios.firstOrNull { (it.Nombre_Usuario == this.Nombre_Usuario && it.Contra == this.Contra)
            || (it.Nivel == this.Nivel)
    }

}