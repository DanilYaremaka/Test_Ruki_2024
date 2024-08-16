package com.example.test_ruki_2024.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.test_ruki_2024.R
import com.example.test_ruki_2024.domain.entity.Cell
import com.example.test_ruki_2024.domain.entity.CellState
import com.example.test_ruki_2024.domain.entity.World

@Composable
fun WorldComponent(
    world: World
) {
    val listState = rememberLazyListState()

    LaunchedEffect(world.cells.size) {
        listState.animateScrollToItem(world.cells.size - 1)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 15.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatText(text = stringResource(R.string.life_count, world.stats.lifeCount))

            StatText(text = stringResource(R.string.killed_count, world.stats.killedCount))
        }

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            items(world.cells) {
                CellItem(cell = it)
            }

        }

        Text(
            text = stringResource(R.string.created_count, world.stats.createdCount),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun StatText(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun CellItem(cell: Cell) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 5.dp)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.DarkGray
        )

    ) {

        Row(
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .size(80.dp)
                    .clip(shape = CircleShape)
                    .background(getBackgroundColor(state = cell.state))
                    .align(Alignment.CenterVertically)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(getImgUrl(cell.state))
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp),
                )
            }
            Column(modifier = Modifier.padding(top = 12.dp)) {
                StateTitle(cell)
                StateSubTitle(cell)
            }
        }
    }
}

private fun getImgUrl(state: CellState): String {
    return when (state) {
        CellState.DEAD -> "https://cdn-icons-png.flaticon.com/128/3529/3529273.png"
        CellState.ALIVE -> "https://cdn-icons-png.flaticon.com/128/599/599698.png"
        CellState.LIFE -> "https://cdn-icons-png.flaticon.com/128/2519/2519735.png"
        CellState.TERMINATED -> "https://cdn-icons-png.flaticon.com/128/1046/1046774.png"
    }
}

@Composable
fun getBackgroundColor(state: CellState): Color {
    return when (state) {
        CellState.DEAD -> MaterialTheme.colorScheme.tertiary
        CellState.ALIVE -> MaterialTheme.colorScheme.onSecondary
        CellState.LIFE -> MaterialTheme.colorScheme.onPrimary
        CellState.TERMINATED -> Color.LightGray
    }
}

@Composable
fun StateTitle(cell: Cell) {
    val text = when (cell.state) {
        CellState.DEAD -> stringResource(R.string.dead)
        CellState.ALIVE -> stringResource(R.string.alive)
        CellState.LIFE -> stringResource(R.string.life)
        CellState.TERMINATED -> stringResource(R.string.terminated)
    }
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable
fun StateSubTitle(cell: Cell) {
    val text = when (cell.state) {
        CellState.DEAD -> stringResource(R.string.dead_descr)
        CellState.ALIVE -> stringResource(R.string.alive_descr)
        CellState.LIFE -> stringResource(R.string.life_descr)
        CellState.TERMINATED -> stringResource(R.string.terminated_descr)
    }
    Text(
        text = text,
        fontSize = 18.sp,
        modifier = Modifier.padding(bottom = 15.dp, top = 4.dp)
    )
}
