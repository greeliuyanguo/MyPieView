package com.bigdata.gree.pieview;

/**
 * Author:liuyanguo
 * Date:2017/10/8
 * Time:9:20
 * Description:Nothing to say.
 */

public class PieData {

    private String name;
    private float value;
    private float angle;
    private float percentage;
    private int color;

    public PieData(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
