package cz.upce.nnpia.personstatistics.repository

import org.springframework.data.domain.Persistable
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
abstract class AbstractJpaPersistable<T : Serializable> : Persistable<T> {

	companion object {
		private val serialVersionUID = -5554308939380869754L
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private var id: T? = null

	override fun getId(): T? {
		return id
	}

	fun setId(value: T?) {
		id = value
	}

	@Transient
	override fun isNew() = null == getId()

	override fun equals(other: Any?): Boolean {
		other ?: return false

		if (this === other) return true

		if (javaClass != ProxyUtils.getUserClass(other)) return false

		other as AbstractJpaPersistable<*>

		return if (null == this.getId()) false else this.id == other.id
	}

	override fun hashCode() = 31
	override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}
