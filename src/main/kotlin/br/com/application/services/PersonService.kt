package br.com.application.services

import br.com.application.data.vo.v1.PersonVO
import br.com.application.data.vo.v2.PersonVO as PersonVOV2
import br.com.application.exceptions.ResourceNotFoundException
import br.com.application.mapper.DozerMapper
import br.com.application.mapper.custom.PersonMapper
import br.com.application.model.Person
import br.com.application.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger


@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository
    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Find one person!")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

        fun findById(id: Long): PersonVO{
        logger.info("Find one person!")
        var person =  repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found for this ID!")}
            return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun create(person: PersonVO) : PersonVO {
        logger.info("Creating one person with name ${person.firstName}!")
        var entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun createV2(person: PersonVOV2) : PersonVOV2 {
        logger.info("Creating one person with name ${person.firstName}!")
        var entity: Person = mapper.mapVOToEntity(person)
        return mapper.mapEntityToVo(repository.save(entity))
    }

    fun update(person: PersonVO) : PersonVO {
        logger.info("Updating one person with ID ${person.id}!")

        val entity = repository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records foun for this ID!")}

        entity.firstName = person.firstName
        entity.lastName= person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long){
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
        .orElseThrow{ResourceNotFoundException("No records foun for this ID!")}
    repository.delete(entity)
    }


}