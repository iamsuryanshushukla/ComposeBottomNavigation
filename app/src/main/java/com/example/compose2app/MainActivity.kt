package com.example.compose2app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose2app.ui.theme.Compose2AppTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose2AppTheme {
                // A surface container using the 'background' color from the theme
            }
        }
    }
}
@Composable
fun MyApp(){
    Compose2AppTheme{
        Scaffold(bottomBar = {
            AppBottomNavigation()
        }) {paddingValues ->
        HomeScreen()

        }
        
    }
}
@Composable
fun AppBottomNavigation(modifier: Modifier=Modifier){
    BottomNavigation(modifier,
    backgroundColor = MaterialTheme.colors.background) {
        BottomNavigationItem(selected = true, onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription =null ) },
            label = {
                Text(text = "Home")
            }
        )
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ },
        icon = {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        },
        label = {
            Text(text = "Add")
        })
    }
    
}

@Composable
fun HomeScreen(modifier: Modifier=Modifier){

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)) {
        Spacer(Modifier.height(10.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(50.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYOurBodyRow()
        }
        Spacer(modifier = Modifier.height(50.dp))
        HomeSection(title = R.string.favourite_collection) {
            FavouriteCollectionGrid()
        }
        
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(value = "", onValueChange = {},
    modifier = modifier
        .fillMaxWidth()
        .heightIn(56.dp)
    , leadingIcon = {
        Icon(imageVector = Icons.Filled.Search, contentDescription ="Search" )
        }
    , colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
    ),
    placeholder = {
        Text(text = "Search")
    })
}

@Composable
fun HomeSection(
    @StringRes title:Int,
    modifier: Modifier=Modifier,
    content:@Composable ()-> Unit
) {
    Column(modifier) {
        Text(text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp))
        content()
    }

}

@Composable
fun AlignYourBody(modifier: Modifier=Modifier,@DrawableRes drawable: Int,@StringRes text: Int){
    Column(modifier=modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(drawable) , contentDescription =null, modifier = modifier
            .size(88.dp)
            .clip(CircleShape)
        , contentScale = ContentScale.Crop)
        Text(text = stringResource(id = text),modifier=modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp), style = MaterialTheme.typography.h6)
    }
}+



@Composable
fun AlignYOurBodyRow(modifier: Modifier=Modifier){
    LazyRow(
        modifier=modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(alignYourBodyData){ item->
            AlignYourBody(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun FavouriteCollection(@DrawableRes drawable: Int,@StringRes text: Int,modifier: Modifier=Modifier){
    Surface(modifier = Modifier,
    shape = MaterialTheme.shapes.small) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)) {
            Image(painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp))
            Text(text = stringResource(text),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun FavouriteCollectionGrid(modifier: Modifier=Modifier){
    LazyHorizontalGrid(rows = GridCells.Fixed(2), modifier = modifier
        .height(150.dp),
    contentPadding = PaddingValues(10.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(favouriteCollection){item ->
            FavouriteCollection(item.drawable,item.text)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFF0EAE2
)
@Composable
fun DefaultPreview() {
    Compose2AppTheme {
        MyApp()
//        HomeScreen()
//        AppBottomNavigation()
//        HomeSection(title = R.string.favourite_collection) {
//            FavouriteCollectionGrid()
//        }

//        FavouriteCollection(drawable = R.drawable.yoga, text = R.string.ab6_pre_natal_yoga, modifier = Modifier.size(56.dp))
    }

}

private val alignYourBodyData = listOf(
    R.drawable.first_yoga to R.string.ab1_inversions,
    R.drawable.second_yoga to R.string.ab2_quick_yoga,
    R.drawable.third_yoga to R.string.ab3_stretching,
    R.drawable.fourth_yoga to R.string.ab4_tabata,
    R.drawable.six_yoga to R.string.ab5_hiit,
    R.drawable.yoga to R.string.ab6_pre_natal_yoga
)
    .map { DrawableStringPair(it.first, it.second)}

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

private val favouriteCollection = listOf(
    R.drawable.second_yoga to R.string.favourite_collection,
    R.drawable.second_yoga to 1,
    R.drawable.second_yoga to 1,
    R.drawable.second_yoga to 1,
    R.drawable.second_yoga to 1,
    R.drawable.second_yoga to 1
).map { FavouriteCollectionData(it.first,it.second) }

private data class FavouriteCollectionData(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)