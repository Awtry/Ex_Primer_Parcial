package com.awtry.ex_primer_parcial

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import com.squareup.moshi.Moshi

class Frag_Detalle : Fragment(R.layout.fragment_frag__detalle) {

    override fun onResume() {
        super.onResume()
        Inicializador_Vista()
    }

    //region Elementos de vista

    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val PREFS = "MY_PREFERENCES"
   // private val DETALLE_LISTA = "DETALLE_LISTA"


    private lateinit var btn_Cora_Detalle: Button
    private lateinit var btn_IZQ: Button
    private lateinit var btn_DER: Button
    private lateinit var btn_Confirm: Button

    private lateinit var Imagen_Detalle: ImageView

    private lateinit var txv_Titulo: EditText
    private lateinit var txv_Detalle: EditText

    private lateinit var escritor: Detalle_Escritor
    private lateinit var generos: Generos
    private lateinit var usuario: Usuario

    private lateinit var vista_libros: ArrayList<Generos>
    private lateinit var lista_articulos: MutableList<Detalle_Escritor>
    private lateinit var lista_base_articulos: MutableList<Detalle_Escritor>

    private val moshi = Moshi.Builder().build()

    var nom: String = ""
    var det: String = ""

    private var lugar = 0
    private var centinela = 0

    //endregion

    private fun Inicializador_Vista(){
        btn_Cora_Detalle = requireView().findViewById(R.id.btn_Corazon_Detalle)
        btn_IZQ = requireView().findViewById(R.id.btn_IZQ)
        btn_DER = requireView().findViewById(R.id.btn_DER)
        btn_Confirm = requireView().findViewById(R.id.btn_Confirmar)
        Imagen_Detalle = requireView().findViewById(R.id.Foto_Muestra_Detalle)
        txv_Titulo = requireView().findViewById(R.id.Txv_Titulo_Noedit)
        txv_Detalle = requireView().findViewById(R.id.Txv_Detalle_Noedit)

        escritor = requireArguments().getParcelable("LibroSeleccionado") ?: Detalle_Escritor()
        generos = requireArguments().getParcelable("NuevoArticulo") ?: Generos()
        usuario = requireArguments().getParcelable("UsuarioSeleccionado") ?: Usuario()
        lugar = requireArguments().getInt("elemento")

        preferences = requireActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        MostrarDatos()
        Disparador_Boton_Escritor()
    }

    private fun MostrarDatos(){
        if(generos.Titulo.isEmpty() && escritor.persona != null){
            Imagen_Detalle.setImageResource(escritor.foto_genero.text)
            txv_Titulo.setText(escritor.titulo)
            txv_Detalle.setText(escritor.descripcion)
        }else{
            Imagen_Detalle.setImageResource(generos.Imagen_Libro.text)
            txv_Titulo.setText(generos.Titulo)
            txv_Detalle.setText(generos.Detalle)
        }

        lista_base_articulos = escritor.Contador_Articulos()
        lista_articulos = escritor.Articulos_aislados()
        vista_libros = generos.conteoGeneros()

        if (escritor != null){

        }

        if (usuario.Nivel == Nivel_Usuario.LECTOR){
            txv_Titulo.isEnabled = false
            txv_Detalle.isEnabled = false
            btn_DER.isGone = true
            btn_IZQ.isGone = true
            btn_Confirm.isGone = true
        }
    }

    private fun Disparador_Boton_Escritor() {
        btn_DER.setOnClickListener {
            if (centinela == vista_libros.size - 1) {
                centinela = 0
            } else {
                centinela++
            }

            Rotacion_IMG_Escritor()
        }

        btn_IZQ.setOnClickListener {
            if (centinela == 0) {
                centinela = vista_libros.size - 1
            } else {
                centinela--
            }
            Rotacion_IMG_Escritor()
        }

        btn_Confirm.setOnClickListener{
            Actualizar_Articulo()
            Guardar_arrglo(Detalle_Escritor())
            (requireActivity() as MainActivity).replaceFragment(Frag_Info_General().apply {
                arguments = Bundle().apply {
                    putParcelable("ESCRITOR", escritor)
                    putParcelable("DATOSALIDA", usuario) //LibroSeleccionado

                }
            })
        }
    }

    private fun Guardar_arrglo(Salida: Detalle_Escritor?){
        editor = preferences.edit()
        editor.putString("DETALLE_LISTA", moshi.adapter(Detalle_Escritor::class.java).toJson(Salida))
        editor.apply()
    }

    private fun Rotacion_IMG_Escritor() {
        Imagen_Detalle.setImageResource(vista_libros[centinela].Imagen_Libro.text)
        /*txv_Titulo.setText(vista_libros[centinela].Titulo)
        txv_Detalle.setText(vista_libros[centinela].Detalle)*/
    }

    private fun Actualizar_Articulo(){
       /* lista_base_articulos.map { Detalle_Escritor(usuario.persona,
            vista_libros[centinela].Imagen_Libro,
            txv_Titulo.toString(),txv_Detalle.toString()) }.toSet()*/

        /*escritor.Contador_Articulos().set(Detalle_Escritor(escritor.persona,
            vista_libros[centinela].Imagen_Libro.text,txv_Titulo.toString(),txv_Detalle.toString())*/

        nom = txv_Titulo.text.toString()
        det = txv_Detalle.text.toString()
        /*
        escritor.Contador_Articulos()[lugar] = Detalle_Escritor(usuario.persona,vista_libros[centinela].Imagen_Libro,
            nom,det)*/

       /* lista_articulos[lugar] = Detalle_Escritor(usuario.persona,vista_libros[centinela].Imagen_Libro,
            nom,det)*/

       /* lista_articulos.find { Detalle_Escritor(usuario.persona,vista_libros[centinela].Imagen_Libro,
            nom,det) }*/

        Detalle_Escritor.articulo[lugar] = Detalle_Escritor(usuario.persona,vista_libros[centinela].Imagen_Libro,
            nom,det)



    }
}