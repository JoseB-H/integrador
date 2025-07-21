package com.paguina.web.servicios;

import com.paguina.web.modelos.Cliente;
import com.paguina.web.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ClienteUserDetailsService implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByCorreoCliente(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado"));

        return new User(
            cliente.getCorreoCliente(),
            cliente.getContraseñaCliente(),
            Collections.singletonList(new SimpleGrantedAuthority(cliente.getRol()))
        );
    }
}
