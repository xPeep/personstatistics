package cz.upce.nnpia.personstatistics.repository

import cz.upce.nnpia.personstatistics.entity.UserMedia
import org.springframework.data.jpa.repository.JpaRepository

interface UserMediaRepository : JpaRepository<UserMedia, Long>
