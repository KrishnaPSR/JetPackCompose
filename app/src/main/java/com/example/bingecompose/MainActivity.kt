package com.example.bingecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bingecompose.ui.theme.BingeComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.Text as Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
@Composable
fun MainScreen(){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope = scope, scaffoldState = scaffoldState)},
        drawerContent = {
            Drawer(scope = scope, scaffoldState= scaffoldState,navController=navController)
        }
    ) {
        Navigation(navController=navController)
    }
}
@Composable
fun TopBar(scope:CoroutineScope, scaffoldState: ScaffoldState){
    TopAppBar(
        title = { Text(text= "BINGE WATCH", fontSize = 20.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu,"")
            }
        },
        backgroundColor = Color.Green,
        contentColor = Color.Black
    )
}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val items = listOf(
        NavigationItem.Profile,
        NavigationItem.List,
        NavigationItem.Notification,
        NavigationItem.Settings,
        NavigationItem.Help
    )
    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.tp),
                contentDescription = " ",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            DrawerItem(item = items,selected = currentRoute == items.route,onItemClick = {
                navController.navigate(items.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
        }
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Krishna",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
@Composable
fun DrawerItem(item: NavigationItem, selected: Boolean, onItemClick: (NavigationItem) -> Unit){
    val background = if(selected) R.color.grey else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ){
        Image(
            painter = painterResource(id = item.icon) ,
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun ProfileScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Profile Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun ListScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Binge List Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun NotificationScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Notification Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun SettingsScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Setting Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun HelpScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Help & Support Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController, startDestination =NavigationItem.Profile.route){
        composable(NavigationItem.Profile.route){
            ProfileScreen()
        }
        composable(NavigationItem.List.route){
            ListScreen()
        }
        composable(NavigationItem.Notification.route){
            NotificationScreen()
        }
        composable(NavigationItem.Settings.route){
            SettingsScreen()
        }
        composable(NavigationItem.Help.route){
            HelpScreen()
        }
    }
}