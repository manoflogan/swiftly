package com.krishnanand.mobile.swiftly.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Base class encapsulating [Product] model.
 */
@Parcelize
data class Product(
    /**
     * Canvas unit
     */
    val canvasUnit: Int,
    /**
     * List of manager spacecials
     */
    val managerSpecials: List<ManagerSpecials>
): Parcelable

/**
 * Instance of the class
 */
@Parcelize
data class ManagerSpecials(
    /**
     * Image URL
     */
    val imageUrl: String,
    /**
     * Image Width
     */
    val width: Int,

    /**
     * Image height
     */
    val height: Int,

    /**
     * Display name
     */
    @SerializedName("display_name")
    val displayName: String,

    /**
     * Original price
     */
    @SerializedName("original_price")
    val originalPrice: Float,

    /**
     * Price.
     */
    val price: Float
): Parcelable



