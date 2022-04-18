package com.app.aigame

import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.math.sign

class GameActivity : AppCompatActivity() {

    lateinit var arr: IntArray
    var winner: Boolean = false
    var CP = "Player 1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //Initialize Array

        arr = intArrayOf((3..9).random(),2,5,4)

        //Set The Image Gravity to Center Horizontal
        setLayoutGravity()

        //Draw`s the circles
        draw()

        //Set All the Click Listeners
        setClickListeners()

        //Finish Game Button
        btnFinishMove.setOnClickListener {
            removeViews()
            draw()
            setClickListeners()
            updateCurrentPlayer()
            checkWinner()
        }

    }


    fun setClickListeners(){

        for (i in arr.indices){
            for (j in 0 until arr[i]){
                if (i==0){
                    onClick(ll1, j)
                }else if (i==1){
                    onClick(ll2, j)
                }else if (i==2){
                    onClick(ll3, j)
                }else if (i==3){
                    onClick(ll4, j)
                }
            }
        }
    }

    fun onClick(ll: LinearLayout, idx: Int){
            ll.getChildAt(idx).setOnClickListener {
                ll.getChildAt(idx).setBackgroundResource(R.drawable.empty_circle)

                when(ll){
                    ll1->{
                        var x = arr[0]
                        arr[0] = x-1
                    }
                    ll2->{
                        var x = arr[1]
                        arr[1] = x-1
                    }
                    ll3->{
                        var x = arr[2]
                        arr[2] = x-1
                    }
                    ll4->{
                        var x = arr[3]
                        arr[3] = x-1
                    }
                }

            }

    }

    fun updateCurrentPlayer(){
        if (currentPlayer.text == "Player 1"){
            currentPlayer.text = "Player 2"
            CP = "Player 2"
        }else if(currentPlayer.text == "Player 2"){
            currentPlayer.text = "Player 1"
            CP = "Player 1"
        }

    }

    fun removeViews(){
        ll1.removeAllViews()
        ll2.removeAllViews()
        ll3.removeAllViews()
        ll4.removeAllViews()
    }

    fun draw(){
        for (i in arr.indices){
            for (j in 0 until arr[i]){
                if(i==0){
                    val img = ImageView(this)
                    img.layoutParams =LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll1.addView(img)
                }
                if(i==1){
                    val img = ImageView(this)
                    img.layoutParams =LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll2.addView(img)
                }
                if(i==2){
                    val img = ImageView(this)
                    img.layoutParams =LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll3.addView(img)
                }
                if(i==3){
                    val img = ImageView(this)
                    img.layoutParams =LinearLayout.LayoutParams(80,80)
                    img.setBackgroundResource(R.drawable.full_circle)
                    ll4.addView(img)
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
            if(CP == "Player 1"){
                currentPlayer.text = "Player 2 wins"
            }else{
                currentPlayer.text = "Player 1 wins"
            }
            btnFinishMove!!.visibility = View.GONE
        }
    }

    fun setLayoutGravity(){
        ll1.gravity = Gravity.CENTER_HORIZONTAL
        ll2.gravity = Gravity.CENTER_HORIZONTAL
        ll3.gravity = Gravity.CENTER_HORIZONTAL
        ll4.gravity = Gravity.CENTER_HORIZONTAL
    }


}