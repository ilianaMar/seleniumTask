package models;

import lombok.*;

@Data
@Builder
public class User {
    public String email, password, phone, firstName, lastName, city, address, postcode;
}
