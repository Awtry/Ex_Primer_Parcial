package com.awtry.ex_primer_parcial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Frag_Info_General : Fragment(R.layout.fragment_frag__info__general) {

    override fun onResume() {
        super.onResume()
    }

    //region Elementos de vista

    private lateinit var lbl_titulo: TextView
    private lateinit var lbl_Apodo: TextView
    private lateinit var lbl_Usuario: TextView
    private lateinit var lbl_Num_Art: TextView
    private lateinit var Detalle_text: TextView

    private lateinit var Imagen_Usuario: ImageView
    private lateinit var Imagen_Muestra: ImageView

    private lateinit var btn_IZQ: Button
    private lateinit var btn_DER: Button
    private lateinit var btn_Agrega: Button
    private lateinit var btn_Elimina: Button
    private lateinit var btn_Cora: Button

    //endregion

    private fun Inicializar_Vista(){
        lbl_titulo = requireView().findViewById(R.id.lblTitulo)
        lbl_Apodo = requireView().findViewById(R.id.lblNickname)
        lbl_Usuario = requireView().findViewById(R.id.lblTipoUsuario)
        lbl_Num_Art = requireView().findViewById(R.id.lblNumArticulos)
        Detalle_text = requireView().findViewById(R.id.Txv_Detalle_Noedit)

        Imagen_Usuario = requireView().findViewById(R.id.ImgView_General)
        Imagen_Muestra = requireView().findViewById(R.id.Foto_Muestra)

        btn_IZQ = requireView().findViewById(R.id.btn_IZQ)
        btn_DER = requireView().findViewById(R.id.btn_DER)
        btn_Agrega = requireView().findViewById(R.id.btn_Agregar)
        btn_Elimina = requireView().findViewById(R.id.btn_Eliminar)
        btn_Cora = requireView().findViewById(R.id.btn_Corazon)

    }


}