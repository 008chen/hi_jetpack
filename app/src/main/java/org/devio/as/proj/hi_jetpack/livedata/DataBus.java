package org.devio.as.proj.hi_jetpack.livedata;


import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.ConcurrentHashMap;

public class DataBus {

    private ConcurrentHashMap<String, StickyLiveData> eventMap = new ConcurrentHashMap<>();

    public <T> StickyLiveData<T> with(String eventName) {
        //基于事件名称 订阅、分发消息，
        //由于 一个 livedata 只能发送 一种数据类型
        //所以 不同的event事件，需要使用不同的livedata实例 去分发
        StickyLiveData<T> liveData = eventMap.get(eventName);
        if (liveData == null) {
            liveData = new StickyLiveData<T>(eventName);
            eventMap.put(eventName, liveData);
        }
        return liveData;
    }

    class StickyLiveData<T> extends LiveData<T> {
        String eventName;
        T mStickyData;
        int mVersion = 0;

        public StickyLiveData(String eventName) {
            super();
            this.eventName = eventName;
        }


        public void setStickyData(T stickyData) {
            mStickyData = stickyData;
            setValue(stickyData);
            //就是在主线程去发送数据
        }

        public void postStickyData(T stickyData) {
            mStickyData = stickyData;
            postValue(stickyData);
            //不受线程的限制
        }


        @Override
        protected void postValue(T value) {
            mVersion++;
            super.postValue(value);
        }

        @Override
        protected void setValue(T value) {
            mVersion++;
            super.setValue(value);
        }

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            observeSticky(owner, false, observer);
        }

        public void observeSticky(@NonNull LifecycleOwner owner, boolean sticky, @NonNull Observer<? super T> observer) {
            //允许指定注册的观察则 是否需要关心黏性事件
            //sticky =true, 如果之前存在已经发送的数据，那么这个observer会受到之前的黏性事件消息
            owner.getLifecycle().addObserver((LifecycleEventObserver) (source, event) -> {
                        //监听 宿主 发生销毁事件，主动把livedata 移除掉。
                        if (event == Lifecycle.Event.ON_DESTROY) {
                            eventMap.remove(eventName);
                        }
                    }
            );
            super.observe(owner, new StickyObserver(this, sticky, observer));
        }
    }

    class StickyObserver<T> implements Observer<T> {

        StickyLiveData<T> stickyLiveData;
        boolean sticky;
        Observer<T> observer;
        int lastVersion;

        public StickyObserver(StickyLiveData<T> stickyLiveData, boolean sticky, Observer<T> observer) {
            this.stickyLiveData = stickyLiveData;
            this.sticky = sticky;
            this.observer = observer;

            //lastVersion 和livedata的version 对齐的原因，就是为控制黏性事件的分发。
            //sticky 不等于true , 只能接收到注册之后发送的消息，如果要接收黏性事件，则sticky需要传递为true
            lastVersion = stickyLiveData.mVersion;
        }

        @Override
        public void onChanged(T t) {
            if (lastVersion >= stickyLiveData.mVersion) {
                //就说明stickyLiveData  没有更新的数据需要发送。
                if (sticky && stickyLiveData.mStickyData != null) {
                    observer.onChanged(stickyLiveData.mStickyData);
                }
                return;
            }
            lastVersion = stickyLiveData.mVersion;
            observer.onChanged(t);
        }
    }
}
