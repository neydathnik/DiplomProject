package com.example.diplomproject.ui.secondscreen

import android.app.Application
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.diplomproject.CurCharName
import com.example.diplomproject.appTypography
import com.example.diplomproject.data.entities.CharacterEntity
import com.example.diplomproject.data.entities.CharacterSkillsEntity
import com.example.diplomproject.data.entities.CharacterStatsEntity
import com.example.diplomproject.data.entities.SkillsEntity
import com.example.diplomproject.ui.theme.*

@Composable
fun SecondScreen (navController: NavHostController){

    val context = LocalContext.current
    val mSecondScrViewModel: SecondScrViewModel = viewModel(
        factory = SecondScrViewModelFactory(context.applicationContext as Application)
    )

    val itemsSkills = mSecondScrViewModel.readAllSkillsData
    val itemsStats = mSecondScrViewModel.readAllStatsData
    val itemsCharSkills = mSecondScrViewModel.readAllCharSkillsData
    val itemsCharStats = mSecondScrViewModel.readAllCharStatsData
    val itemsChar = mSecondScrViewModel.readAllCharData

    val skillsList by itemsSkills.observeAsState(initial = emptyList())
    val statsList by itemsStats.observeAsState(initial = emptyList())
    val charSkillsList by itemsCharSkills.observeAsState(initial = emptyList())
    val charStatsList by itemsCharStats.observeAsState(initial = emptyList())
    val charList by itemsChar.observeAsState(initial = emptyList())



    MaterialTheme(typography = appTypography) {

        val context = LocalContext.current
        val configuration = LocalConfiguration.current
        val scrollState = rememberLazyListState(0)
        LaunchedEffect(Unit) { scrollState.animateScrollToItem(10000) }
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp


        if (charList.isNotEmpty() && skillsList.isNotEmpty() && charSkillsList.isNotEmpty() && charStatsList.isNotEmpty()){
            var name = CurCharName.value

            var profBonus = ProfBonus(name, charList )

            var curStatsList = curStatsListFun(name, charList,charStatsList)

            var curStatBonusList = statsBonus(curStatsList)

            var challList = challenges(name, charList, charStatsList, profBonus, curStatBonusList )

            var skillsFocusList = emptyList<Int?>().toMutableList()
            skillsFocusList.add(0)
            charSkillsList.forEach{skillsFocusList.add(it.Skill_Focus)}



            Box(

            modifier = Modifier
                .width(screenWidth)
                .height(screenHeight)
        )
            {


                Row {
                    //LEFT
                    Box(modifier = Modifier
                        .height(screenHeight)
                        .weight(1f)){

                        Column() {
                            //left top STATS with focus
                            Box(modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .border(1.dp, black)){

                                Column(modifier = Modifier
                                    .fillMaxHeight())
                                {


                                    Row(modifier = Modifier
                                        .weight(1f)
                                        .border(1.dp, black)
                                        ) {


                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .border(1.dp, black)
                                            .fillMaxHeight())
                                        {
                                            Column() {
                                                Text( " СИЛ: ${curStatsList[0]}")
                                                Text (" Бонус: ${curStatBonusList[1]}")
                                            }
                                        }


                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .border(1.dp, black)
                                            .fillMaxHeight()
                                        )
                                        {
                                            Column() {
                                                Text( " ЛВК: ${curStatsList[1]}")
                                                Text (" Бонус: ${curStatBonusList[2]}")
                                            }

                                        }
                                    }

                                    Row(modifier = Modifier
                                        .weight(1f)
                                        .border(1.dp, black))
                                    {

                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .border(1.dp, black)
                                            .fillMaxHeight())
                                        {
                                            Column() {
                                                Text( " ВЫН: ${curStatsList[2]}")
                                                Text (" Бонус: ${curStatBonusList[3]}")
                                            }

                                        }

                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .border(1.dp, black)
                                            .fillMaxHeight())
                                        {

                                            Column() {
                                                Text( " ИНТ: ${curStatsList[3]}")
                                                Text (" Бонус: ${curStatBonusList[4]}")
                                            }

                                        }

                                    }

                                    Row(modifier = Modifier
                                        .weight(1f)
                                        .border(1.dp, black))
                                    {

                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .border(1.dp, black)
                                            .fillMaxHeight())
                                        {
                                            Column() {
                                                Text( " МДР: ${curStatsList[4]}")
                                                Text (" Бонус: ${curStatBonusList[5]}")
                                            }

                                        }

                                        Box(modifier = Modifier
                                            .weight(1f)
                                            .border(1.dp, black)
                                            .fillMaxHeight())
                                        {
                                            Column() {
                                                Text( " ХАР: ${curStatsList[5]}")
                                                Text (" Бонус: ${curStatBonusList[6]}")
                                            }

                                        }

                                    }
                                }
                            }
                            //left bottom STATS BONUS
                            Box(modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .border(1.dp, black))
                            {
                                Column(modifier = Modifier.fillMaxWidth())
                                {
                                    Text (" ${challList[0]} Сила")
                                    Text (" ${challList[1]} Ловкость")
                                    Text (" ${challList[2]} Выносливость")
                                    Text (" ${challList[3]} Интеллект")
                                    Text (" ${challList[4]} Мудрость")
                                    Text (" ${challList[5]} Харизма")
                                }
                            }
                    }
                }

                    //right SKILLS with they modification bonus and ProfBonus
                Column(modifier = Modifier.weight(1f))
                {
                    Box(modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .border(1.dp, black))
                    {

                        Text( "Бонус мастерства: +$profBonus")
                    }

                    Box(modifier = Modifier
                        .height(screenHeight)
                        .weight(1f)
                        .fillMaxWidth()
                        .border(1.dp, black))
                    {
                        skillBonus(name,curStatBonusList,profBonus,charList,charSkillsList,skillsList)
                    }
                }
            }
        }
    }
}
}

fun ProfBonus (charName : String, charList : List<CharacterEntity>) : Int? {
    var i = 0
    charList.forEach{
        if (it.Name == charName){

            if (it.Lvl == 1|| it.Lvl == 2 || it.Lvl == 3 || it.Lvl == 4) { i = 2 }
            if (it.Lvl == 5|| it.Lvl == 6 || it.Lvl == 7 || it.Lvl == 8 ) { i = 3 }
            if (it.Lvl == 9|| it.Lvl == 10 || it.Lvl == 11 || it.Lvl == 12 ) { i = 4 }
            if (it.Lvl == 13|| it.Lvl == 14 || it.Lvl == 15 || it.Lvl == 16 ) { i = 5 }
            if (it.Lvl == 17|| it.Lvl == 18 || it.Lvl == 19 || it.Lvl == 20 ) { i = 6 }

        }
    }
    return i
}

fun curStatsListFun (charName: String, charList: List<CharacterEntity>, charStatsList: List<CharacterStatsEntity>) : List<Int?>{
    var statsList = emptyList<Int?>().toMutableList()
    charList.forEach{
        if (it.Name == charName) {
            var charId = it.Id_Char
            charStatsList.forEach(){ if (it.Id_Char == charId) { statsList.add(it.Stat_Value)} }
        }
    }
    return statsList
}

fun statsBonus (statsList: List<Int?>) : List<Int> {
    var statsBonusList = emptyList<Int>().toMutableList()
    statsBonusList.add(0)
    statsList.forEach{
        if (it == 1) { statsBonusList.add(-5) }
        if (it == 2|| it == 3 ) {statsBonusList.add(-4)}
        if (it == 4|| it == 5 ) {statsBonusList.add(-3)}
        if (it == 6|| it == 7 ) {statsBonusList.add(-2)}
        if (it == 8|| it == 9 ) {statsBonusList.add(-1)}
        if (it == 10|| it == 11 ) {statsBonusList.add(0)}
        if (it == 12|| it == 13 ) {statsBonusList.add(1)}
        if (it == 14|| it == 15 ) {statsBonusList.add(2)}
        if (it == 16|| it == 17 ) {statsBonusList.add(3)}
        if (it == 18|| it == 19 ) {statsBonusList.add(4)}
        if (it == 20|| it == 21 ) {statsBonusList.add(5)}
        if (it == 22|| it == 23 ) {statsBonusList.add(6)}
        if (it == 24|| it == 25 ) {statsBonusList.add(7)}
        if (it == 26|| it == 27 ) {statsBonusList.add(8)}
        if (it == 28|| it == 29 ) {statsBonusList.add(9)}
        if (it == 30 ) {statsBonusList.add(10)}
    }
    return statsBonusList
}

fun challenges (charName: String, charList: List<CharacterEntity>, charStatsList : List<CharacterStatsEntity>, profBonus : Int?, statBonusList : List<Int?>) : List<Int?> {
    var statsChalList = statBonusList.toMutableList()
    var k : Int?
    charList.forEach{
        if (it.Name == charName) {
            var charId = it.Id_Char
            var i = 0
            charStatsList.forEach{
                if (it.Id_Char == charId) {
                    if (it.Stat_Focus == 1) if (it.Stat_Focus == 1) {
                        k = statsChalList[i]
                        k = k!! + profBonus!!
                        statsChalList[i] = k
                    }
                    i ++
                }
            }
        }
    }
    return statsChalList
}

@Composable
fun skillBonus (charName: String, curStatBonus : List<Int>, profBonus: Int?, charList: List<CharacterEntity>, charSkillsList: List<CharacterSkillsEntity>, skillsList: List<SkillsEntity>) {
    var focusList = emptyList<Int?>().toMutableList()
    charList.forEach{
        if (it.Name == charName) {
            var idChar = it.Id_Char
            charSkillsList.forEach{
                if (it.Id_Char == idChar) {
                    focusList.add(it.Skill_Focus)
                }
            }
        }
    }

    var skillsStatList = emptyList<Int>().toMutableList()
    skillsStatList.add(0)
    skillsList.forEach { skillsStatList.add(it.Id_Stat) }

    var skillBonusList = emptyList<Int>().toMutableList()
    skillsStatList.forEach{ skillBonusList.add(curStatBonus[it]) }

    var endBonusList = emptyList<Int>().toMutableList()
    endBonusList.add(0)

    var i = 1
    focusList.forEach {
        if (it == 1) {
            endBonusList.add(skillBonusList[i]+ profBonus!!)
        }
        else{endBonusList.add(skillBonusList[i])}
        i++
    }
    Column() {
        var l = 1
        skillsList.forEach {
            Text(" ${endBonusList[l]} ${it.Name}")
            l++
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Diplom_wTheme() {
//        val navController = rememberNavController()
//        SecondScreen(navController = navController)
//    }
//}