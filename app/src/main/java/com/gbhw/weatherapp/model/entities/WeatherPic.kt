package com.gbhw.weatherapp.model.entities

enum class WeatherPic(val link: String) {
    CLEAR("https://c.pxhere.com/photos/22/6a/cloud_sky_blue_clouds_form_summer_summer_day_clear_frisch-833761.jpg!d"),
    PARTLY_CLOUDY("https://c.pxhere.com/images/94/c6/02e76c7102c0d9b49af39430aac3-1450575.jpg!d"),
    CLOUDY("https://c.pxhere.com/photos/5a/f8/sky_clouds_sun_rays_weather_blue_dark_jacobs_ladder-804985.jpg!d"),
    OVERCAST("https://c.pxhere.com/photos/4f/fc/cloud_overcast_cloudy_mountain_grey-1389004.jpg!d"),
    RAIN("https://c.pxhere.com/photos/95/5d/rain_window_glass_drop_raindrop-113.jpg!d"),
    HEAVY_RAIN("https://c.pxhere.com/images/8b/14/a4f889c88e7e82d6a9a2f924854d-1638651.jpg!s2"),
    SNOW("https://c.pxhere.com/photos/bc/d9/soap_bubble_bubble_eiskristalle_snow_winter_frozen_frozen_bubble-1201501.jpg!s2"),
    HEAVY_SNOW("https://c.pxhere.com/photos/d5/1b/winter_estonia_snowing_snow_nature_park-739021.jpg!d"),
    HAIL("https://c.pxhere.com/photos/1b/59/hail_hailstones_weather_storm_precipitate_grass_rush-1098519.jpg!s2"),
    THUNDERSTORM("https://c.pxhere.com/photos/65/a5/lightning_storm_weather_sky_thunder_strike_bolt_electricity-1237537.jpg!d")
}