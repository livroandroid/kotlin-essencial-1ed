package br.com.livroandroid.hellovideoview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.MediaController
import android.widget.VideoView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val videoView = findViewById(R.id.videoView) as VideoView
//        videoView.setMediaController(MediaController(this))
        val url = "http://www.livroandroid.com.br/livro/carros/esportivos/ferrari_ff.mp4"
//        videoView.setVideoURI(Uri.parse(url))
//        videoView.start()
//
//        // Intent para tocar o vídeo no player nativo
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
    }
}
