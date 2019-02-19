package si.inova.zimskasola

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.zimskasola.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main2.*






private const val NUM_PAGES = 3

class MyFragmentActivity: FragmentActivity() {
    private lateinit var mPager: ViewPager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        Log.e("TEEEEEEEST", "Position: ${item.itemId}")
        when (item.itemId) {
            com.example.zimskasola.R.id.navigation_home -> {
                mPager.currentItem = 0
                return@OnNavigationItemSelectedListener true

            }
            com.example.zimskasola.R.id.navigation_dashboard -> {
                mPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            com.example.zimskasola.R.id.navigation_notifications -> {
                mPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_holder)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mPager.addOnPageChangeListener(
            object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    // When swiping between pages, select the
                    // corresponding tab.
                    Log.e("TEEEEEEEST", "Position: ${navigation.menu.getItem(position).itemId}")
                    navigation.setSelectedItemId(navigation.menu.getItem(position).itemId)
                }
            })

    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment {
            return FragmentError()
        }
    }

}



