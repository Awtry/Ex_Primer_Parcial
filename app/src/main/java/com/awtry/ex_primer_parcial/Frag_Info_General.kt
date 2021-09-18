package com.awtry.ex_primer_parcial

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import com.squareup.moshi.Moshi
import java.lang.Exception

class Frag_Info_General : Fragment(R.layout.fragment_frag__info__general) {

    override fun onResume() {
        super.onResume()
        Inicializar_Vista()
    }

    //region Elementos de vista

    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val PREFS = "MY_PREFERENCES"
   // private val DETALLE_LISTA = "DETALLE_LISTA"


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
    private val moshi = Moshi.Builder().build()

    private var centinela = 0

    //endregion

    private fun Inicializar_Vista() {

        preferences = requireActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        escritor = Lectura_sharepreference()
        generos = Generos()

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


        usuario = requireArguments().getParcelable("DATOSALIDA") ?: Usuario()
        //escritor = requireArguments().getParcelable("ESCRITOR") ?: Detalle_Escritor()
        //Guardar_arrglo(escritor)


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
            Verificador_Lector()
        }else{
            btn_Cora.isGone = true
            Verificador_Escritor()
        }

        articulosEscritor(usuario.persona)

        vista_libros = generos.conteoGeneros()
        lista_articulos = escritor.Articulos_aislados()
        //Guardar_arrglo(escritor)



    }

    /*private fun Guardar_arrglo(Salida: Detalle_Escritor?){
        preferences.edit().putString(DETALLE_LISTA, moshi.adapter(Detalle_Escritor::class.java).toJson(Salida)).apply()
    }*/

    private fun Lectura_sharepreference() =
        preferences.getString("DETALLE_LISTA", null)?.let{
            return@let try{
                moshi.adapter(Detalle_Escritor::class.java).fromJson(it)
            }catch (e: Exception){
                Detalle_Escritor()
            }
        } ?: Detalle_Escritor()

    private fun Verificador_Lector(){
        if (vista_libros.isEmpty()){
            Img_Muestra.setImageResource(Foto.LIBRO_DESCONOCIDO.text)
            lbl_Titulo_Libro.setText(R.string.TITULO_VACIO)
            Detalle_text.setText(R.string.DESCRIPCION_VACIA)
        }else{
            Img_Muestra.setImageResource(vista_libros[centinela].Imagen_Libro.text)
            lbl_Titulo_Libro.setText(vista_libros[centinela].Titulo)
            Detalle_text.setText(vista_libros[centinela].Detalle)
        }


        if (escritor.Articulos_aislados().isEmpty()) {
            lbl_Num_Art.setText("Articulos: " + escritor.Articulos_aislados().size)
        } else {
            lbl_Num_Art.setText("Articulos: " + escritor.Articulos_aislados().size.toString())
        }
    }


    private fun Verificador_Escritor(){

        if (lista_articulos.isEmpty()){
            Img_Muestra.setImageResource(Foto.LIBRO_DESCONOCIDO.text)
            lbl_Titulo_Libro.setText(R.string.TITULO_VACIO)
            Detalle_text.setText(R.string.DESCRIPCION_VACIA)
        }else{
            Img_Muestra.setImageResource(lista_articulos[centinela].foto_genero.text)
            lbl_Titulo_Libro.setText(lista_articulos[centinela].titulo)
            Detalle_text.setText(lista_articulos[centinela].descripcion)
        }


        if (escritor.Articulos_aislados().isEmpty()) {
            lbl_Num_Art.setText("Articulos: " + escritor.Articulos_aislados().size)
        } else {
            lbl_Num_Art.setText("Articulos: " + escritor.Articulos_aislados().size.toString())
        }
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
            if(lista_articulos.isEmpty()){
                Toast.makeText(activity, "No tienes articulos " + usuario.persona.username, Toast.LENGTH_SHORT)
                    .show();
            }else{
                (requireActivity() as MainActivity).replaceFragment(Frag_Detalle().apply {
                    arguments = Bundle().apply {
                        putParcelable("LibroSeleccionado", lista_articulos[centinela])
                        putParcelable("UsuarioSeleccionado", usuario )
                        putInt("elemento", centinela )

                    }
                })
            }
        }

        btn_Logout.setOnClickListener{
            //usuario.
            lista_articulos.clear()
            editor = preferences.edit()
            editor.clear()
            editor.apply()
            (requireActivity() as MainActivity).replaceFragment(Frag_login().apply { })
        }

        btn_Agrega.setOnClickListener(){
            (requireActivity() as MainActivity).replaceFragment(Frag_Detalle().apply {
                arguments = Bundle().apply {
                    putParcelable("UsuarioSeleccionado", usuario)
                    putParcelable("NuevoArticulo", vista_libros[centinela])
                }
            })
        }

        btn_Elimina.setOnClickListener(){

        }
    }

    private fun Rotacion_IMG() {
        if (lista_articulos.isEmpty()){
            Img_Muestra.setImageResource(Foto.LIBRO_DESCONOCIDO.text)
            lbl_Titulo_Libro.setText(R.string.TITULO_VACIO)
            Detalle_text.setText(R.string.DESCRIPCION_VACIA)
        }else{
            Img_Muestra.setImageResource(lista_articulos[centinela].foto_genero.text)
            lbl_Titulo_Libro.setText(lista_articulos[centinela].titulo)
            Detalle_text.setText(lista_articulos[centinela].descripcion)
        }
    }


}