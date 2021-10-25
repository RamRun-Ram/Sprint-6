package com.springweb.mvc.controller

import com.springweb.mvc.model.AddressPerson
import com.springweb.mvc.service.AddressBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping("/api")
class RestController {
    @Autowired
    private lateinit var addressBookService: AddressBookService

    @PostMapping("/add")
    fun addAddress(@RequestBody addressPerson: AddressPerson) {
        addressBookService.addAddress(addressPerson)
    }

    @GetMapping("/list")
    fun getAllAddress(): ConcurrentHashMap<Int, AddressPerson> {
        return addressBookService.getAllAddress()
    }

    @GetMapping("/{id}/view")
    fun getAddress(@PathVariable id: Int): AddressPerson? {
        return addressBookService.getAddress(id)
    }

    @PostMapping("/{id}/edit")
    fun editAddress(@PathVariable id: Int, @RequestBody addressPerson: AddressPerson) {
        addressBookService.updateAddress(id, addressPerson)
    }

    @GetMapping("/{id}/delete")
    fun removeAddress(@PathVariable id: Int) {
        addressBookService.removeAddress(id)
    }
}