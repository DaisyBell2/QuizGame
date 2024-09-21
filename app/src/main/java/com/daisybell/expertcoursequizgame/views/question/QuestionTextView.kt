package com.daisybell.expertcoursequizgame.views.question

import android.content.Context
import android.util.AttributeSet

/**
 * @author DaisyBell on 21.09.2024
 */
class QuestionTextView : androidx.appcompat.widget.AppCompatTextView, UpdateText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(text: String) {
        this.text = text
    }

    override fun getFreezesText(): Boolean = true

}

interface UpdateText {
    fun update(text: String)
}