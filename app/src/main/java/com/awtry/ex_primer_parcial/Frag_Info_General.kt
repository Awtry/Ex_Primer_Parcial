package com.awtry.ex_primer_parcial

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone

class Frag_Info_General : Fragment(R.layout.fragment_frag__info__general) {

    override fun onResume() {
        super.onResume()
        Inicializar_Vista()
    }

    //region Elementos de vista

    private lateinit var preferences: SharedPreferences
    private val PREFS = "MY_PREFERENCES"
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var lbl_titulo: TextView
    private lateinit var lbl_Apodo: TextView
    private lateinit var lbl_Usuario: TextView
    private lateinit var lbl_Num_Art: TextView
    private lateinit var Detalle_text: TextView
    private lateinit var lbl_Titulo_Libro: TextView

    private lateinit var Img_Usuario: ImageView
    private lateinit var Img_Muestra: ImageView

    private lateinit var btn_IZQ: Button
    private lateinit var btn_DER: Button
    private lateinit var btn_Agrega: Button
    private lateinit var btn_Elimina: Button
    private lateinit var btn_Cora: Button
    private lateinit var btn_Logout: Button

    private lateinit var usuario: Usuario
    private lateinit var generos: Generos
    private lateinit var escritor: Detalle_Escritor

    private lateinit var vista_libros: ArrayList<Generos>
    private lateinit var lista_articulos: MutableList<Detalle_Escritor>
    private var centinela = 0

    //endregion

    private fun Inicializar_Vista() {
        generos = Generos()
        escritor = Detalle_Escritor()

        lbl_titulo = requireView().findViewById(R.id.lblTitulo)
        lbl_Apodo = requireView().findViewById(R.id.lblNickname)
        lbl_Usuario = requireView().findViewById(R.id.lblTipoUsuario)
        lbl_Num_Art = requireView().findViewById(R.id.lblNumArticulos)
        Detalle_text = requireView().findViewById(R.id.Txv_Detalle_Noedit)
        lbl_Titulo_Libro = requireView().findViewById(R.id.lblTitulo_Libro)

        Img_Usuario = requireView().findViewById(R.id.ImgView_General)
        Img_Muestra = requireView().findViewById(R.id.Foto_Muestra)

        btn_IZQ = requireView().findViewById(R.id.btn_IZQ)
        btn_DER = requireView().findViewById(R.id.btn_DER)
        btn_Agrega = requireView().findViewById(R.id.btn_Agregar)
        btn_Elimina = requireView().findViewById(R.id.btn_Eliminar)
        btn_Cora = requireView().findViewById(R.id.btn_Corazon)
        btn_Logout = requireView().findViewById(R.id.btnLogout)

        preferences = requireActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        usuario = requireArguments().getParcelable("DATOSALIDA") ?: Usuario()



        Mostrar_Datos()
        Disparador_Boton()
    }


    private fun Mostrar_Datos() {

        lbl_titulo.setText(usuario.Nivel.text)
        lbl_Apodo.setText(usuario.persona.username)
        lbl_Usuario.setText(usuario.Nivel.text)
        Img_Usuario.setImageResource(usuario.Imagen_Usuario.text)

        if (usuario.Nivel == Nivel_Usuario.LECTOR){
            btn_Agrega.isGone = true
            btn_Elimina.isGone = true
            Detalle_text.isGone = true
        }else{
            btn_Cora.isGone = true
        }

        articulosEscritor(usuario.persona)

        vista_libros = generos.conteoGeneros()
        lista_articulos = escritor.Articulos_aislados()

        Img_Muestra.setImageResource(lista_articulos[centinela].foto_genero.text)
        lbl_Titulo_Libro.setText(lista_articulos[centinela].titulo)
        Detalle_text.setText(lista_articulos[centinela].descripcion)


/*
        if (usuario.Art_fav == null) {
            lbl_Num_Art.setText("No hay articulos  favoritos")
        } else {
            lbl_Num_Art.setText(usuario.Art_fav.toString())
        }*/
    }

    private fun articulosEscritor(persona: Persona){
        escritor.Escritor_Seleccionado(persona)
    }

    private fun Disparador_Boton() {
        btn_DER.setOnClickListener {
            if (centinela == lista_articulos.size - 1) {
                centinela = 0
            } else {
                centinela++
            }

            Rotacion_IMG()
        }

        btn_IZQ.setOnClickListener {
            if (centinela == 0) {
                centinela = lista_articulos.size - 1
            } else {
                centinela--
            }
            Rotacion_IMG()
        }

        Img_Muestra.setOnClickListener{
            (requireActivity() as MainActivity).replaceFragment(Frag_Detalle().apply {
                arguments = Bundle().apply {
                    putParcelable("LibroSeleccionado", vista_libros[centinela])
                    putParcelable("UsuarioSeleccionado", usuario)
                }
            })
        }

        btn_Logout.setOnClickListener{
            //usuario.
            lista_articulos.clear()
            editor = preferences.edit()
            editor.clear()
            editor.apply()
            (requireActivity() as MainActivity).replaceFragment(Frag_login().apply { })
        }
    }

    private fun Rotacion_IMG() {
        Img_Muestra.setImageResource(lista_articulos[centinela].foto_genero.text)
        lbl_Titulo_Libro.setText(lista_articulos[centinela].titulo)
        Detalle_text.setText(lista_articulos[centinela].descripcion)
    }


}