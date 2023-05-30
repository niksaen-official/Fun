package aafun.gamesfootbal

import aafun.gamesfootbal.databinding.ActivityGameBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    lateinit var ui:ActivityGameBinding
    var table = arrayOf(
        arrayOf(0,1,2,3),
        arrayOf(4,5,6,7),
        arrayOf(8,9,10,11),
        arrayOf(12,13,14,15)
    )
    lateinit var tableBtn:Array<Array<TextView>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityGameBinding.inflate(layoutInflater)
        tableBtn = arrayOf(
            arrayOf(ui.button0,ui.button1,ui.button2,ui.button3),
            arrayOf(ui.button4,ui.button5,ui.button6,ui.button7),
            arrayOf(ui.button8,ui.button9,ui.button10,ui.button11),
            arrayOf(ui.button12,ui.button13,ui.button14,ui.button15)
        )
        setContentView(ui.root)
        shufleTable()
        init()
        ui.button16.setOnClickListener {
            shufleTable()
            init()
        }
    }
    fun shufleTable(){
        for (row in table){
            row.shuffle()
        }
        table.shuffle()
    }
    fun init(){
        for (y in 0..3){
            for(x in 0..3){
                tableBtn[y][x].text = table[y][x].toString()
                if(table[y][x] == 0){
                    tableBtn[y][x].visibility = View.INVISIBLE
                }else{
                    tableBtn[y][x].visibility = View.VISIBLE
                }
                tableBtn[y][x].setOnClickListener {
                    val buff = table[y][x]
                    if(y==0){
                        if(x==0){
                            if(table[1][0] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[1][0] = buff
                                tableBtn[1][0].visibility = View.VISIBLE
                                tableBtn[1][0].text = buff.toString()
                            }
                            else if(table[0][1]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[0][1] = buff
                                tableBtn[0][1].visibility = View.VISIBLE
                                tableBtn[0][1].text = buff.toString()
                            }
                        }
                        else if(x in 1..2){
                            if(table[y][x+1]==0){
                                table[y][x] = 0
                                table[y][x+1]=buff
                                tableBtn[y][x].visibility = View.INVISIBLE
                                tableBtn[y][x+1].visibility = View.VISIBLE
                                tableBtn[y][x+1].text = buff.toString()
                            }
                            else if(table[1][x]==0){
                                table[y][x] = 0
                                table[1][x]=buff
                                tableBtn[y][x].visibility = View.INVISIBLE
                                tableBtn[1][x].visibility = View.VISIBLE
                                tableBtn[1][x].text = buff.toString()
                            }
                            else if(table[y][x-1]==0){
                                table[y][x] = 0
                                table[y][x-1]=buff
                                tableBtn[y][x].visibility = View.INVISIBLE
                                tableBtn[y][x-1].visibility = View.VISIBLE
                                tableBtn[y][x-1].text = buff.toString()
                            }
                        }
                        else{
                            if(table[0][2] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[0][2] = buff
                                tableBtn[0][2].visibility = View.VISIBLE
                                tableBtn[0][2].text = buff.toString()
                            }
                            else if(table[1][x]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[1][x] = buff
                                tableBtn[1][x].visibility = View.VISIBLE
                                tableBtn[1][x].text = buff.toString()
                            }
                        }
                    }
                    else if(y in 1..2){
                        if(x==0) {
                            if(table[y+1][x] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y+1][x] = buff
                                tableBtn[y+1][x].visibility = View.VISIBLE
                                tableBtn[y+1][x].text = buff.toString()
                            }
                            else if(table[y][1] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][1] = buff
                                tableBtn[y][1].visibility = View.VISIBLE
                                tableBtn[y][1].text = buff.toString()
                            }
                            else if(table[y-1][x] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y-1][x] = buff
                                tableBtn[y-1][x].visibility = View.VISIBLE
                                tableBtn[y-1][x].text = buff.toString()
                            }
                        }
                        else if(x in 1..2){
                            if(table[y][x+1]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][x+1] = buff
                                tableBtn[y][x+1].visibility = View.VISIBLE
                                tableBtn[y][x+1].text = buff.toString()
                            }
                            else if(table[y][x-1]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][x-1] = buff
                                tableBtn[y][x-1].visibility = View.VISIBLE
                                tableBtn[y][x-1].text = buff.toString()
                            }
                            else if(table[y+1][x]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y+1][x] = buff
                                tableBtn[y+1][x].visibility = View.VISIBLE
                                tableBtn[y+1][x].text = buff.toString()
                            }
                            else if(table[y-1][x]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y-1][x] = buff
                                tableBtn[y-1][x].visibility = View.VISIBLE
                                tableBtn[y-1][x].text = buff.toString()
                            }
                        }
                        else{
                            if(table[y+1][x] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y+1][x] = buff
                                tableBtn[y+1][x].visibility = View.VISIBLE
                                tableBtn[y+1][x].text = buff.toString()
                            }
                            else if(table[y-1][x]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y-1][x] = buff
                                tableBtn[y-1][x].visibility = View.VISIBLE
                                tableBtn[y-1][x].text = buff.toString()
                            }
                            else if(table[y][x-1]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][x-1] = buff
                                tableBtn[y][x-1].visibility = View.VISIBLE
                                tableBtn[y][x-1].text = buff.toString()
                            }
                        }
                    }
                    else{
                        if(x==0){
                            if(table[y-1][x] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y-1][x] = buff
                                tableBtn[y-1][x].visibility = View.VISIBLE
                                tableBtn[y-1][x].text = buff.toString()
                            }
                            else if(table[y][1] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][1] = buff
                                tableBtn[y][1].visibility = View.VISIBLE
                                tableBtn[y][1].text = buff.toString()
                            }
                        }
                        else if(x in 1..2){
                            if(table[y][x+1]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][x+1] = buff
                                tableBtn[y][x+1].visibility = View.VISIBLE
                                tableBtn[y][x+1].text = buff.toString()
                            }
                            else if(table[y][x-1]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][x-1] = buff
                                tableBtn[y][x-1].visibility = View.VISIBLE
                                tableBtn[y][x-1].text = buff.toString()
                            }
                            else if(table[y-1][x]==0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y-1][x] = buff
                                tableBtn[y-1][x].visibility = View.VISIBLE
                                tableBtn[y-1][x].text = buff.toString()
                            }
                        }
                        else{
                            if(table[y][2] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y][2] = buff
                                tableBtn[y][2].visibility = View.VISIBLE
                                tableBtn[y][2].text = buff.toString()
                            }
                            else if(table[y-1][x] == 0){
                                table[y][x] = 0
                                tableBtn[y][x].visibility = View.INVISIBLE
                                table[y-1][x] = buff
                                tableBtn[y-1][x].visibility = View.VISIBLE
                                tableBtn[y-1][x].text = buff.toString()
                            }
                        }
                    }
                }
            }
        }
    }
}