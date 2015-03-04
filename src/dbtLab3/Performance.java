package dbtLab3;

/**
 * Created by Aleksandar on 15-02-10.
 */
public class Performance {
    private String movieName;
    private String performanceDate;
    private String theaterName;
    private int availableSeats;

    public Performance(String movieName, String performanceDate, String theaterName, int availableSeats) {
        this.movieName = movieName;
        this.performanceDate = performanceDate;
        this.theaterName = theaterName;
        this.availableSeats = availableSeats;
    }

    public String getmovieName() {
        return movieName;
    }

    public String getperformanceDate() {
        return performanceDate;
    }

    public String gettheaterName() {
        return theaterName;
    }

    public String getavailableSeats() {
        return Integer.toString(availableSeats);
    }
}
