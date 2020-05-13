package com.lance.game.orm.model;

/**
 * @author Lance
 */
public class TestConfig {

    private int id;
    private String name;
    private int age;
    private int testIntPrimitive;
    private Integer testInt;
    private long testLongPrimitive;
    private Long testLong;
    private boolean testBooleanPrimitive;
    private Boolean testBoolean;
    private double testDoublePrimitive;
    private Double testDouble;
//    private float testFloatPrimitive; // 不支持
//    private Float testFloat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTestIntPrimitive() {
        return testIntPrimitive;
    }

    public void setTestIntPrimitive(int testIntPrimitive) {
        this.testIntPrimitive = testIntPrimitive;
    }

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
    }

    public long getTestLongPrimitive() {
        return testLongPrimitive;
    }

    public void setTestLongPrimitive(long testLongPrimitive) {
        this.testLongPrimitive = testLongPrimitive;
    }

    public Long getTestLong() {
        return testLong;
    }

    public void setTestLong(Long testLong) {
        this.testLong = testLong;
    }

    public boolean isTestBooleanPrimitive() {
        return testBooleanPrimitive;
    }

    public void setTestBooleanPrimitive(boolean testBooleanPrimitive) {
        this.testBooleanPrimitive = testBooleanPrimitive;
    }

    public Boolean getTestBoolean() {
        return testBoolean;
    }

    public void setTestBoolean(Boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    public double getTestDoublePrimitive() {
        return testDoublePrimitive;
    }

    public void setTestDoublePrimitive(double testDoublePrimitive) {
        this.testDoublePrimitive = testDoublePrimitive;
    }

    public Double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(Double testDouble) {
        this.testDouble = testDouble;
    }

    @Override
    public String toString() {
        return "TestConfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", testIntPrimitive=" + testIntPrimitive +
                ", testInt=" + testInt +
                ", testLongPrimitive=" + testLongPrimitive +
                ", testLong=" + testLong +
                ", testBooleanPrimitive=" + testBooleanPrimitive +
                ", testBoolean=" + testBoolean +
                ", testDoublePrimitive=" + testDoublePrimitive +
                ", testDouble=" + testDouble +
                '}';
    }
}
