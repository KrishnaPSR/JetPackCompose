package com.example.bingecompose

sealed class NavigationItem(var route:String , var icon: Int, var title: String)
{
    object Profile: NavigationItem("profile", R.drawable.ic_profile,"Profile")
    object List : NavigationItem("list",R.drawable.ic_list,"Binge List")
    object Notification: NavigationItem("notification",R.drawable.ic_notification,"Notifications")
    object Settings: NavigationItem("settings",R.drawable.ic_settings,"Settings")
    object Help: NavigationItem("help",R.drawable.ic_help,"Help & Support")

}
