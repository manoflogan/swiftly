package com.krishnanand.mobile.swiftly.utils

import com.google.gson.Gson
import com.krishnanand.mobile.swiftly.data.Product
import java.io.IOException
import java.io.InputStreamReader

/**
 * Reads productdata from the file
 */
@Throws(IOException::class)
fun readProductFromFile(): Product {
    val gson = Gson()
    var reader: InputStreamReader? = null
    try {
        reader = InputStreamReader(ClassLoader.getSystemResourceAsStream("com/krishnanand/mobile/swiftly/product/product_response.json"))
        return gson.fromJson(reader, Product::class.java)
    } finally {
        reader?.close()
    }
}