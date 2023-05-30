package aafun.gamesfootbal

import android.view.View
import android.widget.Button
import android.widget.RadioButton

class Tile(val gameActivity: GameActivity,val button: Button,val indexY:Int,val indexX:Int) {
    init {
        if (button.text == "0") button.visibility = View.GONE

    }
}