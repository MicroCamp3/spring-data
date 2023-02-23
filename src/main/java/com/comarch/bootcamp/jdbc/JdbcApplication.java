package com.comarch.bootcamp.jdbc;

import com.comarch.bootcamp.jdbc.jpa.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {

    private JdbcTemplate jdbcTemplate;
    private final CustomerRepository customerRepository;

    public JdbcApplication(JdbcTemplate jdbcTemplate, CustomerRepository customerRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        jdbcTemplate.execute("CREATE TABLE if not exists  " +
//                "CUSTOMERS(id Integer not null auto_increment, first_name VARCHAR(255), last_name VARCHAR(255), " +
//                "constraint PK_CUSTOMER primary key (id))");
//
//        List<Object[]> users = Arrays.asList("Krzyszof Kowalski", "Ziutek Ziutkowski")
//                .stream().map(name -> name.split(" "))
//                .collect(Collectors.toList());
//
//        jdbcTemplate.batchUpdate("INSERT INTO CUSTOMERS(first_name, last_name) values ( ?, ?)", users);
//
//        List<Customer> query = jdbcTemplate.query("SELECT id, first_name, last_name FROM customers where id = ?", (rs, rowNum) -> {
//            return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
//        }, 1);
//
//        System.out.println(query);


    }
}
