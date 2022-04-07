package com.hyrule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hyrule.design.theme.HyruleTheme
import com.hyrule.navcontroller.HyruleNavController

class HyruleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HyruleTheme { HyruleNavController() }
        }
    }
}
