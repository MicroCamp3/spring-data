package com.comarch.bootcamp.jdbc;

import com.comarch.bootcamp.jdbc.jpa.model.Customer;
import com.comarch.bootcamp.jdbc.jpa.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void listAllCustomers() {
        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzystof", "Kowalski", "krzkow"));

        customerRepository.findAll();

    }

    @Test
    void listCustomersByName() {
        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzysztof", "Kowalski", "krzkow"));

        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Ziutek", "Ziutkowski", "ziuziu"));

        List<com.comarch.bootcamp.jdbc.jpa.model.Customer> results = customerRepository.findByFirstName("Krzysztof");

        assertThat(results).hasSize(1);

    }

    @Test
    void getById() {
        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzysztof", "Kowalski", "krzkow"));


        Optional<com.comarch.bootcamp.jdbc.jpa.model.Customer> customer = customerRepository.findById(1);

        assertThat(customer).isPresent();
    }

    @Test
    void deleteById() {
        customerRepository.saveAndFlush(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzysztof", "Kowalski", "krzkow"));

        customerRepository.deleteById(1);

        Optional<com.comarch.bootcamp.jdbc.jpa.model.Customer> customer = customerRepository.findById(1);

        assertThat(customer).isNotPresent();
    }

    @Test
    void delete() {
        com.comarch.bootcamp.jdbc.jpa.model.Customer savedCustomer = customerRepository.saveAndFlush(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzysztof", "Kowalski", "krzkow"));

        customerRepository.delete(savedCustomer);

        Optional<com.comarch.bootcamp.jdbc.jpa.model.Customer> customer = customerRepository.findById(savedCustomer.getId());

        assertThat(customer).isNotPresent();
    }


    @Test
    void pagedList() {
        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzysztof", "Kowalski", "krzkow"));

        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Ziutek", "Ziutkowski", "ziuziu"));

        Page<com.comarch.bootcamp.jdbc.jpa.model.Customer> results = customerRepository.findAll(PageRequest.of(1, 1));

        assertThat(results.getContent()).hasSize(1);

    }


    @Test
    void listCustomersByNamePageable() {
        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzysztof", "Kowalski", "krzkow"));

        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Krzysztof", "Ibisz", "krzibi"));

        customerRepository.save(new com.comarch.bootcamp.jdbc.jpa.model.Customer(null,
                "Ziutek", "Ziutkowski", "ziuziu"));

        Page<Customer> results = customerRepository.findByFirstName("Krzysztof", PageRequest.of(0, 1));

        assertThat(results.getContent()).hasSize(1);

    }

}
