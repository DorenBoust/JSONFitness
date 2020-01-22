package com.example.jsonfitness.data;

public class FinalEx {
    private String exName;
    private String sets;
    private String repitition;
    private String rest;
    private String exImage;

    public FinalEx(String exName, String sets, String repitition, String rest, String exImage) {
        this.exName = exName;
        this.sets = sets;
        this.repitition = repitition;
        this.rest = rest;
        this.exImage = exImage;
    }

    public String getExName() {
        return exName;
    }
    public void setExName(String exName) {
        this.exName = exName;
    }
    public String getSets() {
        return sets;
    }
    public void setSets(String sets) {
        this.sets = sets;
    }
    public String getRepitition() {
        return repitition;
    }
    public void setRepitition(String repitition) {
        this.repitition = repitition;
    }
    public String getRest() {
        return rest;
    }
    public void setRest(String rest) {
        this.rest = rest;
    }
    public String getExImage() {
        return exImage;
    }
    public void setExImage(String exImage) {
        this.exImage = exImage;
    }

    @Override
    public String toString() {
        return "FinalEx{" +
                "exName='" + exName + '\'' +
                ", sets='" + sets + '\'' +
                ", repitition='" + repitition + '\'' +
                ", rest='" + rest + '\'' +
                ", exImage='" + exImage + '\'' +
                '}';
    }
}
