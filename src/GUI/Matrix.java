
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andrey
 */
public class Matrix {

    private Cell[][] matr;
    private final int nmbCol;
    private final int nmbRow;

    // public Cell[][] getMatr() {
    //   return matr;
    //}
    public Cell getValue(int i, int j) {
        return matr[i][j];
    }

    public Matrix(DefaultTableModel model) throws NumberFormatException {
        nmbCol = model.getColumnCount();
        nmbRow = model.getRowCount();
        matr = new Cell[nmbRow][nmbCol];
        for (int i = 0; i < nmbRow; i++) {
            for (int j = 0; j < nmbCol; j++) {
                matr[i][j] = new Cell((String) model.getValueAt(i, j));
            }
        }
    }

    public int Cover() {
        int k = 0;
        Domino dom = new Domino();
        boolean result;
        try {
            do {
                k++;
                dom.Init(k);
                result = tryCover(0, 0, dom);
            } while (!result);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return -1;
        }

        return k;
    }

    /* public boolean tryCover(int k, int m, Domino dom) throws ArrayIndexOutOfBoundsException, NumberFormatException {
     boolean result = false;
     for (int i = k; i < nmbRow; i++) {
     for (int j = m; j < nmbCol; j++) {
     if (!matr[i][j].isCovered()) {
     if (canCover(i, j, true, dom)) {
     matr[i][j].setCovered(true);
     matr[i + 1][j].setCovered(true);
     //for gui
     matr[i][j].setDeletedborder(3);
     matr[i+1][j].setDeletedborder(1);
     //
     dom.decVal(matr[i][j], matr[i + 1][j]);
     result = tryCover(i, 0, dom);
     if (!result) {
     dom.incVal(matr[i][j], matr[i + 1][j]);                           
     matr[i][j].setCovered(false);
     matr[i + 1][j].setCovered(false);
     }
     }
     if (!result && canCover(i, j, false, dom)) {
     matr[i][j].setCovered(true);
     matr[i][j + 1].setCovered(true);
     //for gui
     matr[i][j].setDeletedborder(2);
     matr[i][j+1].setDeletedborder(4);
     //
     dom.decVal(matr[i][j], matr[i][j + 1]);
     result = tryCover(i, 0, dom);
     if (!result) {
     dom.incVal(matr[i][j], matr[i][j + 1]);                               
     matr[i][j].setCovered(false);
     matr[i][j + 1].setCovered(false);
     }
     }
     if (!result) {
     return false;
     }
     }

     }
     }
     return true;
     } */
    public boolean tryCover(int k, int m, Domino dom) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        boolean result = false;
        int i = k;
        int j = m;
        boolean find = false;
        while(!find && i<nmbRow){
            if (!matr[i][j].isCovered())
                find = true;
            else
                if (j==nmbCol-1){
                    j=0;
                    i++;
                }else
                    j++;
        }
        if (!find) {
            return true;
        }
        if (canCover(i, j, true, dom)) {
            matr[i][j].setCovered(true);
            matr[i + 1][j].setCovered(true);
            //for gui
            matr[i][j].setDeletedborder(3);
            matr[i + 1][j].setDeletedborder(1);
            //
            dom.decVal(matr[i][j], matr[i + 1][j]);
            result = tryCover(i, j, dom);
            if (!result) {
                dom.incVal(matr[i][j], matr[i + 1][j]);
                matr[i][j].setCovered(false);
                matr[i + 1][j].setCovered(false);
            }
        }
        if (!result && canCover(i, j, false, dom)) {
            matr[i][j].setCovered(true);
            matr[i][j + 1].setCovered(true);
            //for gui
            matr[i][j].setDeletedborder(2);
            matr[i][j + 1].setDeletedborder(4);
            //
            dom.decVal(matr[i][j], matr[i][j + 1]);
            result = tryCover(i, j, dom);
            if (!result) {
                dom.incVal(matr[i][j], matr[i][j + 1]);
                matr[i][j].setCovered(false);
                matr[i][j + 1].setCovered(false);
            }
        }
        return result; 
    }

    public boolean canCover(int i, int j, boolean vert, Domino dom) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        int nmb;
        if (vert) {
            if (i != nmbRow - 1) {
                nmb = dom.isFind(matr[i][j], matr[i + 1][j]);
                matr[i][j].setNmb(dom.getInitial() - nmb + 1);
                matr[i + 1][j].setNmb(dom.getInitial() - nmb + 1);
                return nmb != 0;
            } else {
                return false;
            }
        } else {
            if (j != nmbCol - 1) {
                nmb = dom.isFind(matr[i][j], matr[i][j + 1]);
                matr[i][j].setNmb(dom.getInitial() - nmb + 1);
                matr[i][j + 1].setNmb(dom.getInitial() - nmb + 1);
                return nmb != 0;
            } else {
                return false;
            }
        }

        /*if (vert){
         return i != nmbRow - 1 && dom.isFind(matr[i][j], matr[i + 1][j])!=0;            
         }else{
         return j != nmbCol - 1 && dom.isFind(matr[i][j], matr[i][j + 1])!=0;
         }*/
    }
    /* public int Cover(int k, int m, Domino dom,int start){
     dom.Init(start);
     //for (int i = k;i < nmbRow;i++)
     //for (int j = m;j < nmbCol;j++){
     if (k!=nmbRow-1 && m!=nmbCol-1)
     if (!matr[k][m].isCovered()) {
     int res = tryCover(k,m,dom, start);
     if (res == -1){
     if (k==nmbRow-1 && m==nmbCol-1)
     return -1;
     else{
     return Cover(0,0,dom,start+1);
     //return Cover(i,j,dom,start+1);
     }
                            
     } else
     //return Cover(0,0,dom,start+1);
     return res;
     }
                
     //}
     return start;
     }
     public int tryCover(int i,int j,Domino dom, int currStart){
     //boolean result = false;
     if (i!=nmbRow-1){
     if (dom.isFind(matr[i][j], matr[i+1][j])){
     dom.decVal(matr[i][j], matr[i+1][j]);
     matr[i][j].setCovered(true);
     matr[i+1][j].setCovered(true);
                
     return Cover(i,j+1,dom, currStart);
     //return true;
     }
     }
     if (j!=nmbCol-1){
     if (dom.isFind(matr[i][j], matr[i][j+1])){
     dom.decVal(matr[i][j], matr[i][j+1]);
     matr[i][j].setCovered(true);
     matr[i][j+1].setCovered(true);
                
     return Cover(i+1,j,dom,currStart);
     //return true;
     }
     }
     return -1;
     //return false;
     }*/
}
