package br.com.application.repository

import br.com.application.model.Person
import org.springframework.data.jpa.repository.JpaRepository


interface PersonRepository : JpaRepository<Person, Long?>