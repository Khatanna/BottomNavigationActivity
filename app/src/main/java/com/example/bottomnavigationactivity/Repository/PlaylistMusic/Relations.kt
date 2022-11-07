package com.example.bottomnavigationactivity.Repository.PlaylistMusic

import androidx.room.*
import com.example.bottomnavigationactivity.Repository.Music.Music
import com.example.bottomnavigationactivity.Repository.PlayList.PlayList

data class PlaylistWithSongs(
  @Embedded val playlist: PlayList,
  @Relation(
    parentColumn = "playlistId",
    entityColumn = "songId",
    associateBy = Junction(PlaylistMusicCrossRef::class)
  )
  val musics: List<Music>
)

data class SongWithPlaylists(
  @Embedded val music: Music,
  @Relation(
    parentColumn = "songId",
    entityColumn = "playlistId",
    associateBy = Junction(PlaylistMusicCrossRef::class)
  )
  val playlists: List<PlayList>
)