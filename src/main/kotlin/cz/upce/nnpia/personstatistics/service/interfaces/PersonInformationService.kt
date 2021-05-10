package cz.upce.nnpia.personstatistics.service.interfaces

import cz.upce.nnpia.personstatistics.dto.PersonInformationDto

interface PersonInformationService {
	fun getPersonInformation(personId: Long): PersonInformationDto?
	fun addPersonInformation(personInformationDto: PersonInformationDto)
	fun removePersonInformation(personId: Long)
}
