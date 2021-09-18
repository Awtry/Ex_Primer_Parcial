package com.awtry.ex_primer_parcial

enum class Persona(var username: String?, var password: String?) {

    USUARIO_VACIO(null,null),
    ESCRITOR_ZHONGLI("Zhongli","123"),
    ESCRITOR_ALBEDO("Albedo","456"),
    ESCRITOR_NINGGUANG("Ningguang","789"),
    LECTOR_DILUC("Diluc","987"),
    LECTOR_SUCROSE("Sucrose","654"),
    LECTOR_XINGQIU("Xingqiu","321")


}