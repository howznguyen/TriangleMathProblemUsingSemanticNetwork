package BaiToanTamGiac.Handles;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TriangleHandle {
    private final float angle = 180f;
    private int elementsNumber = 9;
    private int equationNumber = 6;
    private int [][] RTable = new int[this.elementsNumber][this.equationNumber]; // Relationship Table
    private float [][] VTable = new float[this.elementsNumber][this.equationNumber]; // Value Table
    private int stop;

    /**
     * Get RTable
     * @return int [][]
     */
    public int[][] getRTable() {
        return RTable;
    }

    /**
     * Constructor TriangleHandle
     */
    public TriangleHandle() {
        initRVTable();
    }

    /**
     * Start Action Calculate
     */
    public void start(ArrayList<JTextField> txtElements,JTextField txtResult, String selectElement){
        boolean flag = true;
        this.getAllElementsInGUI(txtElements);
        while(flag == true){
            flag = false;
            for (int i = 0; i < equationNumber; i++) {
                int getElement = this.getElement(i);
                if(getElement != -1){
                    if(stop == 1)
                        break;
                    activeSpreadingMechanismactive(i, getElement);
                    flag = true;
                    if(checkElementCaculated(selectElement)){
                        writeElementCaculated(selectElement, txtResult);
                        flag = false;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Init Value for Table of Value and Table of Relationship
     *
     */
    public void initRVTable(){
        int temp = -1;
        for (int i = 0; i < elementsNumber; i++)
            for (int j = 0; j < equationNumber; j++) {
                RTable[i][j] = 0;
                VTable[i][j] = 0;
            }

        //Init Value for Table of Value
        VTable[0][0] = VTable[0][3] = temp;
        VTable[1][0] = VTable[1][1] = VTable[1][3] = temp;
        VTable[2][1] = VTable[2][3] = temp;
        VTable[3][0] = VTable[3][2] = VTable[3][5] = temp;
        VTable[4][0] = VTable[4][1] = VTable[4][2] = VTable[4][5] = temp;
        VTable[5][1] = VTable[5][2] = VTable[5][4] = VTable[5][5] = temp;
        VTable[6][2] = VTable[6][5] = temp;
        VTable[7][2] = VTable[7][4] = temp;
        VTable[8][4] = temp;

        //Init Value for Table of Relationship
        RTable[0][0] = RTable[0][3] = temp;
        RTable[1][0] = RTable[1][1] = RTable[1][3] = temp;
        RTable[2][1] = RTable[2][3] = temp;
        RTable[3][0] = RTable[3][2] = RTable[3][5] = temp;
        RTable[4][0] = RTable[4][1] = RTable[4][2] = RTable[4][5] = temp;
        RTable[5][1] = RTable[5][2] = RTable[5][4] = RTable[5][5] = temp;
        RTable[6][2] = RTable[6][5] = temp;
        RTable[7][2] = RTable[7][4] = temp;
        RTable[8][4] = temp;
    }

    /**
     * Check The Value To Be Calculated
     *
     * @param thisElement
     * @return boolean
     */
    public boolean checkElementCaculated(String thisElement){
        switch (thisElement)
        {
            case "Góc Alpha":
                if(VTable[0][0] == -1) {
                    break;
                }
                return true;

            case "Góc Beta":
                if (VTable[1][0] == -1){
                    break;
                }
                return true;

            case "Góc Delta":
                if(VTable[2][1] == -1) {
                    break;
                }
                return true;

            case "Cạnh A":
                if (VTable[3][0] == -1){
                    break;
                }
                return true;

            case "Cạnh B":
                if(VTable[4][0] == -1) {
                    break;
                }
                return true;

            case "Cạnh C":
                if (VTable[5][1] == -1){
                    break;
                }
                return true;
            case "Chu Vi":
                if(VTable[6][2] == -1) {
                    break;
                }
                return true;

            case "Diện Tích":
                if (VTable[7][2] == -1){
                    break;
                }
                return true;

            case "Đường Cao":
                if(VTable[8][4] == -1) {
                    break;
                }
                return true;
        }
        return false;
    }

    /**
     * Write The Value To Be Calculated
     *
     * @param thisElement
     *
     */
    public void writeElementCaculated(String thisElement, JTextField txtResult){
        switch (thisElement)
        {
            case "Góc Alpha":
                txtResult.setText(String.valueOf(Math.round((VTable[0][0] * angle) / Math.PI)));
                break;

            case "Góc Beta":
                txtResult.setText(String.valueOf(Math.round((VTable[1][0] * angle) / Math.PI)));
                break;

            case "Góc Delta":
                txtResult.setText(String.valueOf(Math.round((VTable[2][1] * angle) / Math.PI)));
                break;

            case "Cạnh A":
                txtResult.setText(String.valueOf(VTable[3][0]));
                break;

            case "Cạnh B":
                txtResult.setText(String.valueOf(VTable[4][0]));
                break;

            case "Cạnh C":
                txtResult.setText(String.valueOf(VTable[5][1]));
                break;

            case "Diện Tích":
                txtResult.setText(String.valueOf(VTable[7][2]));
                break;

            case "Đường Cao":
                txtResult.setText(String.valueOf(VTable[8][4]));
                break;

            case "Chu Vi":
                txtResult.setText(String.valueOf(VTable[6][2] * 2));
                break;
        }
    }

    /**
     * Get Element in ValueTable
     *
     * @param element
     * @return int
     */
    public int getElement(int element){
        int count = 0;
        int value = -1;
        for (int i = 0; i < elementsNumber; i++)
           if(VTable[i][element] == -1){
               count++;
               value = i;
           }
        if(count == 1)
            return value;
        return -1;
    }

    /**
     * Active Spreading Mechanismactive for Problem
     *
     * @param element
     * @param equation
     */
    public void activeSpreadingMechanismactive(int equation, int element){
        float value = -1;
        float temp = -1;
        switch (equation){

            case 0:

                switch (element){
                    case 0:
                        // α = a*sinβ /b
                        temp = (float) ((VTable[3][0] * Math.sin(VTable[1][0])) / (VTable[4][0]));
                        value = (float)(Math.asin(temp) * angle / 3.1415926535897931);
                        break;

                    case 1:
                        // β = b*sinα /a
                        temp = (float)((VTable[4][0] * Math.sin(VTable[0][0])) / (VTable[3][0]));
                        value = (float)(Math.asin(temp) * angle / 3.1415926535897931);
                        break;

                    case 3:
                        // a= b*sinα/ sinβ
                        value = (float)((VTable[4][0] * Math.sin(VTable[0][0])) / Math.sin(VTable[1][0]));
                        break;

                    case 4:
                        // b= a*sinβ /sinα
                        value = (float)((VTable[3][0] * Math.sin(VTable[1][0])) / Math.sin(VTable[0][0]));
                        break;
                }
                break;

            case 1:
                switch (element)
                {
                    case 1:
                        // sinβ= b*sinδ/c
                        temp = (float)((VTable[4][0] * Math.sin(VTable[2][1])) / (VTable[5][1]));
                        value = (float)(Math.asin(temp) * angle / 3.1415926535897931);
                        break;
                    case 2:
                        // sinδ = c*sinβ /b
                        temp = (float)((VTable[5][1] * Math.sin(VTable[1][1])) / VTable[4][1]);
                        value = (float)(Math.asin(temp) * angle / 3.1415926535897931);
                        break;
                    case 4:
                        // b=c*sinβ/sinδ
                        value = (float)((VTable[5][1] * Math.sin(VTable[1][0])) / Math.sin(VTable[2][1]));
                        break;
                    case 5:
                        // c=b*sinδ /sinβ
                        value = (float)((VTable[4][0] * Math.sin(VTable[2][1])) / Math.sin(VTable[1][0]));
                        break;
                }
                break;
            case 2:
                float perimeterP = (float)((VTable[3][0] + VTable[4][0] + VTable[5][1]) / 2f );
                // p=(a+b+c)/2
                switch (element)
                {
                    case 3:
                        // a=p-(s^2/p*(p-b)(p-c)) ct1
                        value = (float)(perimeterP - (Math.pow(VTable[7][2],2.0) / (perimeterP * (perimeterP - VTable[4][0]) * (perimeterP - VTable[5][1]))));
                        break;
                    case 4:
                        // c=p-(s^2/p*(p-a)(p-b))
                        value = (float)(perimeterP - (Math.pow(VTable[7][2],2.0) / (perimeterP * (perimeterP - VTable[3][0]) * (perimeterP - VTable[4][1]))));
                        break;
                    case 5:
                        // a=p-(s^2/p*(p-b)(p-c)) ct2
                        value = (float)(perimeterP - (Math.pow(VTable[7][2],2.0) / (perimeterP * (perimeterP - VTable[4][1]) * (perimeterP - VTable[5][1]))));
                        break;
                    case 6:
                        // p= (a+b+c)/2
                        value = (float)((VTable[3][0] + VTable[4][0] + VTable[5][1]) / 2f);
                        break;
                    case 7:
                        // s=sqrt(p(p-a)(p-b)(p-c))
                        value = (float)Math.sqrt((double)(perimeterP * (perimeterP - VTable[3][0]) * (perimeterP - VTable[4][0]) * (perimeterP - VTable[5][1])));
                        break;
                }
                break;
            case 3:
                switch (element)
                {
                    case 0:
                        // α = pi-β-δ
                        value = (float)((Math.PI - VTable[1][0] - VTable[2][1]));
                        break;
                    case 1:
                        // β = pi-α-δ
                        value = (float)((Math.PI - VTable[0][0] - VTable[2][1]));
                        break;
                    case 2:
                        // δ=pi-α-β
                        value = (float)((Math.PI - VTable[0][0] - VTable[1][0]));
                        break;
                }
                break;
            case 4:
                switch (element)
                {
                    case 5:
                        // c=2*s/h
                        value = (float)(2f * VTable[7][2] / VTable[8][4]);
                        break;
                    case 7:
                        // h=2s/c ct3
                        value = (float)(2f * VTable[7][4] / VTable[5][1]);
                        break;
                    case 8:
                        // h=2s/c ct5
                        value = (float)(2f * VTable[7][2] / VTable[5][1]);
                        break;
                }
                break;
            case 5:
                switch (element)
                {
                    case 3:
                        //a = 2p - b - c
                        value = (float)(2f * VTable[6][2] - VTable[4][0] - VTable[5][1]);
                        break;
                    case 4:
                        //'b = 2p - a - c'
                        value = (float)(2f * VTable[6][2] - VTable[3][0] - VTable[5][1]);
                        break;
                    case 5:
                        //'c = 2p - a - b'
                        value = (float)(2f * VTable[6][2] - VTable[3][0] - VTable[4][0]);
                        break;
                    case 6:
                        //"p = (a + b + c) / 2"
                        value = (float)((VTable[3][0] + VTable[4][0] + VTable[5][1]) / 2f);
                        break;
                }
                break;
        }
        if (value <= 0)
        {
            JOptionPane.showMessageDialog(null,"Các yếu tố nhập vào không hợp lệ!!. Vui lòng kiểm tra lại.","Báo Lỗi!",JOptionPane.OK_OPTION);
            stop = 1;
        }
        else
        {
            for (int i = 0; i < equationNumber; i++)
                if (VTable[element][i] == -1)
            {
                VTable[element][i] = value;
                RTable[element][i] = 1;
            }
        }
    }


    /**
     * Get Value in GUI into VTable and RTable
     *
     * @param txtElements
     */
    public void getAllElementsInGUI(ArrayList<JTextField> txtElements){
        initRVTable();

        if(!this.isNullOrEmpty(txtElements.get(0).getText())){
            float num = ((float)Math.PI * Float.parseFloat(txtElements.get(0).getText())) / angle;
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[0][i] == -1f && this.VTable[0][i] != 0)
                {
                    this.VTable[0][i] = num;
                    this.RTable[0][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(1).getText()))
        {
            float num1 = ((float)Math.PI * Float.parseFloat(txtElements.get(1).getText())) / angle;
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[1][i] == -1f && this.VTable[1][i] != 0)
                {
                    this.VTable[1][i] = num1;
                    this.RTable[1][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(2).getText()))
        {
            float num2 = ((float)Math.PI * Float.parseFloat(txtElements.get(2).getText())) / angle;
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[2][i] == -1f && this.VTable[2][i] != 0)
                {
                    this.VTable[2][i] = num2;
                    this.RTable[2][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(3).getText()))
        {
            float num3 = Float.parseFloat(txtElements.get(3).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[3][i] == -1f && this.VTable[3][i] != 0)
                {
                    this.VTable[3][i] = num3;
                    this.RTable[3][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(4).getText()))
        {
            float num4 = Float.parseFloat(txtElements.get(4).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[4][i] == -1f && this.VTable[4][i] != 0)
                {
                    this.VTable[4][i] = num4;
                    this.RTable[4][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(5).getText()))
        {
            float num5 = Float.parseFloat(txtElements.get(5).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[5][i] == -1f && this.VTable[5][i] != 0)
                {
                    this.VTable[5][i] = num5;
                    this.RTable[5][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(6).getText()))
        {
            float num7 = Float.parseFloat(txtElements.get(6).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[7][i] == -1f && this.VTable[7][i] != 0)
                {
                    this.VTable[7][i] = num7;
                    this.RTable[7][i] = 1;
                }
            }
        }

        if (!this.isNullOrEmpty(txtElements.get(7).getText()))
        {
            float num3 = Float.parseFloat(txtElements.get(7).getText());
            for (int i = 0; i < equationNumber; i++)
            {
                if (this.VTable[8][i] == -1f && this.VTable[8][i] != 0)
                {
                    this.VTable[8][i] = num3;
                    this.RTable[8][i] = 1;
                }
            }
        }

    }


    // Other Functions

    /**
     * Check String Is Null Or Empty
     * @param str
     * @return boolean
     */
    private boolean isNullOrEmpty(String str){
        return (str == null || str.length() == 0);
    }
}