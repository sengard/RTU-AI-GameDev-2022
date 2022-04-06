## Main Functions Mentioned in the Report

#init
```
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
```
#callComputer
```
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
```

#computerMove
```
fun computerMove(): IntArray{
        var idx = 0;
        var number = -999
        for (i in 0 until  arr.size){
            if (arr[i]!=0){
                for(j in 0..arr[i]){
                    if (j>number && arr[i]-j==0){
                        number = j;
                        idx = i
                        return intArrayOf(idx, number)
                    }
                }
            }
        }
        return intArrayOf(0,0)
    }
```

#draw
```
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
```

#checkWinner
```
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
```

