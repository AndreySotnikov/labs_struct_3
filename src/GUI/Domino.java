
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

/**
 *
 * @author andrey
 */
public class Domino {
    private int[][] dom;
    int initial;


    public Domino(){
        initial = 0;
        dom = new int[7][7];
        for(int i = 0;i<7;i++)
            for (int j=0;j<7;j++)
                dom[i][j] = 0;
    }
    
    public int getInitial() {
        return initial;
    }
    
    public void Init(int val){
        initial = val;
        for(int i = 0;i<7;i++)
            for (int j=0;j<7;j++)
                dom[i][j] = val;
    }

    public int isFind(Cell i,Cell j){
        return dom[i.getValue()][j.getValue()];
    }
    public void decVal(Cell i,Cell j){
        dom[i.getValue()][j.getValue()]--;
        if (i.getValue()!=j.getValue())
            dom[j.getValue()][i.getValue()]--;
    }
    public void incVal(Cell i,Cell j){
        dom[i.getValue()][j.getValue()]++;
        if (i.getValue()!=j.getValue())
            dom[j.getValue()][i.getValue()]++;
    }
            
}
