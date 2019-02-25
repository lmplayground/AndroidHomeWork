package com.lmplayground.androidhomework

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.DownloadListener
import android.widget.ImageButton
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.downloader.*

import kotlinx.android.synthetic.main.activity_download.*


class DownloadActivity : AppCompatActivity() {

    lateinit var playBtn :ImageButton
    lateinit var  downloadBtn :ImageButton
    lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        playBtn=findViewById(R.id.imagePlay)
        downloadBtn=findViewById(R.id.imageDownload)
        progressBar=findViewById(R.id.progressBar)
        val url = "https://www.sample-videos.com/video123/mp4/360/big_buck_bunny_360p_20mb.mp4"
        var dirPath = ""
        var fileName = ""
       downloadBtn.setOnClickListener {
           dowanload(url,fileName,dirPath)
       }


    }

    fun dowanload(url:String,fileNameExternal:String,dirPath:String){

        PRDownloader.initialize(applicationContext);
        val downloadId = PRDownloader.download(url, dirPath, fileNameExternal)
            .build()
            .setOnStartOrResumeListener { }
            .setOnPauseListener { }.setOnCancelListener { }.setOnProgressListener { progress ->
                progress.currentBytes
                progress.totalBytes

                progressBar.setProgress(progress.currentBytes.toInt(),true)
                progressBar.max= progress.totalBytes.toInt()
            }.setOnPauseListener { }.start(object : OnDownloadListener {
                override fun onDownloadComplete() {


                    imageDownload.visibility= View.GONE
                    imagePlay.visibility=View.VISIBLE
                }

                override fun onError(error: Error?) {
                }
            })
    }
}
