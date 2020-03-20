/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.bugs._1453;

/**
 * @author Filip Hrisafov
 */
public class Payment {

    private final Long price;

    public Payment(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }
}
