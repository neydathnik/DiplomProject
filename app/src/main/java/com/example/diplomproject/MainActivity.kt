package com.example.diplomproject


import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.Group
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diplomproject.ui.firstscreen.FirstScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.diplomproject.ui.firstscreen.FirstScrViewModel
import com.example.diplomproject.ui.firstscreen.FirstScrViewModelFactory
import com.example.diplomproject.ui.secondscreen.SecondScreen
import com.example.diplomproject.utils.Constants

var CurCharName = mutableStateOf<String>("")

class MainActivity : AppCompatActivity() {
    var prefs: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("com.example.diplomproject", MODE_PRIVATE);
        setContent {
            val navController = rememberNavController()
            Scaffold (
                bottomBar = { BottomNavigationBar(navController = navController) },
                topBar = { TopBar() },
                content = { padding -> NavHostContainer(navController = navController, padding = padding)
                }
            )
        }
    }
}




private val appFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.roboto_condensed_bold,
            weight = FontWeight.W900,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_condensed_italic,
            weight = FontWeight.W900,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.roboto_condensed_light,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_condensed_regular,
            weight = FontWeight.W800,
            style = FontStyle.Normal
        ),
    )
)


private val defaultTypography = Typography()
val appTypography = Typography(
    h1 = defaultTypography.h1.copy(fontFamily = appFontFamily),
    h2 = defaultTypography.h2.copy(fontFamily = appFontFamily),
    h3 = defaultTypography.h3.copy(fontFamily = appFontFamily),
    h4 = defaultTypography.h4.copy(fontFamily = appFontFamily),
    h5 = defaultTypography.h5.copy(fontFamily = appFontFamily),
    h6 = defaultTypography.h6.copy(fontFamily = appFontFamily),
    subtitle1 = defaultTypography.subtitle1.copy(fontFamily = appFontFamily),
    subtitle2 = defaultTypography.subtitle2.copy(fontFamily = appFontFamily),
    body1 = defaultTypography.body1.copy(fontFamily = appFontFamily),
    body2 = defaultTypography.body2.copy(fontFamily = appFontFamily),
    button = defaultTypography.button.copy(fontFamily = appFontFamily),
    caption = defaultTypography.caption.copy(fontFamily = appFontFamily),
    overline = defaultTypography.overline.copy(fontFamily = appFontFamily)
)




@Composable
fun TopBar() {
    val context = LocalContext.current
    val mFirstScrViewModel: FirstScrViewModel = viewModel(
        factory = FirstScrViewModelFactory(context.applicationContext as Application)
    )
    val items = mFirstScrViewModel.readAllCharData
    val charList by items.observeAsState(initial = emptyList())

    if (charList.isNotEmpty()) {
        if (CurCharName.value == "" ) { CurCharName.value = charList[0].Name }
        TopAppBar() {
            var expanded by remember { mutableStateOf(false) }
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Filled.Group, contentDescription = "Показать меню")
            }
            if (charList.isNotEmpty()) {
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    charList.forEach {
                        DropdownMenuItem(onClick = { CurCharName.value = it.Name }) { Text(it.Name) }
                    }
                }
            }
            Text(CurCharName.value)
        }
    }
}


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "first",
        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            composable("first") {
                FirstScreen(navController)
            }
            composable("second"){
                SecondScreen(navController)
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color(0xFF6200EE)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = { navController.navigate(navItem.route) },
                icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                label = { Text(text = navItem.label) },
                alwaysShowLabel = false
            )
        }
    }
}