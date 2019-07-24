package br.com.zapiti.foodtime


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zapiti.foodtime.adapters.RecyclerAdapter
import br.com.zapiti.foodtime.models.NicePlace
import br.com.zapiti.foodtime.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {


    private var mMainActivityViewModel: MainActivityViewModel? = null
    private var mNicePlace:ArrayList<NicePlace> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        fab?.setOnClickListener {
            mMainActivityViewModel?.addNewValue(
                NicePlace(
                    "https://i.imgur.com/ZcLLrkY.jpg",
                    "Washington"
                )
            )
        }

        initRecyclerView()
    }

    private fun initData() {
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mMainActivityViewModel?.init()

        mMainActivityViewModel?.nicePlaces?.observe(this, Observer {
            mNicePlace.clear()
            mNicePlace.addAll(it)
            recycler_view?.adapter?.notifyDataSetChanged()
        })

        mMainActivityViewModel?.isUpdating?.observe(this, Observer { aBoolean ->
            if (aBoolean) {
                showProgressBar()
            } else {
                hideProgressBar()
                recycler_view?.smoothScrollToPosition(mNicePlace.size - 1)
            }
        })



    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        recycler_view?.layoutManager = linearLayoutManager
        mNicePlace.clear()
        mNicePlace.addAll(mMainActivityViewModel?.nicePlaces?.value ?:ArrayList())
        recycler_view?.adapter =  RecyclerAdapter(this, mNicePlace)
    }

    private fun showProgressBar() {
        progress_bar?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar?.visibility = View.GONE
    }
}




















