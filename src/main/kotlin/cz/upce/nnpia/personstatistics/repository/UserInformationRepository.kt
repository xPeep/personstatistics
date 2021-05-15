package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.UserInformation
import org.springframework.data.jpa.repository.JpaRepository

interface UserInformationRepository : JpaRepository<UserInformation, Long>
