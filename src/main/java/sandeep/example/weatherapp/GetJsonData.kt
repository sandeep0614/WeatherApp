package sandeep.example.weatherapp

import android.os.AsyncTask
import android.util.Log
import java.net.URL

class GetJsonData(private val listener:onDownloadComplete):AsyncTask<String,Void,String>() {
    private val TAG="GetJsonData"
    interface onDownloadComplete{
        fun OnDownloadComplete(data:String)
    }

    override fun onPostExecute(result: String) {
//        Log.d(TAG,".onPostExecute: starts")
//        Log.d(TAG,result)
        listener.OnDownloadComplete(result)
    }

    override fun doInBackground(vararg params: String?): String {
       // Log.d(TAG,".doInBackground:starts")
        if(params[0]==null)
        {
            return "No url given"
        }
        else
        {
            try {
                  return URL(params[0]).readText()
            }
            catch (e:Exception)
            {
                return "error found  : ${e.message}"
            }

        }
    }
}