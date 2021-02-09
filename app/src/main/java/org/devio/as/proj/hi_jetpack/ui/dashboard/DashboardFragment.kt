package org.devio.`as`.hi.hirouter.ui.dashboard

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

@Destination(pageUrl = "main/tabs/dashboard", asStarter = false)
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        Log.e("fragment", "DashboardFragment,onCreateView")
        return root
    }
    override fun onResume() {
        super.onResume()
        Log.e("fragment", "DashboardFragment,onResume")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("fragment", "DashboardFragment,onAttach1")
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.e("fragment", "DashboardFragment,onAttach2")
    }

    override fun onPause() {
        super.onPause()
        Log.e("fragment", "DashboardFragment,onPause")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("fragment", "HomeFragment,onActivityResult")
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        return super.onCreateAnimator(transit, enter, nextAnim)
        Log.e("fragment", "DashboardFragment,onCreateAnimator")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("fragment", "DashboardFragment,onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("fragment", "DashboardFragment,onStart")
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Log.e("fragment", "DashboardFragment,onDestroyOptionsMenu")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("fragment", "DashboardFragment,onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("fragment", "DashboardFragment,onDestroyView")
    }

    override fun onStop() {
        super.onStop()
        Log.e("fragment", "DashboardFragment,onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("fragment", "DashboardFragment,onDestroy")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("fragment", "DashboardFragment,onActivityCreated")
    }
}