package br.com.m2yconductorservices.data.remote

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by azul on 29/11/17.
 */
object M2YCDTUserUnauthorizedBus {

    val subject = PublishSubject.create<Any>()

    fun getEvents(): Observable<Any> {
        return subject
    }

    /**
     * Pass any event down to event listeners.
     */
    fun setEvent(error: Any) {
        subject.onNext(error)
    }
}