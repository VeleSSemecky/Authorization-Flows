package com.veles.authorizationflows.data.network.error

import com.veles.authorizationflows.AppApplication.Companion.instance
import com.veles.authorizationflows.R
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class NetworkException private constructor(
    message: String?,
    val url: String?,
    val response: Response<*>?,
    val kind: Kind,
    exception: Throwable?,
    val retrofit: Retrofit?
) : RuntimeException(message, exception) {
    /**
     * Identifies the event kind which triggered a [NetworkException].
     */
    enum class Kind {
        /**
         * An [IOException] occurred while communicating to the server.
         */
        NETWORK,

        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,

        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    /**
     * The request URL which produced the error.
     */
    /**
     * Response object containing status code, headers, body, etc.
     */
    /**
     * The event kind which triggered this error.
     */
    /**
     * The Retrofit this request was executed on
     */

    val responseCode: Int
        get() = response!!.code()

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified `type`.
     */
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (response == null || response.errorBody() == null) {
            return null
        }
        var t: T?
        try {
            val converter: Converter<ResponseBody?, T> =
                retrofit!!.responseBodyConverter(type, arrayOfNulls(0))
            t = converter.convert(response.errorBody())
            if (type.isInstance(t)) return t
        } catch (e: IOException) {
            return null
        }
        if (type.isInstance(response.message())) {
            t = type.cast(
                instance.resources.getString(R.string.error_server)
            )
            try {
                val jObj = JSONObject(response.errorBody()!!.string())
                val keys = jObj.keys()
                if (keys.hasNext()) {
                    val jsonArray = jObj.getJSONArray(keys.next())
                    val message = StringBuilder()
                    for (i in 0 until jsonArray.length()) {
                        message.append(jsonArray.getString(i))
                    }
                    t = type.cast(message.toString())
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return t
        }
        return null
    }

    companion object {
        fun httpError(
            url: String?,
            response: Response<*>,
            retrofit: Retrofit?
        ): NetworkException {
            val message = response.code().toString() + " " + response.message()
            return NetworkException(
                message,
                url,
                response,
                Kind.HTTP,
                null,
                retrofit
            )
        }

        fun networkError(exception: IOException?): NetworkException {
            return NetworkException(
                instance.resources.getString(R.string.error_internet),
                null,
                null,
                Kind.NETWORK,
                exception,
                null
            )
        }

        fun unexpectedError(exception: Throwable): NetworkException {
            return NetworkException(
                exception.message,
                null,
                null,
                Kind.UNEXPECTED,
                exception,
                null
            )
        }
    }

}