package com.bangkumist.bintang.footballapp.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.bangkumist.bintang.footballapp.activity.DetailMatchActivity
import org.jetbrains.anko.support.v4.startActivity

import com.bangkumist.bintang.footballapp.R
import com.bangkumist.bintang.footballapp.adapter.FavoriteAdapter
import com.bangkumist.bintang.footballapp.adapter.ViewPagerAdapter
import com.bangkumist.bintang.footballapp.db.Favorite
import com.bangkumist.bintang.footballapp.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh


class FavoriteFragment : Fragment() {

    private lateinit var viewPagerr: ViewPager
    private lateinit var tabss: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorite, container, false)

        viewPagerr = view.findViewById(R.id.vpFavorite)
        tabss = view.findViewById(R.id.tabFavorite)
        setupViewPager(viewPagerr)
        setHasOptionsMenu(true)

        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(FavoriteMatchFragment(), "Favorite Match")
        adapter.populateFragment(FavoriteTeamFragment(), "Favorite Team")
        viewPager.adapter = adapter
        tabss.setupWithViewPager(viewPagerr)
    }


}
