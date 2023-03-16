public class Clock extends Thread{
    private int hour;
    private int minute;

    Clock(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public void run() throws UnsupportedOperationException{
        while (true) {
            if(minute == 60) {
                ++hour;
                if (hour == 24) {
                    hour = 0;
                }
                minute = 0;
            }else{
                ++minute;
            }
        }
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String currentTime(){
        return hour + ":" + minute; 
    }
}
