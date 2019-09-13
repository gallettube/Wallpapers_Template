package com.wallpaper

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var image_resources = intArrayOf(
            R.drawable.uno,
            R.drawable.dos,
            R.drawable.tres,
            R.drawable.cuatro,
            R.drawable.cinco,
            R.drawable.seis,
            R.drawable.siete
        )

        val adapter = customAdapter(this, image_resources)
        vp_wallpaper.setAdapter(adapter);

        set_wallpaper.setOnClickListener {
            val wallpapermanager: WallpaperManager = WallpaperManager.getInstance(this)
            wallpapermanager.setResource(image_resources.get(vp_wallpaper.currentItem))
        }
    }




    inner class customAdapter(private val ctx: Context, internal var image_resources: IntArray) :
        PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null

        override fun getCount(): Int {
            return image_resources.size
        }

        override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
            return arg0 === arg1
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val item_view = layoutInflater!!.inflate(R.layout.view_pager_layout, container, false)
            val imageView = item_view.findViewById<ImageView>(R.id.image_view)
            imageView.setImageResource(image_resources[position])
            container.addView(item_view)
            return item_view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as LinearLayout)

        }
    }

}
