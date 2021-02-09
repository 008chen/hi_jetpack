package org.devio.`as`.proj.hi_jetpack

import android.app.Activity
import android.os.Bundle
import java.lang.ref.WeakReference

class ActivityManager private constructor() {

    companion object {
        val instance: ActivityManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED)
        {
            ActivityManager()
        }
    }

    private val activityRefs = ArrayList<WeakReference<Activity>>()
    private val frontbackCallback = ArrayList<FrontBackCallback>()
    private var activityStartCount = 0;
    private var front = true;


    fun init(application: DemoApplication) {
        application.registerActivityLifecycleCallbacks(InnerActivityLifecycleCallback())
    }

    inner class InnerActivityLifecycleCallback : android.app.Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {
//            TODO("Not yet implemented")
        }

        override fun onActivityStarted(activity: Activity) {
            activityStartCount++
            if (!front && activityStartCount > 0) {
                front = true
                onFrontBackChanged(front)
            }
        }


        override fun onActivityDestroyed(activity: Activity) {
            for (activityRef in activityRefs) {
                if (activityRef != null && activityRef.get() == activity) {
                    activityRefs.remove(activityRef)
                    break
                }
            }

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
//            TODO("Not yet implemented")

        }

        override fun onActivityStopped(activity: Activity) {
            activityStartCount--
            if (front && activityStartCount <= 0) {
                front = false
                onFrontBackChanged(front)
            }
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activityRefs.add(WeakReference(activity))
        }

        override fun onActivityResumed(activity: Activity) {
//            TODO("Not yet implemented")
        }

    }


    private fun onFrontBackChanged(front: Boolean) {
        for (callback in frontbackCallback) {
            callback.onChanged(front)
        }
    }

    val topActivity: Activity?
        get() {
            if (activityRefs.size <= 0) {
                return null
            } else {
                return activityRefs[activityRefs.size - 1].get()
            }
            return null
        }


    fun addFrontBackCallback(callback: FrontBackCallback) {
        frontbackCallback.add(callback)
    }

    fun removeCallback(callback: FrontBackCallback) {
        frontbackCallback.remove(callback)
    }


    interface FrontBackCallback {
        fun onChanged(front: Boolean)
    }
}


