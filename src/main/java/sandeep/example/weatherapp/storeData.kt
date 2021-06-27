package sandeep.example.weatherapp

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject

class storeData(private val listener:OnDataFecth):AsyncTask<String,Void,Weather>(){
    private val TAG="storeData"
 interface  OnDataFecth{
     fun onDataFecth(data:Weather)
 }

    override fun onPostExecute(result: Weather) {
        //Log.d(TAG,".onPostExecute:starts")
        listener.onDataFecth(result)
    }

    override fun doInBackground(vararg params: String?): Weather {
          //Log.d(TAG,"DoInBackground:starts")
        val weather=Weather()
        try {

            val jsonD=JSONObject(params[0])
            val wthrArray=jsonD.getJSONArray("weather")
            val wthrObj=wthrArray.getJSONObject(0)
            weather.forecast=wthrObj.getString("main")
            val tempObj=jsonD.getJSONObject("main")
            weather.temp=tempObj.getString("temp")


        }
        catch (e:Exception)
        {
            Log.d(TAG,"error found:${e.message}")
        }
        return weather
    }
}