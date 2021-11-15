package com.springweb.mvc.model.repo

import com.springweb.mvc.model.entity.AddressBookModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressBookRepo : JpaRepository<AddressBookModel, Int> {

}