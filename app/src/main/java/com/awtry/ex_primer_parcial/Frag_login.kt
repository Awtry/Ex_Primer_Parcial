package com.awtry.ex_primer_parcial

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
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
    private var lugar: Int = 0

    //endregion

    private fun Inicializador_Vista() {
        usuario = Usuario()
        Img_login = requireView().findViewById(R.id.ImgView_Login)
        txt_Usuario = requireView().findViewById(R.id.txt_user)
        txt_Contra = requireView().findViewById(R.id.txt_password)
        btn_Accesar = requireView().findViewById(R.id.btn_Access)
        //Verificador letra por letra
        txt_Usuario.addTextChangedListener(textWatcher)
        txt_Contra.addTextChangedListener(textWatcher)
        //Leer preferencias
        preferences = requireActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        Img_login.setImageResource(R.drawable.ic_read)

        listUsuario = usuario.cuentaUsuarios()

        Verificar_Preferencias()
        Accion_Boton()
    }

    private fun Verificar_Preferencias() {
        if (preferences.getString("USUARIO", PREFS)
                .isNullOrEmpty() || preferences.getString("CONTRA", PREFS).isNullOrEmpty()
        ) {
            Toast.makeText(activity, "Sin datos, escribeme los datos", Toast.LENGTH_SHORT).show();
        } else {
            usuario.apply {
                persona.username = preferences.getString("USUARIO", "")!!
                persona.password = preferences.getString("CONTRA", "")!!
            }
            Lector_Datos()
        }
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

    private fun VerificarInicio(lugar: Int){
        if(txt_Contra.text.toString() == listUsuario[lugar].persona.password ){
            usuario.apply {
                persona.username = txt_Usuario.text.toString()
                persona.password = txt_Contra.text.toString()

                editor = preferences.edit()
                editor.putString("USUARIO", usuario.persona.username)
                editor.putString("CONTRA", usuario.persona.password)
                editor.apply()
            }
        }else{
            Toast.makeText(activity, "Contraseña erronea", Toast.LENGTH_SHORT)
                .show();
        }
        Lector_Datos()
    }

    private fun Accion_Boton() {
        println(centinela)
        btn_Accesar.setOnClickListener {
            VerificarInicio(lugar)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (txt_Contra.text.toString().isNullOrEmpty()){
                btn_Accesar.isClickable = false
                btn_Accesar.setText(R.string.CAMPO_INCOMPLETO)
            }else{
                btn_Accesar.isClickable = true
                btn_Accesar.setText(R.string.ACCESA)
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
            if (listUsuario.size >= 0) {
                for (centinela in 0..listUsuario.size - 1) {
                    if (txt_Usuario.text.toString() == listUsuario[centinela].persona.username) {
                        Img_login.setImageResource(listUsuario[centinela].Imagen_Usuario.text)
                        lugar = centinela
                        break
                    }
                    if (txt_Usuario.text.isEmpty() ||
                        txt_Usuario.text.toString() != listUsuario[centinela].persona.username
                    ) {
                        Img_login.setImageResource(R.drawable.ic_read)
                    }
                }

            } else {
                Toast.makeText(activity, "No existen usurios", Toast.LENGTH_SHORT)
                    .show();
            }


        }
    }

}