package com.example.worlsnap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.worlsnap.model.Categoria
import com.example.worlsnap.model.Destino
import com.example.worlsnap.model.destinosExemplo
import com.example.worlsnap.ui.theme.DarkNavy
import com.example.worlsnap.ui.theme.LightGray
import com.example.worlsnap.ui.theme.PrimaryBlue

private val categoriasFiltro = listOf(
    Categoria.TODOS,
    Categoria.PRAIAS,
    Categoria.CIDADES,
    Categoria.GASTRONOMIA
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onDestinoClick: (String) -> Unit) {
    var indexCategoria by remember { mutableStateOf(0) }
    var pesquisa by remember { mutableStateOf("") }
    val favoritos = remember { mutableStateListOf<String>() }

    val categoriaAtiva = categoriasFiltro[indexCategoria]

    val destinosFiltrados = destinosExemplo.filter { destino ->
        val matchCategoria = categoriaAtiva == Categoria.TODOS || destino.categoria == categoriaAtiva
        val matchPesquisa = pesquisa.isEmpty() ||
                destino.nome.contains(pesquisa, ignoreCase = true) ||
                destino.pais.contains(pesquisa, ignoreCase = true)
        matchCategoria && matchPesquisa
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // ── Header ───────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkNavy)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = "WorldSnap",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Descobre o mundo",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            TextField(
                value = pesquisa,
                onValueChange = { pesquisa = it },
                placeholder = { Text("Pesquisar destino...", fontSize = 14.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White.copy(alpha = 0.12f),
                    focusedContainerColor = Color.White.copy(alpha = 0.18f),
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedPlaceholderColor = Color.White.copy(alpha = 0.5f),
                    focusedPlaceholderColor = Color.White.copy(alpha = 0.5f),
                    unfocusedLeadingIconColor = Color.White.copy(alpha = 0.5f),
                    focusedLeadingIconColor = Color.White.copy(alpha = 0.8f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )
        }

        // ── Filtros ──────────────────────────────────────────────
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGray)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categoriasFiltro) { categoria ->
                val isActive = categoriaAtiva == categoria
                FilterChip(
                    selected = isActive,
                    onClick = { indexCategoria = categoriasFiltro.indexOf(categoria) },
                    label = {
                        Text(
                            text = categoria.label,
                            fontSize = 12.sp,
                            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryBlue,
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                        labelColor = Color.Gray
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isActive,
                        borderColor = Color(0xFFDDDDDD),
                        selectedBorderColor = PrimaryBlue
                    )
                )
            }
        }

        // ── Lista de destinos ────────────────────────────────────
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (destinosFiltrados.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxWidth().padding(top = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Nenhum destino encontrado", color = Color.Gray, fontSize = 14.sp)
                    }
                }
            } else {
                items(destinosFiltrados) { destino ->
                    DestinoCard(
                        destino = destino,
                        isFavorito = destino.id in favoritos,
                        onFavoritoClick = {
                            if (destino.id in favoritos) favoritos.remove(destino.id)
                            else favoritos.add(destino.id)
                        },
                        onClick = { onDestinoClick(destino.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun DestinoCard(
    destino: Destino,
    isFavorito: Boolean,
    onFavoritoClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = destino.fotos.firstOrNull(),
                contentDescription = destino.nome,
                modifier = Modifier.width(90.dp).height(80.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Text(text = destino.nome, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1A1A2E))
                Text(text = destino.pais, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 2.dp, bottom = 6.dp))
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = categoriaColor(destino.categoria).copy(alpha = 0.15f)
                ) {
                    Text(
                        text = destino.categoria.label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = categoriaColor(destino.categoria),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
            IconButton(
                onClick = onFavoritoClick,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (isFavorito) Color(0xFFE24B4A) else Color.LightGray
                )
            }
        }
    }
}

fun categoriaColor(categoria: Categoria): Color = when (categoria) {
    Categoria.PRAIAS      -> Color(0xFF185FA5)
    Categoria.CIDADES     -> Color(0xFF534AB7)
    Categoria.GASTRONOMIA -> Color(0xFF854F0B)
    Categoria.TODOS       -> Color.Gray
}