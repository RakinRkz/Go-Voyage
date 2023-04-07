package newproject;

import javafx.scene.control.Button;

public class TrainInfo {

    Integer TrainID;
    String trainName;
    
    Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    String From,To,Chair;
    Integer Capacity;
    String Date,Station;
    Button confirmbtn;
    String schedule;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

//    public TrainInfo(Integer TrainID, String From, String To, String Chair, Integer Capacity,String Date,String Station) {
//        this.TrainID = TrainID;
//        this.From = From;
//        this.To = To;
//        this.Date = Date;
//        this.Chair = Chair;
//        this.Station = Station;
//        this.Capacity = Capacity;
//        this.confirmbtn = new Button("CONFIRM");
//    }

    public Integer getTrainID() {
        return TrainID;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }
    
    public String getDate() {
        return Date;
    }

    public String getChair() {
        return Chair;
    }
    public String getStation() {
        return Station;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setTrainID(Integer TrainID) {
        this.TrainID = TrainID;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public Button getConfirmbtn() {
        return confirmbtn;
    }

    public void setConfirmbtn(Button confirmbtn) {
        this.confirmbtn = confirmbtn;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setClass(String Chair) {
        this.Chair = Chair;
    }
    public void setStation(String Station) {
        this.Station = Station;
    }

    public void setCapacity(Integer Capacity) {
        this.Capacity = Capacity;
    }
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
