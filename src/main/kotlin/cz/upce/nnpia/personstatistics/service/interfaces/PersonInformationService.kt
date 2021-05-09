package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.PersonInformationDto

interface PersonInformationService {
	fun getPersonInformation(personId: Long): PersonInformationDto?
	fun addPersonInformation(personId: Long, personInformationDto: PersonInformationDto)
	fun removePersonInformation(personId: Long)
}
