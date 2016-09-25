package com.airbud.api;

/**
 * Created by Nikhil on 9/25/2016.
 */

public class ParseDeltaJSON {

    FlightStatusResponse flightStatusResponse;
    class FlightStatusResponse {
        String status;
        StatusResponse statusResponse;
        class StatusResponse {
            FlightStatusTO flightStatusTO;
            class FlightStatusTO {
                FlightStatusLegTOList flightStatusLegTOList;
                class FlightStatusLegTOList {
                    String arrivalCityName;
                    String departureCityName;
                    double arrivalTsoagLatitudeDecimal;
                    double arrivalTsoagLongitudeDecimal;
                    double departureTsoagLatitudeDecimal;
                    double departureTsoagLongitudeDecimal;
                }
            }
        }
    }

    public String getStatus() {
        return flightStatusResponse.status;
    }

    public String getDepartureCity() {
        return flightStatusResponse.statusResponse.flightStatusTO.flightStatusLegTOList.departureCityName;
    }

    public String getArrivalCity() {
        return flightStatusResponse.statusResponse.flightStatusTO.flightStatusLegTOList.arrivalCityName;
    }

    public double getArrivalLat() {
        return flightStatusResponse.statusResponse.flightStatusTO.flightStatusLegTOList.arrivalTsoagLatitudeDecimal;
    }

    public double getArrivalLong() {
        return flightStatusResponse.statusResponse.flightStatusTO.flightStatusLegTOList.arrivalTsoagLongitudeDecimal;
    }

    public double getDepartureLat() {
        return flightStatusResponse.statusResponse.flightStatusTO.flightStatusLegTOList.departureTsoagLatitudeDecimal;
    }

    public double getDepartureLong() {
        return flightStatusResponse.statusResponse.flightStatusTO.flightStatusLegTOList.departureTsoagLongitudeDecimal;
    }
}
