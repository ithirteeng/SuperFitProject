package com.ithirteeng.superfitproject.mybody.presentation

sealed class MyBodyScreenIntent {
    object Initial : MyBodyScreenIntent()
    class ChangeWeight(weight: Int) : MyBodyScreenIntent()
    class ChangeHeight(height: Int) : MyBodyScreenIntent()
    object DismissError : MyBodyScreenIntent()
}
