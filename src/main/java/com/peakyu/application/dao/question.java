package com.peakyu.application.dao;

public class question {
    private  String Email;  //密码邮箱
    private  String question;       //密保问题
    private  String answer;         //密保答案

    public question(String email, String question, String answer) {
        Email = email;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "question{" +
                "Email='" + Email + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
