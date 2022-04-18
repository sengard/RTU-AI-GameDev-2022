package com.app.aigame;
import java.util.ArrayList;
import java.util.HashMap;

public class StateSpaceGraph {

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

    static ArrayList<ArrayList<Integer>> joinLists(ArrayList<ArrayList<Integer>> l1, ArrayList<ArrayList<Integer>> l2){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.addAll(l1);
        list.addAll(l2);
        return list;
    }

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
}
