package com.example.worlsnap.screens


import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.worlsnap.model.destinosExemplo
import com.example.worlsnap.ui.theme.ColorAmber
import com.example.worlsnap.ui.theme.ColorGreen
import com.example.worlsnap.ui.theme.DarkNavy
import com.example.worlsnap.ui.theme.LightBlue
import com.example.worlsnap.ui.theme.LightGray
import com.example.worlsnap.ui.theme.PrimaryBlue
import java.util.Locale

enum class AudioEstado { PARADO, A_TOCAR, PAUSADO }

@Composable
fun DetailScreen(
    destinoId: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val destino = destinosExemplo.find { it.id == destinoId } ?: return

    var fotoAtual by remember { mutableStateOf(0) }
    var audioEstado by remember { mutableStateOf(AudioEstado.PARADO) }

    // ── ExoPlayer ────────────────────────────────────────────────
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(destino.videoUrl))
            prepare()
        }
    }
    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    // ── Text-to-Speech ───────────────────────────────────────────
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var ttsDisponivel by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        val engine = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) ttsDisponivel = true
        }
        engine.language = if (engine.isLanguageAvailable(Locale("pt", "PT")) >= TextToSpeech.LANG_AVAILABLE)
            Locale("pt", "PT") else Locale("pt", "BR")
        engine.setSpeechRate(0.9f)
        tts = engine
        onDispose {
            engine.stop()
            engine.shutdown()
        }
    }

    fun toggleAudio() {
        val engine = tts ?: return
        when (audioEstado) {
            AudioEstado.PARADO, AudioEstado.PAUSADO -> {
                engine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(id: String?) { audioEstado = AudioEstado.A_TOCAR }
                    override fun onDone(id: String?)  { audioEstado = AudioEstado.PARADO  }
                    override fun onError(id: String?) { audioEstado = AudioEstado.PARADO  }
                })
                engine.speak(destino.curiosidades, TextToSpeech.QUEUE_FLUSH, null, "tts_${destino.id}")
                audioEstado = AudioEstado.A_TOCAR
            }
            AudioEstado.A_TOCAR -> {
                engine.stop()
                audioEstado = AudioEstado.PAUSADO
            }
        }
    }

    // Para tudo ao sair
    DisposableEffect(Unit) {
        onDispose {
            tts?.stop()
            exoPlayer.pause()
        }
    }

    // ── UI ───────────────────────────────────────────────────────
    Column(modifier = Modifier.fillMaxSize()) {

        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkNavy)
                .padding(horizontal = 4.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                }
                Column(modifier = Modifier.padding(start = 4.dp)) {
                    Text(text = destino.nome, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(
                        text = "${destino.pais}  •  ${destino.categoria.label}",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray)
                .verticalScroll(rememberScrollState())
        ) {

            // ── Galeria de fotos ─────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                AsyncImage(
                    model = destino.fotos.getOrNull(fotoAtual),
                    contentDescription = destino.nome,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Seta esquerda
                if (fotoAtual > 0) {
                    Surface(
                        modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp),
                        shape = RoundedCornerShape(50),
                        color = Color.Black.copy(alpha = 0.4f)
                    ) {
                        Text("‹", color = Color.White, fontSize = 22.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                    }
                }

                // Seta direita
                if (fotoAtual < destino.fotos.lastIndex) {
                    Surface(
                        modifier = Modifier.align(Alignment.CenterEnd).padding(end = 8.dp),
                        shape = RoundedCornerShape(50),
                        color = Color.Black.copy(alpha = 0.4f)
                    ) {
                        Text("›", color = Color.White, fontSize = 22.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                    }
                }

                // Dots indicadores
                Row(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    destino.fotos.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier
                                .size(width = if (index == fotoAtual) 18.dp else 6.dp, height = 6.dp)
                                .background(
                                    color = if (index == fotoAtual) Color.White else Color.White.copy(alpha = 0.4f),
                                    shape = RoundedCornerShape(3.dp)
                                )
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {

                // ── Descrição ────────────────────────────────────
                Text(text = "Sobre o destino", fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                    color = Color.Gray, modifier = Modifier.padding(bottom = 6.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Text(text = destino.descricao, fontSize = 14.sp, color = Color(0xFF444444),
                        lineHeight = 21.sp, modifier = Modifier.padding(12.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ── Multimédia ───────────────────────────────────
                Text(text = "Multimédia", fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                    color = Color.Gray, modifier = Modifier.padding(bottom = 6.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                    // Vídeo
                    Card(
                        modifier = Modifier.weight(1f).height(120.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkNavy),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        AndroidView(
                            factory = { ctx ->
                                PlayerView(ctx).apply {
                                    player = exoPlayer
                                    useController = true
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // Áudio TTS
                    Card(
                        modifier = Modifier.weight(1f).height(120.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Curiosidades", fontSize = 12.sp, color = Color.Gray)
                            Button(
                                onClick = { toggleAudio() },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                enabled = ttsDisponivel,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = when (audioEstado) {
                                        AudioEstado.A_TOCAR -> ColorGreen
                                        AudioEstado.PAUSADO -> ColorAmber
                                        AudioEstado.PARADO  -> PrimaryBlue
                                    }
                                )
                            ) {
                                Icon(
                                    imageVector = if (audioEstado == AudioEstado.A_TOCAR)
                                        Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = when (audioEstado) {
                                        AudioEstado.PARADO  -> "Ouvir"
                                        AudioEstado.A_TOCAR -> "Pausar"
                                        AudioEstado.PAUSADO -> "Retomar"
                                    },
                                    fontSize = 12.sp
                                )
                            }
                            Text(
                                text = when (audioEstado) {
                                    AudioEstado.PARADO  -> "narração em português"
                                    AudioEstado.A_TOCAR -> "a reproduzir..."
                                    AudioEstado.PAUSADO -> "pausado"
                                },
                                fontSize = 10.sp,
                                color = Color.LightGray,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}