package br.com.livroandroid.carros.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import br.com.livroandroid.camera.ImageUtils
import java.io.File
import java.io.FileOutputStream

class CameraHelper {

    companion object { private val TAG = "camera" }

    var file: File? = null
        private set

    // Se girou a tela recupera o estado
    fun init(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            file = savedInstanceState.getSerializable("file") as File
        }
    }

    // Salva o estado
    fun onSaveInstanceState(outState: Bundle) {
        if (file != null) {
            outState.putSerializable("file", file)
        }
    }

    // Intent para chamar a câmera
    fun open(context: Context, fileName: String): Intent {
        file = getSdCardFile(context, fileName)
        Log.d("camera",file.toString())
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val uri = FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file)
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        return i
    }

    // Cria o arquivo da foto
    fun getSdCardFile(context: Context, fileName: String): File {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!dir.exists()) {
            dir.mkdir()
        }
        return File(dir, fileName)
    }

    // Lê o bitmap no tamanho desejado
    fun getBitmap(w: Int, h: Int): Bitmap? {
        file?.apply {
            if (exists()) {
                Log.d(TAG, absolutePath)

                // Resize
                val bitmap = ImageUtils.resize(this, w, h)
                Log.d(TAG, "getBitmap w/h: " + bitmap.getWidth() + "/" + bitmap.getHeight())

                return bitmap
            }
        }
        return null
    }

    // Salva o bitmap reduzido no arquivo (para upload)
    fun save(bitmap: Bitmap) {
        file?.apply {
            val out = FileOutputStream(this)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.close()
            Log.d(TAG, "Foto salva: " + absolutePath)
        }
    }
}
