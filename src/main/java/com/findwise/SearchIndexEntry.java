package com.findwise;

public class SearchIndexEntry implements IndexEntry {

    private String id;
    private double score;

    public SearchIndexEntry(String id, double score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
