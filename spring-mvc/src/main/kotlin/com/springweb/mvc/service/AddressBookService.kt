package com.springweb.mvc.service

import com.springweb.mvc.model.AddressPerson
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class AddressBookService {
    private val addressBook = ConcurrentHashMap<Int, AddressPerson>()

    fun addAddress(addressPerson: AddressPerson) {
        addressBook[addressBook.size] = addressPerson
    }

    fun getAddress(id: Int): AddressPerson? {
        return addressBook[id]
    }

    fun getAllAddress(): ConcurrentHashMap<Int, AddressPerson> {
        return addressBook
    }

    fun removeAddress(id: Int): AddressPerson? {
        return addressBook.remove(id)
    }

    fun updateAddress(id: Int, addressPerson: AddressPerson) {
        addressBook[id] = addressPerson
    }
}