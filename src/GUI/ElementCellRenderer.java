/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Implementation of a <code>TableCellRenderer</code> for
 * <code>java.util.Date</code> class
 *
 * @author andrey
 */
public class ElementCellRenderer extends JLabel implements TableCellRenderer {

    private final Matrix matr;

    ElementCellRenderer(Matrix matr) {
        setHorizontalTextPosition(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);
        this.matr = matr;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Color[] col = new Color[]{Color.GREEN, Color.RED, Color.YELLOW, Color.GRAY, Color.CYAN, Color.PINK, Color.WHITE, Color.ORANGE};
        Font font = new Font(null,Font.BOLD,18);
        this.setFont(font);
        int nmb = matr.getValue(row, column).getNmb() - 1;
        setBackground(col[(nmb>=0 && nmb <= 6) ? nmb : 7]);
        /* switch (matr.getValue(row,column).getNmb()){
         case 1:setBackground(Color.GREEN);break;
         case 2:setBackground(Color.RED);break;
         case 3:setBackground(Color.YELLOW);break;
         case 4:setBackground(Color.GRAY);break;
         case 5:setBackground(Color.CYAN);break;
         case 6:setBackground(Color.PINK);break;
         case 7:setBackground(Color.WHITE);break;
         default:setBackground(Color.ORANGE);break;    
         } */
        MyBorder bord = new MyBorder(matr.getValue(row, column).getDeletedborder());
        this.setBorder(bord);
        setText(Integer.toString(matr.getValue(row, column).getValue()));
        return this;
    }

}
