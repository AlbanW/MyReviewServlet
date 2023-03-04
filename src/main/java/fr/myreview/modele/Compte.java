package fr.myreview.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Compte {

    private UUID uuid;
    private String username;
    private String password;

}
