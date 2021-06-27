package sandeep.example.weatherapp

class Weather() {
    var forecast:String=""
    var temp:String=""
    override fun toString(): String {
        return "Weather(forecast=$forecast,temperature=$temp)"
    }
}