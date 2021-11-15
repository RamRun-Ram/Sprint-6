package com.springweb.mvc.service.impl


import com.springweb.mvc.model.entity.AddressBookModel
import com.springweb.mvc.model.repo.AddressBookRepo
import com.springweb.mvc.service.AddressBookService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressBookServiceImpl: AddressBookService {
    val addressBookRepo: AddressBookRepo? = null

    override fun allAddress(): List<AddressBookModel?>? {
        return addressBookRepo!!.findAll()
    }

    override fun findOne(id: Int?): Optional<AddressBookModel?>? {
        return addressBookRepo!!.findById(id!!)
    }

    override fun deleteAddress(id: Int?) {
        addressBookRepo!!.deleteById(id!!)
    }

    override fun isExist(id: Int?): Boolean {
        return addressBookRepo!!.existsById(id!!)
    }

    override fun editAddress(addressBookModel: AddressBookModel?): AddressBookModel? {
        return addressBookRepo!!.save(addressBookModel!!)
    }

    override fun addNewEntry(addressBookModel: AddressBookModel?): AddressBookModel? {
        return addressBookRepo!!.save(addressBookModel!!)
    }
}