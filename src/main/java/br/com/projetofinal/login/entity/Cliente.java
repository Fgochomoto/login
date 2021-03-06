package br.com.projetofinal.login.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "nome", nullable = false)
        private String nome;

        @Column(name = "email")
        private String email;

        @Column(name = "cpf")
        private String cpf;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "isNovaSenha")
        private  Boolean isNovaSenha = false;

        @Column(name = "novaSenha")
        private  String novaSenha;

        @Column(name = "tentativas")
        private  int tentativas = 0;

        @Column(name = "locked")
        private  Boolean isLocked = false;

        @Column(name = "mfaEnabled")
        private boolean mfaEnabled = false;

        @Column(name = "secretImageUri")
        private String secretImageUri;

}
