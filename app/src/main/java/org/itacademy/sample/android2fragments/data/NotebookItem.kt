package org.itacademy.sample.android2fragments.data

import android.graphics.drawable.Drawable
import java.io.Serializable

data class NotebookItem(
    val img: Drawable?,
    val model: String,
    val screen: String?,
    val hardware: String?
) : Serializable