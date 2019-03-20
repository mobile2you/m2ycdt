package br.com.m2yconductorservices.data.remote

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by azul on 29/11/17.
 */
object M2YCDTErrorBus {

    val subject = PublishSubject.create<Throwable>()

    fun getEvents(): Observable<Throwable> {
        return subject
    }

    /**
     * Pass any event down to event listeners.
     */
    fun setEvent(error: Throwable) {
        subject.onNext(error)
    }
}