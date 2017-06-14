package br.com.livroandroid.hellovideoview

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import android.media.MediaPlayer
import android.support.v7.app.AlertDialog
import android.R.raw


class Testes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById(R.id.videoView) as VideoView
        videoView.setMediaController(MediaController(this))
        val url = "http://www.livroandroid.com.br/livro/carros/esportivos/ferrari_ff.mp4"
        videoView.setVideoURI(Uri.parse(url))
        videoView.start()

        // Intent para tocar o vídeo no player nativo
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(url), "video/*")
        startActivity(intent)

//        val player = MediaPlayer.create(this, R.raw.linkin_park1)
//        player.start()

//        val player = MediaPlayer.create(this, Uri.parse("http://www.livroandroid.com.br/livro/linkin_park1.mp3"));
//        player.start()

//        val player = MediaPlayer()
//        player.setDataSource("http://www.livroandroid.com.br/livro/linkin_park1.mp3")
//        player.prepare()
//        player.start()

        // Valida as permissões
        PermissionUtils.validate(this, 0, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish()
                return
            }
        }

        // Se chegou aqui está OK :-)
//        val player = MediaPlayer()
//        player.setDataSource("/sdcard/Music/linkin_park1.mp3")
//        player.prepare()
//        player.start()
    }

    private fun alertAndFinish() {
        // Usuário negou a permissão, mostra alerta e fecha o app
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões.")
        builder.setPositiveButton("OK") { dialog, id -> finish() }
        val dialog = builder.create()
        dialog.show()
    }
}
