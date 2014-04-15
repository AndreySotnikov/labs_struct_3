package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrey
 */
public class Cell {
    private final int value;
    private boolean covered;
    private int deletedborder;
    private int nmb;

    public int getNmb() {
        return nmb;
    }

    public void setNmb(int nmb) {
        this.nmb = nmb;
    }
    
    public Cell(String val) throws NumberFormatException {
        value = Integer.parseInt(val);
        covered = false;
        deletedborder = 0;
        nmb = 0;
    }
    /**
     * deletedborder == 1 -up;
     * deletedborder == 2 -right;
     * deletedborder == 3 -down;
     * deletedborder == 4 -left
     * @return 
     */
    public int getDeletedborder() {
        return deletedborder;
    }
    /**
     * deletedborder == 1 -up;
     * deletedborder == 2 -right;
     * deletedborder == 3 -down;
     * deletedborder == 4 -left 
     * @param deletedborder
     */
    public void setDeletedborder(int deletedborder) {
        this.deletedborder = deletedborder;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }
    public boolean isCovered(){
        return covered;
    }

    public int getValue() {
        return value;
    }
}
