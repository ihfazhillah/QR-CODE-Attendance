package com.ihfazh.absensiqrcode.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class DisposableFragment : Fragment() {
    val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }
}