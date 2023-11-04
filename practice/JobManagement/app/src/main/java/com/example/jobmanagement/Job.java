package com.example.jobmanagement;

import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

class Job {
    private String job, content;
    private Date dateFinish, timeFinish;

    @Override
    public String toString() {
        return "job='" + job + '\'' +
                ", content='" + content + '\'' +
                ", dateFinish=" + getDateFormat(dateFinish) +
                ", timeFinish=" + getTimeFormat(timeFinish);
    }

    public Job(String job, String content, Date dateFinish, Date timeFinish) {
        this.job = job;
        this.content = content;
        this.dateFinish = dateFinish;
        this.timeFinish = timeFinish;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public String getDateFormat(Date d) {
        SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }

    public String getTimeFormat(Date d) {
        SimpleDateFormat dft = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }
}
