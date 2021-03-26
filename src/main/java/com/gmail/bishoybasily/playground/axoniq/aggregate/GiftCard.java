package com.gmail.bishoybasily.playground.axoniq.aggregate;

import com.gmail.bishoybasily.playground.axoniq.command.IssueCommand;
import com.gmail.bishoybasily.playground.axoniq.command.RedeemCommand;
import com.gmail.bishoybasily.playground.axoniq.event.IssuedEvent;
import com.gmail.bishoybasily.playground.axoniq.event.RedeemedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @author bishoybasily
 * @since 2021-03-26
 */
@Data
@Aggregate
public class GiftCard {

    @TargetAggregateIdentifier
    private String id;
    private Double balance;

    @CommandHandler
    public void handle(IssueCommand command) {
        if (command.getAmount() <= 0)
            throw new IllegalArgumentException();
        AggregateLifecycle.apply(new IssuedEvent(command.getId(), command.getAmount()));
    }

    @CommandHandler
    public void handle(RedeemCommand command) {
        if (command.getAmount() > balance)
            throw new IllegalArgumentException();
        AggregateLifecycle.apply(new RedeemedEvent(command.getId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void on(IssuedEvent event) {
        this.id = event.getId();
        this.balance = event.getAmount();
    }

    @EventSourcingHandler
    public void on(RedeemedEvent event) {
        this.balance -= event.getAmount();
    }

}
