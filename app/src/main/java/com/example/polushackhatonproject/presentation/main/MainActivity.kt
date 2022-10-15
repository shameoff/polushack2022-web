package com.example.polushackhatonproject.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.databinding.ActivityMainBinding
import com.example.polushackhatonproject.presentation.main.fragment.MapFragment
import com.example.polushackhatonproject.presentation.main.fragment.ProfileFragment
import com.example.polushackhatonproject.presentation.main.fragment.TaskFragment
import com.example.polushackhatonproject.presentation.main.history.HistoryFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey(TaskFragment.MAPS_API_KEY)
        MapKitFactory.initialize(this)

        setContentView(binding.root)
        onTabSelect()
    }

    private fun onTabSelect() {
        binding.tabLayout.getTabAt(2)?.select()

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        replaceHistoryFragment()
                        binding.pageName.text = resources.getString(R.string.history)
                    }
                    1 -> {
                        replaceMapFragment()
                        binding.pageName.text = resources.getString(R.string.task)
                    }
                    2 -> {
                        replaceTaskFragment()
                        binding.pageName.text = resources.getString(R.string.map)
                    }
                    3 -> {
                        replaceProfileFragment()
                        binding.pageName.text = resources.getString(R.string.profile)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun replaceHistoryFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<HistoryFragment>(R.id.fragmentContainerView)
        }
    }

    private fun replaceProfileFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ProfileFragment>(R.id.fragmentContainerView)
        }
    }

    private fun replaceTaskFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TaskFragment>(R.id.fragmentContainerView)
        }
    }

    private fun replaceMapFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MapFragment>(R.id.fragmentContainerView)
        }
    }
}
