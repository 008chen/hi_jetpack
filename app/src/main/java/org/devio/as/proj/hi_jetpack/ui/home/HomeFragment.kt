package org.devio.`as`.hi.hirouter.ui.home

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.devio.`as`.hi.nav_annotation.Destination
import org.devio.`as`.proj.hi_jetpack.R

@Destination(pageUrl = "main/tabs/home", asStarter = true)
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        Log.e("fragment", "HomeFragment,onCreateView")
        return root
    }

    override fun onResume() {
        super.onResume()
        Log.e("fragment", "HomeFragment,onResume")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("fragment", "HomeFragment,onAttach1")
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.e("fragment", "HomeFragment,onAttach2")
    }

    override fun onPause() {
        super.onPause()
        Log.e("fragment", "HomeFragment,onPause")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("fragment", "HomeFragment,onActivityResult")
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        return super.onCreateAnimator(transit, enter, nextAnim)
        Log.e("fragment", "HomeFragment,onCreateAnimator")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("fragment", "HomeFragment,onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("fragment", "HomeFragment,onStart")
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Log.e("fragment", "HomeFragment,onDestroyOptionsMenu")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("fragment", "HomeFragment,onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("fragment", "HomeFragment,onDestroyView")
    }

    override fun onStop() {
        super.onStop()
        Log.e("fragment", "HomeFragment,onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("fragment", "HomeFragment,onDestroy")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("fragment", "HomeFragment,onActivityCreated")
    }
}