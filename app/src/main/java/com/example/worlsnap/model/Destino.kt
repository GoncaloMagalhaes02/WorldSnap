package com.example.worlsnap.model

data class Destino(
    val id: String,
    val nome: String,
    val pais: String,
    val categoria: Categoria,
    val descricao: String,
    val curiosidades: String,
    val fotos: List<String>,
    val videoUrl: String
)

enum class Categoria(val label: String) {
    TODOS("Todos"),
    PRAIAS("Praias"),
    CIDADES("Cidades"),
    GASTRONOMIA("Gastronomia")
}

val destinosExemplo = listOf(

    Destino(
        id = "maldivas",
        nome = "Maldivas",
        pais = "Maldivas, Ásia",
        categoria = Categoria.PRAIAS,
        descricao = "Arquipélago do Oceano Índico com 1192 ilhas de coral, águas cristalinas e recifes vibrantes. Famoso pelos bangalôs sobre a água e pela biodiversidade marinha.",
        curiosidades = "As Maldivas são o país mais baixo do mundo, com uma altitude média de apenas um metro e meio acima do nível do mar, tornando-o um dos territórios mais vulneráveis às alterações climáticas. " +
                "O arquipélago é composto por 26 atóis e mais de 1000 ilhas de coral, das quais apenas cerca de 200 são habitadas. " +
                "As suas águas são lar de mais de mil espécies de peixes, incluindo tubarões-baleia e raias-manta, tornando-as um dos melhores destinos do mundo para mergulho.",
        fotos = listOf(
            "https://images.unsplash.com/photo-1514282401047-0e3c9c2f1cba?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1573843981267-be1999ff37cd?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1512100526747-ee4b1e5b870a?auto=format&fit=crop&w=800&q=80"
        ),
        videoUrl = "https://cdn.pixabay.com/video/2020/06/21/42835-432728729_large.mp4"
    ),

    Destino(
        id = "santorini",
        nome = "Santorini",
        pais = "Grécia, Europa",
        categoria = Categoria.CIDADES,
        descricao = "Ilha vulcânica no Mar Egeu, famosa pelas icónicas casas brancas com cúpulas azuis em Oia e pelos dramáticos pores do sol sobre a caldeira.",
        curiosidades = "Santorini foi formada há cerca de 3600 anos por uma das maiores erupções vulcânicas da história da humanidade, que destruiu a civilização minoica que habitava a ilha. " +
                "As famosas casas brancas e cúpulas azuis que caracterizam a ilha não são apenas estéticas — o branco reflete o calor do sol e o azul das cúpulas representa a bandeira grega. " +
                "A ilha produz o vinho Assyrtiko, cultivado em videiras enroladas em forma de cesto rente ao chão, protegidas do vento forte que varre a ilha.",
        fotos = listOf(
            "https://images.unsplash.com/photo-1570077681-a0c516de7e8b?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1613395877344-13d4a8480f4e?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1602088113235-229c19758e9f?auto=format&fit=crop&w=800&q=80"
        ),
        videoUrl = "https://cdn.pixabay.com/video/2022/08/16/128143-739956329_large.mp4"
    ),

    Destino(
        id = "toquio",
        nome = "Tóquio",
        pais = "Japão, Ásia",
        categoria = Categoria.GASTRONOMIA,
        descricao = "Capital do Japão e capital mundial da gastronomia. Com mais estrelas Michelin do que qualquer outra cidade do mundo, oferece uma experiência culinária única do sushi ao ramen.",
        curiosidades = "Tóquio tem mais de 200 restaurantes com estrelas Michelin, mais do que Paris, Nova Iorque e Londres juntas, tornando-a a capital gastronómica indiscutível do mundo. " +
                "O mercado de peixe de Toyosu é o maior mercado de peixe do mundo, movimentando mais de 700 toneladas de produtos do mar por dia. " +
                "O ramen tem mais de 30 variações regionais no Japão, e Tóquio tem museus inteiros dedicados exclusivamente a este prato.",
        fotos = listOf(
            "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1503899036084-4215592be29f?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1536098561742-ca998e48cbcc?auto=format&fit=crop&w=800&q=80"
        ),
        videoUrl = "https://cdn.pixabay.com/video/2019/09/04/26654-358296538_large.mp4"
    )
)