package sandeep.example.weatherapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

private const val Temp="temp"
private const val Cond="condition"
private const val Ic="icon"
class MainActivity : AppCompatActivity(),GetJsonData.onDownloadComplete,storeData.OnDataFecth {
    private val TAG="MainActivity"
            private var CityName:String=""
    private  val s1:String="https://api.openweathermap.org/data/2.5/weather?q="
    private  val s2:String="&units=metric&appid=d8e791fa058b84ac6fb74b590ebc89e2"
    override fun onCreate(savedInstanceState: Bundle?) {
        //Log.d(TAG,"onCreate:starts")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchIcon.setOnClickListener {
            CityName = SearchBar.text.toString()

            val url:String = s1 + CityName + s2
            val getJsonData = GetJsonData(this)
            getJsonData.execute(url)

        }
        SearchBar.setOnKeyListener (
            View.OnKeyListener{v, keyCode, event ->
            if(keyCode==KeyEvent.KEYCODE_ENTER&&event.action==KeyEvent.ACTION_UP)
            {
                CityName = SearchBar.text.toString()

                val url = s1 + CityName + s2
                val getJsonData = GetJsonData(this)
                getJsonData.execute(url)
                return@OnKeyListener true
            }
                false
        })

       // Log.d(TAG,"onCreate:Ends")

    }



    override fun OnDownloadComplete(data: String) {
       //Log.d(TAG,data)
        val StoreData=storeData(this)
        StoreData.execute(data)
    }

    override fun onDataFecth(data: Weather) {
//       Log.d(TAG,"forecost=${data.forecast}")
//        Log.d(TAG,"temp=${data.temp}")
        if(data.temp!="")
        {
            val temp=data.temp+" °C"
            cityName.setText(CityName)
            tempView.setText(temp)
            Condition.setText(data.forecast)
            Icon.setImageResource(when(data.forecast){
                "Thunderstorm"->R.drawable.thunder
                "Drizzle"->R.drawable.drizzles
                "Rain"->R.drawable.rain
                "Snow"->R.drawable.snows
                "Clear"->R.drawable.clear
                "Clouds"->R.drawable.cloud
                else->R.drawable.haze
            })
        }
        else
        {
            cityName.text="Enter a Known City"
            tempView.text=""
            Condition.text=""
            Icon.setImageResource(R.drawable.sorry)
        }


    }


}