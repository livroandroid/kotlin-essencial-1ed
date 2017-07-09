package br.com.livroandroid.camera

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import java.io.File

class MainActivity : AppCompatActivity() {
    // Caminho para salvar o arquivo
    var file: File? = null
    val imgView: ImageView by lazy  { findViewById<ImageView>(R.id.imagem)}
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this
        val b = findViewById<View>(R.id.btAbrirCamera)
        b.setOnClickListener {
            // (*1*) Cria o caminho do arquivo no sdcard
            // /storage/sdcard/Android/data/br.com.livroandroid.multimidia/files/Pictures/foto.jpg
            file = getSdCardFile("foto.jpg")
            Log.d("livro", "Camera file: $file")
            // Chama a intent informando o arquivo para salvar a foto
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val uri = FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file)
            i.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(i, 0)
        }
        if (savedInstanceState != null) {
            // (*2) Se girou a tela recupera o estado
            file = savedInstanceState.getSerializable("file") as File
            showImage(file)
        }
    }

    // Cria um arquivo no sdcard privado do aplicativo
    fun getSdCardFile(fileName: String): File {
        val sdCardDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!sdCardDir.exists()) {
            sdCardDir.mkdir()
        }
        val file = File(sdCardDir, fileName)
        return file
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // (*3*) Salvar o estado caso gire a tela
        outState.putSerializable("file", file)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("foto", "resultCode: " + resultCode)
        if (resultCode == Activity.RESULT_OK) {
            // (*4*) Se a câmera retornou, vamos mostrar o arquivo da foto
            showImage(file)
        }
    }

    // Atualiza a imagem na tela
    private fun showImage(file: File?) {
        if (file != null && file.exists()) {
            val w = imgView.width
            val h = imgView.height
            // (*5*) Redimensiona a imagem para o tamanho do ImageView
            val bitmap = ImageUtils.resize(file, w, h)
            toast("w/h:" + imgView.width + "/" + imgView.height + " > " + "w/h:" + bitmap.width + "/" + bitmap.height)
            toast("file:" + file)
            imgView.setImageBitmap(bitmap)
        }
    }

    fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}
