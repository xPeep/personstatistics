package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long>
