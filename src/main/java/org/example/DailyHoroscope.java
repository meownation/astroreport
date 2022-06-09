package org.example;

import java.sql.Date;
import java.util.List;

public class DailyHoroscope {
    //private int id;
    private List<Paragraph> paragraphs; // mozda description
    private Date date;
    private AstroSign sign;

    public DailyHoroscope(Date date, AstroSign sign, List<Paragraph> lp) {
        this.paragraphs=lp;
        this.date=date;
        this.sign=sign;
    }

    public DailyHoroscope() {
    }// potreban za deserijalizaciju pri postu

//    @Override
//    public String toString() {
//        return "DailyHoroscope{" +
//                "paragraphs size=" + paragraphs.toString() +
//                ", date=" + date +
//                ", sign=" + sign +
//                '}';
//    }
    @Override
    public String toString(){
        StringBuffer buff = new StringBuffer();
        paragraphs.forEach(buff::append);
        return sign.srpski + " za dan " + date.toLocalDate() + "\n\n" + buff + "\n";




            //    paragraphs.toString();
    }


    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setDailyHoroscope(List<Paragraph> list) {
        this.paragraphs=list;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AstroSign getSign() {
        return sign;
    }

    public void setSign(AstroSign sign) {
        this.sign = sign;
    }

}
