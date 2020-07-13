package com.veles.authorizationflows.data.network.factory

import com.veles.authorizationflows.data.network.error.NetworkException
import com.veles.authorizationflows.data.network.error.NetworkException.Companion.httpError
import com.veles.authorizationflows.data.network.error.NetworkException.Companion.networkError
import com.veles.authorizationflows.data.network.error.NetworkException.Companion.unexpectedError
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

class RxJava2CallAdapterWrapper<R> internal constructor(
    private val retrofit: Retrofit,
    private val rObjectCallAdapter: CallAdapter<R, Any>
) : CallAdapter<R, Any> {
    override fun responseType(): Type {
        return rObjectCallAdapter.responseType()
    }

    override fun adapt(call: Call<R>): Any {
        return when (val obj = rObjectCallAdapter.adapt(call)) {
            is Completable -> {
                obj.onErrorResumeNext { _throwable: Throwable ->
                    Completable.error(
                        parseException(
                            _throwable
                        )
                    )
                }
            }
            is Single<*> -> {
                obj.onErrorResumeNext { _throwable: Throwable ->
                    Single.error(
                        parseException(_throwable)
                    )
                }
            }
            is Maybe<*> -> {
                obj.onErrorResumeNext { _throwable: Throwable ->
                    Maybe.error(
                        parseException(
                            _throwable
                        )
                    )
                }
            }
            is Observable<*> -> {
                obj.onErrorResumeNext { _throwable: Throwable ->
                    Observable.error(
                        parseException(_throwable)
                    )
                }
            }
            else -> {
                obj
            }
        }
    }

    private fun parseException(_throwable: Throwable): NetworkException {
        // We had non-200 http error
        if (_throwable is HttpException) {
            val response = _throwable.response()
            return httpError(
                response!!.raw().request.url.toString(),
                response,
                retrofit
            )
        }
        // A network error happened
        return if (_throwable is IOException) {
            networkError(_throwable)
        } else unexpectedError(_throwable)
        // We don't know what happened. We need to simply convert to an unknown error
    }

}