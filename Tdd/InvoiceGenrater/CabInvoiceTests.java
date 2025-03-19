package InvoiceGenrater;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CabInvoiceTests {

        @Test
        public void testCalculateFare_NormalFare() {
            CabInvoiceGenerator generator = new CabInvoiceGenerator();
            assertEquals(25, generator.calculateFare(2, 5));
        }

        @Test
        public void testCalculateFare_MinimumFare() {
            CabInvoiceGenerator generator = new CabInvoiceGenerator();
            assertEquals(5, generator.calculateFare(0.1, 1));
        }

        @Test
        public void testCalculatePremiumFare() {
            CabInvoiceGenerator generator = new CabInvoiceGenerator();
            assertEquals(40, generator.calculatePremiumFare(2, 5));
        }

        @Test
        public void testCalculatePremiumFare_MinimumFare() {
            CabInvoiceGenerator generator = new CabInvoiceGenerator();
            assertEquals(20, generator.calculatePremiumFare(0.1, 1), 0.001);
        }

        @Test
        public void testGenerateInvoice_NormalFare() {
            CabInvoiceGenerator generator = new CabInvoiceGenerator();
            double[][] rides = {{2, 10}, {5, 20}, {1, 3}};
            String expectedInvoice = String.format(
                    "Invoice:\nTotal Rides: %d\nRide Type: %s\nTotal Fare: Rs. %.2f\nAverage Fare Per Ride: Rs. %.2f\n",
                    3, "Normal", 65.0, 21.67
            );
            assertEquals(expectedInvoice, generator.generateInvoice(rides, false));
        }

        @Test
        public void testGenerateInvoice_PremiumFare() {
            CabInvoiceGenerator generator = new CabInvoiceGenerator();
            double[][] rides = {{2, 10}, {5, 20}, {1, 3}};
            String expectedInvoice = String.format(
                    "Invoice:\nTotal Rides: %d\nRide Type: %s\nTotal Fare: Rs. %.2f\nAverage Fare Per Ride: Rs. %.2f\n",
                    3, "Premium", 115.0, 38.33
            );
            assertEquals(expectedInvoice, generator.generateInvoice(rides, true));
        }
    }

