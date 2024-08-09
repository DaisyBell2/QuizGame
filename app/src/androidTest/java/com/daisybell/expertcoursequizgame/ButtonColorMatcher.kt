package com.daisybell.expertcoursequizgame

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

/**
 * @author DaisyBell on 08.08.2024
 */
class ButtonColorMatcher(private val color: Int) :
    BoundedMatcher<View, Button>(Button::class.java) {

    constructor(colorString: String) : this(Color.parseColor(colorString))

    override fun describeTo(description: Description) {
        description.appendText("color for button doesn't match expected $color")
    }

    override fun matchesSafely(item: Button): Boolean {
        return (item.background as ColorDrawable).color == color
    }
}