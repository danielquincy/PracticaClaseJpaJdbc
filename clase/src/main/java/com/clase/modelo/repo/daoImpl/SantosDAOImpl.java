package com.clase.modelo.repo.daoImpl;

import com.clase.config.PersonaConfiguration;
import com.clase.modelo.entity.Santos;
import com.clase.modelo.mapper.SantosMapper;
import com.clase.modelo.repo.dao.SantosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SantosDAOImpl implements SantosDAO {
    private final JdbcTemplate oJdbcTemplate;

    @Autowired
    public SantosDAOImpl(JdbcTemplate oJdbcTemplate) {
        this.oJdbcTemplate = oJdbcTemplate;
    }

    public Santos obtenerPorId(Integer prId) {
        return oJdbcTemplate.queryForObject("select * from Santos where id = ?",new SantosMapper(), prId);
    }

    public List<Santos> obtenerTodo() {
        return oJdbcTemplate.query("select * from Santos", new SantosMapper());
    }

    public void guardar(Santos oSantos) {
        oJdbcTemplate.update("INSERT INTO [dbo].[Santos] (" +
                        "[Nombre]," +
                        "[Apellido]," +
                        "[Edad] )" +
                        " values (?,?,?)",
                oSantos.getNombre(),
                oSantos.getApellido(),
                oSantos.getEdad());
    }

    public Santos guardarJdbc(Santos oSantos) {
        oJdbcTemplate.update("INSERT INTO [dbo].[Santos] (" +
                        "[Nombre]," +
                        "[Apellido]," +
                        "[Edad] )" +
                        " values (?,?,?)",
                oSantos.getNombre(),
                oSantos.getApellido(),
                oSantos.getEdad());

        return oJdbcTemplate.query("select top 1 * from Santos order by 1 desc", new SantosMapper()).get(0);
    }

    public Santos actualizarJdbc(Santos oSantos) {
        oJdbcTemplate.update("UPDATE [dbo].[Santos] SET " +
                        "[Nombre] = ?," +
                        "[Apellido] = ?," +
                        "[Edad] = ?" +
                        " WHERE Id = ?",
                oSantos.getNombre() ,
                oSantos.getApellido()   ,
                oSantos.getEdad(),
                oSantos.getId());

        return oJdbcTemplate.query("select * from Santos", new SantosMapper())
                .stream()
                .filter(x -> x.getId().equals(oSantos.getId()))
                .findFirst()
                .orElse(null);
    }

    public void borrarJdbc(Santos oSantos) {
        oJdbcTemplate.update("delete [dbo].[Santos] WHERE ID = ?",
                oSantos.getId());
    }

    public List<Santos> guardarSimpleJdbcInsert(Santos oSantos) {
        DataSource oDataSource = new PersonaConfiguration().getDataSource();

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(oDataSource)
                .withTableName("SANTOS")
                .usingGeneratedKeyColumns("Id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Nombre", oSantos.getNombre());
        parameters.put("Apellido", oSantos.getApellido());
        parameters.put("Edad", oSantos.getEdad());
        jdbcInsert.execute(parameters);

        return obtenerTodo();
    }

}
