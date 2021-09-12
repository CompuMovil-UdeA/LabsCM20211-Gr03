package co.edu.udea.compumovil.labs20211_gr03.lab2.util

import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import java.io.File

class ImagenUtil {
    companion object {
        fun assignUrlImageToView(imagenUrl: String, view : ImageView){
            val f = File(imagenUrl)
            if (f.exists()) {
                 view.setImageBitmap(BitmapFactory.decodeFile(f.absolutePath))
            }
        }

        fun getPathFromURI(contentUri: Uri?, activity : FragmentActivity): String? {
            var res: String? = null
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = activity.contentResolver.query(contentUri!!, proj, null, null, null)
            if (cursor?.moveToFirst()!!) {
                val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                res = cursor.getString(columnIndex)
            }
            cursor.close()
            return res
        }
    }
}