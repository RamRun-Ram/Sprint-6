package com.springweb.mvc.service


import com.springweb.mvc.model.entity.AddressBookModel
import java.util.*

interface AddressBookService {
    fun allAddress(): List<AddressBookModel?>?
    fun findOne(id: Int?): Optional<AddressBookModel?>?
    fun deleteAddress(id: Int?)
    fun isExist(id: Int?): Boolean
    fun editAddress(addressBookModel: AddressBookModel?): AddressBookModel?
    fun addNewEntry(addressBookModel: AddressBookModel?): AddressBookModel?
}