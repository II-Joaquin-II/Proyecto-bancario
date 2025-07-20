package com.banco.interbank.DAO;

import com.banco.interbank.model.Usuario;
import com.banco.interbank.repository.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private UsuarioRepositorio clienterepositorio;

    @Override
    public List<Usuario> get() {
        return clienterepositorio.findAll();
    }

    @Override
    public Usuario get(Long id) {
        return clienterepositorio.findById(id).orElse(null);
    }

    @Override
    public void save(Usuario usuario) {
        clienterepositorio.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        clienterepositorio.save(usuario);
    }

    @Override
    public void delete(Long id) {
        clienterepositorio.deleteById(id);
    }

    @Override
    public Usuario findByDni(String dni) {
        return clienterepositorio.findByDni(dni).orElse(null);
    }
}
