package com.example.careermitra;

public class PredictionRequest {

    private float os_score;
    private float algorithms_score;
    private float programming_score;
    private float se_score;
    private float cn_score;
    private float electronics_score;
    private float ca_score;
    private float math_score;
    private float comm_score;

    public PredictionRequest(float os_score, float algorithms_score, float programming_score, float se_score,
                             float cn_score, float electronics_score, float ca_score, float math_score, float comm_score) {
        this.os_score = os_score;
        this.algorithms_score = algorithms_score;
        this.programming_score = programming_score;
        this.se_score = se_score;
        this.cn_score = cn_score;
        this.electronics_score = electronics_score;
        this.ca_score = ca_score;
        this.math_score = math_score;
        this.comm_score = comm_score;
    }

    // Getter and Setter methods (you can use Lombok for brevity if desired)
    public float getOs_score() {
        return os_score;
    }

    public void setOs_score(float os_score) {
        this.os_score = os_score;
    }

    // Repeat similar getter and setter methods for other fields
}
