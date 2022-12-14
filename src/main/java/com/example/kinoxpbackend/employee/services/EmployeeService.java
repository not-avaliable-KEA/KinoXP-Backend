package com.example.kinoxpbackend.employee.services;

import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.repositories.EmployeeRepository;
import org.hibernate.resource.beans.container.spi.BeanLifecycleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    private final String PEPPER_CHARACTERS = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
    private final Random random = new Random();


    // might need to be changed to take a customer
    public Employee create(Employee employee) {
        String salt = generateSalt();
        String pepper = generatePepper();

        employee.setSalt(salt);
        employee.setPassword(hashPassword(pepper, employee.getPassword(), salt));

        //String name, String role, String email, String telefon, String password, String salt
        return repository.save(employee);
    }

    public Optional<Employee> get(long id) {
        return repository.findById(id);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    //updates employees, if employee has password, then it is also updated
    public Employee update(Employee employee) {

        Optional<Employee> optionalEmployee = get(employee.getId());

        // check if present, return null if not
        if (optionalEmployee.isEmpty()) return null;

        // checks if password needs changing
        if (employee.getPassword() == null || employee.getPassword().isEmpty()) {
            optionalEmployee.get().update(employee);
        } else {
            optionalEmployee.get().update(employee);
            optionalEmployee.get().setSalt(generateSalt());
            optionalEmployee.get().setPassword(
                    hashPassword(generatePepper(),
                            employee.getPassword(),
                            optionalEmployee.get().getSalt()));
        }

        // update values
        return repository.save(optionalEmployee.get());
    }

    public Boolean delete(long id) {
        Optional<Employee> optionalEmployee = get(id);

        if (optionalEmployee.isPresent()) {
            repository.delete(optionalEmployee.get());
            return true;
        } else {
            return false;
        }
    }


    private boolean checkPassword(String userPassword, String userSalt, String passwordToCheck) {
        String hashToCheck;

        //Goes through the whole hash and converts it to the password then checks password
        for (int i = 0; i < PEPPER_CHARACTERS.length(); i++) {
            hashToCheck = hashPassword(PEPPER_CHARACTERS.substring(i, i + 1),
                    passwordToCheck,
                    userSalt);

            if (hashToCheck.equals(userPassword)) {
                return true;
            }
        }
        return false;
    }
    public Employee checkLogin(Employee employee){
        Optional<Employee> optionalEmployee = repository.getSingleEntityByUsername(employee.getUsername());

        if(optionalEmployee.isEmpty()) return null;

        if (checkPassword(
                optionalEmployee.get().getPassword(),
                optionalEmployee.get().getSalt(),
                employee.getPassword())) {
            return optionalEmployee.get();
        } else {
            return null;
        }
    }

    private String generatePepper() {
        return String.valueOf(
                PEPPER_CHARACTERS.charAt(
                        random.nextInt(PEPPER_CHARACTERS.length())));
    }

    /**
     * generates a random 16 character string, from 94 possible characters
     */
    private String generateSalt() {
        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            salt.append(Character.toChars(random.nextInt(94) + 32));
        }
        return salt.toString();
    }

    /**
     * hashes the password, with the salt and pepper added unto it
     */
    private String hashPassword(String pepper, String password, String salt) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] encodedHash = digest.digest((pepper + password + salt).getBytes(StandardCharsets.UTF_8));//hvordan forst??r vi det byte set, specifikation for sprog.


        return bytesToHex(encodedHash);
    }

    /**
     * converts the byte[] into a string
     */
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b); // bitwise operator "&"
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}