## Main Functions Mentioned in the Report

# init
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
# callComputer
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

# computerMove
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

# draw
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

# checkWinner
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
---
                                                                    # State Space Graph
                                                                    
#main
```
public static void main(String[] args) {

        ArrayList<Integer> init = new ArrayList<>();
        init.add(2);
        init.add(1);

        ArrayList<ArrayList<Integer>> newList = new ArrayList<>();
        newList.add(init);

        ArrayList<Integer> two = new ArrayList<>();
        two.add(1);
        two.add(2);
        newList.add(two);

        HashMap<Integer, ArrayList<ArrayList<Integer>>> map = new HashMap<>();
        map.put(0, newList);

        int total=0;
        for (int i = 0; i < map.get(0).get(0).size(); i++){
            total += map.get(0).get(0).get(i);
        }


        for (int i = 1; i < total+1; i++){
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<ArrayList<Integer>> ll = new ArrayList<>();
            ll.add(list);
            map.put(i, ll);
        }


        generatePossibilitiesRecursive(map, 0);

        for (int i = 0; i < map.size(); i++){
            System.out.println(i+": "+map.get(i));
        }


    }
```

# generatePossibilitiesRecursive
```
static void generatePossibilitiesRecursive(HashMap<Integer, ArrayList<ArrayList<Integer>>> map, int depth){
        ArrayList<ArrayList<Integer>> parentList;
        //if (map.get(depth).isEmpty()) return;
        for (int i = depth == 0 ? 0 : 1 ; i < map.get(depth).size(); i++){
            int total = 0;
            for (int j = 0; j < map.get(depth).get(i).size(); j++){
                total+=map.get(depth).get(i).get(j);
            }
            if (total==0 && i==map.get(depth).size()-1) return;
            ArrayList<Integer> newList = new ArrayList<>(map.get(depth).get(i));
            parentList = generateList(newList, total);
            ArrayList<ArrayList<Integer>> finalList;
            if (map.get(depth+1)==null){
                finalList = parentList;
            }else{
                ArrayList<ArrayList<Integer>> list = new ArrayList<>(map.get(depth+1));
                finalList = joinLists(list, parentList);
            }
            map.put(depth+1, finalList);
        }
        generatePossibilitiesRecursive(map, depth+1);
    }
```

# joinLists
```
static ArrayList<ArrayList<Integer>> joinLists(ArrayList<ArrayList<Integer>> l1, ArrayList<ArrayList<Integer>> l2){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.addAll(l1);
        list.addAll(l2);
        return list;
    }
```

# generateLists
```
static ArrayList<ArrayList<Integer>> generateList(ArrayList<Integer> list, int total){
        ArrayList<ArrayList<Integer>> parent = new ArrayList<>();
        ArrayList<Integer> child = new ArrayList<>(list);
        int idx = 0;
        for (int i = 0; i < total; i++){
            if (child.get(idx)!=0){
                ArrayList<Integer> newList = new ArrayList<>(child);
                newList.set(idx, child.get(idx)-1);
                int x = child.get(idx);
                child.set(idx, x-1);
                parent.add(newList);
            }else {
                child=new ArrayList<>(list);
                ArrayList<Integer> newList = new ArrayList<>(child);
                idx++;
                newList.set(idx, child.get(idx)-1);
                int x = child.get(idx);
                child.set(idx, x-1);
                parent.add(newList);
            }
        }

        return parent;
    }
```
