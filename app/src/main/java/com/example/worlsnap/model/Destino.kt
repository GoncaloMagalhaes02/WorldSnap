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
            "https://plus.unsplash.com/premium_photo-1666432045848-3fdbb2c74531?q=80&w=1332&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1573843981267-be1999ff37cd?auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1586495985096-787fb4a54ac0?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        videoUrl = "android.resource://com.example.worlsnap/raw/maldivas"
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
            "https://plus.unsplash.com/premium_photo-1661964149725-fbf14eabd38c?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1613395877344-13d4a8e0d49e?q=80&w=735&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://plus.unsplash.com/premium_photo-1661963145672-a2bd28eba0fb?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        videoUrl = "android.resource://com.example.worlsnap/raw/santorini"
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
            "https://plus.unsplash.com/premium_photo-1661914240950-b0124f20a5c1?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1536098561742-ca998e48cbcc?auto=format&fit=crop&w=800&q=80"
        ),
        videoUrl = "android.resource://com.example.worlsnap/raw/toquio"
    ),
    Destino(
        id = "algarve",
        nome = "Algarve",
        pais = "Portugal, Europa",
        categoria = Categoria.PRAIAS,
        descricao = "A região mais a sul de Portugal, famosa pelas praias douradas, falésias de calcário e águas azuis. Um dos destinos turísticos mais premiados da Europa.",
        curiosidades = "O Algarve tem mais de 300 dias de sol por ano, tornando-o um dos destinos mais ensolarados da Europa. " +
                "A Praia da Marinha foi considerada uma das dez praias mais bonitas da Europa. " +
                "A região é famosa pelas cataplanas de marisco, um prato típico cozinhado numa panela de cobre em forma de amêijoa.",
        fotos = listOf(
            "https://plus.unsplash.com/premium_photo-1661957387235-3bc814072fb3?q=80&w=1171&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1566988148642-343730e135bf?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1655987271109-1fb3c1c6d71d?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        videoUrl = "android.resource://com.example.worlsnap/raw/algarve"
    ),

    Destino(
        id = "paris",
        nome = "Paris",
        pais = "França, Europa",
        categoria = Categoria.CIDADES,
        descricao = "A capital francesa, conhecida como a Cidade Luz. Famosa pela Torre Eiffel, pelo Museu do Louvre e pela sua arquitetura romântica às margens do Rio Sena.",
        curiosidades = "Paris recebe cerca de 30 milhões de turistas por ano, tornando-a a cidade mais visitada do mundo. " +
                "A Torre Eiffel foi construída em 1889 para a Exposição Universal e era para ser demolida 20 anos depois. " +
                "O Museu do Louvre é o maior museu de arte do mundo, com mais de 35 mil obras expostas, incluindo a Mona Lisa.",
        fotos = listOf(
            "https://plus.unsplash.com/premium_photo-1661919210043-fd847a58522d?q=80&w=1171&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1499856871958-5b9627545d1a?q=80&w=1120&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1550340499-a6c60fc8287c?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        videoUrl = "android.resource://com.example.worlsnap/raw/paris"
    ),

    Destino(
        id = "roma",
        nome = "Roma",
        pais = "Itália, Europa",
        categoria = Categoria.GASTRONOMIA,
        descricao = "A capital italiana e berço da gastronomia mediterrânica. De la pasta ao gelato, Roma oferece uma experiência culinária única com mais de 2000 anos de história.",
        curiosidades = "Roma tem mais de 900 igrejas e é a cidade com maior concentração de património histórico do mundo. " +
                "A pasta alla carbonara é originária de Roma e na receita tradicional não leva natas — apenas ovos, queijo pecorino, guanciale e pimenta preta. " +
                "O gelato italiano tem menos gordura e ar do que o gelado americano, o que lhe dá uma textura mais densa e sabor mais intenso.",
        fotos = listOf(
            "https://images.unsplash.com/photo-1531572753322-ad063cecc140?q=80&w=1176&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1529154036614-a60975f5c760?q=80&w=1176&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1552832230-c0197dd311b5?q=80&w=1096&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        videoUrl = "android.resource://com.example.worlsnap/raw/rome"
    )

)