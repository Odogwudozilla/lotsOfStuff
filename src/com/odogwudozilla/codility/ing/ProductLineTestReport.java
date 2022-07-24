package com.odogwudozilla.codility.ing;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class ProductLineTestReport {
    long correctCnt;      // number of correct products
    long checkedExcCnt;   // number of products which threw a checked exception during verification
    long uncheckedExcCnt; // number of products which threw an unchecked exception during verification
    long otherExcCnt;     // number of products which threw an error during verification

    ProductLineTestReport(long correctCnt, long checkedExcCnt, long uncheckedExcCnt, long otherExcCnt) {
        this.correctCnt = correctCnt;
        this.checkedExcCnt = checkedExcCnt;
        this.uncheckedExcCnt = uncheckedExcCnt;
        this.otherExcCnt = otherExcCnt;
    }
}

class ProductionLineTester {

    private final ProductVerifier verifier;
    private static final String INVALID_STATUS = "invalid";
    private static final int NUM_TO_IGNORE = 10;
    private static final int NUM_TO_PROCESS = 20;


    ProductionLineTester(ProductVerifier verifier) {
        this.verifier = verifier;
    }

    ProductLineTestReport test(Stream<Product> products) {

        if (products == null) {
            // Return empty report for null products stream
            return new ProductLineTestReport(0, 0, 0, 0);
        }

        AtomicLong correctCnt = new AtomicLong();
        AtomicLong checkedExcCnt = new AtomicLong();
        AtomicLong uncheckedExcCnt = new AtomicLong();
        AtomicLong otherExcCnt = new AtomicLong();

        products
                .filter(Objects::nonNull)
                .filter(product -> !product.status.equalsIgnoreCase(INVALID_STATUS))
                .skip(NUM_TO_IGNORE)
                .limit(NUM_TO_PROCESS)
                .forEach(product -> {
                    try {
                        verifier.verify(product);
                        // There is no error/exception for this product. Increment correct count.
                        correctCnt.getAndIncrement();
                    } catch (RuntimeException e) {
                        // Unchecked exceptions
                        uncheckedExcCnt.getAndIncrement();
                        e.printStackTrace();
                    } catch (Exception ex) {
                        // Checked exceptions (because Runtime exceptions will be caught in the preceding 'catch' block).
                        checkedExcCnt.getAndIncrement();
                    } catch (Error er) {
                        // Errors
                        otherExcCnt.getAndIncrement();
                    }
                });

        return new ProductLineTestReport(correctCnt.get(), checkedExcCnt.get(), uncheckedExcCnt.get(), otherExcCnt.get());
    }

}

