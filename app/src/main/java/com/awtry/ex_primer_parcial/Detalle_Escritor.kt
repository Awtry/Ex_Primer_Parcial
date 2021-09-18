package com.awtry.ex_primer_parcial

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize

class Detalle_Escritor(
    var persona: Persona = Persona.ESCRITOR_ZHONGLI,
    var foto_genero: Foto = Foto.GEOGRAFIA,
    var titulo: String = "",
    var descripcion: String = ""
) : Parcelable {

    companion object {
        val articulo = arrayListOf(
            Detalle_Escritor(
                Persona.ESCRITOR_ZHONGLI,
                Foto.GEOGRAFIA,
                "Geografía de Lyue",
                "Amplia descripción"
            ),
            Detalle_Escritor(
                Persona.ESCRITOR_ZHONGLI,
                Foto.INVESTIGACION,
                "Teología",
                "De todo tevant"
            ),
            Detalle_Escritor(
                Persona.ESCRITOR_NINGGUANG,
                Foto.TECNOLOGIA,
                "Mecanismos elementales",
                "Guia practica"
            ),
            Detalle_Escritor(
                Persona.ESCRITOR_NINGGUANG,
                Foto.MATEMATICA,
                "Calculo empresarial de Lyue",
                "Uso local"
            )
        )

        var articulo_personalizado = mutableListOf<Detalle_Escritor>()
    }

    fun Contador_Articulos() = articulo
    fun Articulos_aislados() = articulo_personalizado

    fun Escritor_Seleccionado(escritor: Persona) {
        articulo_personalizado.clear()
        for (centi in 0..articulo.size-1)
            if (escritor === articulo[centi].persona) {
                articulo_personalizado.addAll(articulo.filter { it.persona === escritor })
                break
            }
    }
}