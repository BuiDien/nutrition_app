package com.example.nutrition_app;
import android.util.Log;



public class nutrition {
    private static final String TAG = "nutrition";
    private static final int  PROTEIN_CAL = 4; // 1g protein = 4 Cal
    private static final int  FAT_CAL = 9 ;// 1g fat = 9 Cal
    private static final int  CARB_CAL = 4 ;// 1g carb = 4 Cal
    private double BMR; // Basal metabolic rate: Total Cal for rest
    private double TEF;// Thermic effect of food: Total Cal for process food
    private double TEA;  // Thermic effect of ACtivity: Total Cal for activity
    private double TDEE;// Total Daily Energy EXpenditure
    private int sex;
    private double  weight;  // Unit KG
    private double  height;  // Uint Cm
    private int age;
    private int type; // type activity
    public nutrition(double height_input, double weight_input,int sex_input,int age_input,int type_input){
        weight = weight_input;
        height = height_input;
        sex =  sex_input;
        age = age_input;
        type = type_input;
        if (sex == 1){
            BMR = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        }
        else if (sex == 2){
            BMR = 447.593 + (9.247 * weight) + (3.098 * height) - (4.433 * age);
        }
        else{
            Log.e(TAG, "Something wrong here BMR");
        }
        TEF = BMR * 0.1;
        switch (type){
            case 1:
                TEA = BMR * 0.2;
                break;
            case 2:
                TEA = BMR * 0.375;
                break;
            case 3:
                TEA = BMR * 0.55;
                break;
            case 4:
                TEA = BMR * 0.725;
                break;
            case 5:
                TEA = BMR * 0.9;
                break;
            default:
                Log.e("nutrition", "Something wrong here TEA");
                break;
        }
        TDEE = TEF + BMR + TEA;
    }

    public double getTDEE() {
        return TDEE;
    }
    public void setTDEE(double TDEE) {
        this.TDEE = TDEE;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getHeight() {
        return height;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public int getSex() {
        return sex;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public double Max_Cal(int kind){
        double max_cal = 0.0;
        switch (kind){
            case 1:
                max_cal = TDEE;
                break;
            case 2:
                max_cal = TDEE * 0.2 + TDEE;
                break;
            case 3:
                max_cal = TDEE - TDEE * 0.2;
                break;
            default:
                Log.e("nutrition", "Something wrong here Max_Cal");
                break;
        }
        return max_cal;
    }
}
