package cz.upce.nnpia.personstatistics.entity

import cz.upce.nnpia.personstatistics.repository.AbstractJpaPersistable
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class UserMedia(
	@Column(length = 500) var timeStamp: ZonedDateTime,
	@Lob var image: ByteArray,
	@ManyToOne(fetch = FetchType.LAZY) var applicationUser: ApplicationUser
) : AbstractJpaPersistable<Long>()
