package com.banco.interbank.DAO;

import com.banco.interbank.model.Usuario;
import java.util.List;

public interface ClienteDAO {

    List<Usuario> get();

    Usuario get(Long id);

    void save(Usuario usuario);

    void update(Usuario usuario);

    void delete(Long id);
    
    Usuario findByDni(String dni);
}
