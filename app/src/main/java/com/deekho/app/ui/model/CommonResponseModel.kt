package com.deekho.app.ui.model

data class AnimeListResponseModel(
    val pagination: Pagination,
    val data: List<Anime>
)

data class AnimeDetailsResponse(
    val data: Anime
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: PaginationItems
)

data class PaginationItems(
    val count: Int,
    val total: Int,
    val per_page: Int
)

data class Anime(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val trailer: Trailer?,
    val approved: Boolean,
    val titles: List<Title>,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val title_synonyms: List<String>,
    val type: String,
    val source: String,
    val episodes: Int?,
    val status: String,
    val airing: Boolean,
    val aired: Aired,
    val duration: String?,
    val rating: String?,
    val score: Double?,
    val scored_by: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val broadcast: Broadcast?,
    val producers: List<Producer>,
    val licensors: List<Producer>,
    val studios: List<Producer>,
    val genres: List<Genre>,
    val explicit_genres: List<Genre>,
    val themes: List<Genre>,
    val demographics: List<Genre>
)

data class Images(
    val jpg: ImageFormat,
    val webp: ImageFormat
)

data class ImageFormat(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

data class Trailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?,
    val images: TrailerImages?
)

data class TrailerImages(
    val image_url: String?,
    val small_image_url: String?,
    val medium_image_url: String?,
    val large_image_url: String?,
    val maximum_image_url: String?
)

data class Title(
    val type: String,
    val title: String
)

data class Aired(
    val from: String?,
    val to: String?,
    val prop: AiredProp,
    val string: String
)

data class AiredProp(
    val from: DateProp,
    val to: DateProp
)

data class DateProp(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

data class Broadcast(
    val day: String?,
    val time: String?,
    val timezone: String?,
    val string: String?
)

data class Producer(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)
