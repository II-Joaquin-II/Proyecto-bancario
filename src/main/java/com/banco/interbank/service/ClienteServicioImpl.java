package com.banco.interbank.service;

import com.banco.interbank.DAO.ClienteDAO;
import com.banco.interbank.model.Usuario;
import com.banco.interbank.repository.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    @Override
    public List<Usuario> get() {
        return clienteDAO.get();
    }

    @Transactional
    @Override
    public Usuario get(Long id) {
        return clienteDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Usuario usuario) {
        clienteDAO.save(usuario);
    }

    @Transactional
    @Override
    public void update(Usuario usuario) {
        clienteDAO.update(usuario);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        clienteDAO.delete(id);
    }

    @Override
    public Usuario findByDni(String dni) {
        return clienteDAO.findByDni(dni);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

}
