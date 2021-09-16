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

class Frag_Info_General : Fragment(R.layout.fragment_frag__info__general) {

    override fun onResume() {
        super.onResume()
        Inicializar_Vista()
    }

    //region Elementos de vista

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

    private lateinit var usuario: Usuario
    private lateinit var libros: Libros

    private lateinit var vista_libros: ArrayList<Libros>
    private var centinela = 1

    //endregion

    private fun Inicializar_Vista() {
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

        usuario = requireArguments().getParcelable("DATOSALIDA") ?: Usuario()
        libros = Libros()


        Mostrar_Datos()
        Disparador_Boton()
    }


    private fun Mostrar_Datos() {

        lbl_titulo.setText(usuario.Nivel.text)
        lbl_Apodo.setText(usuario.Nombre_Usuario)
        lbl_Usuario.setText(usuario.Nivel.text)
        Img_Usuario.setImageResource(usuario.Imagen_Usuario.text)

        if (usuario.Nivel == Nivel_Usuario.LECTOR){
            btn_Agrega.isGone = true
            btn_Elimina.isGone = true
            btn_Cora.isGone = true
        }

        vista_libros = libros.conteoLibros()

        Img_Muestra.setImageResource(vista_libros[centinela].Imagen_Libro.text)
        lbl_Titulo_Libro.setText(vista_libros[centinela].Titulo)
        Detalle_text.setText(vista_libros[centinela].Detalle)



        if (usuario.Art_fav == null) {
            lbl_Num_Art.setText("No hay articulos  favoritos")
        } else {
            lbl_Num_Art.setText(usuario.Art_fav.toString())
        }
    }

    private fun Disparador_Boton() {
        btn_DER.setOnClickListener {
            if (centinela == vista_libros.size - 1) {
                centinela = 0
            } else {
                centinela++
            }

            Rotacion_IMG()
        }

        btn_IZQ.setOnClickListener {
            if (centinela == 0) {
                centinela = vista_libros.size - 1
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
    }

    private fun Rotacion_IMG() {
        Img_Muestra.setImageResource(vista_libros[centinela].Imagen_Libro.text)
        lbl_Titulo_Libro.setText(vista_libros[centinela].Titulo)
        Detalle_text.setText(vista_libros[centinela].Detalle)
    }


}