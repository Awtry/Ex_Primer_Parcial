package com.awtry.ex_primer_parcial

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class Frag_login : Fragment(R.layout.fragment_frag_login) {

    override fun onResume() {
        super.onResume()

        Inicializador_Vista()
    }

    //region Elementos de vista

    private lateinit var preferences: SharedPreferences
    private val PREFS = "MY_PREFERENCES"
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var Img_login: ImageView
    private lateinit var txt_Usuario: EditText
    private lateinit var txt_Contra: EditText
    private lateinit var btn_Accesar: Button
    private lateinit var usuario: Usuario
    private lateinit var listUsuario: ArrayList<Usuario>
    private var centinela = 0

    //endregion

    private fun Inicializador_Vista() {
        // usuario = requireArguments().getParcelable("USUARIOS") ?: Usuario()



        usuario = Usuario()
        Img_login = requireView().findViewById(R.id.ImgView_Login)
        txt_Usuario = requireView().findViewById(R.id.txt_user)
        txt_Contra = requireView().findViewById(R.id.txt_password)
        btn_Accesar = requireView().findViewById(R.id.btn_Access)

        //txt_Usuario.addTextChangedListener(textWatcher)
        preferences = requireActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        if (preferences.getString("USUARIO",PREFS).isNullOrEmpty() && preferences.getString("CONTRA",PREFS).isNullOrEmpty()){
           //Toast Ingrese sus datos
        }else{
            usuario.apply {
                Nombre_Usuario = preferences.getString("USUARIO","")!!
                Contra = preferences.getString("CONTRA","")!!
            }
            Lector_Datos()
        }

        listUsuario = usuario.cuentaUsuarios()

        Accion_Boton()
    }

    private fun Lector_Datos() {
        usuario.validarUsuario()?.let {
            (requireActivity() as MainActivity).replaceFragment(Frag_Info_General().apply {
                arguments = Bundle().apply {
                    putParcelable("DATOSALIDA", it)
                }
            })
        }
    }

    private fun Accion_Boton() {
        btn_Accesar.setOnClickListener {
            usuario.apply {
                Nombre_Usuario = txt_Usuario.text.toString()
                Contra = txt_Contra.text.toString()
                editor = preferences.edit()
                editor.putString("USUARIO", Nombre_Usuario)
                editor.putString("CONTRA",Contra)
                editor.apply()
            }
            Lector_Datos()

        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

            if (listUsuario.size >= 0){
                /* do {
                     if (txt_Usuario.text.toString() == listUsuario[centinela].Nombre_Usuario) Img_login.setImageResource(listUsuario[centinela].Imagen_Usuario.text)
                     centinela++
                 }while (centinela >= listUsuario.size)*/
                for (centinela in 0..listUsuario.size){
                    if (txt_Usuario.text.toString() == listUsuario[centinela].Nombre_Usuario){
                        Img_login.setImageResource(listUsuario[centinela].Imagen_Usuario.text)
                    }else{
                        Img_login.setImageResource(R.drawable.ic_heart)
                    }
                }

            }else{
                //Toast, no tienes usuarios
            }
        }
    }
/*
    override fun afterTextChanged(p0: Editable?) {

        for (counter in 0..2)
        {
            if(editUser.text.toString() == Readers.users[counter].username) loginPhoto.setImageResource(Readers.users[counter].profile)
            if(editUser.text.toString() == Writers.users[counter].username) loginPhoto.setImageResource(Writers.users[counter].profile)

            if(editUser.text.isEmpty()) loginPhoto.setImageResource(R.drawable.logo)
        }
    }*/

}