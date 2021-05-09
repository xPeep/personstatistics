package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.PersonInformation
import org.springframework.data.jpa.repository.JpaRepository

interface PersonInformationRepository : JpaRepository<PersonInformation, Long>
