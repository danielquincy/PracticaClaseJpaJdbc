package com.clase.modelo.repo.daoImpl;

import com.clase.config.PersonaConfiguration;
import com.clase.modelo.entity.Daniel;
import com.clase.modelo.entity.Persona;
import com.clase.modelo.mapper.PersonaMapper;
import com.clase.modelo.repo.dao.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonaDAOImpl implements PersonaDAO {

    private final JdbcTemplate oJdbcTemplate;

    @Autowired
    public PersonaDAOImpl(JdbcTemplate oJdbcTemplate) {
        this.oJdbcTemplate = oJdbcTemplate;
    }

    public Persona obtenerPorId(Integer prId) {
        return oJdbcTemplate.queryForObject("select * from PERSONA where id = ?",new PersonaMapper(), prId);
    }

    public List<Persona> obtenerTodo() {
        return oJdbcTemplate.query("select * from Persona", new PersonaMapper());
    }

    public void guardar(Persona oPersona) {
        oJdbcTemplate.update("INSERT INTO [dbo].[PERSONA] (" +
                        "[PRIMER_NOMBRE]," +
                        "[SEGUNDO_NOMBRE]," +
                        "[PRIMER_APELLIDO]," +
                        "[SEGUNDO_APELLIDO] )" +
                        " values (?,?,?,?)",
                oPersona.getNombre1(),
                oPersona.getNombre2(),
                oPersona.getApellido1(),
                oPersona.getApellido2());
    }

    public Persona guardarJdbc(Persona oPersona) {
        oJdbcTemplate.update("INSERT INTO [dbo].[PERSONA] (" +
                        "[PRIMER_NOMBRE]," +
                        "[SEGUNDO_NOMBRE]," +
                        "[PRIMER_APELLIDO]," +
                        "[SEGUNDO_APELLIDO])" +
                        " values (?,?,?,?)",
                oPersona.getNombre1(),
                oPersona.getNombre2(),
                oPersona.getApellido1(),
                oPersona.getApellido2());

        return oJdbcTemplate.query("select top 1 * from Persona order by 1 desc", new PersonaMapper()).get(0);
    }

    public Persona actualizarJdbc(Persona oPersona) {
        oJdbcTemplate.update("UPDATE [dbo].[PERSONA] SET " +
                        "[PRIMER_NOMBRE] = ?," +
                        "[SEGUNDO_NOMBRE] = ?," +
                        "[PRIMER_APELLIDO] = ?," +
                        "[SEGUNDO_APELLIDO] = ?" +
                        " WHERE ID = ?",
                oPersona.getNombre1(),
                oPersona.getNombre2(),
                oPersona.getApellido1(),
                oPersona.getApellido2(),
                oPersona.getId());

        return oJdbcTemplate.query("select * from Persona", new PersonaMapper())
                .stream()
                .filter(x -> x.getId().equals(oPersona.getId()))
                .findFirst()
                .orElse(null);
    }

    public void borrarJdbc(Persona oPersona) {
        oJdbcTemplate.update("delete [dbo].[PERSONA] WHERE ID = ?",
                oPersona.getId());
    }

    public List<Persona> guardarSimpleJdbcInsert(Persona oPersona) {
        DataSource oDataSource = new PersonaConfiguration().getDataSource();

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(oDataSource)
                .withTableName("PERSONA")
                .usingGeneratedKeyColumns("Id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("PRIMER_NOMBRE", oPersona.getNombre1());
        parameters.put("SEGUNDO_NOMBRE", oPersona.getNombre2());
        parameters.put("PRIMER_APELLIDO", oPersona.getApellido1());
        parameters.put("SEGUNDO_APELLIDO", oPersona.getApellido2());
        jdbcInsert.execute(parameters);

        return obtenerTodo();
    }


}
