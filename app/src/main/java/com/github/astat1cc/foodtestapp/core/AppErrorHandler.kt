package com.github.astat1cc.foodtestapp.core

import android.content.Context
import com.github.astat1cc.foodtestapp.R
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

interface AppErrorHandler {

    fun getErrorTypeOf(exception: Exception): ErrorType

    fun getErrorMessageOf(error: ErrorType): String

    class Impl(private val context: Context) : AppErrorHandler {

        override fun getErrorTypeOf(exception: Exception) = when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> throw exception
//                ErrorType.GENERIC
        }

        override fun getErrorMessageOf(error: ErrorType): String = when (error) {
            ErrorType.NO_CONNECTION -> context.getString(R.string.no_connection_error_message)
            ErrorType.SERVICE_UNAVAILABLE -> context.getString(R.string.service_unavailable_error_message)
            ErrorType.GENERIC -> context.getString(R.string.something_went_wrong_error_message)

        }
    }
}