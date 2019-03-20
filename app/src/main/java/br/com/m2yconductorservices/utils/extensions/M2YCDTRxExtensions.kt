package br.com.m2yconductorservices.utils.extensions

import br.com.m2yconductorservices.data.remote.M2YCDTRetrofitException
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by joaopaulo on 18/10/17.
 */
fun <T> Single<T>.m2yCdtSingleSubscribe(onSuccess: ((t: T) -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        onSuccess?.let { it(t) }
                    }

                    override fun onError(e: Throwable) {
                        if ((e as? M2YCDTRetrofitException)?.response?.raw()?.code() != 401) {
                            onError?.let { it(e) }
                        }
                    }
                })