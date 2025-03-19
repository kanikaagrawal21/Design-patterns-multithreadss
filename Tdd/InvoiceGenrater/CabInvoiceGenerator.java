package InvoiceGenrater;



public class CabInvoiceGenerator {

    private static final double NORMAL_RATE_PER_KM = 10;
    private static final double NORMAL_RATE_PER_MIN = 1;
    private static final double PREMIUM_RATE_PER_KM = 15;
    private static final double PREMIUM_RATE_PER_MIN = 2;
    private static final double MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_FARE = 20;

    public double calculateFare(double distance, double time) {
        double fare = (distance * NORMAL_RATE_PER_KM) + (time * NORMAL_RATE_PER_MIN);
        return Math.max(fare, MINIMUM_FARE);
    }

    public double calculatePremiumFare(double distance, double time) {
        double fare = (distance * PREMIUM_RATE_PER_KM) + (time * PREMIUM_RATE_PER_MIN);
        return Math.max(fare, PREMIUM_MINIMUM_FARE);
    }

    public double calculateTotalFare(double[][] rides, boolean isPremium) {
        double totalFare = 0;
        for (double[] ride : rides) {
            totalFare += isPremium ? calculatePremiumFare(ride[0], ride[1]) : calculateFare(ride[0], ride[1]);
        }
        return totalFare;
    }

    public String generateInvoice(double[][] rides, boolean isPremium) {
        double totalFare = calculateTotalFare(rides, isPremium);
        int totalRides = rides.length;
        double averageFare = totalFare / totalRides;
        String rideType = isPremium ? "Premium" : "Normal";

        return String.format(
                "Invoice:\nTotal Rides: %d\nRide Type: %s\nTotal Fare: Rs. %.2f\nAverage Fare Per Ride: Rs. %.2f\n",
                totalRides, rideType, totalFare, averageFare
        );
    }

    public static void main(String[] args) {
        CabInvoiceGenerator generator = new CabInvoiceGenerator();
        double[][] rides = {{2, 10}, {5, 20}, {1, 3}};

        System.out.println(generator.generateInvoice(rides, false));
        System.out.println(generator.generateInvoice(rides, true));
    }
}
