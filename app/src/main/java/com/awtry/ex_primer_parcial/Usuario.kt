package com.awtry.ex_primer_parcial

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

class Usuario(
    var Imagen_Usuario: Foto = Foto.ESCRITOR_ZHONGLI,
    var persona: Persona = Persona.USUARIO_VACIO,
    //var Nombre_Usuario: String = "",
    //var Contra: String = "",
    var Nivel: Nivel_Usuario = Nivel_Usuario.LECTOR,
) : Parcelable {

    companion object {
        val usuarios = arrayListOf(
            Usuario(Foto.ESCRITOR_ZHONGLI, Persona.ESCRITOR_ZHONGLI, Nivel_Usuario.ESCRITOR),
            Usuario(Foto.ESCRITOR_ALBEDO, Persona.ESCRITOR_ALBEDO, Nivel_Usuario.ESCRITOR),
            Usuario(Foto.ESCRITOR_NINGGUANG, Persona.ESCRITOR_NINGGUANG, Nivel_Usuario.ESCRITOR),
            Usuario(Foto.LECTOR_DILUC, Persona.LECTOR_DILUC, Nivel_Usuario.LECTOR),
            Usuario(Foto.LECTOR_SUCROSE, Persona.LECTOR_SUCROSE, Nivel_Usuario.LECTOR),
            Usuario(Foto.LECTOR_XINGQIU, Persona.LECTOR_XINGQIU, Nivel_Usuario.LECTOR)
        )
    }

    fun validarUsuario() =
        usuarios.firstOrNull { (it.persona.username == this.persona.username && it.persona.password == this.persona.password) }

    fun cuentaUsuarios() = usuarios

}