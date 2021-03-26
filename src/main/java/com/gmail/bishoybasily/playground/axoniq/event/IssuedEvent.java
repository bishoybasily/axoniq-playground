package com.gmail.bishoybasily.playground.axoniq.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author bishoybasily
 * @since 2021-03-26
 */
@Data
@RequiredArgsConstructor
public class IssuedEvent {

    private final String id;
    private final Double amount;

}
