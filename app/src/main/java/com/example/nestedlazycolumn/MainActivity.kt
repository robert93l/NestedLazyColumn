package com.example.nestedlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nestedlazycolumn.ui.theme.NestedLazyColumnTheme

class MainActivity : ComponentActivity() {

    private val parentItemList = ArrayList<ParentDataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedLazyColumnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setData()
                    MyApp(modifier = Modifier, parentItemList)
                }
            }
        }
    }

    private fun setData() {
       val images = listOf(
           ChildDataClass(R.drawable.book1),
           ChildDataClass(R.drawable.book2),
           ChildDataClass(R.drawable.book3),
           ChildDataClass(R.drawable.book4),
           ChildDataClass(R.drawable.book5),
           ChildDataClass(R.drawable.book6),
           ChildDataClass(R.drawable.book7),
           ChildDataClass(R.drawable.book8),
           ChildDataClass(R.drawable.book9),
           ChildDataClass(R.drawable.book10),
           ChildDataClass(R.drawable.book11),
           ChildDataClass(R.drawable.book12),
           ChildDataClass(R.drawable.book13),
           ChildDataClass(R.drawable.book14),
       )
        parentItemList.add(ParentDataClass("Novel: ", images))
        parentItemList.add(ParentDataClass("Best Seller: ", images.shuffled()))
        parentItemList.add(ParentDataClass("History: ", images.shuffled()))
        parentItemList.add(ParentDataClass("Favorite: ", images.shuffled()))
        parentItemList.add(ParentDataClass("Crime: ", images.shuffled()))
        parentItemList.add(ParentDataClass("Drama: ", images.shuffled()))
        parentItemList.add(ParentDataClass("New topics: ", images.shuffled()))

    }
}

@Composable
fun MyApp(modifier: Modifier.Companion, parentItemList: ArrayList<ParentDataClass>) {
    LazyColumn(contentPadding = PaddingValues(15.dp)){
        items(parentItemList.size){
            ColumnItemUI(
                modifier = modifier,
                parentList = parentItemList,
                itemIndex = it
            )


        }
    }
}

@Composable
fun ColumnItemUI(
    itemIndex: Int,
    parentList: ArrayList<ParentDataClass>,
    modifier: Modifier.Companion
) {
    Card(modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor =  Color.DarkGray
        )
    ){
            Text(text = parentList[itemIndex].title,
                modifier.padding(12.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0XffffC107)
            )
        LazyRow(
            modifier.padding(paddingValues = PaddingValues(12.dp))
        ){
            items(parentList[itemIndex].childList.size){
                RowItemUI(
                    modifier = modifier,
                    childList = parentList[itemIndex].childList,
                    itemIndex = it
                )
            }
        }
    }
}

@Composable
fun RowItemUI(modifier: Modifier.Companion, childList: List<ChildDataClass>, itemIndex: Int) {
    Box(
        modifier
            .height(200.dp)
            .width(160.dp)
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xff201e1e))){
        Image(painter = painterResource(id = childList[itemIndex].image) , contentDescription = null,
            modifier.fillMaxSize())
    }
}
