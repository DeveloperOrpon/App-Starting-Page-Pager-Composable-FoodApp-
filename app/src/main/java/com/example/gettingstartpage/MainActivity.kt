package com.example.gettingstartpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gettingstartpage.ui.theme.BottomCardShape
import com.example.gettingstartpage.ui.theme.GettingStartPageTheme
import com.example.gettingstartpage.ui.theme.Poppins
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.uistack.onboarding.ui.theme.ColorBlue
import com.uistack.onboarding.ui.theme.ColorGreen
import com.uistack.onboarding.ui.theme.ColorYellow

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GettingStartPageTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val items=ArrayList<Data>()
                    items.add(
                        Data(
                            R.drawable.fruit,
                            "Hmmm, Healthy Food",
                            "A variety of healthy foods made by the best chefs. Ingredients are easy to find. all delicious flavors can only be found at cookbunda",
                            backgroundColor = Color(0xFF0189C5),
                            mainColor = Color(0xFF00B5EA)
                        )
                    )

                    items.add(
                        Data(
                            R.drawable.food,
                            "Fresh Drinks, Stay Fresh",
                            "Not only food. we provide clear healthy drink options for you. Fresh taste always accompanies you",
                            backgroundColor = Color(0xFFE4AF19),
                            mainColor = ColorYellow
                        )
                    )
                    items.add(
                        Data(
                            R.drawable.cooking,
                            "Let’s Cooking",
                            "Are you ready to make a dish for your friends or family? create an account and cook",
                            backgroundColor = Color(0xFF96E172),
                            mainColor = ColorGreen
                        )
                    )

                    ///Start Pager
                    val pagerState= rememberPagerState(
                        pageCount = items.size,
                        initialOffscreenLimit = 2,
                        infiniteLoop = false,
                        initialPage = 0
                    )
                    OnPager(
                        item =items,
                        pagerState=pagerState,
                        modifier=Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun OnPager(
        item: List<Data>,
        pagerState: PagerState,
        modifier: Modifier=Modifier
    ) {
        Box(modifier = modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HorizontalPager(state = pagerState) { page ->  
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(item[page].backgroundColor),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(painter = painterResource(id = item[page].image),
                            contentDescription = "Image",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
        Box(contentAlignment = Alignment.BottomCenter) {
            Card(backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp)),
                elevation = 0.dp
                ) {
                Box {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PagerIndicator(item=item,currentPage=pagerState.currentPage)
                        Text(
                            text =item[pagerState.currentPage].title,
                            color = item[pagerState.currentPage].mainColor,
                            modifier=Modifier.padding(top = 20.dp , end = 30.dp),
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = Poppins,
                            fontSize = 20.sp
                        )
                        Text(
                            text =item[pagerState.currentPage].desc,
                            color = item[pagerState.currentPage].mainColor,
                            modifier=Modifier.padding(top = 20.dp , end = 30.dp),
                            fontWeight = FontWeight.ExtraLight,
                            fontFamily = Poppins,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Box(modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(30.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (pagerState.currentPage!=2) {
                                TextButton(
                                    onClick = { mutableSetOf(pagerState.currentPage.inc())},
                                    border = BorderStroke(
                                        1.dp,
                                        item[pagerState.currentPage].mainColor
                                    )
                                ) {
                                    Text(
                                        text = "Skip Now",
                                        fontSize = 14.sp,
                                        fontFamily = Poppins,
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Center,
                                        color = item[pagerState.currentPage].mainColor
                                    )
                                }
                                OutlinedButton(
                                    onClick = { /*TODO*/ },
                                    border = BorderStroke(
                                        1.dp,
                                        item[pagerState.currentPage].mainColor
                                    )
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic__right),
                                        contentDescription = "icon",
                                        tint = item[pagerState.currentPage].mainColor
                                    )
                                }
                            }
                            else{
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier=Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(vertical = 12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = item[pagerState.currentPage].mainColor
                                    )
                                ) {
                                    Text(
                                        text = "Getting Start",
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                }
                            }

                        }

                    }
                }
            }

        }
    }

    private @Composable
    fun PagerIndicator(item: List<Data>, currentPage: Int) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 20.dp)
            ) {
            repeat(item.size){
                Indicator(
                    isSelected=it==currentPage,
                    color=item[it].mainColor

                )
            }
        }
    }

    private @Composable
    fun Indicator(isSelected: Boolean, color: Color) {
        val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)
        Box(
            modifier = Modifier
                .padding(4.dp)
                .height(10.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(
                    if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
                )
        )
    }
}

