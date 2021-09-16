package com.awtry.ex_primer_parcial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone

class Frag_Detalle : Fragment(R.layout.fragment_frag__detalle) {

    override fun onResume() {
        super.onResume()
        Inicializador_Vista()
    }

    //region Elementos de vista

    private lateinit var btn_Cora_Detalle: Button
    private lateinit var btn_IZQ: Button
    private lateinit var btn_DER: Button
    private lateinit var btn_Confirm: Button

    private lateinit var Imagen_Detalle: ImageView

    private lateinit var txv_Titulo: TextView
    private lateinit var txv_Detalle: TextView

    private lateinit var libros: Libros
    private lateinit var usuario: Usuario

    //endregion

    private fun Inicializador_Vista(){
        btn_Cora_Detalle = requireView().findViewById(R.id.btn_Corazon_Detalle)
        btn_IZQ = requireView().findViewById(R.id.btn_IZQ)
        btn_DER = requireView().findViewById(R.id.btn_DER)
        btn_Confirm = requireView().findViewById(R.id.btn_Confirmar)
        Imagen_Detalle = requireView().findViewById(R.id.Foto_Muestra_Detalle)
        txv_Titulo = requireView().findViewById(R.id.Txv_Titulo_Noedit)
        txv_Detalle = requireView().findViewById(R.id.Txv_Detalle_Noedit)

        libros = requireArguments().getParcelable("LibroSeleccionado") ?: Libros()
        usuario = requireArguments().getParcelable("UsuarioSeleccionado") ?: Usuario()

        MostrarDatos()
    }

    private fun MostrarDatos(){
        Imagen_Detalle.setImageResource(libros.Imagen_Libro.text)
        txv_Titulo.setText(libros.Titulo)
        txv_Detalle.setText(libros.Detalle)

        if (usuario.Nivel == Nivel_Usuario.LECTOR){
            btn_DER.isGone = true
            btn_IZQ.isGone = true
        }
    }


}