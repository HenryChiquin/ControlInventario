package com.grupopdc.controlinventario.models.recursos;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDynamic {
    private TableLayout tableLayout;
    private Context context;

    private String[] header;
    private ArrayList<String[]> body;

    private TableRow tableRow;
    private TextView txtCell;

    private int indexC;
    private int indexR;

    private Boolean multiColor = false;
    int firtColor, secondColor;

    public TableDynamic(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader(String[] header){
        this.header = header;
        createHeader();
    }
    public void addBody(ArrayList<String[]> body){
        this.body = body;
        createBodyTable();
    }

    private void newRow(){
        tableRow = new TableRow(context);
    }
    private void newCell(){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.LEFT);
        txtCell.setTextSize(18);

    }

    private void createHeader(){
        indexC = 0;
        newRow();
        while (indexC<header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createBodyTable(){
        String info;
        for(indexR=1;indexR<= body.size();indexR++){
            newRow();
            for(indexC=0;indexC<header.length;indexC++){
                newCell();
                String[] row = body.get(indexR-1);
                info = (indexC<row.length)?row[indexC]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }

    public void backgroundHeader(int color){
        indexC = 0;

        while (indexC<header.length){
            txtCell = getCell(0,indexC++);
            txtCell.setBackgroundColor(color);
            txtCell.setTextColor(Color.parseColor("#FFFDFDFD"));
        }

    }
    public void backgroundBody(int firtColor, int secondColor){
        for(indexR=1;indexR<= body.size();indexR++){
            multiColor = !multiColor;
            for(indexC=0;indexC<header.length;indexC++){
                txtCell = getCell(indexR,indexC);
                txtCell.setBackgroundColor((multiColor)?firtColor:secondColor);
            }
        }
        this.firtColor = firtColor;
        this.secondColor = secondColor;
    }
    public void reColoring(){
        indexC = 0;
        multiColor =! multiColor;
        while (indexC<header.length){
            txtCell = getCell(body.size()-1,indexC++);
            txtCell.setBackgroundColor((multiColor)?firtColor:secondColor);
        }
    }
    private TableRow getRow(int index){
        return (TableRow) tableLayout.getChildAt(index);
    }

    private TextView getCell(int rowIndex, int columIndex){
        tableRow = getRow(rowIndex);
        return (TextView) tableRow.getChildAt(columIndex);
    }

    public void addItems(String[] item){
        String info;
        body.add(item);
        indexC = 0;
        newRow();
        while (indexC<header.length){
            newCell();

            info = (indexC<item.length)?item[indexC++]:"";
            txtCell.setText(info);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow,body.size()-1);
    }
    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight = 1;
        return params;
    }

}
