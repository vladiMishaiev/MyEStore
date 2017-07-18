package com.app.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.app.domain.Ticket;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, Integer>{

}
