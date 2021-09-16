package com.awtry.ex_primer_parcial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class Frag_login : Fragment(R.layout.fragment_frag_login) {

    override fun onResume() {
        super.onResume()

        Inicializador_Vista()
    }

    //region Elementos de vista

    private lateinit var Img_login: ImageView
    private lateinit var txt_Usuario: EditText
    private lateinit var txt_Contra: EditText
    private lateinit var btn_Accesar: Button
    private lateinit var usuario: Usuario

    //endregion

    private fun Inicializador_Vista(){
       // usuario = requireArguments().getParcelable("USUARIOS") ?: Usuario()

        usuario = Usuario()
        Img_login = requireView().findViewById(R.id.ImgView_Login)
        txt_Usuario = requireView().findViewById(R.id.txt_user)
        txt_Contra = requireView().findViewById(R.id.txt_password)
        btn_Accesar = requireView().findViewById(R.id.btn_Access)

        Accion_Boton()
    }

    private fun Lector_Datos(){
       usuario.validarUsuario()?.let {
           (requireActivity() as MainActivity).replaceFragment(Frag_Info_General().apply {
               arguments = Bundle().apply {
                   putParcelable("DATOSALIDA", it)
               }
           })
       }
    }

    private fun Accion_Boton(){
        btn_Accesar.setOnClickListener{
            usuario.apply {
                Nombre_Usuario = txt_Usuario.text.toString()
                Contra = txt_Contra.text.toString()
            }
            Lector_Datos()
        }
    }

}