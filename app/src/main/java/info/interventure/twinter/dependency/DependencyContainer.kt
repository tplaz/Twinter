package info.interventure.twinter.dependency

import info.interventure.twinter.logic.data.FirebaseApi
import info.interventure.twinter.logic.presenter.swipe.SwipePresenter

object DependencyContainer {
    fun provideSwipePresenter() = SwipePresenter(provideFirebaseApi())

    fun provideFirebaseApi() = FirebaseApi()
}