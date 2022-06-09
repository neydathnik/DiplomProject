package com.example.diplomproject.ui.firstscreen


import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.diplomproject.CurCharName
import com.example.diplomproject.appTypography
import com.example.diplomproject.data.entities.CharacterEntity
import com.example.diplomproject.data.entities.CharacterStatsEntity
import com.example.diplomproject.data.entities.ClassesEntity
import com.example.diplomproject.data.entities.RacesEntity
import com.example.diplomproject.ui.theme.*
import java.io.InputStream

fun IntToBool(int : Int) : Boolean {
    var bool : Boolean
    if (int == 1) {bool = true} else { bool = false}
    return bool
}

fun BoolToInt (bool : Boolean) : Int {
    var int : Int
    if (bool) {int = 1 } else {int = 0}
    return int
}

@Composable
fun FirstScreen(navController: NavHostController) {
    val context = LocalContext.current
    fun getClassImage(class_num: String): Bitmap? {
        var inputStream: InputStream =
            context.assets.open("ClassesImg/$class_num.jpg")
        return BitmapFactory.decodeStream(inputStream)
    }

    val mFirstScrViewModel: FirstScrViewModel = viewModel(
        factory = FirstScrViewModelFactory(context.applicationContext as Application)
    )

    val itemsChar = mFirstScrViewModel.readAllCharData
    val itemsClass = mFirstScrViewModel.readAllClassData
    val itemsRace = mFirstScrViewModel.readAllRaceData
    val itemsCharStats = mFirstScrViewModel.readAllCharStatsData

    val charList by itemsChar.observeAsState(initial = emptyList())
    val classesList by itemsClass.observeAsState(initial = emptyList())
    val raceList by itemsRace.observeAsState(initial = emptyList())
    val charStatsList by itemsCharStats.observeAsState(initial = emptyList())

    MaterialTheme(typography = appTypography) {

        val context = LocalContext.current
        val configuration = LocalConfiguration.current
        val scrollState = rememberLazyListState(0)
        LaunchedEffect(Unit) { scrollState.animateScrollToItem(10000) }
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        var name = CurCharName.value

        Box(
            modifier = Modifier
                .width(screenWidth)
                .height(screenHeight)
                .background(white)
                .alpha(1f)
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()) {
                Row {
                    Box(modifier = Modifier
                        .height(300.dp)
                        .weight(2f)
                        .border(1.dp, black))
                    {
                        Image(
                            bitmap =  getClassImage(classImg(name, charList))!!.asImageBitmap() ,
                            contentDescription = " Some description ",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize())
                    }
                    Box(modifier = Modifier
                        .height(300.dp)
                        .weight(2f)
                        .border(1.dp, black))
                    {
                        Column() {
                            TakeClass(name, charList, classesList)
                            TakeRace(name, charList, raceList)
                            TakeLvl(name , charList)
                            TakeHp(name , charList)
                        }
                    }
                }
                Row {
                    Box(modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .border(1.dp, black))
                    {
                        DeathRows()
                    }

                    Box(modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .border(1.dp, black))
                    {
                        CharInspFun(name, charList, charStatsList)
                    }
                }
                Box(modifier = Modifier
                    .width(screenWidth)
                    .height(40.dp)
                    .border(1.dp, black)) {
                    Row() {
                        Text(" Вдохновение")
                        charList.forEach {
                            if (name == it.Name) {
                                var insp: Int?
                                insp = it.Inspiration
                                if (insp == null) { insp = 0 }
                                var inspb = IntToBool(insp)
                                Checkbox(checked = inspb, onCheckedChange = {
                                    inspb = it
                                    if (inspb) { insp = 1 } else { insp = 0 }
                                    mFirstScrViewModel.updateCharInsp(name, insp)
                                }
                                )
                            }
                        }
                    }

                }
                Box(modifier = Modifier
                    .weight(1f)
                    .background(white)) {}
            }
        }
    }
}

@Composable
fun DeathRows () {
    Column {
        Divider(color= white,thickness = 10.dp)
        Row {
            IconButton(onClick = { DeathMinus(BoxSetWin) }) { Icon(imageVector = Icons.Filled.NavigateBefore,
                contentDescription = "-" ) }
            BoxSetWinFun(BoxSetWin)
            IconButton(onClick = { DeathPlus(BoxSetWin) }) { Icon(imageVector = Icons.Filled.NavigateNext,
                contentDescription = "+" ) }
        }
        Divider(color= white,thickness = 10.dp)
        Row {
            IconButton(onClick = { DeathMinus(BoxSetLose) }) { Icon(imageVector = Icons.Filled.NavigateBefore,
                contentDescription = "-" ) }
            BoxSetLoseFun(BoxSetLose)
            IconButton(onClick = { DeathPlus(BoxSetLose) }) { Icon(imageVector = Icons.Filled.NavigateNext,
                contentDescription = "+" ) }
        }
    }
}

var BoxSetWin = mutableStateListOf(false, false, false)
var BoxSetLose = mutableStateListOf(false, false, false)

fun DeathPlus (list : MutableList<Boolean>) {
    for (index in list.indices) { if (list[index] == false) {list[index] = true; break} }
}

fun DeathMinus (list : MutableList<Boolean>) {
    for (index in list.indices.reversed()) { if (list[index] == true) {list[index] = false; break} }
}

@Composable
fun BoxSetWinFun( box_set: MutableList<Boolean>) {
    Row { box_set.forEach { item -> BoxPaintFunWin(item, "Win") } }
}

@Composable
fun BoxSetLoseFun( box_set: MutableList<Boolean>) {
    Row { box_set.forEach { item -> BoxPaintFunWin(item, "Lose") } }
}

@Composable
fun BoxPaintFunWin (item: Boolean, i : String){
    var box_color: Color? = null
    if (i == "Win") { box_color = if (item){ green } else { white }}
    else {box_color = if (item){ red } else { white }}
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 40.dp,
                bottomStart = 40.dp,
                bottomEnd = 40.dp
            )
            )
            .border(1.dp,
                Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                RoundedCornerShape(topStart = 40.dp,
                    topEnd = 40.dp,
                    bottomStart = 40.dp,
                    bottomEnd = 40.dp))
            .background(box_color)
    )
}

@Composable
fun TakeLvl(CharName : String, charList : List<CharacterEntity>) {
    charList.forEach{
        if (CharName == it.Name) {
            var lvl = it.Lvl
            var exp = it.EP
            Column() {
                Text(" Уровень: $lvl")
                Text(" Опыт: $exp")
            }
        }
    }
}

@Composable
fun TakeHp (CharName : String, charList : List<CharacterEntity>) {
    charList.forEach{
        if (CharName == it.Name) {
            var hp = it.Hp
            var max_hp = it.Max_Hp
            Text(" Hp: $hp / $max_hp")
        }
    }
}

@Composable
fun TakeClass(CharName: String, charList: List<CharacterEntity>, classList: List<ClassesEntity>) {
    var class_id: Int?
    charList.forEach{
        if (CharName == it.Name) {
            class_id = it.Id_Class
            classList.forEach{
                if (class_id == it.Id){
                    var i = it.Class_Name
                    Text(" Класс: $i")
                }
            }
        }
    }
}

@Composable
fun TakeRace(CharName: String, charList: List<CharacterEntity>, racesList: List<RacesEntity>) {
    var race_id : Int?
    charList.forEach{
        if (CharName == it.Name) {
            race_id = it.Id_Class
            racesList.forEach{
                if (race_id == it.Id){
                    var i = it.Race_Name
                    Text(" Раса: $i")
                }
            }
        }
    }
}

fun classImg (CharName: String, charList: List<CharacterEntity>) : String {
    var i = 0
    charList.forEach{
        if (CharName == it.Name) { i = it.Id_Class!! }
    }
    return i.toString()
}

@Composable
fun CharInspFun (CharName: String, charList: List<CharacterEntity>, charStatsList : List<CharacterStatsEntity>) {
    charList.forEach{
        if (CharName == it.Name){
            var charId = it.Id_Char
            charStatsList.forEach{
                if (it.Id_Char == charId && it.Id_Stats == 2 ) {
                    if (it.Stat_Value == 1) { Text("Инициатива -5 ") }
                    if (it.Stat_Value == 2 || it.Stat_Value == 3) { Text("Инициатива -4 ") }
                    if (it.Stat_Value == 4 || it.Stat_Value == 5) { Text("Инициатива -3 ") }
                    if (it.Stat_Value == 6 || it.Stat_Value == 7) { Text("Инициатива -2 ") }
                    if (it.Stat_Value == 8 || it.Stat_Value == 9) { Text("Инициатива -1 ") }
                    if (it.Stat_Value == 10 || it.Stat_Value == 11) { Text("Инициатива -0 ") }
                    if (it.Stat_Value == 12 || it.Stat_Value == 13) { Text("Инициатива +1 ") }
                    if (it.Stat_Value == 14 || it.Stat_Value == 15) { Text("Инициатива +2 ") }
                    if (it.Stat_Value == 16 || it.Stat_Value == 17) { Text("Инициатива +3 ") }
                    if (it.Stat_Value == 18 || it.Stat_Value == 19) { Text("Инициатива +4 ") }
                    if (it.Stat_Value == 20 || it.Stat_Value == 21) { Text("Инициатива +5 ") }
                    if (it.Stat_Value == 22 || it.Stat_Value == 23) { Text("Инициатива +6 ") }
                    if (it.Stat_Value == 24 || it.Stat_Value == 25) { Text("Инициатива +7 ") }
                    if (it.Stat_Value == 26 || it.Stat_Value == 27) { Text("Инициатива +8 ") }
                    if (it.Stat_Value == 28 || it.Stat_Value == 29) { Text("Инициатива +9 ") }
                    if (it.Stat_Value == 30) { Text("Инициатива +10 ") }
                }
            }
        }
    }
}