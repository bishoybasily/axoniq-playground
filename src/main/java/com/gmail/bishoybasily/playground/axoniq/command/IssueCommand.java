package com.gmail.bishoybasily.playground.axoniq.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author bishoybasily
 * @since 2021-03-26
 */
@Data
public class IssueCommand {

    @TargetAggregateIdentifier
    private String id;
    private Double amount;

}
