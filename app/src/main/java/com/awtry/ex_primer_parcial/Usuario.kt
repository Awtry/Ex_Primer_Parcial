package com.awtry.ex_primer_parcial

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

class Usuario(
    var Imagen_Usuario: Foto = Foto.ESCRITOR_ZHONGLI,
    var Nombre_Usuario: String = "",
    var Contra: String = "",
    var Nivel: Nivel_Usuario = Nivel_Usuario.LECTOR,
    var Art_fav: Int? = null
): Parcelable {

    companion object{
        val usuarios = arrayListOf(
            Usuario(Foto.ESCRITOR_ZHONGLI,"Zhongli","123",Nivel_Usuario.ESCRITOR, null),
            Usuario(Foto.ESCRITOR_ALBEDO,"Albedo","456",Nivel_Usuario.ESCRITOR, null),
            Usuario(Foto.ESCRITOR_NINGGUANG,"Ningguang","789",Nivel_Usuario.ESCRITOR, null),
            Usuario(Foto.LECTOR_DILUC,"Diluc","987",Nivel_Usuario.LECTOR, null),
            Usuario(Foto.LECTOR_SUCROSE,"Sucrose","654",Nivel_Usuario.LECTOR, null),
            Usuario(Foto.LECTOR_XINGQIU,"Xingqiu","321",Nivel_Usuario.LECTOR, null)
        )
    }

    fun validarUsuario() = usuarios.firstOrNull { (it.Nombre_Usuario == this.Nombre_Usuario && it.Contra == this.Contra)}

    fun cuentaUsuarios() = usuarios

}