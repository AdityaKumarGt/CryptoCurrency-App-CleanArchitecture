package com.example.cryptocurrencyapp.presentation.coin_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.presentation.coin_detail.components.CoinTag
import com.example.cryptocurrencyapp.presentation.coin_detail.components.LinkItem
import com.example.cryptocurrencyapp.presentation.coin_detail.components.TeamListItem
import com.example.cryptocurrencyapp.presentation.ui.BoxColor
import com.example.cryptocurrencyapp.common.utils.TimeFormatter
import com.example.cryptocurrencyapp.presentation.ui.BackgroundColor
import com.google.accompanist.flowlayout.FlowRow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()
        .background(BackgroundColor)) {

        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            color = Color.White,
                            modifier = Modifier.weight(8f)
                        )

                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .border(2.dp, BoxColor, RoundedCornerShape(10.dp))
                                .width(180.dp)
                                .height(180.dp)
                                .padding(8.dp)
                        ) {
                            val painter = rememberImagePainter(
                                data = coin.logoUrl,
                                builder = {
                                    crossfade(true)
                                }
                            )

                            Box(
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(180.dp)
                                    .aspectRatio(1f)
                                    .align(Center)
                            ) {
                                Image(
                                    painter = painter,
                                    contentDescription = "coin logo",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }


                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .height(100.dp)
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp))
                                        .border(
                                            2.dp,
                                            BoxColor,
                                            RoundedCornerShape(10.dp)
                                        )
                                ) {
                                    Text(
                                        text = if (coin.hashAlgorithm != null) "Hash Algorithm: ${coin.hashAlgorithm}" else "Hash Algorithm: Not mentioned",
                                        color = Color.White,
                                        modifier = Modifier.padding(10.dp)
                                            .align(Alignment.Center),
                                        style = TextStyle(fontWeight = FontWeight.Bold),
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp))
                                        .border(
                                            2.dp,
                                            BoxColor,
                                            RoundedCornerShape(10.dp)
                                        )
                                ) {
                                    Text(
                                        text = if (coin.startedAt != null) "Released Date: ${
                                            TimeFormatter.intoISTFormat(
                                                coin.startedAt
                                            )
                                        }" else "Released Date: Unavailable",
                                        color = Color.White,
                                        modifier = Modifier.padding(10.dp)
                                            .align(Alignment.Center),
                                        style = TextStyle(fontWeight = FontWeight.Bold),
                                    )
                                }

                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(15.dp))

                    coin.description?.let {
                        Text(
                            text = coin.description,
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }


                    if (coin.tags?.isEmpty() == false) {
                        Text(
                            text = "Tags:",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            coin.tags?.forEach { tag ->
                                CoinTag(tag = tag.name)
                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))
                    }

                    if (coin.team?.isEmpty() == false) {
                        Text(
                            text = "Team members:",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                    }

                }

                coin?.team?.let {
                    items(coin.team) { teamMember ->
                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Divider()

                    }
                }


                coin?.links?.let {
                    item {
                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            text = "Links:",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        coin?.links?.facebook?.get(0)?.let {
                            LinkItem(
                                painter = painterResource(id = R.drawable.facebook),
                                link = coin.links.facebook[0],
                                linkType = "Facebook"
                            )
                        }
                        coin?.links?.reddit?.get(0)?.let {
                            LinkItem(
                                painter = painterResource(id = R.drawable.reddit),
                                link = coin.links.reddit[0],
                                linkType = "Reddit"
                            )
                        }
                        coin?.links?.sourceCode?.get(0)?.let {
                            LinkItem(
                                painter = painterResource(id = R.drawable.github),
                                link = coin.links.sourceCode[0],
                                linkType = "Source Code(GitHub)"
                            )
                        }
                        coin?.links?.youtube?.get(0)?.let {
                            LinkItem(
                                painter = painterResource(id = R.drawable.youtube),
                                link = coin.links.youtube[0],
                                linkType = "YouTube"
                            )
                        }
                        coin?.links?.website?.get(0)?.let {
                            LinkItem(
                                painter = painterResource(id = R.drawable.web),
                                link = coin.links.website[0],
                                linkType = "Official Website"
                            )
                        }
                        coin?.whitePaper?.link?.let {
                            LinkItem(
                                painter = painterResource(id = R.drawable.white_paper),
                                link = coin.whitePaper.link,
                                linkType = "White Paper"
                            )
                        }

                    }
                }


                coin?.links?.explorer?.let {

                    item {
                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            text = "Other Links:",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    items(coin.links.explorer) { link ->
                        LinkItem(
                            painter = painterResource(id = R.drawable.external_link),
                            link = link,
                            linkType = link,
                            modifier = Modifier.size(15.dp)
                        )
                        Divider()
                    }

                }
            }


        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }

    }
}