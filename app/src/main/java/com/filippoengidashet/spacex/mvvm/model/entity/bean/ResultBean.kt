package com.filippoengidashet.spacex.mvvm.model.entity.bean

/**
 * @author Filippo 12/11/2021
 */
sealed class ResultBean<out T> {

    class Succeeded<T>(val data: T) : ResultBean<T>()
    class Failed(val error: String?) : ResultBean<Nothing>()

    companion object {

        fun <T> success(data: T) = Succeeded(data)
        fun failure(error: String?) = Failed(error)
    }
}
