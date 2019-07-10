/**
 * Copyright 2017 Harish Sridharan
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cooltechworks.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.cooltechworks.sample.adapters.CardAdapter
import com.cooltechworks.sample.utils.BaseUtils
import com.cooltechworks.sample.utils.DemoConfiguration
import com.cooltechworks.views.shimmer.ShimmerRecyclerView

class DemoActivity : AppCompatActivity() {

    private var shimmerRecycler: ShimmerRecyclerView? = null
    private var mAdapter: CardAdapter? = null

    private val type: Int
        get() = intent.getIntExtra(EXTRA_TYPE, BaseUtils.TYPE_LIST)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = type

        val layoutManager: RecyclerView.LayoutManager

        val demoConfiguration = BaseUtils.getDemoConfiguration(type, this)
        setTheme(demoConfiguration!!.styleResource)
        setContentView(demoConfiguration.layoutResource)
        layoutManager = demoConfiguration.layoutManager
        setTitle(demoConfiguration.titleResource)

        shimmerRecycler = findViewById(R.id.shimmer_recycler_view)

        if (demoConfiguration.itemDecoration != null) {
            shimmerRecycler!!.addItemDecoration(demoConfiguration.itemDecoration)
        }

        mAdapter = CardAdapter()
        mAdapter!!.setType(type)

        shimmerRecycler!!.layoutManager = layoutManager
        shimmerRecycler!!.adapter = mAdapter
        shimmerRecycler!!.showShimmerAdapter()

        shimmerRecycler!!.postDelayed({ loadCards() }, 3000)
    }

    private fun loadCards() {
        val type = type

        mAdapter!!.setCards(BaseUtils.getCards(resources, type))
        shimmerRecycler!!.hideShimmerAdapter()
    }

    companion object {

        val EXTRA_TYPE = "type"
    }
}
