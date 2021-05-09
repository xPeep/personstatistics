package cz.upce.nnpia.personstatistics.service

import cz.upce.nnpia.personstatistics.entity.PersonInformation

interface PersonInformationService {
	fun modifyUserInformation(userId: Long, userInformation: PersonInformation)
}
