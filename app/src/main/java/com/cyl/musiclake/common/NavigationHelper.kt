package com.cyl.musiclake.common

import android.annotation.TargetApi
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.util.Pair
import android.view.View
import com.cyl.musiclake.R
import com.cyl.musiclake.data.db.Playlist
import com.cyl.musiclake.db.Artist
import com.cyl.musiclake.player.MusicPlayerService
import com.cyl.musiclake.ui.main.MainActivity
import com.cyl.musiclake.ui.music.download.DownloadFragment
import com.cyl.musiclake.ui.music.local.fragment.AlbumDetailFragment
import com.cyl.musiclake.ui.music.local.fragment.ArtistSongsFragment
import com.cyl.musiclake.ui.music.local.fragment.FolderSongsFragment
import com.cyl.musiclake.ui.music.local.fragment.LocalMusicFragment
import com.cyl.musiclake.ui.music.playlist.LoveFragment
import com.cyl.musiclake.ui.music.playlist.PlaylistDetailFragment
import com.cyl.musiclake.ui.music.playlist.RecentlyFragment
import com.cyl.musiclake.ui.music.playqueue.PlayQueueFragment
import java.io.File

/**
 * Created by yonglong on 2016/12/24.
 * 导航工具类
 */

object NavigationHelper {

    /**
     * 跳转到专辑
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun navigateToAlbum(context: Activity, albumID: String, title: String, transitionViews: Pair<View, String>?) {

        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment

        if (transitionViews != null) {
            val changeImage = TransitionInflater.from(context).inflateTransition(R.transition.image_transform)
            transaction.addSharedElement(transitionViews.first, transitionViews.second)
            fragment = AlbumDetailFragment.newInstance(albumID, title, transitionViews.second)
            fragment.setSharedElementEnterTransition(changeImage)
        } else {
            transaction.setCustomAnimations(R.anim.activity_fade_in,
                    R.anim.activity_fade_out, R.anim.activity_fade_in, R.anim.activity_fade_out)
            fragment = AlbumDetailFragment.newInstance(albumID, title, null)
        }
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(title).commit()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun navigateToArtist(context: Activity, artistID: String, title: String, transitionViews: Pair<View, String>?) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment

        if (transitionViews != null) {
            val changeImage = TransitionInflater.from(context).inflateTransition(R.transition.image_transform)
            transaction.addSharedElement(transitionViews.first, transitionViews.second)
            fragment = ArtistSongsFragment.newInstance(artistID, title, transitionViews.second)
            fragment.setSharedElementEnterTransition(changeImage)
        } else {
            transaction.setCustomAnimations(R.anim.activity_fade_in,
                    R.anim.activity_fade_out, R.anim.activity_fade_in, R.anim.activity_fade_out)
            fragment = ArtistSongsFragment.newInstance(artistID, title, null)
        }
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(title).commit()
    }


    fun navigateToLocalMusic(context: Activity, transitionViews: Pair<View, String>?) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment

        if (transitionViews != null) {
            transaction.addSharedElement(transitionViews.first, transitionViews.second)
            fragment = LocalMusicFragment.newInstance("local")
        } else {
            //            transaction.setCustomAnimations(R.anim.activity_fade_in,
            //                    R.anim.activity_fade_out, R.anim.activity_fade_in, R.anim.activity_fade_out);
            fragment = LocalMusicFragment.newInstance("local")
        }
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.getTag()).commit()
    }

    fun navigateToFolderSongs(context: Activity, path: String) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment

        fragment = FolderSongsFragment.newInstance(path)
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.getTag()).commit()
    }

    fun navigateRecentlyMusic(context: Activity) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment
        fragment = RecentlyFragment.newInstance()
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.getTag()).commit()
    }

    fun navigatePlayQueue(context: Activity) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment = PlayQueueFragment.newInstance()
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.tag).commit()
    }

    fun navigateToLoveMusic(context: Activity, transitionViews: Pair<View, String>) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment

        fragment = LoveFragment.newInstance()
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.getTag()).commit()
    }

    fun navigateToDownload(context: Activity, transitionViews: Pair<View, String>?) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment

        if (transitionViews != null) {
            transaction.addSharedElement(transitionViews.first, transitionViews.second)
            fragment = DownloadFragment.newInstance()
        } else {
            //            transaction.setCustomAnimations(R.anim.activity_fade_in,
            //                    R.anim.activity_fade_out, R.anim.activity_fade_in, R.anim.activity_fade_out);
            fragment = DownloadFragment.newInstance()
        }
        //        transaction.hide(((AppCompatActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container));
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.getTag()).commit()
    }

    fun navigateToPlaylist(context: Activity, playlist: Playlist, transitionViews: Pair<View, String>?) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment

//        if (transitionViews != null) {
//            val changeImage = TransitionInflater.from(context).inflateTransition(R.transition.image_transform)
//            transaction.addSharedElement(transitionViews.first, transitionViews.second)
//            fragment = PlaylistDetailFragment.newInstance(playlist, true, transitionViews.second)
//            fragment.setSharedElementEnterTransition(changeImage)
//
//        } else {
//            transaction.setCustomAnimations(R.anim.activity_fade_in,
//                    R.anim.activity_fade_out, R.anim.activity_fade_in, R.anim.activity_fade_out)
        fragment = PlaylistDetailFragment.newInstance(playlist, false, null)
//        }
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.getTag()).commit()
    }

    fun navigateToPlaylist(context: Activity, artist: Artist) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val fragment: Fragment
        fragment = PlaylistDetailFragment.newInstance(artist)
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.getTag()).commit()
    }


    fun navigateFragment(context: Activity, fragment: Fragment) {
        val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        transaction.hide(context.supportFragmentManager.findFragmentById(R.id.fragment_container))
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.tag).commit()
    }


    fun getNowPlayingIntent(context: Context): Intent {
        val intent = Intent(context, MainActivity::class.java)
        intent.action = Constants.DEAULT_NOTIFICATION
        return intent
    }

    fun getUpdateWidgetIntent(context: Context): Intent {
        return Intent("com.cyl.music_lake.appwidget_update")
    }

    fun getLyricIntent(context: Context): Intent {
        val intent = Intent(MusicPlayerService.ACTION_LYRIC)
        intent.component = ComponentName(context, MusicPlayerService::class.java)
        return intent
    }


    /**
     * 扫描文件夹
     *
     * @param ctx
     * @param filePath
     */
    fun scanFileAsync(ctx: Context, filePath: String) {
        val scanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        scanIntent.data = Uri.fromFile(File(filePath))
        ctx.sendBroadcast(scanIntent)
    }
}
