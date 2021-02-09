package org.devio.`as`.proj.hi_jetpack

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.cl.loglib.LogConfig
import com.cl.loglib.LogManager
import com.cl.loglib.LogType
import com.cl.loglib.Printer.ViewPrinter
import com.cl.loglib.iLog
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.devio.`as`.proj.hi_jetpack.navigation.NavUtil


class MainActivity : AppCompatActivity() {


    //    var navView: BottomNavigationView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPrinter = ViewPrinter(this)
//        printLog()
        viewPrinter!!.getViewProvider().showFloatingView()
        // printLog()
//        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(
                R.id.nav_view
        )

        //寻找出路由控制器对象,它是我们路由跳转的唯一入口
        val navController: NavController = findNavController(
                R.id.nav_host_fragment
        )
        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        NavUtil.builderNavGraph(
                this, hostFragment!!.childFragmentManager,
                navController,
                R.id.nav_host_fragment
        )

        NavUtil.builderBottomBar(
                navView
        )

        navView.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.itemId)
            true
        }
        //将navController和BottomNavigationView绑定，形成联动效果
        //navView.setupWithNavController(navController)


//        navController!!.navigate(R.id.navigation_notifications)
//        navController!!.navigate(R.id.navigation_notifications, Bundle.EMPTY)
//        navController!!.navigate(Uri.parse("www.imooc.com"))
//
//        navController!!.navigateUp()
//        navController!!.popBackStack(R.id.navigation_dashboard,false)


        ActivityManager.instance.addFrontBackCallback(object : ActivityManager.FrontBackCallback {
            override fun onChanged(front: Boolean) {
                Toast.makeText(applicationContext, "当前处于：${front}", Toast.LENGTH_SHORT).show();
            }
        })

        Log.e("fragment", "MainActivity,onCreate")
    }

    override fun onResume() {
        super.onResume()
        iLog.d("onResume" + nav_view.width.toString() + "---" + nav_view.height.toString());
        nav_view.post {
            iLog.d("onResume:post" + nav_view.width.toString() + "---" + nav_view.height.toString());
        }
        nav_view.viewTreeObserver.addOnGlobalLayoutListener {
            iLog.d("onResume:viewTreeObserver" + nav_view.width.toString() + "---" + nav_view.height.toString());
        }

    }

    //    问号?，表示该变量是Nullable，不加表示该变量不可为null
    private var viewPrinter: ViewPrinter? = null
    private fun printLog() {
        LogManager.getInstance().addPointer(viewPrinter)
        iLog.log(object : LogConfig() {
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, LogType.E, "DemoApplication", "55555")
        iLog.a("9900")
    }
}