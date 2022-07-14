package tv.snews.souv

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import tv.snews.souv.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val myWebView: WebView = findViewById(R.id.teste)
        myWebView.loadUrl("https://whatwebcando.today/camera-microphone.html")

        myWebView.settings.apply {
            loadWithOverviewMode = true
            useWideViewPort = true
            javaScriptEnabled = true
            allowFileAccess = true
mediaPlaybackRequiresUserGesture = true
            allowContentAccess = true
            mediaPlaybackRequiresUserGesture = true
            allowContentAccess = true
            allowContentAccess = true
            javaScriptCanOpenWindowsAutomatically = true
            mediaPlaybackRequiresUserGesture = false
            domStorageEnabled = true // Set because [WEBSITE] team say to use it.

        }

        myWebView.webViewClient = WebViewClient()

        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                Log.d("---", "consoleMessage ${consoleMessage?.message()}")
                return super.onConsoleMessage(consoleMessage)
            }
            }


        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    Toast.makeText(applicationContext, "Camera Access", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Not Permitted ", Toast.LENGTH_SHORT).show()

                }
            }


        val requestCamera = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Toast.makeText(applicationContext, "Camera Access", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Not Permitted ", Toast.LENGTH_SHORT).show()

            }
        }
        requestCamera.launch(android.Manifest.permission.CAMERA)



    }




    override fun onBackPressed() {
        val myWebView: WebView = findViewById(R.id.teste)

        // if your webview can go back it will go back
        if (myWebView.canGoBack())
            myWebView.goBack()

        else
            super.onBackPressed()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}