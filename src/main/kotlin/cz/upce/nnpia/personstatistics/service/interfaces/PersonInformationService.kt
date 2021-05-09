package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.PersonInformationDto
import cz.upce.nnpia.personstatistics.entity.PersonInformation

interface PersonInformationService {
	fun modifyPersonInformation(userId: Long, userInformation: PersonInformationDto)
}
