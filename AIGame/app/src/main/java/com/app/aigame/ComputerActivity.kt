package com.app.aigame

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_computer.*
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class ComputerActivity : AppCompatActivity() {

    lateinit var arr: IntArray
    var winner: Boolean = false
    private lateinit var CP: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computer)

        //Initialize Current Player
        CP = intent.getStringExtra("cp").toString()

        init()

        btnPlayAgain!!.setOnClickListener {
            init()
            currentPlayer2.text = CP
            btnPlayAgain!!.visibility = View.GONE
            btnFinishMove2!!.visibility = View.VISIBLE
        }


    }

    fun init(){
        //Initialize Array
        arr = intArrayOf(3,5,2,4)
        //Set The Image Gravity to Center Horizontal
        setLayoutGravity()
        //Draw`s the circles
        draw()
        //Set All the Click Listeners
        setClickListeners()
        //Finish Game Button
        if (CP=="Computer"){
            updateCurrentPlayer()
            callComputer()
            callPlayer()
        }
        if (CP=="Player"){
            callPlayer()
        }
    }

    fun callPlayer(){
        btnFinishMove2.setOnClickListener {
            removeViews()
            draw()
            setClickListeners()
            updateCurrentPlayer()
            checkWinner()
            if (CP=="Computer"){
                callComputer()
            }
        }
    }

    fun callComputer(){
        if(!winner){
            progressBar?.visibility = View.VISIBLE
        }
        btnFinishMove2?.visibility = View.GONE
        Handler().postDelayed({
            checkGamePlayerByComputer()
            progressBar?.visibility = View.GONE
            if (!winner){
                btnFinishMove2?.visibility = View.VISIBLE
            }
        }, 2000)
    }

    fun checkGamePlayerByComputer(){

        playComputerFunction()
        removeViews()
        draw()
        setClickListeners()
        updateCurrentPlayer()
        checkWinner()

    }

    fun setClickListeners(){

        for (i in arr.indices){
            for (j in 0 until arr[i]){
                if (i==0){
                    onClick(ll11, j)
                }else if (i==1){
                    onClick(ll22, j)
                }else if (i==2){
                    onClick(ll33, j)
                }else if (i==3){
                    onClick(ll44, j)
                }
            }
        }
    }

    fun onClick(ll: LinearLayout, idx: Int){
        ll.getChildAt(idx).setOnClickListener {
            ll.getChildAt(idx).setBackgroundResource(R.drawable.empty_circle)

            when(ll){
                ll11->{
                    var x = arr[0]
                    arr[0] = x-1
                }
                ll22->{
                    var x = arr[1]
                    arr[1] = x-1
                }
                ll33->{
                    var x = arr[2]
                    arr[2] = x-1
                }
                ll44->{
                    var x = arr[3]
                    arr[3] = x-1
                }
            }

        }

    }

    fun updateCurrentPlayer(){
        if (currentPlayer2.text == "Player"){
            currentPlayer2.text = "Computer"
            CP = "Computer"
        }else if(currentPlayer2.text == "Computer"){
            currentPlayer2.text = "Player"
            CP = "Player"
        }
    }

    fun playComputerFunction(){
        val newArray = computerMove()
        arr[newArray[0]] = arr[newArray[0]] - newArray[1];
        draw()
    }

    fun computerMove(): IntArray{
        var idx = 0;
        var number = -999
        for (i in 0 until  arr.size){
            if (arr[i]!=0){
//                for(j in 0..arr[i]){
//                    if (j>number && arr[i]-j==0){
//                        number = j;
//                        idx = i
//                        return intArrayOf(idx, number)
//                    }
//                }
                return findMinimum();
            }
        }
        return intArrayOf(0,0)
    }

    fun findMinimum(): IntArray{
        var res = 20;
        var idx = 0;
        for (i in 0 until arr.size){
            if (arr[i]!=0) {
                res = Math.min(res, arr[i]);
            }
        }

        for (i in 0 until arr.size){
            if (arr[i] == res) idx = i;
        }
        return intArrayOf(idx, res);
    }

    fun removeViews(){
        ll11.removeAllViews()
        ll22.removeAllViews()
        ll33.removeAllViews()
        ll44.removeAllViews()
    }

    fun draw(){

        for (i in arr.indices){
            for (j in 0 until arr[i]){
                if(i==0){
                    val img = ImageView(this)
                    img.layoutParams = LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll11.addView(img)
                }
                if(i==1){
                    val img = ImageView(this)
                    img.layoutParams = LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll22.addView(img)
                }
                if(i==2){
                    val img = ImageView(this)
                    img.layoutParams = LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll33.addView(img)
                }
                if(i==3){
                    val img = ImageView(this)
                    img.layoutParams = LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll44.addView(img)
                }
            }

        }

    }

    fun checkWinner(){
        var x = 0
        for (i in arr.indices){
            if (arr[i]!=0)
                x++;
        }
        winner = x == 0
        if (winner){
            if(CP == "Player"){
                currentPlayer2.text = "Computer wins"
            }else{
                currentPlayer2.text = "Player wins"
            }
            btnFinishMove2!!.visibility = View.GONE
            btnPlayAgain!!.visibility = View.VISIBLE
        }
    }

    fun setLayoutGravity(){
        ll11.gravity = Gravity.CENTER_HORIZONTAL
        ll22.gravity = Gravity.CENTER_HORIZONTAL
        ll33.gravity = Gravity.CENTER_HORIZONTAL
        ll44.gravity = Gravity.CENTER_HORIZONTAL
    }
}