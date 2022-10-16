package com.app.ichsanulalifwan.moviecalatogtest.utils

import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity

object DataDummy {

    fun generateDummyDetailMovie(
        movie: MovieNowPlayingEntity,
        isWishlist: Boolean
    ): MovieDetailWithGenre {
        movie.isWishlist = isWishlist
        return MovieDetailWithGenre(movie, generateDummyMovieGenres(movie.movieId))
    }

    fun generateLocalDummyDetailMovies(): List<MovieNowPlayingEntity> {

        val detailMovies = ArrayList<MovieNowPlayingEntity>()

        detailMovies.add(
            MovieNowPlayingEntity(
                460465,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                110,
                false
            )
        )

        detailMovies.add(
            MovieNowPlayingEntity(
                567189,
                "Tom Clancy's Without Remorse",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "2021-04-29",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                109,
                false
            )
        )

        detailMovies.add(
            MovieNowPlayingEntity(
                527774,
                "Raya and the Last Dragon",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "2021-03-03",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                107,
                false
            )
        )
        return detailMovies
    }

    fun generateDummyDetailTv(tv: TvShowAiringEntity, isWishlist: Boolean): TvDetailWithGenre {
        tv.isWishlist = isWishlist
        return TvDetailWithGenre(tv, generateDummyTvGenres(tv.tvId))
    }

    fun generateLocalDummyDetailTv(): List<TvShowAiringEntity> {

        val detailTv = ArrayList<TvShowAiringEntity>()

        detailTv.add(
            TvShowAiringEntity(
                60735,
                "The Flash",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                146,
                7,
                false
            )
        )

        detailTv.add(
            TvShowAiringEntity(
                62688,
                "Supergirl",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zsaiq8ZclPuneuN7loLEbsh1ANJ.jpg",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                118,
                6,
                false
            )
        )

        detailTv.add(
            TvShowAiringEntity(
                75006,
                "The Umbrella Academy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                20,
                2,
                false
            )
        )
        return detailTv
    }

    private fun generateDummyMovieGenres(id: Int): List<MovieGenreEntity> {

        val genreItem = ArrayList<MovieGenreEntity>()

        genreItem.add(
            MovieGenreEntity(
                id,
                28,
                "Action"
            )
        )

        genreItem.add(
            MovieGenreEntity(
                id,
                12,
                "Adventure"
            )
        )

        genreItem.add(
            MovieGenreEntity(
                id,
                53,
                "Thriller"
            )
        )

        genreItem.add(
            MovieGenreEntity(
                id,
                10752,
                "War"
            )
        )
        return genreItem
    }

    private fun generateDummyTvGenres(id: Int): List<TvGenreEntity> {

        val genreItem = ArrayList<TvGenreEntity>()

        genreItem.add(
            TvGenreEntity(
                id,
                28,
                "Action"
            )
        )

        genreItem.add(
            TvGenreEntity(
                id,
                12,
                "Adventure"
            )
        )

        genreItem.add(
            TvGenreEntity(
                id,
                53,
                "Thriller"
            )
        )

        genreItem.add(
            TvGenreEntity(
                id,
                10752,
                "War"
            )
        )
        return genreItem
    }
}